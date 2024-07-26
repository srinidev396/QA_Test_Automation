package api.Api_TestSteps;

import com.jayway.jsonpath.JsonPath;
import io.restassured.http.Method;
import org.testng.Assert;
import utilities.web_api_BaseClass;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class userViews_Test_Steps extends web_api_BaseClass {

    public int totalViewNameCount;
    public ArrayList<String> getListCount = new ArrayList<>();

    /** Request Body For Table Schema */
    public void requestBody()
    {
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer " + api_token);

        //Response object
        response = httpRequest.request(Method.GET, "/Data/GetUserViews");
    }

    /** Verify Status Code Of API */
    public void verifyStatusCode()
    {
        //Status Code Verification
        int statusCode = response.getStatusCode();
        System.out.println("Status Code is:" + statusCode);
        Assert.assertEquals(statusCode, 200);
    }

    /** Verify Disabled User Status Code And Error Message */
    public void verifyDisabledUserStatusCodeAndErrorMessage()
    {
        //Status Code Verification
        int statusCode = response.getStatusCode();
        System.out.println("Status Code is:" + statusCode);
        Assert.assertEquals(statusCode, 200);

        //Verifying disabled user response error message
        String errorMessage = response.jsonPath().getJsonObject("errorMessages.fusionMessage");
        System.out.println(errorMessage);
        Assert.assertEquals(errorMessage,"Faild to authenticate, username or password are incorrect!");
    }

    /** Get All View Name Count */
    public void getAllViewNameCount()
    {
        String responseBody = response.getBody().asString();

        List<Object> specificKeyValueCount = JsonPath.read(responseBody, "$.."+"viewName");

        for (Object value : specificKeyValueCount)
        {
            getListCount.add(value.toString());
        }

        totalViewNameCount = getListCount.size();
    }

    /** Verify View Name Count Of Permitted User */
    public void verifyViewNameCountOfPermittedUser()
    {
        Assert.assertEquals(totalViewNameCount,2);
    }

    /** Verify View Name Not Contains Duplicated Name */
    public void verifyViewNameNotContainsDuplicated()
    {
        // Extract the list from the response
        List<String> actualViewNameList = getListCount;

        // Check for duplicates by comparing the size of the list with the size of a set created from the list
        Set<String> actualViewNameSet = new HashSet<>(actualViewNameList);

        // Assert that the list and set sizes are equal, which means there are no duplicates
        Assert.assertEquals(actualViewNameList.size(), actualViewNameSet.size(), "The list contains duplicate values.");
    }

    /** Verify Disabled User View Status Code */
    public void verifyDisabledUserViewStatusCode()
    {
        //Status Code Verification
        int statusCode = response.getStatusCode();
        System.out.println("Status Code is:" + statusCode);
        Assert.assertEquals(statusCode, 401,"Verify Disabled User View Status Code");
    }

    /** Verify View Name Count Of No View User */
    public void verifyViewNameCountOfNoViewUser()
    {
        Assert.assertEquals(totalViewNameCount,0);
    }
}
