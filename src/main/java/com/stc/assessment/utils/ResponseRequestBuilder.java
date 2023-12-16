package com.stc.assessment.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stc.assessment.DTO.ApiStatusDTO;
import com.stc.assessment.DTO.ErrorDTO;
import com.stc.assessment.DTO.RequestData;
import com.stc.assessment.DTO.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ResponseRequestBuilder<T> {

    @Autowired
    ObjectMapper objectMapper;

    public <T> T getRequestData(RequestData requestDTO, Class<T> clazz) throws Exception {
        try {
            if (requestDTO.getData() == null) {
                throw new Exception("Missing Request Body");
            }
            return objectMapper.convertValue(requestDTO.getData(), clazz);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new Exception("Invalid Request Data");
        }
    }

    public Map getRequestData(RequestData requestData) {
        Map<String, Object> data = requestData.getData() == null ? new HashMap<>() : requestData.getData();
        return data == null ? new HashMap() : data;
    }

    public Object getRequestData(RequestData requestDTO, String attribute) throws Exception {
        try {
            if (requestDTO.getData() == null) {
                throw new Exception("Missing Request Body");
            }
            return requestDTO.getData().get(attribute);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new Exception("Invalid Request Data");
        }
    }

    public Object getRequestDataWithValidateNull(RequestData requestDTO, String attribute) throws Exception {
        try {
            if (requestDTO.getData() == null) {
                throw new Exception("Missing Request Body");
            }
            Object object = requestDTO.getData().get(attribute);
            if (object == null) {
                throw new Exception("Missing Request Attribute : " + attribute);
            }
            return object;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new Exception("Invalid Request Body");
        }
    }

    /**
     * @param status
     * @return
     */
    public ResponseData wrapEmptySuccessResponse(ApiStatusDTO status) {
        ResponseData response = new ResponseData();
        response.setStatus(status);
        return response;
    }

    /**
     * @param message
     * @return
     */
    public ResponseData wrapFailureResponse(String message) {
        return wrapEmptySuccessResponse(generateFailureResponse(message));
    }

    /**
     * @param value
     * @param name
     * @param status
     * @return
     */
    public ResponseData wrapEmptySuccessResponse(String name, T value, ApiStatusDTO status) {
        Map<String, Object> data = new HashMap<>();
        ResponseData response = new ResponseData();
        data.put(name, value);
        response.setData(data);
        response.setStatus(status);
        return response;
    }

    /**
     * @param name
     * @param value
     * @param responseData
     * @return
     */
    public ResponseData addAttributeToResponseBody(String name, T value, ResponseData responseData) {
        Map<String, Object> data = (Map<String, Object>) responseData.getData();
        data.put(name, value);
        responseData.setData(data);
        return responseData;
    }

    /**
     * @param value
     * @param name
     * @return
     */
    public ResponseData wrapSuccessResponse(String name, T value) {
        Map<String, Object> data = new HashMap<>();
        ResponseData response = new ResponseData();
        data.put(name, value);
        response.setData(data);
        response.setStatus(generateSuccessResponse());
        return response;
    }

    public ResponseData wrapSuccessResponse(T data) {
        ResponseData response = new ResponseData();
        response.setData(data);
        response.setStatus(generateSuccessResponse());
        return response;
    }

    /**
     * @return
     */
    public ResponseData wrapEmptySuccessResponse() {
        ResponseData response = new ResponseData();
        response.setStatus(generateSuccessResponse());
        return response;
    }

    /**
     * @return
     */
    private ApiStatusDTO generateSuccessResponse() {
        return new ApiStatusDTO("200", "Operation done successfully");
    }

    /**
     * @param message
     * @return
     */
    private ApiStatusDTO generateFailureResponse(String message) {
        return new ApiStatusDTO("500", message);
    }

    public ResponseData wrapFailureResponseWithCode(String message, String code) {
        return wrapEmptySuccessResponse(generateFailureResponseWithCode(message, code));
    }

    private ApiStatusDTO generateFailureResponseWithCode(String message, String code) {
        return new ApiStatusDTO(code, message);
    }
}
