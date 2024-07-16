package com.test.practiceProject.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Data
@Getter
@Slf4j
public class RandomStuff {
    private String message;

    public RandomStuff(String message) {
        this.message = message;
        log.info(this.message);
    }
}
