package com.rkc.zds;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * @author rcampion
 */
public class SimpleServer {

	int port = 4245;
	String strResponse = "";
	boolean testOK = true; // Set to true if want to test positive response.

	HttpServer server = null;

	public static void main(String[] args) {
		new SimpleServer().run();
	}

	public void run() {

		try {

			// registerGoodResponse();

			server = HttpServer.create(new InetSocketAddress(port), 10);

			server.setExecutor(Executors.newFixedThreadPool(10));

			server.createContext("/api/info", new InfoHandler());

			server.createContext("/api/get", new GetHandler());

			server.createContext("/api/get/test", new GetHandlerOne());

			server.createContext("/api/test", new TestHandler());

			server.createContext("/api/greeting", (exchange -> {

				if ("GET".equals(exchange.getRequestMethod())) {
					String responseText = "Hello World! from our framework-less REST API\n";
					exchange.sendResponseHeaders(200, responseText.getBytes().length);
					OutputStream output = exchange.getResponseBody();
					output.write(responseText.getBytes());
					output.flush();
				} else {
					exchange.sendResponseHeaders(405, -1);// 405 Method Not Allowed
				}
				exchange.close();
			}));

			server.start();

			System.out.println("The server is running");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	static class GetHandlerOne implements HttpHandler {
		public void handle(HttpExchange httpExchange) throws IOException {

			byte[] response = getResponse().getBytes();
			sendAndGetResponse(httpExchange, response);

			/*
			 * StringBuilder sb = new StringBuilder(); try (InputStream inputStream =
			 * httpExchange.getRequestBody()) { sb = new StringBuilder(); int i; while ((i =
			 * inputStream.read()) != -1) { sb.append((char) i); } }
			 * 
			 * 
			 * 
			 * if (testOK) { replyOK(httpExchange); } else { replyError(httpExchange); }
			 */
		}
	}

	/**
	 * 
	 * @param httpExchange
	 * 
	 *                     Sends a 200 OK back
	 */
	private void replyOK(HttpExchange httpExchange) {

		try {
			Headers responseHeaders = httpExchange.getResponseHeaders();
			responseHeaders.add("Content-Type", ("application/json"));
			strResponse = getResponse();
			if (strResponse != null) {
				httpExchange.sendResponseHeaders(200, strResponse.length());

				try (OutputStream os = httpExchange.getResponseBody()) {
					os.write(strResponse.getBytes());
				}
			} else {
				httpExchange.sendResponseHeaders(200, -1);
			}

		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}
	}

	static String getResponse() {
		return "HttpServer Works!\n";
	}

	/**
	 * 
	 * @param httpExchange There are any number of ways to create an error in the
	 *                     response. This method sends back a 404 and also
	 *                     mismatched the content length and the content, to create
	 *                     an unexpected EOF error.
	 * 
	 */
	private void replyError(HttpExchange httpExchange) {
		String replyString = "Error";
		try {
			Headers responseHeaders = httpExchange.getResponseHeaders();
			responseHeaders.add("Content-Type", ("application/json"));
			httpExchange.sendResponseHeaders(404, 0);

			try (OutputStream os = httpExchange.getResponseBody()) {
				os.write(replyString.getBytes());
			}

		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}
	}

	private static void sendAndGetResponse(HttpExchange httpExchange, byte[] response) throws IOException {
		if (response.length > 0) {
			httpExchange.getResponseHeaders().add("Content-type", "application/json");
			httpExchange.getResponseHeaders().add("Content-length", Integer.toString(response.length));
			httpExchange.sendResponseHeaders(200, response.length);
			httpExchange.getResponseBody().write(response);
			httpExchange.close();
		}
	}

	public void registerGoodResponse() {
		server.createContext("/good", new HttpHandler() {

			@Override
			public void handle(HttpExchange exchange) throws IOException {
				try {
					String response = new String("Test");
					exchange.getResponseHeaders().add("Content-Type", "text/html");
					exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length());
					// IOUtils.write(response, exchange.getResponseBody());
					exchange.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw e;
				}
			}
		});
	}

//--------------

	static class InfoHandler implements HttpHandler {

		public void handle(HttpExchange httpExchange) throws IOException {

			String response = "Use /get?hello=word&foo=bar to see how to handle url parameters";

			SimpleServer.writeResponse(httpExchange, response.toString());

		}

	}

	static class GetHandler implements HttpHandler {

		public void handle(HttpExchange httpExchange) throws IOException {

			StringBuilder response = new StringBuilder();

			Map<String, String> parms = SimpleServer.queryToMap(httpExchange.getRequestURI().getQuery());

			response.append("<html><body>");

			response.append("hello : " + parms.get("hello") + "<br/>");

			response.append("foo : " + parms.get("foo") + "<br/>");

			response.append("</body></html>");

			SimpleServer.writeResponse(httpExchange, response.toString());

		}

	}

	// Handler for '/test' context
	static class TestHandler implements HttpHandler {

		@Override
		public void handle(HttpExchange httpExchange) throws IOException {
			System.out.println("Serving the request");

			// Serve for POST requests only
			if (httpExchange.getRequestMethod().equalsIgnoreCase("POST")) {

				try {

					// REQUEST Headers
					Headers requestHeaders = httpExchange.getRequestHeaders();
					Set<Map.Entry<String, List<String>>> entries = requestHeaders.entrySet();

					int contentLength = Integer.parseInt(requestHeaders.getFirst("Content-length"));

					// REQUEST Body
					InputStream is = httpExchange.getRequestBody();

					byte[] data = new byte[contentLength];
					int length = is.read(data);

					// RESPONSE Headers
					Headers responseHeaders = httpExchange.getResponseHeaders();

					// Send RESPONSE Headers
					httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, contentLength);

					// RESPONSE Body
					OutputStream os = httpExchange.getResponseBody();

					os.write(data);

					httpExchange.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}

	public static void writeResponse(HttpExchange httpExchange, String response) throws IOException {

		httpExchange.sendResponseHeaders(200, response.length());

		OutputStream os = httpExchange.getResponseBody();

		os.write(response.getBytes());

		os.close();

	}

	/**
	 * 
	 * returns the url parameters in a map
	 * 
	 * @param query
	 * 
	 * @return map
	 * 
	 */

	public static Map<String, String> queryToMap(String query) {

		Map<String, String> result = new HashMap<String, String>();

		for (String param : query.split("&")) {

			String pair[] = param.split("=");

			if (pair.length > 1) {

				result.put(pair[0], pair[1]);

			} else {

				result.put(pair[0], "");

			}

		}

		return result;

	}
}

