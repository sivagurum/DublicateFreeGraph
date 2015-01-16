/**
 * 
 */
package com.sg.vedha.services;

import java.util.List;
import java.util.Map;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.traversal.Traverser;

import com.sg.vedha.bean.Book;

/**
 * @author Sivaguru
 *
 */
public interface BookFinderService {
	Node getNeoNode(GraphDatabaseService graphDb,long neoNodeId);
	Map<Long,Book> getAllNodes( GraphDatabaseService graphDb);
	List<String> getAllRelationShip();
	Traverser findBooks(GraphDatabaseService graphDb,Node neoNode,String relationShip);
	String printNeoBooks(GraphDatabaseService graphDb,long neoNodeNumber,String relationShip);
	String shortestPath(GraphDatabaseService graphDb,Node fromNode,Node toNode);
	//void findNodes(GraphDatabaseService graphDb,String propertyName,String findWord);
	ResourceIterator<Node> findNodes(GraphDatabaseService graphDb,String propertyName,String findWord);
	List<String> searchBookAllResult(GraphDatabaseService graphDb,long neoNodeId,String propertyName,String findWord);
	List<String> searchBookAllResult(GraphDatabaseService graphDb,String findWord);
}
