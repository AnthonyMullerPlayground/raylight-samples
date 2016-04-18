package com.sap.webi.sample;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sap.webi.sample.model.About;

/**
 * Test Web Intelligence RESTful API (Raylight) using JAX-RS 2.0 Client API (Apache CXF Implementation).
 * 
 * @author Anthony MÜLLER
 */
public class Main {

	private final static String BI4_API_ROOT = "http://<server>:6405/biprws";
	
	public static void main(String[] args) {
		
		// Init Client
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BI4_API_ROOT);
		
		// Perform '/about' call
		target = target.path("raylight/v1/about");		
		Invocation.Builder builder = target.request().accept(MediaType.APPLICATION_XML);
		Response response = builder.get();
		
		About about = response.readEntity(About.class);
		System.out.println(about.getTitle());
	}
}
