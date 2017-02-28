package com.christian.icecat.utility;

public class JavaVersionChecker {

	public static boolean isJava8OrGreater() {
		String javaVers = System.getProperty("java.version");
		if (javaVers != null) {
			String[] nrs = javaVers.split("\\.");
			// the second number is the version
			try {
				return nrs.length > 2 && Integer.parseInt(nrs[1]) >= 8;
			} catch (NumberFormatException e) {
				// was not possible to parse the java.version property
				return false;
			}
		}
		return false;
	}

}
