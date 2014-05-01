package com.christian.icecat.parser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.christian.icecat.connector.AbstractIcecatConnector;
import com.christian.icecat.connector.IcecatFileConnector;
import com.christian.icecat.connector.IcecatURLConnector;
import com.christian.icecat.model.product.ICECATInterface;

/**
 * Class that will parse the information 
 * of a specific Icecat Product.
 *  
 * @author christian
 *
 */
public class IcecatProductParser extends AbstractIcecatParser<ICECATInterface> {

	public IcecatProductParser(AbstractIcecatConnector connector) {
		super(connector);
	}

	@Override
	public JAXBContext createJAXBContext() throws JAXBException {
		return JAXBContext.newInstance(ICECATInterface.class);
	}
	
	public void setProductId(long productId){
		AbstractIcecatConnector connector = getConnector();
		
		if(connector instanceof IcecatURLConnector){
			IcecatURLConnector iceConn = (IcecatURLConnector)connector;
			String tempUrl = iceConn.getUrl();
			String newUrl = String.format("%s%d.xml", tempUrl, productId);
			iceConn.setUrl(newUrl);
		}
	}
	
	public IcecatProductParser cloneProductParser(){
		AbstractIcecatConnector connector = this.getConnector();
		AbstractIcecatConnector newConnector;
		if(connector instanceof IcecatFileConnector){
			newConnector = new IcecatFileConnector((IcecatFileConnector) connector);
		}else if(connector instanceof IcecatURLConnector) {
			newConnector = new IcecatURLConnector((IcecatURLConnector) connector);
		}else{
			throw new ClassCastException();
		}
		
		return new IcecatProductParser(newConnector);
	}

}
