package api.Api_TestSteps;

import io.restassured.http.Method;
import org.testng.Assert;
import utilities.web_api_BaseClass;

import java.io.IOException;

public class editRecord_Test_Steps extends web_api_BaseClass {

    /** Request Body Of New Record */
    public void requestBody(String filePath) throws IOException
    {
        String reqPayload = readJsonFile(filePath);

        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer " + api_token);

        httpRequest.body(reqPayload);

        //Response object
        response = httpRequest.request(Method.POST, "/Data/EditRecord");
    }

    /** Verify Status Code */
    public void verifyStatusCodeAndRecord()
    {
        //Status Code Verification
        int statusCode = response.getStatusCode();
        System.out.println("Status Code is:" + statusCode);
        Assert.assertEquals(statusCode, 200);
    }

    /** Verify Edited Record Successfully */
    public void verifyEditedRecordSuccessfully()
    {
        //Verify Single Record Added Successfully Message From Response
        Assert.assertEquals(response.getBody().jsonPath().getString("errorMessages.fusionMessage"),"Record Updated!");
    }

    /** Verify Non-Existing Table Error */
    public void verifyNonExistingTableError()
    {
        //Verify Non Existing Table Error Message From Response
        Assert.assertTrue(response.getBody().jsonPath().getString("errorMessages.fusionMessage").contains("doesn't exist!"));
    }

    /** Verify Unique Id Error */
    public void verifyUniqueIdError()
    {
        //Verify Unique Id Error Message From Response
        Assert.assertTrue(response.getBody().jsonPath().getString("errorMessages.message").contains("Cannot update identity column"));
    }

    /** Verify Invalid Column Value Error */
    public void verifyInvalidColumnValueError()
    {
        //Verify Invalid Column value Error Message From Response
        Assert.assertTrue(response.getBody().jsonPath().getString("errorMessages.fusionMessage").contains("doesn't exist!"));
    }
}
