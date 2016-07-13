package com.sap.webi.sample;

import java.util.List;

import com.sap.webi.sample.model.About;
import com.sap.webi.sample.model.Dataprovider;
import com.sap.webi.sample.model.Document;
import com.sap.webi.sample.model.InfoObject;
import com.sap.webi.sample.model.Report;

/**
 * Test Web Intelligence RESTful API (Raylight) using JAX-RS 2.0 Client API (Apache CXF Implementation).
 * 
 * @author Anthony MÜLLER
 */
public class Main {

	private final static String BI4_API_ROOT_URL = "http://localhost:6405/biprws";
	
	private final static String FORMATTING_SAMPLE_CUID = "AQtkbbSqN4NOj3ydf.Sw1lY";
	
	private final static String USER = "Administrator";
	private final static String PASSWORD = "Password1";
	
	public static void main(String[] args) throws Exception {
		
		final BI4EndPoint bi4 = new BI4EndPoint(BI4_API_ROOT_URL);

		// Perform '/about' call
		System.out.println("== about ==");
		About about = bi4.about();
		System.out.println(about.getTitle() + " (" + about.getVersion() + ")\n");
		
		// Logon
		System.out.println("== logon ==");
		bi4.logon(USER, PASSWORD);
		System.out.println();
		
		// Listing of some Webi documents		
		System.out.println("== list documents ==");
		List<Document> webiDocuments = bi4.documents(3 /* limit */, 0 /* offset */);
		for (Document webiDocument : webiDocuments) {
			System.out.println(webiDocument.getId() + "\t" + webiDocument.getCuid() + "\t" + webiDocument.getName());
		}
		System.out.println();
		
		// Get Webi document details
		System.out.println("== get ID from CUID ==");
		InfoObject documentObj = bi4.getInfoObject(FORMATTING_SAMPLE_CUID);
		Document document = bi4.document(documentObj.getId());
		System.out.println("CUID = " + FORMATTING_SAMPLE_CUID + " --> ID = " + document.getId());
		System.out.println();
		
		// Listing of reports
		System.out.println("== reports of '" + document.getName() + "' ==");
		List<Report> reports = bi4.reports(document.getId());
		for (Report report : reports) {
			System.out.println(report.getId() + "\t" + report.getName() + "\t" + report.getReference());	
		}
		System.out.println();
		
		// Listing of dataproviders
		System.out.println("== dataproviders of '" + document.getName() + "' ==");
		List<Dataprovider> dataproviders = bi4.dataproviders(document.getId());
		for (Dataprovider dataprovider : dataproviders) {
			System.out.println(dataprovider.getId() + "\t" + dataprovider.getName());	
		}
		System.out.println();
		
		// Details of dataprovider
		if(!dataproviders.isEmpty()) {
			Dataprovider dataprovider = dataproviders.get(0);
			System.out.println("== dataprovider '" + dataprovider.getName() + "' details ==");
			dataprovider = bi4.dataprovider(document.getId(), dataprovider.getId());
			System.out.println(dataprovider.getId() + "\t" + dataprovider.getDataSourceId() + "\t" + dataprovider.getDataSourceType() + "\t" + dataprovider.getUpdated() + "\t" + dataprovider.getFlowCount());
			if(dataprovider.getFlowCount() > 0) {
				System.out.println(bi4.flowTxt(document.getId(), dataprovider.getId(), 0));	
			}
			System.out.println();
		}
		
		// Logoff
		System.out.println("== logoff ==");
		bi4.logoff();		
	}
}
