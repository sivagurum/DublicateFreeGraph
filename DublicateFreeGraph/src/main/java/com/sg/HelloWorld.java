package com.sg;

import java.io.File;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import com.sg.common.GraphDBUtility;
import com.sg.common.RelTypes;

/**
 * Hello world!
 *
 */
public class HelloWorld 
{	
	/*public static void main( String[] args )
    {		
    	System.out.println( "Config Required Java1.7 and Above!" );
    	
    	GraphDatabaseService graphDB = null;
    	Node firstNode;
    	Node secondNode;
    	Relationship relationShip;
    	
    	try{
    		GraphDBUtility.deleteFileOrDirectory(new File(GraphDBUtility.DB_PATH));
	    	graphDB = new GraphDatabaseFactory().newEmbeddedDatabase(GraphDBUtility.DB_PATH);
	    	
	    	System.out.println(graphDB.toString());
	    		try(Transaction tx = graphDB.beginTx()){
	    			firstNode = graphDB.createNode();	    			
	    			firstNode.setProperty("message", "Hello");
	    			secondNode = graphDB.createNode();
	    			secondNode.setProperty("message", "World");
	    			
	    			relationShip = firstNode.createRelationshipTo(secondNode, RelTypes.KNOWS);	    			
	    			relationShip.setProperty("message", "Brave Neo4j");
	    			
	    			//Delete Node and Relationship
	    			
	    			firstNode.getSingleRelationship( RelTypes.KNOWS, Direction.OUTGOING ).delete();
	    			firstNode.delete();
	    			secondNode.delete();
	    			
	    			
	    			tx.success();
	    		}catch (Exception e) {
					e.printStackTrace();					
				}
	    	
	    	GraphDBUtility.registerShutdownHook(graphDB);	    	
	        System.out.println( "*** Successfully Completed ***" );
    	}catch(Exception e){
    		
    	}
    }*/
    
    
}
