package com.stc.assessment.controller;

import com.stc.assessment.DTO.ResponseData;
import com.stc.assessment.utils.ResponseRequestBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseController {
    private static final Logger logger = LogManager.getLogger(BaseController.class);
    @Autowired
    protected ResponseRequestBuilder responseReqBuilder;

    protected ResponseEntity<ResponseData> wrapException(Exception ex, Logger logger) {
        ResponseData apiResponse = responseReqBuilder.wrapFailureResponse(ex.getMessage());
        logger.error(ex.getMessage());
        ex.printStackTrace();
        return ResponseEntity.badRequest().body(apiResponse);
    }


    protected ResponseEntity<ResponseData> wrapException(String message, Logger logger) {
        ResponseData apiResponse = responseReqBuilder.wrapFailureResponse(message);
        logger.error("ValidationsErrors due to " + message);
        return ResponseEntity.badRequest().body(apiResponse);
    }

    protected ResponseEntity<ResponseData> wrapNullPointerException(NullPointerException ex, Logger logger) {
        String message = "Null Pointer Exception occurred due to : " + ex.getCause();
        ResponseData apiResponse = responseReqBuilder.wrapFailureResponse(message);
        logger.error(message);
        ex.printStackTrace();
        return ResponseEntity.badRequest().body(apiResponse);
    }

}

