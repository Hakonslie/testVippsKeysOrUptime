package no.hakon.httpRequest;

import static org.assertj.core.api.Assertions.assertThat;


import java.io.IOException;

import org.junit.Test;

import no.hakon.httpRequest.HttpRequest;
import no.hakon.httpRequest.HttpResponse;

public class HttpClientTest {

    @Test
    public void shouldReadStatusCode() throws IOException {
        HttpRequest request = new HttpRequest("urlecho.appspot.com", "/echo?status=200");
        HttpResponse response = request.execute();

        assertThat(response.getStatusCode()).isEqualTo(200);
    }

    @Test
    public void shouldReadOtherStatusCodes() throws IOException {
        HttpRequest request = new HttpRequest("urlecho.appspot.com", "/echo?status=404");
        HttpResponse response = request.execute();

        assertThat(response.getStatusCode()).isEqualTo(404);
    }

    @Test
    public void shouldReadResponseHeaders() throws IOException {
        HttpRequest request = new HttpRequest("urlecho.appspot.com",
                "/echo?status=307&Location=http%3A%2F%2Fwww.google.com");
        HttpResponse response = request.execute();

        assertThat(response.getStatusCode()).isEqualTo(307);
        assertThat(response.getHeader("Location")).isEqualTo("http://www.google.com");
        
    }
    @Test
    public void shouldReadResponseFromSimilarHeaders() throws IOException {
        HttpRequest request = new HttpRequest("urlecho.appspot.com",
                "/echo?status=307&Location=http%3A%2F%2Fwww.google.com");
        HttpResponse response = request.execute();

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
        HttpResponse response = request.execute();

        assertThat(response.getStatusCode()).isEqualTo(307);
        assertThat(response.getHeader("notaheader")).isEqualTo(null);
        assertThat(response.getHeader(null)).isEqualTo(null);
        assertThat(response.getHeader("")).isEqualTo(null);
 
    }

    

}

