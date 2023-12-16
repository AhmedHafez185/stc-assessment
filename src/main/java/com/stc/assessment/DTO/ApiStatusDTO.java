package com.stc.assessment.DTO;

import java.util.List;

public class ApiStatusDTO {
    private String message;
    private String code;

    public ApiStatusDTO() {

    }
    public ApiStatusDTO(String code, String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
