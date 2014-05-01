package com.christian.icecat.view;

public class Messenger {
	// Messages
	public static final String FORMATTED_EXIT_PROGRAM_MESSAGE = "Sorry connection with %s was not possible. Try again later...\n"
			+ "The program is stoping.";
	public static final String WRONG_INFO_MESSAGE = "Wrong information provided, try again...";

	public static final String ENTER_INFO_MESSAGE = "Please enter your %s...\n";

	public static final String ENTER_LANGUAGE_MESSAGE = "Please enter one of the following languages:\n"
			+ "- For Spanish: es\n"
			+ "- For English: en\n"
			+ "- For Ducth: nl\n"
			+ "- For German: de\n"
			+ "- For Russian: ru";

	public static final String EXIT_PROGRAM_MESSAGE = "Sorry but the program cannot work with invalid information. \n"
			+ "The program is stoping.";
	public static final String IMPORT_NR_PRODUCTS_MESSAGE = "There are %d products available to import \nHow many products do you want to import?\n";

	public static final String WRONG_SIZE_EXIT_PROGRAM_MESSAGE = "The number of products to import is invalid. \nThe program is stoping";

	public static final String WRONG_LANGUAGE_TYPE_MESSAGE = "Wrong langauge type";
	
	public static final String PRODUCTS_NOT_IMPORTED_MESSAGE = "Not all products were imported, please check the logs to know why.";
	
	private static final String WRONG_NR_MESSAGE = "The input: %s is not a number\n";

	// Successful Messages
	public static final String WELCOME_ICECAT_IMPORTER = "Welcom to ICECAT importer!";

	public static final String ENTER_ICECAT_CREDENTIALS = "Please enter your ICECAT credentials:";

	public static final String SUCCESSFUL_CONNECTION = "Connection to %s was successful!";

	public static final String NR_PRODUCTS_IMPORT_MESSAGE = "%d products will be imported...";

	public static final String GOODBYE_MESSAGE = "%d products were parsed and saved in the database! \nGoodbye!";

	public static final String WAIT_MESSAGE = "Please wait a moment...";
	
	public static final String BIG_IMPORT_MESSAGE = "A big ammount of products will be imported...\nGo and take a coup of coffee that this operation will take some minutes...";

	
	
	public static void productsNotIncludedMessage() {
		System.out.println(PRODUCTS_NOT_IMPORTED_MESSAGE);
		
	}

	public static void pleaseWait() {
		System.out.println(WAIT_MESSAGE);
	}

	public static void goodbyeMessage(int nrProducts) {
		System.out.println(String.format(GOODBYE_MESSAGE, nrProducts));
		
	}

	public static void successfullyConnected(String where) {
		System.out.println(String.format(SUCCESSFUL_CONNECTION, where));
	}

	public static void welcomeMessage() {
		System.out.println(WELCOME_ICECAT_IMPORTER);
		System.out.println(ENTER_ICECAT_CREDENTIALS);
	}

	public static void wrongNumberMessage(String nr) {
		System.out.format(WRONG_NR_MESSAGE, nr);
		
	}


}
