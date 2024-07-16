package com.test.practiceProject.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserGithub {

    private String name;
    private String blog;

    @Override
    public String toString() {
        return "User [name=" + name + ", blog=" + blog + "]";
    }

}
