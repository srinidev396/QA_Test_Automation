package api.Api_TestSteps;

import io.restassured.http.Method;
import org.testng.Assert;
import utilities.web_api_BaseClass;

import java.io.IOException;

public class newRecordMulti_Test_Steps extends web_api_BaseClass {

    /** Request Body Of New Record */
    public void requestBody(String filePath) throws IOException
    {
        String reqPayload = readJsonFile(filePath);

        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer " + api_token);

        httpRequest.body(reqPayload);

        //Response object
        response = httpRequest.request(Method.POST, "/Data/NewRecordMulti");
    }

    /** Verify Status Code */
    public void verifyStatusCodeAndRecord()
    {
        //Status Code Verification
        int statusCode = response.getStatusCode();
        System.out.println("Status Code is:" + statusCode);
        Assert.assertEquals(statusCode, 200);
    }

    /** Verify Multiple Record Added Successfully */
    public void verifyAddedSuccessfullyRecord()
    {
        Assert.assertTrue(response.getBody().jsonPath().getString("errorMessages.fusionMessage").contains("Added 2 Rows!"));
    }
}
