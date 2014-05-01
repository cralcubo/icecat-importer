package com.christian.icecat;

import com.christian.icecat.view.CommandLineView;

/**
 * Main class that will run the icecat importer
 * from the command line.
 *  
 * @author christian
 *
 */
public class Main {
	
	public static void main(String... args){
		CommandLineView view = new CommandLineView();
		view.run();
	}
	
}
