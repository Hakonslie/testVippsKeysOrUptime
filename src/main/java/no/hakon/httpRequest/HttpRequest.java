package no.hakon.httpRequest;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import javax.net.ssl.*;

public class HttpRequest {
	String URL, URI;
	String [] requestHeaders;

	public HttpRequest(String URL, String URI) {
		this.URL = URL;
		this.URI = URI;	
		requestHeaders = new String [] { ("GET " + URI + " HTTP/1.1\r\n"), ("Host: " + URL + "\r\n"), "Connection: close\r\n", "\r\n" };
	}
	
	public HttpRequest(String[] headers, String URL, String URI) {
		requestHeaders = headers;
		this.URL = URL;
		this.URI = URI;	
	}
	
	public HttpResponse executeSpecifiedSSL() throws IOException {
		

		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
	;
		
		try(SSLSocket socket = (SSLSocket) sslsocketfactory.createSocket(URL, 443)) {
		for(String header : requestHeaders) {
			socket.getOutputStream()
			
				.write((header + "\r\n").getBytes());		
		}
			socket.getOutputStream()
				.write(("Connection: close\r\n").getBytes());
			socket.getOutputStream()
				.write(("\r\n").getBytes());
			
			InputStream input = socket.getInputStream();
            StringBuilder header = new StringBuilder();
            
            int c;
            while ((c = input.read()) != -1) {
            	header.append((char)c);
            }
            
            return new HttpResponse(header.toString());
        }
			
		}
	
	
	public HttpResponse executeSpecified() throws IOException {
		
		try(Socket socket = new Socket(URL, 80)) {
		for(String header : requestHeaders) {
			socket.getOutputStream()
			
				.write((header).getBytes());		
		}
			socket.getOutputStream()
				.write(("Connection: close\r\n").getBytes());
			socket.getOutputStream()
				.write(("\r\n").getBytes());
			
			InputStream input = socket.getInputStream();
            StringBuilder header = new StringBuilder();
            
            int c;
            while ((c = input.read()) != -1) {
            	header.append((char)c);
            }
            
            return new HttpResponse(header.toString());
        }
			
		}
	
}
