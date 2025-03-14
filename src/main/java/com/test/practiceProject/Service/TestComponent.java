package com.test.practiceProject.Service;

import com.test.practiceProject.Interfaces.AnimalInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Service  *
 * @Author: Admin
 * @Date: 1/12/2025
 * @Time: 9:44 AM
 */

@Component
public class TestComponent {
    private AnimalInterface animalInterface;

    public TestComponent(@Qualifier("cat") AnimalInterface animalInterface) {
        this.animalInterface = animalInterface;
    }
}
