package com.test.practiceProject.Entity;

import com.test.practiceProject.Interfaces.AnimalInterface;
import org.springframework.context.annotation.Primary;
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
