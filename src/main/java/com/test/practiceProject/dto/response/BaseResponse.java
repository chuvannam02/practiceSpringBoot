package com.test.practiceProject.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
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
