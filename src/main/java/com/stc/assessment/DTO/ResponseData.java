package com.stc.assessment.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData<T> {

    // Members
    private T data;


    private ApiStatusDTO status;


    public ResponseData() {
    }

    public ResponseData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ApiStatusDTO getStatus() {
        return status;
    }

    public void setStatus(ApiStatusDTO status) {
        this.status = status;
    }


    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this, ResponseData.class);
    }
}
