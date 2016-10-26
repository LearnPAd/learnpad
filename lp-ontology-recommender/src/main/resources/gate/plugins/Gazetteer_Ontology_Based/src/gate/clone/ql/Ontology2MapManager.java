/**
 * 
 */
package gate.clone.ql;

import gate.clone.ql.utils.Ontology2Map;
import gate.creole.ontology.Ontology;

/**
 * @author danica damljanovic
 * 
 */
public class Ontology2MapManager {
  private Ontology2MapManager() {
  }

  private static Ontology2MapManager myInstance;
  private static Ontology2Map ontology2Map;

  public static Ontology2MapManager getInstance() {
    if(myInstance == null) myInstance = new Ontology2MapManager();
    return myInstance;
  }

  public void addOntologyToIndex(Ontology ontology) {
    ontology2Map = new Ontology2Map(ontology);
   // ontology2Map.put(ontology.getURL().toString(), os);
  }

  public Ontology2Map getOntology2Map() {
    return ontology2Map;
  }
}
