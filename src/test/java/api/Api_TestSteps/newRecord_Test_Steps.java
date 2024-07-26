package api.Api_TestSteps;

import io.restassured.http.Method;
import org.testng.Assert;
import utilities.web_api_BaseClass;

import java.io.IOException;
import java.util.Random;


public class newRecord_Test_Steps extends web_api_BaseClass {

    /** Request Body Of New Record */
    public void requestBody(String filePath) throws IOException
    {
        String reqPayload = readJsonFile(filePath);

        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer " + api_token);

        httpRequest.body(reqPayload);

        //Response object
        response = httpRequest.request(Method.POST, "/Data/NewRecord");
    }

    /** Verify Status Code */
    public void verifyStatusCodeAndRecord()
    {
        //Status Code Verification
        int statusCode = response.getStatusCode();
        System.out.println("Status Code is:" + statusCode);
        Assert.assertEquals(statusCode, 200);
    }

    /** Verify Added Record Successfully */
    public void verifyAddedSuccessfullyRecord()
    {
        //Verify Single Record Added Successfully Message From Response
        Assert.assertEquals(response.getBody().jsonPath().getString("errorMessages.fusionMessage"),"New Record Added!");
    }

    /** Verify Multiple Column Record Error */
    public void verifyMultipleColumnError()
    {
        //Verify Multiple Column Record Added Error Message From Response
        Assert.assertEquals(response.getBody().jsonPath().getString("errorMessages.fusionMessage"),"Column ExtraField doesn't exist!");
    }

    /** Verify Unique Id Record Error */
    public void verifyUniqueIdRecordError()
    {
        //Verify Multiple Column Record Added Error Message From Response
        Assert.assertEquals(response.getBody().jsonPath().getString("errorMessages.message"),"Cannot insert explicit value for identity column in table 'ElectronicRecords' when IDENTITY_INSERT is set to OFF.");
    }

}
