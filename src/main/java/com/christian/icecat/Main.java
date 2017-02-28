package com.christian.icecat;

import com.christian.icecat.utility.JavaVersionChecker;
import com.christian.icecat.view.CommandLineView;

/**
 * Main class that will run the icecat importer from the command line.
 * 
 * @author christian
 *
 */
public class Main {

	public static void main(String... args) {
		/*
		 * Checking java version, because Restrict access to external DTDs and
		 * external Entity References to the protocols specified, was made default
		 * since java 1.8.
		 */
		if (JavaVersionChecker.isJava8OrGreater()) {
			System.setProperty("javax.xml.accessExternalDTD", "all");
		}

		CommandLineView view = new CommandLineView();
		view.run();
	}

}
