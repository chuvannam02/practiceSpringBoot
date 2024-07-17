package com.test.practiceProject.Response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.practiceProject.DTO.BookDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class BaseResponse {
    @JsonProperty("error_code")
    private String error_code;

    @JsonProperty("message")
    private String message;

    private Object object;

    public BaseResponse() {
        this.error_code = "0";
        this.message = "SUCCESS";
    }

    public <T> void setObject(T properties) {
        this.object = properties;
    }
    public <T> void setPageResponseObject(PageResponse<T> page) {
        this.object = page;
    }
}
