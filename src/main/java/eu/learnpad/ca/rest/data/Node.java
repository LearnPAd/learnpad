package eu.learnpad.ca.rest.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement(name = "Node")
public class Node implements Comparable<Object>{
	
	@XmlTransient
	protected Integer OffSet=-1;

	protected Integer id=-1;
	
	public Node(){
		
	}
	
	public Node(Integer id){
		this.id=id;
	}

	
	
	public Node(Integer id,int offSet) {
		OffSet = offSet;
		this.id = id;
	}

	public Integer getOffSet() {
		return OffSet;
	}

	public void setOffSet(int offSet) {
		OffSet = offSet;
	}

	/**
	 * Get the value of id.
	 * 
	 * @return
	 *     possible object is
	 *     {@link Integer }
	 *     
	 */
	@XmlAttribute(name = "id")
	public Integer getId() {
		return id;
	}

	/**
	 * Set the value of id.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link Integer }
	 *     
	 */
	public void setId(Integer value) {
		this.id = value;
	}

	@Override
	public String toString() {
		return "Node id=" + id + " ";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Node))
			return false;
		Node other = (Node) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public int compareTo(Object o) {
		Node node = (Node) o;
		return OffSet.compareTo(node.getOffSet());
	}



}
