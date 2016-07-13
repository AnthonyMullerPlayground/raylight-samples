package com.sap.webi.sample;

import com.sap.webi.sample.model.InfoObject;

/**
 * Test Web Intelligence RESTful API (Raylight) using CXF Web Client API (Apache CXF Specific).
 * 
 * @author Anthony MÜLLER
 */
public class GetData {

	private final static String BI4_API_ROOT_URL = "http://localhost:6405/biprws";

	private final static String FORMATTING_SAMPLE_CUID = "AQtkbbSqN4NOj3ydf.Sw1lY";

	private final static String USER = "Administrator";
	private final static String PASSWORD = "Password1";

	public static void main(String[] args) throws Exception {

		final BI4EndPoint bi4 = new BI4EndPoint(BI4_API_ROOT_URL);
		bi4.logon(USER, PASSWORD);
		
		// Get data from a dataprovider and print it out
		String dpData = getDataFromDataProvider(
			bi4.getLogonToken(), 		// a valid logon token
			FORMATTING_SAMPLE_CUID, 	// a Webi document CUID
			"DP1", 						// a dataprovider id
			0,							// a flow id
			"xml"						// an output format (txt or xml). default is txt.
		);
		System.out.println(dpData);
		
		// Get data from a report part and print it out
		String rpData = getDataFromReportPart(
			bi4.getLogonToken(), 		// a valid logon token
			FORMATTING_SAMPLE_CUID, 	// a Webi document CUID
			3, 							// a report id
			13,							// a element id
			"xml"						// an output format (json or xml). default is json.
		);
		System.out.println(rpData);
		
		bi4.logoff();	
	}
		
	/**
	 * Get data from a data provider.
	 * 
	 * @param logonToken
	 * @param documentCuid
	 * @param dpId
	 * @param flowId
	 * @param format
	 * @return
	 * @throws Exception
	 */
	public static String getDataFromDataProvider(String logonToken, String documentCuid, String dpId, int flowId, String format) throws Exception {
		
		final BI4EndPoint bi4 = new BI4EndPoint(BI4_API_ROOT_URL);
		bi4.setLogonToken(logonToken);

		// Get Webi document SI_ID from CUID
		InfoObject document = bi4.getInfoObject(documentCuid);

		// Data from dataprovider
		String data;
		if("xml".equalsIgnoreCase(format)) {
			data = bi4.flowXml(document.getId(), dpId, flowId);
		} else {
			data = bi4.flowTxt(document.getId(), dpId, flowId);
		}

		return data;
	}
	
	/**
	 * Get data from a report part.
	 * 
	 * @param logonToken
	 * @param documentCuid
	 * @param reportId
	 * @param elementId
	 * @param format
	 * @return
	 * @throws Exception
	 */
	public static String getDataFromReportPart(String logonToken, String documentCuid, int reportId, int elementId, String format) throws Exception {
		
		final BI4EndPoint bi4 = new BI4EndPoint(BI4_API_ROOT_URL);
		bi4.setLogonToken(logonToken);
		
		// Get Webi document SI_ID from CUID
		InfoObject document = bi4.getInfoObject(documentCuid);
		
		// Data from report part
		String data;
		if("xml".equalsIgnoreCase(format)) {
			data = bi4.datasetXml(document.getId(), reportId, elementId);
		} else {
			data = bi4.datasetJson(document.getId(), reportId, elementId);
		}

		return data;
	}
}
