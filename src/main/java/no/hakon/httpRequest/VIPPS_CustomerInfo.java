package no.hakon.httpRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VIPPS_CustomerInfo {

@SerializedName("mobileNumber")
@Expose
private String mobileNumber;

public String getMobileNumber() {
return mobileNumber;
}

public void setMobileNumber(String mobileNumber) {
this.mobileNumber = mobileNumber;
}

}