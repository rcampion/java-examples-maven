package com.rkc.zds.system.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.net.SocketFactory;
import javax.servlet.http.HttpServletRequest;

//import io.github.minorg.whoisclient.WhoisClient;

//import org.apache.commons.net.whois.WhoisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
//import org.thryft.native_.InternetDomainName;

//import com.google.common.base.Optional;
//import com.google.common.collect.ImmutableList;
//import com.rkc.zds.dashboard.pcm.entity.ApiKeyEntity;
//import com.rkc.zds.dashboard.pcm.repository.ApiKeyRepository;
//import com.rkc.zds.dashboard.pcm.service.ApiKeyService;

public class SystemUtils {

	private static final String[] IP_HEADER_CANDIDATES = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
			"HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP",
			"HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR" };

	private static final Logger LOGGER = LoggerFactory.getLogger(SystemUtils.class);

	// static variable single_instance of type Singleton
	private static SystemUtils instance = null;

	// @Autowired
	// ApiKeyService apiKeyService;

	public static final String IANA_WHOIS_SERVER = "whois.iana.org";
	public static final int WHOIS_PORT = 43;

	// private constructor restricted to this class itself
	private SystemUtils() {
		// this.apiKeyService = apiKeyService;
	}

	// static method to create instance of Singleton class
	public static SystemUtils getInstance() {
		if (instance == null)
			instance = new SystemUtils();

		return instance;
	}

/*
	public void setWhoisInfo(String input, WhoisDTO whois) throws Exception {

		InetAddress node;

		String ip;

		// get the bytes of the address
		try {
			node = InetAddress.getByName(input);

		} catch (UnknownHostException e) {
			return;
		}
		if (isHostname(input)) {
			ip = node.getHostAddress();
		} else {
			// this is an IP address
			ip = input;
		}

		if (ip.equalsIgnoreCase("192.168.1.100")) {
			ip = "174.79.160.56";
		}

		// System.out.println("IP Address: " + ip);
		LOGGER.debug("IP Address: " + ip);

		String host = ip;
		String parsedString = null;
		String parsedJSON = null;
		String parsedXML = null;
		GeoIP geo = null;

		// get the address of the authoritative Whois server from IANA
		WhoisClient whoisClient = new WhoisClient();
		whoisClient.connect(IANA_WHOIS_SERVER, WHOIS_PORT);
		String tmpStr = whoisClient.query(host);
		whoisClient.disconnect();
		int idx = tmpStr.indexOf("whois:");
		tmpStr = tmpStr.substring(idx + 6).trim();
		String actualServer = tmpStr.substring(0, tmpStr.indexOf("\n"));

		// System.out.println("Actual Server: "+actualServer);
		LOGGER.debug("Actual Server: " + actualServer);

		if (actualServer.contains("A WHOIS server")) {
			actualServer = IANA_WHOIS_SERVER;
		}
		// we need to special-case some TLDs
		String tld = host.substring(host.lastIndexOf(".") + 1).trim().toLowerCase();

		// suppress Japanese characters in output
		if ("jp".equals(tld))
			host += "/e";

		// get the actual Whois data
		whoisClient.connect(actualServer, WHOIS_PORT);
		// tmpStr = whoisClient.query("?");

		// The prefix "domain " solves the problem with spurious server names
		// (like for google.com, apple.com. yahoo.com, microsoft.com etc.)
		if ("com".equals(tld))
			tmpStr = whoisClient.query("domain " + host);
		else if ("de".equals(tld))
			// The syntax for .de is slightly different.
			tmpStr = whoisClient.query("-T dn " + host);
		else if ("dk".equals(tld))
			// show more information for .dk
			tmpStr = whoisClient.query("--show-handles " + host);
		else if (actualServer.equalsIgnoreCase("whois.arin.net")) {
			tmpStr = whoisClient.query("z + " + host);
			// whois -h whois.arin.net -- 'n + 8.8.8.8'
		} else
			tmpStr = whoisClient.query(host);

		whoisClient.disconnect();
		// printResults(actualServer, tmpStr);

		// if there is a more specific server, query that one too
		idx = tmpStr.toLowerCase().indexOf("whois server:");
		if (idx != -1) {
			tmpStr = tmpStr.substring(idx + 13).trim();
			actualServer = tmpStr.substring(0, tmpStr.indexOf("\n")).trim();
			whoisClient.connect(actualServer, WHOIS_PORT);
			tmpStr = whoisClient.query(host);
			whoisClient.disconnect();
			// printResults(actualServer, tmpStr);
		}
		// final SocketFactory socketFactory = n
		// ZdsWhoisClient zdsclient = new ZdsWhoisClient(final SocketFactory
		// socketFactory);
		// final InternetDomainName queriedName = InternetDomainName.from(tld);
		tmpStr = tmpStr.replaceAll("\r", "");

		try {

			parsedString = MyWhoisUtil.getInstance().parseWhois(tmpStr);

			parsedJSON = MyWhoisUtil.getInstance().parseWhoisToJSON(tmpStr);

			parsedXML = MyWhoisUtil.getInstance().parseWhoisToXML(tmpStr);

			geo = RawDBDemoGeoIPLocationService.getInstance().getLocation(ip);

		} catch (Exception e) {
			// ignore
			System.out.println(e);
		}

		whois.setWhois(tmpStr);
		whois.setParsedWhois(parsedString);
		whois.setParsedKeyValues(parsedString);
		whois.setParsedJSON(parsedJSON);
		whois.setParsedXML(parsedXML);
		whois.setGeoIP(geo);

	}
*/
	private static boolean isHostname(String host) {

		// is this a ipv6 address
		if (host.indexOf(":") != -1)
			return false;

		char[] ca = host.toCharArray();
		// if we see a character that is neither a digit nor a period
		// then host is probably a hostname
		for (int i = 0; i < ca.length; i++) {
			if (!Character.isDigit(ca[i])) {
				if (ca[i] != '.')
					return true;
			}
		}
		// Everything was either a digit or a period
		// so host looks like a ip4v address in dotted quad format

		return false;
	}// end ishostname

/*
	public void setLogWhoisInfo(HttpAccessLogEntity log, HttpAccessLogService logService) throws Exception {


		InetAddress node;

		String ip = log.getIp();

		if (ip.equalsIgnoreCase("192.168.1.100")) {
			ip = "174.79.160.56";
		}
		// System.out.println("IP Address: " + ip);
		LOGGER.debug("IP Address: " + ip);

		String host = ip;
		String parsedString = null;
		String parsedJSON = null;
		String parsedXML = null;
		GeoIP geo = null;

		// get the address of the authoritative Whois server from IANA
		WhoisClient whoisClient = new WhoisClient();
		whoisClient.connect(IANA_WHOIS_SERVER, WHOIS_PORT);
		String tmpStr = whoisClient.query(host);
		whoisClient.disconnect();
		int idx = tmpStr.indexOf("whois:");
		tmpStr = tmpStr.substring(idx + 6).trim();
		String actualServer = tmpStr.substring(0, tmpStr.indexOf("\n"));

		// System.out.println("Actual Server: "+actualServer);
		LOGGER.debug("Actual Server: " + actualServer);

		if (actualServer.contains("A WHOIS server")) {
			actualServer = IANA_WHOIS_SERVER;
		}
		// we need to special-case some TLDs
		String tld = host.substring(host.lastIndexOf(".") + 1).trim().toLowerCase();

		// suppress Japanese characters in output
		if ("jp".equals(tld))
			host += "/e";

		// get the actual Whois data
		whoisClient.connect(actualServer, WHOIS_PORT);
		// tmpStr = whoisClient.query("?");

		// The prefix "domain " solves the problem with spurious server names
		// (like for google.com, apple.com. yahoo.com, microsoft.com etc.)
		if ("com".equals(tld))
			tmpStr = whoisClient.query("domain " + host);
		else if ("de".equals(tld))
			// The syntax for .de is slightly different.
			tmpStr = whoisClient.query("-T dn " + host);
		else if ("dk".equals(tld))
			// show more information for .dk
			tmpStr = whoisClient.query("--show-handles " + host);
		else if (actualServer.equalsIgnoreCase("whois.arin.net")) {
			tmpStr = whoisClient.query("z + " + host);
			// whois -h whois.arin.net -- 'n + 8.8.8.8'
		} else
			tmpStr = whoisClient.query(host);

		whoisClient.disconnect();
		// printResults(actualServer, tmpStr);

		// if there is a more specific server, query that one too
		idx = tmpStr.toLowerCase().indexOf("whois server:");
		if (idx != -1) {
			tmpStr = tmpStr.substring(idx + 13).trim();
			actualServer = tmpStr.substring(0, tmpStr.indexOf("\n")).trim();
			whoisClient.connect(actualServer, WHOIS_PORT);
			tmpStr = whoisClient.query(host);
			whoisClient.disconnect();
			// printResults(actualServer, tmpStr);
		}
		// final SocketFactory socketFactory = n
		// ZdsWhoisClient zdsclient = new ZdsWhoisClient(final SocketFactory
		// socketFactory);
		// final InternetDomainName queriedName = InternetDomainName.from(tld);
		tmpStr = tmpStr.replaceAll("\r", "");

		try {

			parsedString = MyWhoisUtil.getInstance().parseWhois(tmpStr);

			parsedJSON = MyWhoisUtil.getInstance().parseWhoisToJSON(tmpStr);

			parsedXML = MyWhoisUtil.getInstance().parseWhoisToXML(tmpStr);

			geo = RawDBDemoGeoIPLocationService.getInstance().getLocation(ip);

		} catch (Exception e) {
			// ignore
			System.out.println(e);
		}
		log.setWhois(tmpStr);
		log.setParsedWhois(parsedString);
		log.setParsedKeyValues(parsedString);
		log.setParsedJSON(parsedJSON);
		log.setParsedXML(parsedXML);
		log.setGeoIP(geo);
	}
*/
	public static String exceptionStacktraceToString(Exception e) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		e.printStackTrace(ps);
		ps.close();
		return baos.toString();
	}

	public boolean isApiKeyValid(String apiKey) {
/*
		ApiKeyRepository apiKeyRepo = StaticContextAccessor.getBean(ApiKeyRepository.class);

		ApiKeyEntity entity = apiKeyRepo.findByApiKey(apiKey);

		if (entity != null) {
			return true;
		}

		return false;
*/
		return true;
	}

	public static String getClientIpAddressIfServletRequestExist() {

		if (RequestContextHolder.getRequestAttributes() == null) {
			return "0.0.0.0";
		}

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		for (String header : IP_HEADER_CANDIDATES) {
			String ipList = request.getHeader(header);
			if (ipList != null && ipList.length() != 0 && !"unknown".equalsIgnoreCase(ipList)) {
				String ip = ipList.split(",")[0];
				return ip;
			}
		}

		return request.getRemoteAddr();
	}

	public static String replaceEmbeddedQuotes(String input) {
		String progress = "";
		for (int c = 0; c < input.length(); c++) {
			char ch = input.charAt(c);
			if (ch == '\\') { // Skip if the next character is already escaped
				c++;
				continue;
			}
			if (ch == '\"')
				progress += "\\\""; // Results in \"
			else
				progress += ch; // Add the character to progress
		}
		return progress;
	}
}
