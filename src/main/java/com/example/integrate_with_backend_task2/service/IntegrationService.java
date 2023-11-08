package com.example.integrate_with_backend_task2.service;

import com.example.integrate_with_backend_task2.entity.InputData;
import com.example.integrate_with_backend_task2.exception.ApiRequestException;
import com.example.integrate_with_backend_task2.model.OutputData;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Component
@Service
public class IntegrationService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${mock.service.url}")
    private String mockServiceUrl;
    public OutputData handleControlerData (InputData inputData) {
        validateDataEntered(inputData);
        OutputData outputData = getDataForUser(inputData);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create HttpEntity
        HttpEntity<InputData> requestEntity = new HttpEntity<>(inputData, headers);

        // Make the external request
        ResponseEntity<String> responseEntity = restTemplate.exchange(mockServiceUrl, HttpMethod.POST, requestEntity, String.class);
        String responseEntityData = responseEntity.getBody();

        // Assuming the responseEntityData is a JSON string and has a field "statusDesc"
        try {
            JSONObject jsonResponse = new JSONObject(responseEntityData);
            if (jsonResponse.has("statusDesc")) {
                String statusDesc = jsonResponse.getString("statusDesc");
                System.out.println("statusDesc"+statusDesc);
                outputData.setStatusDesc(statusDesc);
            }
        } catch (JSONException e) {
            throw new ApiRequestException("Invalid JSON response from backend server");
        }

        return outputData;
    }

    public OutputData getDataForUser(InputData inputData)
    {
        OutputData outputData=new OutputData();
        outputData.setCode("200");
        String mas=getMassageForUser(inputData);
        outputData.setMessage(mas);
        return outputData;
    }
    public void validateDataEntered(InputData inputData) {
        // Null-check for inputData to prevent NullPointerException
        if (inputData == null) {
            throw new ApiRequestException("InputData object cannot be null");
        }

        // Validate SMS field
        String sms = inputData.getSms();
        if (sms == null || sms.trim().isEmpty() || "null".equalsIgnoreCase(sms)) {
            throw new ApiRequestException("The message cannot be null or empty");
        }

        // Validate PhoneNumber field
        String phoneNumber = inputData.getPhoneNumber();
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new ApiRequestException("The PhoneNumber value cannot be null or empty");
        } else if (!phoneNumber.matches("^(\\+20|0020|0)(15|11|12|10)\\d{8}$")) {
            throw new ApiRequestException("The phone number is not valid");
        }
    }


    public String getMassageForUser(InputData inputData)
    {

        String message =
                inputData.getSms().length() > 20 ? "This massage will be sent as 2 sms" : "This massage will be sent as 1 sms";


        return message;
    }


}
