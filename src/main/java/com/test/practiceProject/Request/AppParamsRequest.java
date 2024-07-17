package com.test.practiceProject.Request;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class AppParamsRequest {
    private String paramName;
    private String paramValue;
}
