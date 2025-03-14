package com.test.practiceProject.Utils;

import java.util.ArrayList;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Utils  *
 * @Author: Admin
 * @Date: 1/9/2025
 * @Time: 4:29 PM
 */

public class NoNullsList<E> extends ArrayList<E> {
    public void add(int index, E element) {
        if (element != null) {
            super.add(index, element);
        }
    }

    public boolean add(E element) {
        return element == null ? false : super.add(element);
    }
}
