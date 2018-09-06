package no.hakon.httpRequest;
import java.util.HashMap;

// To do: Add response body as well

public class HttpResponse {
	
	HashMap<String, String> headers;
	
	// Constructor opens HashMap and temporary String array for converting headers. Will store headers as key (without colon), and
	// store value as String including everything after first space. Stops reading at "Connection: close" to avoid nullpointerexception
	public HttpResponse(String response) 
	{	
		headers = new HashMap<String, String>();
		String [] headerLine;
		
		for(String s : response.split("\r\n")) {			
				headerLine = s.split(" ", 2);
				headers.put(headerLine[0].replaceFirst(":", ""), headerLine[1]);
				if(headerLine[0].equals("Connection:") && headerLine[1].equals("close")) break;				
		}	

	}

	// use getHeader to fetch statusCode by searching and splitting the code from the message
	// Error might show if there is no " " to split on? Not sure
		public int getStatusCode() 
		{			
			return Integer.parseInt(getHeader("HTTP/1.1").split(" ")[0]);
	}
	 
	// getHeader check if input is valid and then searches the HashMap for the key and returns the corresponding value		
	public String getHeader(String headerName) 
	{		
		if(headerName == null || headerName == "") return null;		
		for(HashMap.Entry<String, String> head : headers.entrySet()) {
			if(head.getKey().equals(headerName)) return head.getValue();
		}		
		return null;	
	}
}
