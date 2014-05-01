package com.christian.icecat.view;

import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.christian.icecat.connectable.Connectable;
import com.christian.icecat.connector.AbstractIcecatConnector;
import com.christian.icecat.connector.IcecatURLConnector;
import com.christian.icecat.controller.DatabaseController;
import com.christian.icecat.controller.IcecatController;
import com.christian.icecat.controller.IcecatControllerInterface;
import com.christian.icecat.controller.ParsingController;
import com.christian.icecat.dao.HibernateConnector;
import com.christian.icecat.importer.IcecatProductsImporter;
import com.christian.icecat.model.entities.SimpleProduct;

/**
 * Class that will render all the options that can be executed by the ICECAT
 * parser in the Command Line interface.
 * 
 * @author christian
 *
 */
public class CommandLineView {
	private final Logger LOG = LoggerFactory.getLogger(CommandLineView.class);
	
	private static final int TIMES = 3; // This is the number of times that the
										// console will ask for an input or the number
										// of times that a connection will be re-tried.
	
	//Icecat 
	private static final String ICECAT_USER = "Icecat user";
	private static final String ICECAT_PASSWORD = "Icecat password";
	private static final String PRODUCTS_NR = "Products number";
	private static final String ICECAT_LANG = "Icecat language";
	private static final String ICECAT_ROOT_URL = "https://data.icecat.biz/export/freexml/%s/";

	
	//Database
	private static final String DB_USER = "Database user";
	private static final String DB_PASSWORD = "Database password";
	private static final String DB_HOST = "Database host";
	private static final String DB_PORT = "Database port";
	private static final String DB_NAME = "Database name";
	
	private Scanner scanner;

	public CommandLineView() {
		scanner = new Scanner(System.in);
	}

	public void run() {
		Messenger.welcomeMessage();
		//Connect to ICECAT
		AbstractIcecatConnector icecatConnector = connectToIcecat();
		Messenger.successfullyConnected("Icecat");
		
		//Connect to DB
		HibernateConnector databaseConnector = connectToDatabase();
		Messenger.successfullyConnected("Database");

		ParsingController parsingController = new ParsingController(icecatConnector);
		DatabaseController databaseController = new DatabaseController(databaseConnector);
		IcecatControllerInterface icecatController = new IcecatController(parsingController, databaseController);

		IcecatProductsImporter importer = new IcecatProductsImporter(icecatController);
		
		//How many products will be imported?
		numberOfProductsToImport(importer);
		
		// create thread pool!!!
		int nrThreads = Runtime.getRuntime().availableProcessors();
		ForkJoinPool pool = new ForkJoinPool(nrThreads);
		pool.invoke(importer);
		
		//once all the products are parsed lets persist them
		int nrProductsParsed = importer.getProductsParsed().size();
		LOG.info("Products parsed: {}", nrProductsParsed);
		for(SimpleProduct product : importer.getProductsParsed()){
			LOG.info("Saving product {} to Database", product.getId());
			icecatController.saveProduct(product);
		}
		
		if(nrProductsParsed != importer.getResizeDimension()){
			Messenger.productsNotIncludedMessage();
		}
		
		Messenger.goodbyeMessage(importer.getProductsParsed().size());
	}
	
	

	
	private void numberOfProductsToImport(IcecatProductsImporter importer) {
		int newSize = 0;
		boolean validSize = false;
		int numberOfProducts = importer.getProductsIdMap().size();
		System.out.format(Messenger.IMPORT_NR_PRODUCTS_MESSAGE, numberOfProducts);
		for (int i = 0; i <= TIMES; i++) {
			String nrStr = readInputNumber(PRODUCTS_NR);
			try{
				newSize = Integer.parseInt(nrStr);
				
				//is a valid size?
				if(newSize > 0 && newSize <= numberOfProducts){
					validSize = true;
					break;
				} 
				
				System.out.println(Messenger.WRONG_INFO_MESSAGE);
				
			} catch(NumberFormatException e){
				System.out.println(Messenger.WRONG_INFO_MESSAGE);
			}
		}
		
		if(!validSize){
			System.out.println(Messenger.WRONG_SIZE_EXIT_PROGRAM_MESSAGE);
			System.exit(1);
		}
		
		importer.setResizeDimension(newSize);
		
		System.out.println(String.format(Messenger.NR_PRODUCTS_IMPORT_MESSAGE, newSize));
		if(newSize > 100){
			System.out.println(Messenger.BIG_IMPORT_MESSAGE);
		}
	}
	
	private HibernateConnector connectToDatabase() {
		boolean connected = false;
		HibernateConnector databaseConnector = null;
		for (int i = 0; i <= TIMES; i++) {
			// get input parameters for DB
			String dbUser = readInput(DB_USER);
			String dbPwd = readInput(DB_PASSWORD);
			String dbHost = readInput(DB_HOST);
			String dbPort = readInputNumber(DB_PORT);
			String dbName = readInput(DB_NAME);

			databaseConnector = new HibernateConnector(dbUser, dbPwd, dbHost, dbPort, dbName);
			
			// test connection
			connected = testConnection(databaseConnector);
			if(connected){
				break;
			}
		}
		
		if(!connected){
			System.out.format(Messenger.FORMATTED_EXIT_PROGRAM_MESSAGE, "Database");
			System.exit(1);
		}
		
		return databaseConnector;
	}
	
	private String readInputNumber(String variableName){
		String val = "";
		for (int i = 0; i <= TIMES; i++) {
			String tmpVal = readInput(variableName);
			try{
				Integer.parseInt(tmpVal);
				val = tmpVal;
				break;
			} catch(NumberFormatException e){
				Messenger.wrongNumberMessage(tmpVal);
				continue;
			}
		}
		
		emptyInputChecker(val);
		
		return val;
	}
	
	private boolean testConnection(Connectable connector){
		//Try with resources is cool!
		try (Connectable connectable = connector) {
			Messenger.pleaseWait();
			connectable.open();
			connectable.connect();
			return true;
		} catch (Exception e) {
			LOG.error("Error connecting to Database: {}", e.getMessage());
			System.out.println(Messenger.WRONG_INFO_MESSAGE);
			return false;
		}
	}

	private AbstractIcecatConnector connectToIcecat() {
		boolean connected = false;
		AbstractIcecatConnector connector = null;
		for (int i = 0; i <= TIMES; i++) {
			// get input parameters for Icecat
			String icecatUser = readInput(ICECAT_USER);
			String icecatPassword = readInput(ICECAT_PASSWORD);
			String icecatUrl = URLValidator();
			
			connector = new IcecatURLConnector(icecatUser, icecatPassword, icecatUrl);
			
			// Test connection
			connected = testConnection(connector);
			if(connected){
				break;
			}
		}
		
		if(!connected){
			System.out.format(Messenger.FORMATTED_EXIT_PROGRAM_MESSAGE, "Icecat");
			System.exit(1);
		}
		
		return connector;
	}
	
	private String URLValidator(){
		System.out.println(Messenger.ENTER_LANGUAGE_MESSAGE);
		for (int i = 0; i <= TIMES; i++) {
			String language = readInput(ICECAT_LANG);
			if(language.equalsIgnoreCase("en") || 
					language.equalsIgnoreCase("es") || 
					language.equalsIgnoreCase("nl") || 
					language.equalsIgnoreCase("ru") || 
					language.equalsIgnoreCase("de")){
				return String.format(ICECAT_ROOT_URL, language.toUpperCase());
			}
			System.out.println(Messenger.WRONG_LANGUAGE_TYPE_MESSAGE);
		}
		
		System.out.println(Messenger.EXIT_PROGRAM_MESSAGE);
		System.exit(1);
		return null;
	}

	private String readInput(String variableName) {
		String variable = "";
		for (int i = 0; i <= TIMES; i++) {
			System.out.format("%s: ", variableName);
			variable = scanner.nextLine();
			variable = variable.trim();
			if (!variable.isEmpty()) {
				break;
			}
			System.out.format(Messenger.ENTER_INFO_MESSAGE, variableName);
		}
		
		emptyInputChecker(variable);

		return variable;
	}
	
	private void emptyInputChecker(String input){
		// If no info was provided, finish the program
		if (input.isEmpty()) {
			System.out.println(Messenger.EXIT_PROGRAM_MESSAGE);
			System.exit(1);
		}		
	}

}
