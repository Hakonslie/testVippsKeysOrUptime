# testVippsKeysOrUptime

Script to test subsciption-keys and uptime in Vipps test environment.

To run this script you need an IDE with capabilities of running a Junit Test application.

For the WebPageButtonClicker to run you need to properly link your 
geckodriver with your JVM and you need firefox installed. Look at the comment to specify.

You need to create a VIPPS_APIKeys.java file and include these fields:
(Got from https://api-portal.vipps.no or https://apitest-portal.vipps.no )


### General:
```java
public static String orderId = "501283542";
public static final String urlRoot = "apitest.vipps.no"; // Can also be api.vipps.no
public static final String mobileNumber = ""; // Mobile test number here
```

### Access Token:
``` java
public static final String accesstokenuri = "/accesstoken/get";
public static final String clientId = "";
public static final String clientSecret = "";
public static final String accessTokenSubscriptionKey = "";
```

### Product keys:
``` java
public static final String merchantSerialNumber = "";
public static final String productSubscriptionKey = "";
public static final String producturi = "/ecomm/v2/payments";
```

### incrementOrderId method:
``` java
public static void incrementOrderId() {
	orderId = "" + (Integer.parseInt(orderId) + 1);
}
```
feel free to contribute!

Regards,
Håkon
