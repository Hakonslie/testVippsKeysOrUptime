package no.hakon.httpRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VIPPS_InitiatePaymentResponse {

@SerializedName("orderId")
@Expose
private String orderId;
@SerializedName("url")
@Expose
private String url;

public String getOrderId() {
return orderId;
}

public void setOrderId(String orderId) {
this.orderId = orderId;
}

public String getUrl() {
return url;
}

public void setUrl(String url) {
this.url = url;
}

}