package no.hakon.httpRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VIPPS_InitiatePayment {

@SerializedName("merchantInfo")
@Expose
private VIPPS_MerchantInfo merchantInfo;
@SerializedName("customerInfo")
@Expose
private VIPPS_CustomerInfo customerInfo;
@SerializedName("transaction")
@Expose
private VIPPS_Transaction transaction;

public VIPPS_MerchantInfo getMerchantInfo() {
return merchantInfo;
}

public void setMerchantInfo(VIPPS_MerchantInfo merchantInfo) {
this.merchantInfo = merchantInfo;
}

public VIPPS_CustomerInfo getCustomerInfo() {
return customerInfo;
}

public void setCustomerInfo(VIPPS_CustomerInfo customerInfo) {
this.customerInfo = customerInfo;
}

public VIPPS_Transaction getTransaction() {
return transaction;
}

public void setTransaction(VIPPS_Transaction transaction) {
this.transaction = transaction;
}

}