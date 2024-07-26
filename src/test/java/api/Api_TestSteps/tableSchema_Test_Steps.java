package api.Api_TestSteps;

import com.jayway.jsonpath.JsonPath;
import io.restassured.http.Method;
import org.testng.Assert;
import utilities.web_api_BaseClass;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class tableSchema_Test_Steps extends web_api_BaseClass {

    public int totalColumnCount;
    public int totalColumnNameCount;
    public ArrayList<String> getListCount = new ArrayList<>();

    /** Request Body Params Parameter
     * @param tableName = Enter Table Name For Table Schema */
    public void requestBodyParams(String tableName)
    {
        //Request RAW JSON Body Parameter
        httpRequest.queryParam("tableName", tableName);
    }

    /** Request Body For Table Schema */
    public void requestBody()
    {
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer " + api_token);

        //Response object
        response = httpRequest.request(Method.GET, "/Data/GetTableSchema");

        //Get TotalSchemaTablesCount
        totalColumnCount = response.jsonPath().getJsonObject("columsCount");
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
        Assert.assertTrue(totalColumnCount != 0);
    }

    /** Get All Table Name Count */
    public void getAllColumnNameCount()
    {
        String responseBody = response.getBody().asString();

        List<Object> specificKeyValueCount = JsonPath.read(responseBody, "$.."+"columnName");

        for (Object value : specificKeyValueCount)
        {
            getListCount.add(value.toString());
        }

        totalColumnNameCount = getListCount.size();
    }

    /** Verify Total Column Count And Column Name Count */
    public void verifyTotalColumnCountAndColumnNameCount()
    {
        Assert.assertEquals(totalColumnCount, totalColumnNameCount);
        System.out.println("Total Column Count : " + totalColumnCount + " And Total Column Name Count : " + totalColumnNameCount + " Equal");
    }

    /** Verify Non Existent Table Name Error */
    public void verifyNonexistentTableNameError()
    {
        //Verify Total Column Count Is 0
        Assert.assertEquals(totalColumnCount, 0);

        //Verify Table Name Is Null
        Assert.assertNull(response.jsonPath().getJsonObject("tableName"),"Table Name Is Null");
    }

    /** Verify Table Name Parameter Case Insensitivity */
    public void verifyTableNameParameterCaseInsensitivity()
    {
        Assert.assertTrue(response.jsonPath().getJsonObject("tableName").toString().equalsIgnoreCase(prop.getProperty("tableSchemaName")));
    }

    /** Verify Column Name Not Contains Duplicated Name */
    public void verifyColumnNameNotContainsDuplicated()
    {
        // Extract the list from the response
        List<String> actualColumnNameList = getListCount;

        // Check for duplicates by comparing the size of the list with the size of a set created from the list
        Set<String> actualColumnNameSet = new HashSet<>(actualColumnNameList);

        // Assert that the list and set sizes are equal, which means there are no duplicates
        Assert.assertEquals(actualColumnNameList.size(), actualColumnNameSet.size(), "The list contains duplicate values.");
    }

    /** Verify Leading And Trailing Of Given Table Name */
    public void verifyLeadingAndTrailingOfGivenTableName()
    {
        System.out.println(totalColumnCount);
        if (totalColumnCount == 0)
        {
            Assert.assertNull(response.jsonPath().getJsonObject("tableName"));
            System.out.println("Table Name Is Null : " + response.jsonPath().getJsonObject("tableName"));
        }
        else
        {
            System.out.println("Table Name Is Not Null : " + response.jsonPath().getJsonObject("tableName"));
        }
    }
}
