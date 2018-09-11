package no.hakon.httpRequest;

import java.io.IOException;

import com.google.gson.Gson;

public class VIPPS_InitiatePaymentRequest {
	

	VIPPS_InitiatePayment ipObject = new VIPPS_InitiatePayment();
	VIPPS_MerchantInfo miObject = new VIPPS_MerchantInfo();
	VIPPS_Transaction trObject = new VIPPS_Transaction();
	VIPPS_CustomerInfo ciObject = new VIPPS_CustomerInfo();
	String orderId, deeplink;
	
	HttpResponse response;
	
	public VIPPS_InitiatePaymentRequest()
	{
		miObject.setMerchantSerialNumber(VIPPS_APIKeys.merchantSerialNumber);
		miObject.setFallBack("http://www.vipps.no");
		miObject.setIsApp(false);
		miObject.setCallbackPrefix("http://www.vipps.no");
		
		trObject.setAmount("50000");
		trObject.setOrderId("5005543912839");
		trObject.setTransactionText("text of transaction");
		
		ciObject.setMobileNumber(VIPPS_APIKeys.mobileNumber);
		
		ipObject.setCustomerInfo(ciObject);
		ipObject.setMerchantInfo(miObject);
		ipObject.setTransaction(trObject);
		
	}
	
	public void initiatePayment(String accesstoken) throws IOException {
		HttpRequest request;
		Gson gson = new Gson();
		String body = gson.toJson(ipObject);
		
		
		String [] headers = new String [] 
				{ 
					("POST " + VIPPS_APIKeys.producturi + " HTTP/1.1"), 
					("Host: " + VIPPS_APIKeys.urlRoot), 
					("Authorization: Bearer " + accesstoken),
					("Ocp-Apim-Subscription-Key: " + VIPPS_APIKeys.productSubscriptionKey),
					("Content-Type: " + "application/json"),
					("Content-Length: " + body.length())
				};	

			request = new HttpRequest(headers, VIPPS_APIKeys.urlRoot, body);
			response = request.executeSSL();
			
			VIPPS_InitiatePaymentResponse ipr = gson.fromJson(response.getBody(), VIPPS_InitiatePaymentResponse.class);
			try {
			deeplink = ipr.getUrl();
			orderId = ipr.getOrderId();
			}
			catch(NullPointerException e) {
				System.out.println("Didnt get URL or Order from Initiate Payment!");
			}
	}
	
	public String getDeeplinkUrl( ) {
		System.out.println(deeplink);
		return deeplink;
	}
	
}