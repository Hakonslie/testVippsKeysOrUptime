package no.hakon.httpRequest;

import static org.assertj.core.api.Assertions.assertThat;


import java.io.IOException;

import org.junit.After;
import org.junit.Test;

import no.hakon.httpRequest.HttpRequest;
import no.hakon.httpRequest.HttpResponse;

public class HttpClientTest {

    @Test
    public void shouldReadStatusCode() throws IOException {
        HttpRequest request = new HttpRequest("urlecho.appspot.com", "/echo?status=200");
        HttpResponse response = request.executeSpecified();

        assertThat(response.getStatusCode()).isEqualTo(200);
    }

    @Test
    public void shouldReadOtherStatusCodes() throws IOException {
        HttpRequest request = new HttpRequest("urlecho.appspot.com", "/echo?status=404");
        HttpResponse response = request.executeSpecified();

        assertThat(response.getStatusCode()).isEqualTo(404);
    }

    @Test
    public void shouldReadResponseHeaders() throws IOException {
        HttpRequest request = new HttpRequest("urlecho.appspot.com",
                "/echo?status=307&Location=http%3A%2F%2Fwww.google.com");
        HttpResponse response = request.executeSpecified();

        assertThat(response.getStatusCode()).isEqualTo(307);
        assertThat(response.getHeader("Location")).isEqualTo("http://www.google.com");
        
    }
    @Test
    public void shouldReadResponseFromSimilarHeaders() throws IOException {
        HttpRequest request = new HttpRequest("urlecho.appspot.com",
                "/echo?status=307&Location=http%3A%2F%2Fwww.google.com");
        HttpResponse response = request.executeSpecified();

        assertThat(response.getStatusCode()).isEqualTo(307);
        assertThat(response.getHeader("Location")).isEqualTo("http://www.google.com");
        assertThat(response.getHeader("Location")).isEqualTo("http://www.google.com");
        assertThat(response.getHeader("Location")).isEqualTo("http://www.google.com");
        assertThat(response.getHeader("Location")).isEqualTo("http://www.google.com");   
    }
    @Test
    public void shouldReadResponseFromINVALIDHeaders() throws IOException {
        HttpRequest request = new HttpRequest("urlecho.appspot.com",
                "/echo?status=307&Location=http%3A%2F%2Fwww.google.com");
        HttpResponse response = request.executeSpecified();

        assertThat(response.getStatusCode()).isEqualTo(307);
        assertThat(response.getHeader("notaheader")).isEqualTo(null);
        assertThat(response.getHeader(null)).isEqualTo(null);
        assertThat(response.getHeader("")).isEqualTo(null);
 
    }
    @Test
    public void shouldReadBodyFromResponse() throws IOException {
    	HttpRequest request = new HttpRequest("urlecho.appspot.com", "/echo?status=200&body=hallohallo");
    	HttpResponse response = request.executeSpecified();
    }
    @Test
    public void shouldGetAccessTokenFromVipps() throws IOException {
    	
		String URL = "apitest.vipps.no";
		String URI = "/accessToken/get";
		String [] requestHeaders = new String [] {
				("POST " + URI + " HTTP/1.1"),
				("Host: " + URL),
				("Ocp-Apim-Subscription-Key: " + APIKeys.accessTokenSubscriptionKey ),
				("client_id: " + APIKeys.clientId),
				("client_secret: " + APIKeys.clientSecret),
				("Cache-Control: " + "No Cache"),
				("Content-Type: " + "text/html"),
				("Content-Length: 0")
		};
    	HttpRequest request = new HttpRequest(requestHeaders, URL, URI);
    	HttpResponse response = request.executeSpecifiedSSL();
    	assertThat(response.getStatusCode()).isEqualTo(200);
    	System.out.println(response.getBody("access_token"));
    }
    @Test
    public void ShouldInitiatePaymentWithAccessToken() throws IOException  {
    	
    	String token;
    	
		String URL = APIKeys.urlRoot;
		String URI = APIKeys.accesstokenuri;
		String [] requestHeaders = new String [] {
				("POST " + URI + " HTTP/1.1"),
				("Host: " + URL),
				("Ocp-Apim-Subscription-Key: " + APIKeys.accessTokenSubscriptionKey),
				("client_id: " + APIKeys.clientId),
				("client_secret: " + APIKeys.clientSecret),
				("Cache-Control: " + "No Cache"),
				("Content-Type: " + "text/html"),
				("Content-Length: 0")
		};
		
    	HttpRequest request = new HttpRequest(requestHeaders, URL, URI);
    	HttpResponse response = request.executeSpecifiedSSL();
    	token = response.getBody("access-token");
    	
    	
    	URI = APIKeys.producturi;
    	requestHeaders = new String [] {
    			("POST: " + URI + " HTTP/1.1"),
    			("Host: " + URL),
    			("Ocp-Apim-Subscription-Key: " + APIKeys.productSubscriptionKey),
    			("Content-Type: " + "text/html"),
    			("Content-Length: ")
    	}
    	
    	
    	
    }

    

}

