package com.sg.common;

import java.io.File;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.RelationshipType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraphDBUtility {
	
	/*private static final Logger logger = LoggerFactory
			.getLogger(GraphDBUtility.class);
	
	//Common DBPath
	public static final String DB_PATH = "target/neo4j-store";
		
	public static void registerShutdownHook(final GraphDatabaseService graphDb){
		logger.info(" *** registerShutdownHook() initiated ***");
    	// Registers a shutdown hook for the Neo4j instance so that it
        // shuts down nicely when the VM exits (even if you "Ctrl-C" the
        // running application).    	
    	Runtime.getRuntime().addShutdownHook(
    				new Thread(){
    					@Override
    					public void run(){
    						System.out.println("*** Graphdatabase Shutdown Success ***");
    						graphDb.shutdown();
    					}
    				}
    			);
    }
	
	public static void deleteFileOrDirectory( File file )
    {		
        if ( file.exists() )
        {
            if ( file.isDirectory() )
            {
                for ( File child : file.listFiles() )
                {
                    deleteFileOrDirectory( child );
                }
            }
            file.delete();
        }
        
    }*/
}
