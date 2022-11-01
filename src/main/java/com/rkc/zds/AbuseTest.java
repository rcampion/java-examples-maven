package com.rkc.zds;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpHeaders;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;

public class AbuseTest {

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
		HttpGet request = new HttpGet(uri);
//		0bcc85815f4b99bdc39c7180a2b1b53b5496867b18ecb4542320a579332ec93be1b5f35705bdd593
		request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		request.setHeader("Key", "0bcc85815f4b99bdc39c7180a2b1b53b5496867b18ecb4542320a579332ec93be1b5f35705bdd593");		
		System.out.println(request.getURI());
		
		HttpClient client = new DefaultHttpClient();

	}

}
