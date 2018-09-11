package no.hakon.httpRequest;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import javax.net.ssl.*;

public class HttpRequest {
	String host, URI, body;
	String [] requestHeaders;

	// Constructor for specifying URL and URI for 
	
	public HttpRequest(String host, String URI) {
		this.host = host;
		this.URI = URI;
		requestHeaders = new String [] { ("GET " + URI + " HTTP/1.1\r\n"), ("Host: " + host + "\r\n"), "Connection: close\r\n", "\r\n" };
	}
	
	// Constructor for specifying headers in the String[] headers parameter
	
	public HttpRequest(String[] headers, String host, String body) {
		requestHeaders = headers;
		this.host = host;
		this.body = body;
		
	}
	
	// This executes a secure SSL request with headers specified in the requestHeaders array.
	
	public HttpResponse executeSSL() throws IOException {
		
		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		try(SSLSocket socket = (SSLSocket) sslsocketfactory.createSocket(host, 443)) 
		{
			for(String header : requestHeaders) {
				socket.getOutputStream().write((header + "\r\n").getBytes());		
			}
			socket.getOutputStream().write(("Connection: close\r\n").getBytes());
			socket.getOutputStream().write(("\r\n").getBytes());
			
			if(body != "") {
				socket.getOutputStream().write(body.getBytes());
			}
			
			InputStream input = socket.getInputStream();
            StringBuilder header = new StringBuilder();
            
            int c;
            while ((c = input.read()) != -1) {
            	header.append((char)c);
            }
                      
            return new HttpResponse(header.toString());
        }
			
	}
	
	// This executes a request with headers specified in the requestHeaders array.
	
	public HttpResponse execute() throws IOException {
		
		try(Socket socket = new Socket(host, 80)) 
		{
			for(String header : requestHeaders) 
			{
				socket.getOutputStream().write((header).getBytes());		
			}
			socket.getOutputStream().write(("Connection: close\r\n").getBytes());
			socket.getOutputStream().write(("\r\n").getBytes());
			
			InputStream input = socket.getInputStream();
            StringBuilder header = new StringBuilder();
            
            int c;
            while ((c = input.read()) != -1) 
            {
            	header.append((char)c);
            }
            
            return new HttpResponse(header.toString());
        }
			
	}
	
}
