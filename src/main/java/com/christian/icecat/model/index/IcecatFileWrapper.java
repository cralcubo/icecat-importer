package com.christian.icecat.model.index;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Class that has all the icecat-files
 * that contain information to retrieve
 * products information.
 * 
 * @author christian
 *
 */
@XmlRootElement(name = "ICECAT-interface")
public class IcecatFileWrapper {
	
	private List<IcecatFile> files;
	
	@XmlElementWrapper(name = "files.index")
	@XmlElement(name = "file")
	public List<IcecatFile> getFiles() {
		return files;
	}

	public void setFiles(List<IcecatFile> files) {
		this.files = files;
	}

}
