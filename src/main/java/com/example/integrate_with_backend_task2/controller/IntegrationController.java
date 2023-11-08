package com.example.integrate_with_backend_task2.controller;

import com.example.integrate_with_backend_task2.entity.InputData;
import com.example.integrate_with_backend_task2.model.OutputData;
import com.example.integrate_with_backend_task2.service.IntegrationService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntegrationController {
    @Autowired
    private IntegrationService integrationService;

    @PostMapping("/getUserData")
    public ResponseEntity<OutputData>  returnUserInfo(@RequestBody InputData inputData)
    {
        OutputData outputData = integrationService.handleControlerData(inputData);

        return new ResponseEntity<>(outputData, HttpStatus.OK);

    }
}
