package com.bini.Lucky_Round.controller;
import com.bini.Lucky_Round.dto.SpinB2CRequest;
import com.bini.Lucky_Round.dto.SpinB2CResponse;
import com.bini.Lucky_Round.dto.SpinRewardRequest;
import com.bini.Lucky_Round.dto.SpinRewardResponse;
import com.bini.Lucky_Round.service.ProcessB2C;
import com.bini.Lucky_Round.service.SpinProbabilityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("api/v1/process")
public class ProcessController {

    public ProcessController(SpinProbabilityService processRequest, ProcessB2C processB2C) {
        this.processRequest = processRequest;
        this.processB2C = processB2C;
    }

    private final SpinProbabilityService processRequest;
    private final ProcessB2C processB2C;
    private static final Logger logManager = LoggerFactory.getLogger(ProcessController.class);


    @PostMapping("/reward")
    public ResponseEntity<SpinRewardResponse> processReward(@RequestBody SpinRewardRequest spinRequest) throws NoSuchAlgorithmException, JsonProcessingException, KeyManagementException {



        SpinRewardResponse response = processRequest.processRequest(spinRequest, (java.util.logging.Logger) logManager);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/claim/b2c")
    public ResponseEntity<SpinB2CResponse> processB2C(@RequestBody SpinB2CRequest spinB2CRequest) throws NoSuchAlgorithmException, JsonProcessingException, KeyManagementException {


        processB2C.processB2CReward(spinB2CRequest, (java.util.logging.Logger) logManager);

        return ResponseEntity.ok(SpinB2CResponse.builder()
                .responseCode("200")
                .build());

    }
}
