/**
 * 
 */
package com.sg.vedha.process;

import java.io.File;
import java.io.IOException;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.kernel.impl.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Sivaguru
 *
 */
public class BookSearchUtility {
	
	private static final Logger logger = LoggerFactory
			.getLogger(BookSearchUtility.class);
	
	public enum BookNodeLabel implements Label {
		Book
	}
	
	public enum BookProperty{
		BOOK_NAME("BOOK_NAME"),
		BOOK_KEYWORD("BOOK_KEYWORD");
		
		private String property;
		private BookProperty(String property){
			this.property = property;
		}
		
		public String toString(){
			return property;
		}
	}	
	
	//Common DBPath
	public static final String DB_PATH = "target/neo4j-store";
	private static boolean shutdownHookInitiated = false;
		
	public static void registerShutdownHook(final GraphDatabaseService graphDb){		
    	// Registers a shutdown hook for the Neo4j instance so that it
        // shuts down nicely when the VM exits (even if you "Ctrl-C" the
        // running application).
		if(!shutdownHookInitiated){
			shutdownHookInitiated = true;
			logger.info(" *** registerShutdownHook() initiated ***");
			Runtime.getRuntime().addShutdownHook(
	    		new Thread(){
	    			@Override
	    			public void run(){    						
	    				graphDb.shutdown();
	    				System.out.println("*** Graphdatabase Shutdown Success ***");
	    			}
	    		}
	    	);
		}
    }
	
	public static void shutdownGraphDB(final GraphDatabaseService graphDb)
    {
        graphDb.shutdown();
    }
	
	public static boolean isFileExist(File file){
		if ( file.exists() ){
			logger.info(file.getName()+" Folder Size : "+FileUtils.directorySize(file));
			return FileUtils.directorySize(file)/1024 >= 20 ? true : false;
        }
		return false;
	}
	
	public static void deleteFileOrDirectory(File file){
		if(file.exists()){
			try {
				FileUtils.deleteRecursively(file);
				logger.info("Folder Deleted Successfully : "+file.getCanonicalPath());
			} catch (IOException e) {
				logger.error(e.toString());
				e.printStackTrace();
			}
		}
	}
	
	/*public static void deleteFileOrDirectory( File file )
    {		
        if ( file.exists() )
        {
            if ( file.isDirectory() )
            {
                for ( File child : file.listFiles() )
                {
                    deleteFileOrDirectory( child );
                }
            }
            file.delete();
        }
        
    }*/
}
