package api.Api_TestSteps;

import com.jayway.jsonpath.JsonPath;
import io.restassured.http.Method;
import org.testng.Assert;
import utilities.web_api_BaseClass;

import java.util.ArrayList;
import java.util.List;

public class dbSchema_Test_Steps extends web_api_BaseClass {

    public int totalSchemaTables;
    public int totalTableNameCount;
    public int totalColumnCount;

    /** Request Body For DB Schema */
    public void requestBody()
    {
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer " + api_token);

        //Response object
        response = httpRequest.request(Method.GET, "/Data/GetDbSchema");

        //Get TotalSchemaTablesCount
        totalSchemaTables = response.jsonPath().getJsonObject("totalSchemaTables");

        //Get TotalColumnCount
        totalColumnCount = response.jsonPath().getJsonObject("getDbSchemas[0].columnCount");
    }

    /** Verify Status Code Of API */
    public void verifyStatusCode()
    {
        //Status Code Verification
        int statusCode = response.getStatusCode();
        System.out.println("Status Code is:" + statusCode);
        Assert.assertEquals(statusCode, 200);
    }

    /** Verify Total Schema Table Count Not Zero */
    public void verifyTotalSchemaTableCountNotZero()
    {
        Assert.assertTrue(totalSchemaTables != 0);
    }

    /** Get All Table Name Count */
    public void getAllTableNameCount()
    {
        String responseBody = response.getBody().asString();
        ArrayList<String> getListCount = new ArrayList<>();

        List<Object> specificKeyValueCount = JsonPath.read(responseBody, "$.."+"tableName");

        for (Object value : specificKeyValueCount)
        {
            getListCount.add(value.toString());
        }

        totalTableNameCount = getListCount.size();
    }

    /** Verify Total Schema Tables And Total Table Name Count */
    public void verifyTotalSchemaTableAndTableNameCount()
    {
        Assert.assertEquals(totalTableNameCount, totalSchemaTables);
    }

    /** Get Total Column Count */
    public void verifyTotalColumnCountAndTotalColumnNameCount()
    {
        for (int i = 0; i < totalColumnCount-1; i++)
        {
            int totalColumnCount = response.jsonPath().getJsonObject("getDbSchemas["+i+"].columnCount");
            ArrayList<String> getColumnNameCount = new ArrayList<>();

            for (int j = 0; j < totalColumnCount; j++)
            {
                getColumnNameCount.add(response.jsonPath().getJsonObject("getDbSchemas["+i+"].listOfColumns["+j+"].columnName"));
            }

            Assert.assertEquals(totalColumnCount, getColumnNameCount.size());
            System.out.println("Total Column Count : " + totalColumnCount + "  | Column Name Count : " + getColumnNameCount.size());
        }
    }
}
