package api.Api_TestSteps;

import io.restassured.http.Method;
import org.testng.Assert;
import utilities.web_api_BaseClass;

public class authToken_Test_Steps extends web_api_BaseClass {

    /** Request body of the login API and header and Endpoint */
    public void requestBody(String userName, String passowrd, String databaseName)
    {
        //Decoded the Encoded Password
        byte[] decodedString = org.apache.hc.client5.http.utils.Base64.decodeBase64(passowrd);

        //Request RAW JSON Body Parameter
        requestParams.put("username", userName);
        requestParams.put("password", new String(decodedString));
        requestParams.put("database", databaseName);

        //Header Body
        httpRequest.header("Content-Type", "application/json");

        httpRequest.body(requestParams.toJSONString()); // Attach above data to the request

        //Response object and Endpoint
        response = httpRequest.request(Method.POST, "/GenerateToken");

//        //Print Response in Console Window
//        String responseBody = response.getBody().asString();
//        System.out.println("Response Body is:" + responseBody);
    }

    /** Verify Response Status Code */
    public void verifyStatusCode()
    {
        //Status Code Verification
        int statusCode = response.getStatusCode();
        System.out.println("Status Code is:" + statusCode);
        Assert.assertEquals(statusCode, 200);
    }

    /** Storing Token In Global Variable */
    public void storeTokenInGlobalVariable()
    {
        api_token = response.getBody().jsonPath().getString("token");
    }

    /** Verify Response Token is not null */
    public void verifyTokenNotNull()
    {
        if (api_token == null)
        {
            System.out.println("The token is null.");
        }
        else
        {
            System.out.println("The token is not null: " + api_token);
        }
    }
}
