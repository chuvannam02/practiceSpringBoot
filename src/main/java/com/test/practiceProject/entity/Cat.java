package com.test.practiceProject.entity;

import com.test.practiceProject.interfaces.AnimalInterface;
import org.springframework.stereotype.Component;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Entity  *
 * @Author: Admin
 * @Date: 1/12/2025
 * @Time: 9:40 AM
 */

@Component("cat")
//@Primary
public class Cat implements AnimalInterface {
    @Override
    public void makeSound() {
        System.out.println("Meow Meow Meow!");
    }
}
