package no.hakon.httpRequest;

import java.util.HashMap;

/*
 * This HttpResponse class takes in the response but does not retrieve parameters before it is specifically asked to do so.
 * Uses HashMap for caching retrieved parameters.
 */


public class HttpResponse {

	// To do: Add response body aswell
	String [] responseArray;
	HashMap<String, String> header = new HashMap<String, String>();

	/*
	 * The constructor opens the @response received and splits it line by line
	 */
	public HttpResponse(String response) {
		responseArray = response.split("\r\n");	
		
		/*
		 * If you wish to automatically add any parameters to the HashMap, remove the next lines of commenting and add your 
		 * wanted parameters:
		 * 	fetchContentFromResponse("HEADERNAME");
		 * 	fetchContentFromResponse("HEADERNAME");
		 * 	fetchContentFromResponse("HEADERNAME");
		 */
		
	}
	/*
	 * This function fetches content from the response. Searching for headers corresponding to parameterName and adds this 
	 * parameter to the header HashMap. It searches by using startsWith() and returns true if it finds a match.
	 * @parameterName the name of the header that should be searched for.
	 */
	private boolean fetchContentFromResponse(String parameterName) {
		boolean found = false;
		for(String s : responseArray)
		{
			if(s.startsWith(parameterName) ) {
				header.put(parameterName, s.split(" ")[1]);
				found = true;
			}
		}
		return found;
	}
	
	// returns statusCode by using getHeader function
		
		public int getStatusCode() {			
			return Integer.parseInt(getHeader("HTTP"));
	}

	/*
	 * getHeader function iterates the header HashMap for a key corresponding with headerName in parameter,
	 * if it does not find a matching header it will search through the response again to see if it has not been picked up. If it 
	 * finds it at this time it runs itself from the beginning with the same header and will find it. If it doesn't find it it returns
	 * If it does not find it then it will return "Header @headerName Not found"
	 * @headerName the name of the header that should be searched for
	 */
	public String getHeader(String headerName) {
		if(headerName == null || headerName == "") return "headerName parameter is null or empty";
		for(HashMap.Entry<String, String> head : header.entrySet()) {
			if(head.getKey() == headerName) return head.getValue();
		}
		
		// Did not find this value, search again with the new parameter, if it does find it and successfully adds. Then search again.
		if(fetchContentFromResponse(headerName)) return getHeader(headerName);

		return "Header: " + headerName + " Not found";
	
	}
	}
