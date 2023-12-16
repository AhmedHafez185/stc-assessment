package com.stc.assessment.DTO;

import com.google.gson.Gson;

import java.util.Map;

public class RequestData {

    // Members
    private Map<String,Object> data;


    public RequestData() {
    }

    public RequestData(Map<String, Object> data) {
        this.data = data;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this, RequestData.class);
    }
}
