/**
 * 
 */
package com.sg.vedha.process;

import org.neo4j.graphdb.RelationshipType;

/**
 * @author Sivaguru
 *
 */
public enum BookRelationship implements RelationshipType {
	NEO_NODE,
	SCIENCE,
	MATHS,
	HISTORY	
}
