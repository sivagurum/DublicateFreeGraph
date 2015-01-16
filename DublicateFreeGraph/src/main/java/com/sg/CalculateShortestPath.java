package com.sg;

import java.io.File;

import org.neo4j.graphalgo.GraphAlgoFactory;
import org.neo4j.graphalgo.PathFinder;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.PathExpanders;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;
import org.neo4j.graphdb.traversal.Paths;
import org.neo4j.graphmatching.filter.RegexPattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sg.common.GraphDBUtility;


public class CalculateShortestPath {
	/*private static final Logger logger = LoggerFactory
			.getLogger(CalculateShortestPath.class);
	
	private static final String NAME_KEY = "name";
	private static RelationshipType KNOWS = DynamicRelationshipType.withName("KNOWS");
	
	private static GraphDatabaseService graphDb;
	private static Index<Node> indexService;
	
	public static void main(final String[] args){
		logger.info("*** main() ***");
		GraphDBUtility.deleteFileOrDirectory(new File( GraphDBUtility.DB_PATH));
		graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(GraphDBUtility.DB_PATH);
		GraphDBUtility.registerShutdownHook(graphDb);
		try(Transaction tx = graphDb.beginTx()){			
			indexService = graphDb.index().forNodes("nodes");
			createChain( "Neo", "Trinity" );
            createChain( "Neo", "Morpheus", "Trinity" );
            createChain( "Morpheus", "Cypher", "Agent Smith" );
            createChain( "Morpheus", "Agent Smith" );
            tx.success();
		}
		
		try(Transaction tx = graphDb.beginTx()){
			// So let's find the shortest path between Neo and Agent Smith
			Node neo = getOrCreateNode("Neo");
			Node agentSmith = getOrCreateNode("Agent Smith");
			// START SNIPPET: shortestPathUsage			
			PathFinder<Path> finder = GraphAlgoFactory.shortestPath(PathExpanders.forTypeAndDirection(KNOWS, Direction.BOTH), 4);
			Path foundPath = finder.findSinglePath(neo, agentSmith);
			System.out.println(" Path from Neo to Agent Smith: "+Paths.simplePathToString(foundPath,NAME_KEY));
			// END SNIPPET: shortestPathUsage
			
			//Delete
			agentSmith = getNode("Agent Smith");
			finder = GraphAlgoFactory.shortestPath(PathExpanders.forTypeAndDirection(KNOWS, Direction.BOTH), 4);
			foundPath = finder.findSinglePath(neo, agentSmith);
			System.out.println("*** Path from Neo to Agent Smith: "+Paths.simplePathToString(foundPath,NAME_KEY));
			//Delete
		}
		
		System.out.println( "Shutting down database ..." );
        graphDb.shutdown();
	}
	
	private static void createChain(String... names){
		for(int i=0;i<names.length-1;i++){
			Node firstNode = getOrCreateNode(names[i]);
			Node secondNode = getOrCreateNode( names[i + 1] );
			firstNode.createRelationshipTo(secondNode, KNOWS);
		}
	}
	
	private static Node getNode(String name){
		Node node = indexService.get(NAME_KEY, name).getSingle();		
		//indexService.getName().contains(s)
		//IndexHits<Node> hits = indexService.query(new RegexPattern("label", "property", "pattern", "options"));
		//node = hits.getSingle();
		return node;
	}

	private static Node getOrCreateNode(String name) {
		Node node = indexService.get(NAME_KEY, name).getSingle();		
		if(node == null){
			node = graphDb.createNode();
			node.setProperty(NAME_KEY, name);			
			indexService.add(node, NAME_KEY, name);
		}
		return node;
	}*/
}
