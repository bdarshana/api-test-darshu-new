package StepDefination;

import HTTPCLientHelper.HTTPClientHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class stepDefn {

    private static HTTPClientHelper httpClientHelper;
    private static ObjectMapper objectMapper;
    private static String jsonString;
    private static String json="{}";
    private String httpRequestUrl ="";
    private String getHttpBaseUrl ="https://reqres.in";


    private static Map<String ,String> apiResponseData;

    @Before
    public void setUp() throws Exception {
        httpClientHelper = new HTTPClientHelper();
        objectMapper = new ObjectMapper();
        apiResponseData = new HashMap<>();
        }


    @Given("User prepares {string} Request")
    public void userPrepares(String requestType) {

        switch (requestType){
            case "Get All User":
                httpRequestUrl = getHttpBaseUrl + "/api/users?page=2";
                break;
            case "Missing Details":
                httpRequestUrl = "https://reqres.in/api/register";
                break;
                default:
                    Assert.fail("Invalid request type");
        }
    }

    @When("{string} Request is Submitted")
    public void requestIsSubmitted(String httprequestType) {
        switch (httprequestType){
            case "GET":
                apiResponseData =HTTPClientHelper.submitGetRequestAndGetResponse(httpRequestUrl);
                     break;
                case "POST":
                    apiResponseData =HTTPClientHelper.submitPostRequestAndPostResponse(httpRequestUrl,json);
                    break;
                    default:
                        Assert.fail("Invalid http request type");
        }
    }

    @Then("Response Code is {string}")
    public void responseCodeIs(String responseCode) {
        Assert.assertEquals(responseCode, apiResponseData.get("ResponseCode"));
    }

    @And("user response data is matched as expected")
    public void userResponseDataIsMatched() {

        jsonString =apiResponseData.get("ResponseBody");
        System.out.println(jsonString);

        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray jsonArray = new JSONArray(jsonObject.get("data").toString());

        int perPage = (int) jsonObject.get("per_page");
        int total = (int) jsonObject.get("total");

        String useremail = (String) jsonArray.getJSONObject(0).get("email");

        Assert.assertFalse(jsonArray.isEmpty());
        Assert.assertEquals(perPage,6);
        Assert.assertEquals(total,12 );
        Assert.assertEquals(useremail,"michael.lawson@reqres.in");
    }

    @Given("User prepares {string} Request with Below Data")
    public void userPreparesWithBelowData(String requestType,DataTable dataTable) {

        switch (requestType){
            case "Create new User":
                httpRequestUrl = getHttpBaseUrl + "/api/users";

                List<Map<String, String>> requestRows = dataTable.asMaps(String.class, String.class);
                Map<String, String> requestRow = requestRows.getFirst();

                String body = requestRow.get("body");
                String title = requestRow.get("title");

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("title",title);
                jsonObject.put("body",body);

                json = jsonObject.toString();
                break;
                default:
                    Assert.fail("Invalid request type");
        }
    }
}
