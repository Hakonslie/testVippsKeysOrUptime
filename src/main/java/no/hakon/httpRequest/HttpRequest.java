package no.hakon.httpRequest;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class HttpRequest {
	String URL, URI;
	HttpResponse response;

	public HttpRequest(String URL, String URI) {
		
		this.URL = URL;
		this.URI = URI;
		
	}
	
	
	public HttpResponse execute() throws IOException {
        try(Socket socket = new Socket(URL, 80)) {
            socket.getOutputStream()
                .write(("GET " + URI + " HTTP/1.1\r\n").getBytes());
            socket.getOutputStream()
                .write(("Host: " + URL + "\r\n").getBytes());
            socket.getOutputStream()
                .write("Connection: close\r\n".getBytes());
            socket.getOutputStream()
            	.write("\r\n".getBytes());

            InputStream input = socket.getInputStream();
            StringBuilder header = new StringBuilder();
            
            int c;
            while ((c = input.read()) != -1) {
            	header.append((char)c);
            }
            response = new HttpResponse(header.toString());
            
            
            return response;
        }
    }
	
}
