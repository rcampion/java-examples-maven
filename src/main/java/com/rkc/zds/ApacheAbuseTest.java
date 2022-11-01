package com.rkc.zds;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClients;

public class ApacheAbuseTest {

	public static void main(String[] args) {
		URIBuilder builder = new URIBuilder();
		builder.setScheme("https").setHost("api.abuseipdb.com").setPath("/api/v2/check")
			.setParameter("ipAddress", "118.25.6.39")
		    .setParameter("maxAgeInDays", "90");
		URI uri = null;
		try {
			uri = builder.build();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//HttpGet request = new HttpGet(uri);
//		0bcc85815f4b99bdc39c7180a2b1b53b5496867b18ecb4542320a579332ec93be1b5f35705bdd593
		//request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		//request.setHeader("Key", "0bcc85815f4b99bdc39c7180a2b1b53b5496867b18ecb4542320a579332ec93be1b5f35705bdd593");		
		//System.out.println(request.getURI());
		
		System.out.println(uri);
		
		HttpClient client = HttpClients.custom().build();
		HttpUriRequest request = RequestBuilder.get()
		  .setUri(uri)
		  .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
		  .setHeader("Key", "0bcc85815f4b99bdc39c7180a2b1b53b5496867b18ecb4542320a579332ec93be1b5f35705bdd593")		
		  .build();
		HttpResponse response = null;
		try {
			response = client.execute(request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String responseString = null;
		try {
			responseString = new BasicResponseHandler().handleResponse(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(responseString);

	}

}
