package com.christian.icecat.connector;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Class that will open a stream to
 * import icecat data from an xml file
 * stored locally.
 * 
 * @author christian
 *
 */
public class IcecatFileConnector extends AbstractIcecatConnector{
	private Path path;
	
	public IcecatFileConnector(IcecatFileConnector connector){
		this(connector.getPath());
	}

	public IcecatFileConnector(Path path) {
		this.path = path;
	}

	public void open() throws IOException {
		connectionStream = Files.newInputStream(path);
	}
	
	public Path getPath() {
		return path;
	}

}
