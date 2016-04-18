package com.sap.webi.sample;

import java.util.List;

import com.sap.webi.sample.model.About;
import com.sap.webi.sample.model.Document;

/**
 * Test Web Intelligence RESTful API (Raylight) using JAX-RS 2.0 Client API (Apache CXF Implementation).
 * 
 * @author Anthony MÜLLER
 */
public class Main {

	private final static String BI4_API_ROOT_URL = "http://dewdftv01222.dhcp.pgdev.sap.corp:6405/biprws";
	
	public static void main(String[] args) throws Exception {
		
		final BI4EndPoint bi4 = new BI4EndPoint(BI4_API_ROOT_URL);

		// Perform '/about' call
		About about = bi4.about();
		System.out.println(about.getTitle());
		System.out.println(about.getVersion());
		
		// Logon
		bi4.logon("Administrator", "Password1");
		
		// Documents
		List<Document> documents = bi4.documents();
		for (Document document : documents) {
			System.out.println(document.getId() + "\t" + document.getCuid() + "\t" + document.getName());
		}
		
		// Logoff
		bi4.logoff();		
	}
}
