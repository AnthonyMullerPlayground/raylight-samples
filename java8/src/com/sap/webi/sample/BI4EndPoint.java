package com.sap.webi.sample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.provider.json.JSONProvider;
import org.codehaus.jettison.json.JSONObject;

import com.sap.webi.sample.model.About;
import com.sap.webi.sample.model.Document;
import com.sap.webi.sample.model.Documents;

public class BI4EndPoint {

	private static final String X_SAP_LOGON_TOKEN = "X-SAP-LogonToken";

	
	private final WebTarget target;
	private String logonToken;
	
	
	public BI4EndPoint(final String url) {
		// Init JAX-RS Client
		Client client = ClientBuilder.newClient();
		client.register(JSONProvider.class);
		target = client.target(url);
	}
	
	public boolean logon(String userName, String password) throws Exception {
		Response response = buildRequest("logon/long").get();
		String content = response.readEntity(String.class);
		
		JSONObject credentials = new JSONObject(content);
		credentials.put("userName", userName);
		credentials.put("password", password);
	
		response = buildRequest("logon/long").post(Entity.entity(credentials.toString(), MediaType.APPLICATION_JSON));
		logonToken = response.getHeaderString(X_SAP_LOGON_TOKEN);
		
		return logonToken != null && !logonToken.isEmpty();
	}

	public boolean logoff() {		
		Response response = buildRequest("logoff").post(null);		
		return response.getStatus() == 200;
	}

	public About about() {
		Response response = buildRequest("raylight/v1/about").get();		
		return response.readEntity(About.class);
	}

	public List<Document> documents() {
		return documents(null);
	}
	
	public List<Document> documents(Integer limit, Integer offset) {
		final Map<String, Object> options = new HashMap<>();
		options.put("limit", limit);
		options.put("offset", offset);
		
		return documents(options);
	}
	
	protected List<Document> documents(Map<String, Object> options) {		
		Response response = buildRequest("raylight/v1/documents", options).get();		
		Documents root = response.readEntity(Documents.class);		
		return root.documentList;
	}
	
	private Invocation.Builder buildRequest(String request) {
		WebTarget requestTarget = this.target.path(request);
		Invocation.Builder builder = requestTarget.request().accept(MediaType.APPLICATION_JSON);
		if(logonToken != null) {
			builder = builder.header(X_SAP_LOGON_TOKEN, logonToken);			
		}
		return builder;
	}
	
	private Invocation.Builder buildRequest(String request, Map<String, Object> properties) {
		WebTarget requestTarget = this.target.path(request);
		for (String propertyName : properties.keySet()) {
			Object propertyvalue = properties.get(propertyName);
			if(propertyvalue != null) {
				requestTarget = requestTarget.queryParam(propertyName, propertyvalue);	
			}
		}
		Invocation.Builder builder = requestTarget.request().accept(MediaType.APPLICATION_JSON);
		if(logonToken != null) {
			builder = builder.header(X_SAP_LOGON_TOKEN, logonToken);			
		}
		return builder;
	}	
}