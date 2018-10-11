# hakon.HttpRequest

Script to test subsciption-keys and uptime in Vipps test environment.

For the WebPageButtonClicker to run you need to properly link your 
geckodriver with your JVM. Look at the comment to specify.

You need to create a VIPPS_APIKeys.java file and include these fields:
(Got from https://api-portal.vipps.no or https://apitest-portal.vipps.no )


### Root host:
public static String urlRoot = "";

### Access Token:
public static String accesstokenuri = "";
public static String accessTokenSubscriptionKey = "";
public static String clientId = "";
public static String clientSecret = "";

### Product keys:
public static String producturi = "";
public static String merchantSerialNumber = "";
public static String productSubscriptionKey = "";



feel free to contribute!

Regards,
Håkon
