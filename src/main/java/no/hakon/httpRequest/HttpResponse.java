package no.hakon.httpRequest;
import java.util.HashMap;

// To do: Add response body as well

public class HttpResponse {
	
	HashMap<String, String> headers;
	HashMap<String, String> body;
	
	// Constructor opens HashMap and temporary String array for converting headers. Will store header parameter as key (without colon), and
	// store header content as value(including everything after first space). Stops reading at "Connection: close" to avoid nullpointerexception.
	// @param response is String separating lines by "\r\n".
	
	public HttpResponse(String response) 
	{	
		if(response == null || response.equals("")) throw new IllegalArgumentException("Empty response from HttpRequest");
		
		headers = new HashMap<String, String>();
		String [] headerLineSplits;
		String [] headerLines = response.split("\r\n");
		
		for(int i = 0; i < headerLines.length ; i++) {			
				headerLineSplits = headerLines[i].split(" ", 2);
				headers.put(headerLineSplits[0].replaceFirst(":", ""), headerLineSplits[1]);
				if(headerLineSplits[0].equals("Connection:") && headerLineSplits[1].equals("close")) {
					if(getHeader("Content-Type").startsWith("application/json")) {
						retreiveJsonBody(headerLines[i + 3]);
					}
					else 
						headers.put("Body", headerLines[i + 2]);
					break;
					
				}
		}	

	}

	private void retreiveJsonBody(String bodyContent) {
		body = new HashMap<String, String>();
		bodyContent.replaceAll("[\"]", "");
		String [] bodyLines = bodyContent.split(",");
		String [] bodyLineSplits;
		
		for(String s : bodyLines) {
			bodyLineSplits = s.split(":", 2);
			body.put(bodyLineSplits[0].replaceAll("[^a-zA-Z0-9_-]", ""), bodyLineSplits[1].replaceAll("[^a-zA-Z0-9_-]", ""));
		}
		
	}

	// Will fetch statusCode by searching getHeader and splitting the actual code from the message.
	// Error might show if there is no " " to split on? Not sure.
	public int getStatusCode() 
	{			
		return Integer.parseInt(getHeader("HTTP/1.1").split(" ")[0]);
	}
	 
	// getHeader check if input is valid and then searches the HashMap for the key and returns the corresponding value		
	// @param headerName must be identical to Key in HashMap as withdrawn from response.
	public String getHeader(String headerName) 
	{		
		if(headerName == null || headerName == "") return null;		
		for(HashMap.Entry<String, String> head : headers.entrySet()) {
			if(head.getKey().equals(headerName)) return head.getValue();
		}		
		return null;	
	}
	public String getBody(String bodyName) {
		for(HashMap.Entry<String, String> bodyPart : body.entrySet()) {
			if(bodyPart.getKey().equals(bodyName)) return bodyPart.getValue();
		}
		return null;
	}
	
	
}
