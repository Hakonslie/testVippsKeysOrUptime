package no.hakon.httpRequest;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.Test;


public class TestVippsPaymentFlow {

    @Test
    public void shouldFetchAccessTokenAndInitiatePayment() throws Exception {

    	VIPPS_APIKeys.incrementOrderId();
    	
    	VIPPS_getAccessToken gat = new VIPPS_getAccessToken();
    	gat.fetchAccessToken();
    	VIPPS_InitiatePaymentRequest ipr = new VIPPS_InitiatePaymentRequest();
    	
    	ipr.initiatePayment(gat.fetchAccessToken());
    	
    	if(VIPPS_WebPageButtonClicker.main(ipr.getDeeplinkUrl())) System.out.println("Success!!");

    }
}
