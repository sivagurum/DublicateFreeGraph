/**
 * 
 */
package com.sg.vedha;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.traversal.Paths;

import com.sg.vedha.bean.Book;
import com.sg.vedha.process.BookFinderInitialDataSetup;
import com.sg.vedha.process.BookRelationship;
import com.sg.vedha.process.BookSearchUtility;
import com.sg.vedha.process.BookSearchUtility.BookProperty;
import com.sg.vedha.services.BookFinderDBService;
import com.sg.vedha.services.BookFinderDBServiceImpl;
import com.sg.vedha.services.BookFinderService;
import com.sg.vedha.services.BookFinderServiceImpl;

/**
 * @author Sivaguru
 *
 */
public class TestBookFinder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BookFinderDBService bFinderDb = new BookFinderDBServiceImpl();
		GraphDatabaseService graphDb;
		BookFinderInitialDataSetup bFinderInitial = new BookFinderInitialDataSetup();
		BookFinderService bFinderService = new BookFinderServiceImpl();
		long neoNodeId = 0L;
		try {
			graphDb = bFinderDb.createNewDBService(BookSearchUtility.DB_PATH);
			
			neoNodeId = bFinderInitial.createBasicNodespace(graphDb);
			
			//Get All Node Map Format
			Map<Long, Book> nodeMap = bFinderService.getAllNodes(graphDb);
			System.out.println(" Get All Node Map Format --> "+nodeMap);
			
			//Get All Relationship List format
			List<String> relationShip = bFinderService.getAllRelationShip();
			System.out.println("All Relation ship --> "+relationShip);
			
			String allNeoBook = bFinderService.printNeoBooks(graphDb, neoNodeId, BookRelationship.SCIENCE.toString());
			System.out.println(allNeoBook);
			
			//Find Shortest Path
			Node fromNode = bFinderService.getNeoNode(graphDb, 0L);
			Node toNode = bFinderService.getNeoNode(graphDb, 4L);
			String foundBestPath = bFinderService.shortestPath(graphDb,fromNode,toNode);
			System.out.println(foundBestPath);
			
			bFinderService.findNodes(graphDb,BookSearchUtility.BookProperty.BOOK_KEYWORD.toString(),"1996");
						
			List<String> searchResult = bFinderService.searchBookAllResult(graphDb, "1996");
			System.out.println(" **** "+searchResult);
		} catch (Exception e) {			
			e.printStackTrace();
		}

	}

}
