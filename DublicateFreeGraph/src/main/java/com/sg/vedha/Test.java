/**
 * 
 */
package com.sg.vedha;

import java.io.File;
import java.io.IOException;

import org.neo4j.kernel.impl.util.FileUtils;

import com.sg.vedha.services.BookFinderServiceImpl;

/**
 * @author Sivaguru
 *
 */
public class Test {
	public static void main(String[] args) {
		BookFinderServiceImpl bm = new BookFinderServiceImpl();
		System.out.println(bm.queryBuilder("BOOK_NAME", "1996-Alley"));
		
	}
}
