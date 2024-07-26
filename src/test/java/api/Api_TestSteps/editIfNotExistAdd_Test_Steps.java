package api.Api_TestSteps;

import io.restassured.http.Method;
import org.testng.Assert;
import utilities.web_api_BaseClass;

import java.io.IOException;

public class editIfNotExistAdd_Test_Steps extends web_api_BaseClass {

    /** Request Body Of New Record */
    public void requestBody(String filePath) throws IOException
    {
        String reqPayload = readJsonFile(filePath);

        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer " + api_token);

        httpRequest.body(reqPayload);

        //Response object
        response = httpRequest.request(Method.POST, "/Data/EditIfNotExistAdd");
    }

    /** Verify Status Code */
    public void verifyStatusCodeAndRecord()
    {
        //Status Code Verification
        int statusCode = response.getStatusCode();
        System.out.println("Status Code is:" + statusCode);
        Assert.assertEquals(statusCode, 200);
    }

    /** Verify Insert New Record Successfully */
    public void verifyInsertNewRecordSuccessfully()
    {
        //Verify Inserted New Record Added Successfully Message From Response
        Assert.assertEquals(response.getBody().jsonPath().getString("errorMessages.fusionMessage"),"New Record Added!");
    }

    /** Verify Updated Record Successfully */
    public void verifyUpdatedRecordSuccessfully()
    {
        //Verify Updated Record Successfully Message From Response
        Assert.assertEquals(response.getBody().jsonPath().getString("errorMessages.fusionMessage"),"Record Updated!");
    }
}
