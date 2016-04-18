package com.sap.webi.sample;

import java.util.List;

import com.sap.webi.sample.model.About;
import com.sap.webi.sample.model.Document;
import com.sap.webi.sample.model.InfoObject;
import com.sap.webi.sample.model.Report;

/**
 * Test Web Intelligence RESTful API (Raylight) using JAX-RS 2.0 Client API (Apache CXF Implementation).
 * 
 * @author Anthony MÜLLER
 */
public class Main {

	private final static String BI4_API_ROOT_URL = "http://dewdftv01222.dhcp.pgdev.sap.corp:6405/biprws";
	
	private final static String FORMATTING_SAMPLE_CUID = "AQtkbbSqN4NOj3ydf.Sw1lY";
	
	public static void main(String[] args) throws Exception {
		
		final BI4EndPoint bi4 = new BI4EndPoint(BI4_API_ROOT_URL);

		// Perform '/about' call
		About about = bi4.about();
		System.out.println(about.getTitle() + " (" + about.getVersion() + ")\n");
		
		// Logon
		bi4.logon("Administrator", "Password1");
		
		// Listing of some Webi documents		
		List<Document> webiDocuments = bi4.documents(3 /* limit */, 0 /* offset */);
		for (Document webiDocument : webiDocuments) {
			System.out.println(webiDocument.getId() + "\t" + webiDocument.getCuid() + "\t" + webiDocument.getName());
		}
		
		// Get Webi document details
		InfoObject documentObj = bi4.getInfoObject(FORMATTING_SAMPLE_CUID);
		Document document = bi4.document(documentObj.getId());
		
		// Listing of reports
		List<Report> reports = bi4.reports(document.getId());
		for (Report report : reports) {
			System.out.println(report.getId() + "\t" + report.getName() + "\t" + report.getReference());	
		}
		
		// Logoff
		bi4.logoff();		
	}
}
