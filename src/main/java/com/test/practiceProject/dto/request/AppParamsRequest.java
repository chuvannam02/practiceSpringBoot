package com.test.practiceProject.dto.request;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class AppParamsRequest {
    private String paramName;
    private String paramValue;
}
