package com.test.practiceProject.Entity;

import com.test.practiceProject.Interfaces.AnimalInterface;
import org.springframework.stereotype.Component;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Entity  *
 * @Author: Admin
 * @Date: 1/12/2025
 * @Time: 9:41 AM
 */

    @Component("dog")
    public class Dog implements AnimalInterface {
        @Override
        public void makeSound() {
            System.out.println("Bark Bark Bark!");
        }
    }
