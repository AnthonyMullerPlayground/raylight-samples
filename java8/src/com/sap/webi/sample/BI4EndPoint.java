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
import com.sap.webi.sample.model.Dataprovider;
import com.sap.webi.sample.model.Dataproviders;
import com.sap.webi.sample.model.Document;
import com.sap.webi.sample.model.Documents;
import com.sap.webi.sample.model.InfoObject;
import com.sap.webi.sample.model.Report;
import com.sap.webi.sample.model.Reports;

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

	public InfoObject getInfoObject(String cuid) throws Exception {
		Response response = buildRequest("infostore/cuid_" + cuid).get();
		
		String content = response.readEntity(String.class);		
		JSONObject item = new JSONObject(content);
		
		InfoObject infoObject = new InfoObject();
		infoObject.setId(item.getInt("id"));
		infoObject.setCuid(item.getString("cuid"));
		infoObject.setName(item.getString("name"));
		infoObject.setDescription(item.getString("description"));
		
		return infoObject;
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
		final Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("limit", limit);
		queryParams.put("offset", offset);
		
		return documents(queryParams);
	}
	
	protected List<Document> documents(Map<String, Object> queryParams) {		
		Response response = buildRequest(new BuildRequestParameter("raylight/v1/documents", null, queryParams)).get();		
		Documents root = response.readEntity(Documents.class);		
		return root.documentList;
	}
	
	public Document document(Integer documentId) {
		final Map<String, Object> pathParams = new HashMap<>();
		pathParams.put("documentId", documentId);
		
		Response response = buildRequest(new BuildRequestParameter("raylight/v1/documents/{documentId}", pathParams, null)).get();		
		Document document = response.readEntity(Document.class);		
		return  document;
	}	

	public List<Report> reports(Integer documentId) {
		final Map<String, Object> pathParams = new HashMap<>();
		pathParams.put("documentId", documentId);
		
		Response response = buildRequest(new BuildRequestParameter("raylight/v1/documents/{documentId}/reports", pathParams, null)).get();
		
		Reports root = response.readEntity(Reports.class);
		return  root.reportList;
	}
	
	public List<Dataprovider> dataproviders(Integer documentId) {
		final Map<String, Object> pathParams = new HashMap<>();
		pathParams.put("documentId", documentId);
		
		Response response = buildRequest(new BuildRequestParameter("raylight/v1/documents/{documentId}/dataproviders", pathParams, null)).get();
		
		Dataproviders root = response.readEntity(Dataproviders.class);

		return  root.dataproviderList;
	}
	
	public Dataprovider dataprovider(Integer documentId, String dataproviderId) {
		final Map<String, Object> pathParams = new HashMap<>();
		pathParams.put("documentId", documentId);
		pathParams.put("dataproviderId", dataproviderId);
		
		Response response = buildRequest(new BuildRequestParameter("raylight/v1/documents/{documentId}/dataproviders/{dataproviderId}", pathParams, null)).get();
		
		Dataprovider dataprovider = response.readEntity(Dataprovider.class);

		return  dataprovider;
	}
	
	public String flowXml(Integer documentId, String dataproviderId, Integer flowId) {
		return flow(documentId, dataproviderId, flowId, MediaType.TEXT_XML_TYPE);
	}
	
	public String flowTxt(Integer documentId, String dataproviderId, Integer flowId) {
		return flow(documentId, dataproviderId, flowId, MediaType.TEXT_PLAIN_TYPE);
	}
	
	private String flow(Integer documentId, String dataproviderId, Integer flowId, MediaType flowMediaType) {
		final Map<String, Object> pathParams = new HashMap<>();
		pathParams.put("documentId", documentId);
		pathParams.put("dataproviderId", dataproviderId);
		pathParams.put("flowId", flowId);
		
		BuildRequestParameter requestParameter = new BuildRequestParameter(
				"raylight/v1/documents/{documentId}/dataproviders/{dataproviderId}/flows/{flowId}", pathParams, null
		);
		requestParameter.setAccept(flowMediaType);
		Response response = buildRequest(requestParameter).get();
	
		return response.readEntity(String.class);
	}
	

	
	private class BuildRequestParameter
	{
		private String request;
		private Map<String, Object> pathParams;
		private Map<String, Object> queryParams;
		private MediaType accept = MediaType.APPLICATION_JSON_TYPE;
		
		public BuildRequestParameter(String request, Map<String, Object> pathParams, Map<String, Object> queryParams) {
			this.request = request;
			this.pathParams = pathParams;
			this.queryParams = queryParams;
		}

		public MediaType getAccept() {
			return accept;
		}
		
		public void setAccept(MediaType acceptType) {
			this.accept = acceptType;
		}

		public String getRequest() {
			return request;
		}

		public Map<String, Object> getPathParams() {
			return pathParams;
		}

		public Map<String, Object> getQueryParams() {
			return queryParams;
		}
	}
	
	private Invocation.Builder buildRequest(String request) {
		return buildRequest(new BuildRequestParameter(request, null, null));
	}
	
	private Invocation.Builder buildRequest(BuildRequestParameter parameterObject) {
		WebTarget requestTarget = this.target.path(parameterObject.getRequest());
		
		if(parameterObject.getPathParams() != null) {
			for (String parameterName : parameterObject.getPathParams().keySet()) {
				Object parameterValue = parameterObject.getPathParams().get(parameterName);
				if(parameterValue != null) { 
					requestTarget = requestTarget.resolveTemplate(parameterName, parameterValue);	
				}
			}
		}
		
		if(parameterObject.getQueryParams() != null) {
			for (String parameterName : parameterObject.getQueryParams().keySet()) {
				Object parameterValue = parameterObject.getQueryParams().get(parameterName);
				if(parameterValue != null) {
					requestTarget = requestTarget.queryParam(parameterName, parameterValue);	
				}
			}	
		}
		
		Invocation.Builder builder = requestTarget.request().accept(parameterObject.getAccept());
		if(logonToken != null) {
			builder = builder.header(X_SAP_LOGON_TOKEN, logonToken);			
		}
		return builder;
	}
}