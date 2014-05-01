package com.christian.icecat.utility;

import java.math.BigInteger;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.christian.icecat.exceptions.IcecatNullIdException;
import com.christian.icecat.model.transformer.Transformable;

public class TransformerUtlity {
	private static final Logger LOG = LoggerFactory.getLogger(TransformerUtlity.class); 
	
	public static <N,O> Set<N> loop(List<O> originalList, Transformable<N,O> transformable) throws IcecatNullIdException{
		Set<N> simpleSet = new HashSet<>();
		for(O originalObject : originalList){
			N simpleObject = transformable.transform(originalObject);
			simpleSet.add(simpleObject);
		}
		return simpleSet;
	}
	
	public static Date parseDate(String date){
		if(date != null && !date.isEmpty()){
			SimpleDateFormat formatter;
			if(date.contains("-")){
				formatter = new SimpleDateFormat("yyyy-MM-dd");
			} else{
				formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			}
			
			try {
				return new Date(formatter.parse(date).getTime());
			} catch (ParseException e) {
				LOG.warn("The string: {}, could ot be pasrsed to a Date object", date);
				return null;
			}
		}
		return null;
	}
	
	public static long idChecker(BigInteger id) throws IcecatNullIdException{
		if(id != null){
			return id.longValue();
		} else {
			throw new IcecatNullIdException();
		}
	}
	
	public static String bigStringVerifier(String bigString){
		if(bigString!= null && bigString.length() > 255){
			LOG.warn("The string: {}, is to long to store in database", bigString);
			bigString = bigString.substring(0, 255);
		}
		
		return bigString;
	}

}
