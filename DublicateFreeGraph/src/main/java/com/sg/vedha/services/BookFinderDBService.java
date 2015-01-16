/**
 * 
 */
package com.sg.vedha.services;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;

import com.sg.vedha.bean.Book;

/**
 * @author Sivaguru
 *
 */
public interface BookFinderDBService {
	GraphDatabaseService createNewDBService(String MATRIX_DB);
	GraphDatabaseService getDBService(String MATRIX_DB);
	Node createNode(GraphDatabaseService graphDb, Book book) throws Exception;
	Relationship createRelationShip(GraphDatabaseService graphDb, RelationshipType relationshipType,Node fromNode,Node toNode);
}
