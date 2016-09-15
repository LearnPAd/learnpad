package com.ontotext.gate.vr;

import gate.creole.gazetteer.MappingDefinition;
import gate.creole.gazetteer.MappingNode;
import gate.creole.ontology.AnonymousClass;
import gate.creole.ontology.OClass;
import gate.creole.ontology.OConstants;
import gate.creole.ontology.OInstance;
import gate.creole.ontology.OURI;
import gate.creole.ontology.Ontology;
import gate.util.GateRuntimeException;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/** Represents a single class node from the visualized ontology */
public class ClassNode implements IFolder, Transferable, Cloneable,
		Serializable {

	private static final long serialVersionUID = 3258128055154063414L;

	/** flavor used for drag and drop */
	final public static DataFlavor CLASS_NODE_FLAVOR = new DataFlavor(
			ClassNode.class, "Class Node");

	static DataFlavor flavors[] = { CLASS_NODE_FLAVOR };

	private String name;
	private Vector<ClassNode> children = new Vector<ClassNode>();
	private Object source;

	/**
	 * create a structure representing the class hierarchy of an ontology
	 * 
	 * @return the root node of the structure
	 */
	public static ClassNode createRootNode(Ontology o) {
		return createRootNode(o, false, false);
	}

	public static ClassNode createRootNode(Ontology o,
			boolean includeInstances, boolean includeAnonymousClasses) {
		if (null == o)
			throw new NullPointerException("ontology is null.");

		ClassNode root = new ClassNode(o);
		Iterator<OClass> itops = o.getOClasses(true).iterator();
		Vector<ClassNode> kids = new Vector<ClassNode>();
		if (includeAnonymousClasses) {
			while (itops.hasNext()) {
				ClassNode node = new ClassNode(itops.next());
				kids.add(node);
			} // while
		} else {
			while (itops.hasNext()) {
				OClass aClass = itops.next();
				if (aClass instanceof AnonymousClass) {
					continue;
				}
				ClassNode node = new ClassNode(aClass);
				kids.add(node);
			} // while
		}

		root.source = o;
		root.setChildren(kids);
		Vector<ClassNode> parents = kids;
		Vector<ClassNode> allKids;
		do {
			allKids = new Vector<ClassNode>();
			for (int i = 0; i < parents.size(); i++) {
				ClassNode parent = parents.get(i);
				kids = new Vector<ClassNode>();

				// skip this one if it's an instance
				if (parent.getSource() instanceof OInstance)
					continue;

				OClass ocl = (OClass) parent.getSource();

				// if we include instances, then get them too
				if (includeInstances && (o instanceof Ontology)) {
					Ontology kb = (Ontology) o;
					Set<OInstance> instances = kb.getOInstances(ocl,
							OConstants.Closure.DIRECT_CLOSURE);
					if (instances != null && !instances.isEmpty()) {
						Iterator<OInstance> insti = instances.iterator();
						while (insti.hasNext())
							kids.add(new ClassNode(insti.next()));
					}
				}

				if (0 == ocl.getSubClasses(OConstants.Closure.DIRECT_CLOSURE)
						.size()) {
					if (!kids.isEmpty())
						// add the instances as children, but do not add them
						// for future
						// traversal to allKids
						parent.setChildren(kids);
					continue;
				} // if 0 children

				Iterator<OClass> kidsi = ocl.getSubClasses(
						OConstants.Closure.DIRECT_CLOSURE).iterator();

				while (kidsi.hasNext()) {
					OClass aClass = kidsi.next();
					if (!includeAnonymousClasses
							&& aClass instanceof AnonymousClass)
						continue;
					kids.add(new ClassNode(aClass));
				} // while kidsi
				parent.setChildren(kids);
				allKids.addAll(kids);

			} // for i
			parents = allKids;
		} while (0 < allKids.size());

		return root;
	}// createRootNode()

	/**
	 * Creates a structure representing the class hierarchy of an ontology and
	 * the gazetteerLists mapped to it.
	 * 
	 * @param o
	 *            an ontology
	 * @param mapping
	 *            mapping definition
	 * @param nameVsNode
	 *            : this is actually a return value: should be initialized
	 *            before passing to this method and afterwards one can find a
	 *            mapping of class names vs class nodes there.
	 * @return the root node of the structure
	 */
	@SuppressWarnings("deprecation")
	public static ClassNode createRootNode(Ontology o,
			MappingDefinition mapping, Map<String, ClassNode> nameVsNode) {
		if (null == o || null == nameVsNode || null == mapping)
			throw new NullPointerException(
					"mapping, nameVsNode or ontology-o is null.");
		ClassNode root = new ClassNode(o);
		Iterator<OClass> itops = o.getOClasses(true).iterator();
		Vector<ClassNode> kids = new Vector<ClassNode>();
		while (itops.hasNext()) {
			ClassNode node = new ClassNode(itops.next());
			nameVsNode.put(node.toString(), node);
			kids.add(node);
		} // while

		root.source = o;
		root.setChildren(kids);
		Vector<ClassNode> parents = kids;
		Vector<ClassNode> allKids;
		do {
			allKids = new Vector<ClassNode>();
			for (int i = 0; i < parents.size(); i++) {
				ClassNode parent = parents.get(i);

				OClass ocl = (OClass) parent.getSource();
				if (0 == ocl.getSubClasses(OConstants.Closure.DIRECT_CLOSURE)
						.size()) {
					continue;
				} // if 0 children

				Iterator<OClass> kidsi = ocl.getSubClasses(
						OConstants.Closure.DIRECT_CLOSURE).iterator();

				kids = new Vector<ClassNode>();
				while (kidsi.hasNext()) {
					ClassNode cn = new ClassNode(kidsi.next());
					kids.add(cn);
					nameVsNode.put(cn.toString(), cn);
				} // while kidsi
				parent.setChildren(kids);
				allKids.addAll(kids);

			} // for i
			parents = allKids;
		} while (0 < allKids.size());

		// display mapping
		Iterator<MappingNode> inodes = mapping.iterator();
		MappingNode mn;
		while (inodes.hasNext()) {
			mn = inodes.next();
			URL turl = null;
			try {
				turl = new URL(mn.getOntologyID());
			} catch (java.net.MalformedURLException x) {
			}
			if (null != turl) {
				Ontology o2 = null;
				try {
					o2 = gate.creole.ontology.OntologyUtilities.getOntology(turl);
				} catch (gate.creole.ResourceInstantiationException x) {
				}
				if (o2 != null && o2.equals(o)) {
					ClassNode cmn = new ClassNode(mn);
					ClassNode cn = nameVsNode.get(mn.getClassID());
					if (null != cn) {
						cn.children.add(cn.children.size(), cmn);
					}
				}// if from the same ontology
			} // turl != null
		}// while inodes

		return root;
	}// createRootNode()

	/**
	 * Constructs a root class node from an ontology
	 * 
	 * @param o
	 *            the ontology
	 */
	public ClassNode(Ontology o) {
		name = o.getName();
	}

	/**
	 * Constructs a class node given an ontology class
	 * 
	 * @param clas
	 *            ontology class
	 */
	public ClassNode(OClass clas) {
		name = clas.getName();
		source = clas;
	}

	/**
	 * Constructs a class node given an ontology instance
	 * 
	 * @param instance
	 *            ontology instance
	 */
	public ClassNode(OInstance instance) {
		name = instance.getName();
		source = instance;
	}

	/**
	 * Constructs a class node given a mapping node
	 * 
	 * @param mapNode
	 *            mapping node
	 */
	public ClassNode(MappingNode mapNode) {
		name = mapNode.getList();
		source = mapNode;
	}

	public int getIndexOfChild(Object child) {
		return children.indexOf(child);
	}

	public Iterator<ClassNode> getChildren() {
		return children.iterator();
	}

	public void setChildren(Vector<ClassNode> chldrn) {
		children = chldrn;
	}

	public Vector<ClassNode> children() {
		return children;
	}

	public String toString() {
		return name;
	}

	public int getChildCount() {
		return children.size();
	}

	public IFolder getChild(int index) {
		return children.get(index);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassNode other = (ClassNode) obj;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		return true;
	}

	/**
	 * Gets the Source object
	 * 
	 * @return the source object e.g. an gate.creole.TClass or a
	 *         gate.creole.Ontology
	 */
	public Object getSource() {
		return source;
	}

	/**
	 * Sets the source object
	 * 
	 * @param o
	 *            the source object to be set
	 */
	public void setSource(Object o) {
		source = o;
	}

	/**
	 * Renames this class node
	 * 
	 * @param newName
	 *            the new name of the node
	 */
	public void rename(String newName) {
		name = newName;
	}

	/**
	 * Removes a sub class
	 * 
	 * @param sub
	 *            the sub class to be removed
	 */
	public void removeSubNode(ClassNode sub) {
		if (children.contains(sub)) {
			children.remove(sub);
			Object source = this.getSource();
			if (source instanceof OClass) {
				OClass c = (OClass) source;
				if (sub.getSource() instanceof OClass)
					c.removeSubClass((OClass) sub.getSource());
				else if (sub.getSource() instanceof OInstance
						&& c.getOntology() instanceof Ontology)
					((Ontology) c.getOntology())
							.removeOInstance((OInstance) sub.getSource());
			} else if (source instanceof Ontology) {
				Ontology o = (Ontology) source;
				o.removeOClass((OClass) sub.getSource());
			} else if (source instanceof OInstance) {
				// cannot remove anything from an instance
				return;
			} else {
				throw new GateRuntimeException(
						"Can not remove a sub node from a classnode.\n"
								+ "The source is neither an Ontology neither TClass");
			} // else
		} // if contains
	} // removeSubNode

	/**
	 * Adds a sub node
	 * 
	 * @param sub
	 *            the sub node to be added
	 */
	public void addSubNode(ClassNode sub) {
		if (!children.contains(sub)) {
			Object source = this.getSource();
			if (source instanceof OClass) {
				OClass c = (OClass) source;
				if (!(sub.getSource() instanceof OClass)
						&& !(sub.getSource() instanceof OInstance))
					throw new GateRuntimeException(
							"The sub node's source is not an instance of TClass or OInstance");
				if (sub.getSource() instanceof OClass) {
					OClass sc = (OClass) sub.getSource();
					c.addSubClass(sc);
					// this code originally used the deprecated method
					// addOClass(URI, byte)
					// with the byte constant indicating a class, without
					// checking for
					// sc not being an anonymous class.
					c.getOntology().addOClass((OURI) sc.getONodeID());
					children.add(sub);
				}
				if (sub.getSource() instanceof OInstance
						&& c.getOntology() instanceof Ontology) {
					OInstance inst = (OInstance) sub.getSource();
					if (!((Ontology) c.getOntology()).containsOInstance(inst
							.getOURI())) {
						Iterator<OClass> instClasses = inst.getOClasses(
								OConstants.Closure.DIRECT_CLOSURE).iterator();
						while (instClasses.hasNext()) {
							((Ontology) c.getOntology()).addOInstance(
									inst.getOURI(), instClasses.next());
						}
					}

					children.add(sub);
				}

			} else {
				if (source instanceof Ontology) {
					Ontology o = (Ontology) source;
					if (!(sub.getSource() instanceof OClass))
						throw new GateRuntimeException(
								"The sub node's source is not an instance of TClass");
					OClass sc = (OClass) sub.getSource();
					o.addOClass((OURI) sc.getONodeID());
					children.add(sub);
				} else {
					throw new GateRuntimeException(
							"cannot add a sub node to something which "
									+ "is neither an Ontology neither an TClass");
				} // else
			} // else
		} // if ! contains
	} // addSubNode()

	/*--- Transferable interface implementation ---*/
	public boolean isDataFlavorSupported(DataFlavor df) {
		return df.equals(CLASS_NODE_FLAVOR);
	}

	public Object getTransferData(DataFlavor df)
			throws UnsupportedFlavorException, IOException {
		if (df.equals(CLASS_NODE_FLAVOR)) {
			return this;
		} else
			throw new UnsupportedFlavorException(df);
	}

	public DataFlavor[] getTransferDataFlavors() {
		return flavors;
	}

} // class ClassNode