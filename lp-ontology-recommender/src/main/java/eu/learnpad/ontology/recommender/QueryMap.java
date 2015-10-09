/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.recommender;

import eu.learnpad.ontology.config.APP;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sandro.emmenegger
 */
public class QueryMap {

    private static final Map<String, RecommenderQuery> queries = new HashMap<>();

    static {
        try {
            final Path queriesFilePath = Paths.get(QueryMap.class.getResource(APP.CONF.getString("recommender.queries.file")).toURI());
            BufferedReader br = new BufferedReader(new FileReader(queriesFilePath.toFile()));
            String queryName = "";
            String comment = "";
            StringBuilder queryStr = new StringBuilder();
            for (String line; (line = br.readLine()) != null;) {
                if(line.startsWith("##START##")){
                    queryName=line.substring(line.indexOf(":")+1);
                }else{
                    if(line.startsWith("##END##")){
                        queries.put(queryName, new RecommenderQuery(queryStr.toString(), comment));
                        queryName = "";
                        comment = "";
                        queryStr = new StringBuilder();
                    }else{
                        if(line.startsWith("##COMMENT")){
                            comment = line.substring(line.indexOf(":")+1);
                        }else{
                            queryStr.append(line).append("\n");
                        }
                    }
                }
            }

        } catch (URISyntaxException ex) {
            Logger.getLogger(QueryMap.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(QueryMap.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(QueryMap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static RecommenderQuery getQuery(String name){
        return queries.get(name);
    }
    
}
