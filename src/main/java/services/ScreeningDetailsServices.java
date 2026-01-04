package services;

import base.APIControlActions;
import base.ScreeningControl;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class ScreeningDetailsServices extends APIControlActions {

    public Response getScreeningDetails( String screenTestID) {

        setHeader("Accept", "application/json");

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("candidateApplicationId", ScreeningControl.jobApplicationID);
        queryParams.put("candidateScreeningId", ScreeningControl.candidateScreeningId);
        setQueryParams(queryParams);

        return executeGetAPI("/api/candidateScreening/get-screening-questions/" + screenTestID);
    }

}
