/**
 * 
 */
package com.sg.vedha.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphalgo.GraphAlgoFactory;
import org.neo4j.graphalgo.PathFinder;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.PathExpanders;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.traversal.Evaluators;
import org.neo4j.graphdb.traversal.Paths;
import org.neo4j.graphdb.traversal.TraversalDescription;
import org.neo4j.graphdb.traversal.Traverser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sg.vedha.bean.Book;
import com.sg.vedha.process.BookRelationship;
import com.sg.vedha.process.BookSearchUtility.BookProperty;
import com.sg.vedha.process.BookSearchUtility.BookNodeLabel;

/**
 * @author Sivaguru
 *
 */
public class BookFinderServiceImpl implements BookFinderService{
	private static final Logger logger = LoggerFactory
			.getLogger(BookFinderServiceImpl.class);
	/**
	 * get First Node or Base Node
	 * 
	 * @param graphDb
	 * @param neoNodeId
	 * @return Node
	 */
	public Node getNeoNode(GraphDatabaseService graphDb,long neoNodeId){
		logger.info(" ***getNeoNode()*** "); 
		//return graphDb.getNodeById(neoNodeId).getSingleRelationship(BookRelationship.NEO_NODE, Direction.OUTGOING).getEndNode();
		try(Transaction tx = graphDb.beginTx()){
			return graphDb.getNodeById(neoNodeId);			
		}
	}
	
	 public Map<Long,Book> getAllNodes( GraphDatabaseService graphDb){
		 HashMap<Long, Book> nodeMap = new HashMap<Long, Book>();		 
		 Book book = null;
		 String bookNameProperty = BookProperty.BOOK_NAME.toString();
		 String bookKeyWordProperty = BookProperty.BOOK_KEYWORD.toString();
		 logger.info(" ***getAllNodes()*** ");
	    	try(Transaction tx = graphDb.beginTx()){	    		
		    	for(Node node:graphDb.getAllNodes()){
		    		book = new Book(node.getProperty(bookNameProperty).toString(),node.getProperty(bookKeyWordProperty).toString());
		    		nodeMap.put(node.getId(), book);
		    		//System.out.println("****2****"+node.getId()+node.getProperty(BookProperty.BOOK_NAME.toString()));		    		
		    	}
		    	
		    	return (HashMap<Long, Book>) nodeMap.clone();
	    	}	    	
	    }
	 
	 public List<String> getAllRelationShip(){
		 ArrayList<String> list = new ArrayList<String>();
		 list.add(BookRelationship.NEO_NODE.toString());
		 list.add(BookRelationship.SCIENCE.toString());
		 list.add(BookRelationship.MATHS.toString());
		 list.add(BookRelationship.HISTORY.toString());
		 return (List<String>) list.clone();
	 }
	 
	/**
	 * Return Books Based on Node Relationship
	 * If you want all book relationShip put "ALL"
	 * 
	 * @param graphDb
	 * @param bookNode
	 * @param relationShip
	 * @return Traverser
	 */
	public Traverser findBooks(GraphDatabaseService graphDb,Node neoNode,String relationShip){
		logger.info(" ***findBooks()*** ");
		TraversalDescription td = null;
		try(Transaction tx = graphDb.beginTx()){
			switch (relationShip) {			
				case "SCIENCE":
					td = graphDb.traversalDescription()
								.breadthFirst()
								.relationships(BookRelationship.NEO_NODE,Direction.OUTGOING)
								.relationships(BookRelationship.SCIENCE,Direction.OUTGOING)
								.evaluator(Evaluators.excludeStartPosition());	
				break;
				
				case "MATHS":
					td = graphDb.traversalDescription()
								.breadthFirst()
								.relationships(BookRelationship.NEO_NODE,Direction.OUTGOING)
								.relationships(BookRelationship.MATHS,Direction.OUTGOING)
								.evaluator(Evaluators.excludeStartPosition());	
				break;
				
				case "HISTORY":
					td = graphDb.traversalDescription()
								.breadthFirst()
								.relationships(BookRelationship.NEO_NODE,Direction.OUTGOING)
								.relationships(BookRelationship.HISTORY,Direction.OUTGOING)
								.evaluator(Evaluators.excludeStartPosition());	
				break;
				
				case "NEO_NODE":
					td = graphDb.traversalDescription()
								.breadthFirst()
								.relationships(BookRelationship.NEO_NODE,Direction.OUTGOING)
								.evaluator(Evaluators.excludeStartPosition());	
				break;
				
				case "ALL":
					td = graphDb.traversalDescription()
								.breadthFirst()
								.relationships(BookRelationship.NEO_NODE,Direction.OUTGOING)
								.relationships(BookRelationship.SCIENCE,Direction.OUTGOING)				
								.relationships(BookRelationship.MATHS,Direction.OUTGOING)
								.relationships(BookRelationship.HISTORY,Direction.OUTGOING)							
								.evaluator(Evaluators.excludeStartPosition());	
				break;
				
				default:
					td = graphDb.traversalDescription()
								.breadthFirst()
								.relationships(BookRelationship.NEO_NODE,Direction.OUTGOING)
								.relationships(BookRelationship.SCIENCE,Direction.OUTGOING)				
								.relationships(BookRelationship.MATHS,Direction.OUTGOING)
								.relationships(BookRelationship.HISTORY,Direction.OUTGOING)							
								.evaluator(Evaluators.excludeStartPosition());
				break;
			}		
			
			return td.traverse(neoNode);
		}
	}
	
	/**
	 * Print All Neo Book Friend Nodes
	 * 
	 * @param graphDb
	 * @param neoNodeNumber
	 * @return
	 */
	public String printNeoBooks(GraphDatabaseService graphDb,long neoNodeNumber,String relationShip){
		logger.info(" ***printNeoBooks()*** ");
		BookFinderServiceImpl bFinder = new BookFinderServiceImpl();
		int numberOfBook = 0;
		String outPut = null;
		try(Transaction tx = graphDb.beginTx()){
			Node neoNode = bFinder.getNeoNode(graphDb, neoNodeNumber);
			outPut = neoNode.getProperty(BookProperty.BOOK_NAME.toString())+"'s "+relationShip+" Books :\n";
			Traverser findBookTraverser = findBooks(graphDb, neoNode, relationShip);
			
			for(Path bookPath: findBookTraverser){
				outPut += " At Depth "+bookPath.length()
						+" --> "
						+bookPath.endNode().getProperty(BookProperty.BOOK_NAME.toString())
						+" \n";				
				numberOfBook++;
			}
			
			outPut += " Number of Books found : "+numberOfBook+"\n";
		}
		return outPut;
	}
	
	/**
	 * 
	 */
	public String shortestPath(GraphDatabaseService graphDb,Node fromNode,Node toNode){
		logger.info(" ***shortestPath()*** ");
		StringBuffer shortestPath = new StringBuffer();
		try (Transaction tx = graphDb.beginTx()){
			PathFinder<Path> finder = GraphAlgoFactory.shortestPath(PathExpanders.allTypesAndDirections(), 50);
			
			Path foundPath = finder.findSinglePath(fromNode,toNode);
			
			shortestPath.append(" Path from ");
			shortestPath.append(fromNode.getProperty(BookProperty.BOOK_NAME.toString()));
			shortestPath.append(" to ");
			shortestPath.append(toNode.getProperty(BookProperty.BOOK_NAME.toString()));
			shortestPath.append(" : ");
			shortestPath.append(Paths.simplePathToString(foundPath,BookProperty.BOOK_NAME.toString()));
			shortestPath.append(" Path Length ");
			shortestPath.append(foundPath.length());
		}
		return shortestPath.toString();
		
	}
	
	public String queryBuilder(String propertyName,String findWord){
		String[] findWords = findWord.split("-");
		StringBuilder queryNew = new StringBuilder();
		queryNew.append("MATCH (book:"+BookNodeLabel.Book+") WHERE ");
		
		for(String word:findWords){
			queryNew.append(" UPPER(book."+propertyName+") =~ UPPER('.*"+word.trim()+".*$') ");
			queryNew.append(" OR ");
		}
		queryNew.delete(queryNew.length()-3, queryNew.length());
		queryNew.append("RETURN book");
		//String query = "MATCH (book:"+BookNodeLabel.Book+") WHERE UPPER(book."+propertyName+") =~ UPPER('.*"+findWord+".*$') RETURN book";
		return queryNew.toString();
	}
	
	/**
	 * @param graphDb
	 * @param propertyName
	 * @param findWord
	 * @return
	 */
	public ResourceIterator<Node> findNodes(GraphDatabaseService graphDb,String propertyName,String findWord){
		try (Transaction tx = graphDb.beginTx()){
			//String query = "MATCH (book:Book) WHERE UPPER(book.BOOK_KEYWORD) =~ UPPER('.*1996.*$') RETURN book";
		  	String query = "MATCH (book:"+BookNodeLabel.Book+") WHERE UPPER(book."+propertyName+") =~ UPPER('.*"+findWord+".*$') RETURN book";
		   	ExecutionEngine engine = new ExecutionEngine(graphDb);
		   	ExecutionResult result = engine.execute(query);
		   	ResourceIterator<Node> resourceIterator = result.columnAs("book"); 
		   	/*while(resourceIterator.hasNext()){
		   		Node node = resourceIterator.next();
		   		logger.info("Node Id : "+node.getId()+" | Node Property Value : "+node.getProperty(propertyName));
		   	}*/
		   	return resourceIterator;
		}
	}
	
	public List<String> searchBookAllResult(GraphDatabaseService graphDb,long neoNodeId,String propertyName,String findWord){
		try (Transaction tx = graphDb.beginTx()){
			List<String> allSearchResult = new ArrayList<String>();
			if(propertyName == null){
				propertyName = BookProperty.BOOK_KEYWORD.toString();
			}
			BookFinderService bookFinder = new BookFinderServiceImpl();		
			ResourceIterator<Node> resourceIterator = bookFinder.findNodes(graphDb, propertyName, findWord);
			Node neoNode = bookFinder.getNeoNode(graphDb, neoNodeId);
			while(resourceIterator.hasNext()){
		   		Node node = resourceIterator.next();
		   		allSearchResult.add(bookFinder.shortestPath(graphDb, neoNode, node));
		   	}
			return allSearchResult;
		}
	}
	
	public List<String> searchBookAllResult(GraphDatabaseService graphDb,String findWord){
		BookFinderService bookFinder = new BookFinderServiceImpl();
		return bookFinder.searchBookAllResult(graphDb, 0, null, findWord);
	}
}
