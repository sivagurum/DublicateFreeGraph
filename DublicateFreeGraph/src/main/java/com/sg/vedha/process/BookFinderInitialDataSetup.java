/**
 * 
 */
package com.sg.vedha.process;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sg.vedha.bean.Book;
import com.sg.vedha.services.BookFinderDBServiceImpl;
import com.sg.vedha.services.BookFinderServiceImpl;

/**
 * @author Sivaguru
 *
 */
public class BookFinderInitialDataSetup {
	private static final Logger logger = LoggerFactory
			.getLogger(BookFinderInitialDataSetup.class);
	/**
	 * Just Create Some Basic Nodes And Set the Relationship of the Nods here 
	 * @param graphDb
	 * @return
	 * @throws Exception
	 */
	public long createBasicNodespace(GraphDatabaseService graphDb)
			throws Exception {
		logger.info(" *** createBasicNodespace()  started *** ");
		long neoNodeId = 0;
		BookFinderDBServiceImpl bFinder = new BookFinderDBServiceImpl();

		Book book = new Book("Neo", "Neo Node");
		Node node = bFinder.createNode(graphDb, book);
		neoNodeId = node.getId();

		book = new Book(
				"Alice in the Land of Plants",
				"Biology of Plants and Their Importance for Planet Earth, Manetas, Yiannis, 1988");
		Node node1 = bFinder.createNode(graphDb, book);
		book = new Book(
				"Alice in the Land of Plants",
				"Biology of Plants and Their Importance for Planet Earth, Manetas, Yiannis, 1988");
		Node node2 = bFinder.createNode(graphDb, book);
		book = new Book("From Research to Manuscript",
				"A Guide to Scientific Writing,Katz, Michael Jay, 2009");
		Node node3 = bFinder.createNode(graphDb, book);
		book = new Book("Sushi",
				"Maths and Science Relevent Book, Mouritsen, Ole G, 2009");
		Node node4 = bFinder.createNode(graphDb, book);
		book = new Book("The Joy of Science",
				"An Examination of How Scientists, Lockshin, Richard A, 2007");
		Node node5 = bFinder.createNode(graphDb, book);
		book = new Book("The ABCs of Gene Cloning",
				"The ABCs of Gene Cloning, Wong, Dominic, 2006");
		Node node6 = bFinder.createNode(graphDb, book);
		book = new Book("The Craft of Editing",
				"A Guide for Managers, Scientists, and Engineers, Alley, Michael, 2000");
		Node node7 = bFinder.createNode(graphDb, book);
		book = new Book("Michael Ryan’s Writings on Medical Ethics",
				"A Guide for Managers, Scientists, and Engineers, Alley, Michael, 2000");
		Node node8 = bFinder.createNode(graphDb, book);
		logger.info(" *** createBasicNodespace()  All Science Book Created Successfully *** ");

		bFinder.createRelationShip(graphDb, BookRelationship.NEO_NODE, node,
				node1);
		bFinder.createRelationShip(graphDb, BookRelationship.SCIENCE, node1,
				node2);
		bFinder.createRelationShip(graphDb, BookRelationship.SCIENCE, node1,
				node3);
		bFinder.createRelationShip(graphDb, BookRelationship.SCIENCE, node2,
				node4);
		bFinder.createRelationShip(graphDb, BookRelationship.SCIENCE, node2,
				node5);
		bFinder.createRelationShip(graphDb, BookRelationship.SCIENCE, node4,
				node6);
		bFinder.createRelationShip(graphDb, BookRelationship.SCIENCE, node4,
				node7);
		bFinder.createRelationShip(graphDb, BookRelationship.SCIENCE, node6,
				node8);
		logger.info(" *** createBasicNodespace()  All Science Book Relation Created Successfully *** ");
		
		book = new Book("Notes from Trigonometry","Pythagorean theorem, proof by contradiction, limits, and proof by induction, Rozhkovskaya, Tamara, 1988");
		node1 = bFinder.createNode(graphDb, book);
		book = new Book("Encyclopaedia of Mathematical","Encyclopaedia of Mathematical Sciences, Gamkrelidze, Revaz V., 1996");
		node2 = bFinder.createNode(graphDb, book);
		book = new Book("International Mathematical Series","International Mathematical Series, Rozhkovskaya, 1996");
		node3 = bFinder.createNode(graphDb, book);
		book = new Book("Lecture Notes in Mathematics","Lecture Notes in Mathematics, Teissier, Bernard, 1988");
		node4 = bFinder.createNode(graphDb, book);
		book = new Book("Mathematics and its Applications","Mathematics and its Applications, Soviet Series , 1988");
		node5 = bFinder.createNode(graphDb, book);
		book = new Book("Mathematics and its Applications","Mathematics and its Applications, East European Series, 1996");
		node6 = bFinder.createNode(graphDb, book);
		book = new Book("Mathematics and Its Applications","Mathematics and Its Applications, Hazewinkel, Michiel (Ed.), 1996");
		node7 = bFinder.createNode(graphDb, book);
		book = new Book("Mathematics and Visualization","Mathematics and Visualization, Gamkrelidze, Revaz V., 1996");
		node8 = bFinder.createNode(graphDb, book);
		logger.info(" *** createBasicNodespace()  All Maths Book Created Successfully *** ");
		
		bFinder.createRelationShip(graphDb, BookRelationship.NEO_NODE, node,node1);
		bFinder.createRelationShip(graphDb, BookRelationship.MATHS, node1,node2);
		bFinder.createRelationShip(graphDb, BookRelationship.MATHS, node1,node3);
		bFinder.createRelationShip(graphDb, BookRelationship.MATHS, node2,node4);
		bFinder.createRelationShip(graphDb, BookRelationship.MATHS, node2,node5);
		bFinder.createRelationShip(graphDb, BookRelationship.MATHS, node4,node6);
		bFinder.createRelationShip(graphDb, BookRelationship.MATHS, node4,node7);
		bFinder.createRelationShip(graphDb, BookRelationship.MATHS, node6,node8);
		logger.info(" *** createBasicNodespace()  All Maths Book Relation Created Successfully *** ");
		
		long sushiBookId = 4L;
		bFinder.createRelationShip(graphDb, BookRelationship.MATHS, node1,new BookFinderServiceImpl().getNeoNode(graphDb, sushiBookId));
		logger.info(" *** createBasicNodespace()  Suchi Book Relation to Maths Created Successfully *** ");
		
		return neoNodeId;
	}
}
