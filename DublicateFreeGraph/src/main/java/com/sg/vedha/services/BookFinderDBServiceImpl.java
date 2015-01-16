/**
 * 
 */
package com.sg.vedha.services;

import java.io.File;

import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sg.vedha.bean.Book;
import com.sg.vedha.process.BookSearchUtility;

import static com.sg.vedha.process.BookSearchUtility.BookProperty.*;
import static com.sg.vedha.process.BookSearchUtility.BookNodeLabel.*;

/**
 * @author Sivaguru
 *
 */
public class BookFinderDBServiceImpl implements BookFinderDBService{
	private static final Logger logger = LoggerFactory
			.getLogger(BookFinderDBServiceImpl.class);
    
    /**
     * Create New Neo4j DB and Get the service
     * 
     * @param MATRIX_DB
     * @return
     */
    public GraphDatabaseService createNewDBService(String MATRIX_DB){
    	GraphDatabaseService graphDb = null;
    	try{
	    	BookSearchUtility.deleteFileOrDirectory(new File(MATRIX_DB));
	    	graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(MATRIX_DB);
	    	BookSearchUtility.registerShutdownHook(graphDb);
    	}catch(Exception e){
    		logger.error(e.toString());
    	}
    	return graphDb;
    }
    
    /**
     * Get the existing Neo4j DB service
     * @param MATRIX_DB
     * @return
     */
    public GraphDatabaseService getDBService(String MATRIX_DB){
    	GraphDatabaseService graphDb = null;
    	try{
    		graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(MATRIX_DB);
    		BookSearchUtility.registerShutdownHook(graphDb);
    	}catch(Exception e){
    		logger.error(e.toString());
    	}
    	return graphDb;
    }
    
    
    /**
     * Create New Node
     * @param graphDb
     * @param book
     * @return
     * @throws Exception
     */
    public Node createNode(GraphDatabaseService graphDb, Book book) throws Exception{    	
		logger.info(" ** createNode() ** "+book.toString());
		if(book == null || (book.getBookName() == null && book.getKeyword() == null)){
			throw new Exception("Unable to create Node For empty Object : Book ");			
		}
		Node node = null;
		
		try(Transaction tx = graphDb.beginTx()){
			node = graphDb.createNode(Book);
			
			if(book.getBookName() != null && book.getBookName().length() != 0 ){
				node.setProperty(BOOK_NAME.toString(), book.getBookName());
			}
			
			if(book.getKeyword() != null && book.getKeyword().length() != 0 ){
				node.setProperty(BOOK_KEYWORD.toString(), book.getKeyword());
			}
			
			tx.success();
		}
		
		return node;
	}
    
    /**
     * Create Relationship of the two existing node 
     * @param graphDb
     * @param relationshipType
     * @param fromNode
     * @param toNode
     * @return
     */
    public Relationship createRelationShip(GraphDatabaseService graphDb, RelationshipType relationshipType,Node fromNode,Node toNode){
    	Relationship relationShip;
    	try(Transaction tx = graphDb.beginTx()){
    		relationShip = fromNode.createRelationshipTo(toNode, relationshipType);
    		tx.success();
    	}
    	return relationShip;
    }
    
}
