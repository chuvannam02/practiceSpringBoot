package com.test.practiceProject.component;

import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.component  *
 * @Author: ChuVanNam
 * @Date: 10/6/2025
 * @Time: 12:06 AM
 */

public class CaptureOf {
//    public static void updateNumbers(List<Number> numbers) {

    //    }
    public static void main(String[] args) {
        // Parameterized Lists
        // As the type Integer extends Number => we might want to call this method (updateNumbers) with a list of Integer
        List<Integer> numbers = Arrays.asList(1, 2, 3);
//        updateNumbers(numbers);
        // Đoạn code không đươc biên dịch
        // Vì mặc dù Integer extends Number nhưng Integer<Integer> thì không extends List<Number>
        // java: incompatible types: java.util.List<java.lang.Integer> cannot be converted to java.util.List<java.lang.Number>

        // Wildcards
        // lower-bounded wildcards
//        process(numbers);
//        updateNumbers(numbers);
        // Using Type Parameters
//        updateNumberTypeParameters(numbers, Integer.valueOf(5), 1);
//        System.out.println(numbers.get(1));

        // ===============================
        // 1️⃣ Unbounded — ?
        // ===============================
//        List<String> names = List.of("Alice", "Bob", "Charlie");
//        List<Integer> nums = List.of(1, 2, 3);
//        printAll(names);
//        printAll(nums);
//
//        // ===============================
//        // 2️⃣ Upper-bounded — ? extends Number
//        // ===============================
//        List<Integer> intList = List.of(10, 20, 30);
//        List<Double> doubleList = List.of(1.5, 2.5, 3.5);
//
//        System.out.println("Tổng intList = " + sumNumbers(intList));
//        System.out.println("Tổng doubleList = " + sumNumbers(doubleList));
//        System.out.println();
//
//        // ===============================
//        // 3️⃣ Lower-bounded — ? super Integer
//        // ===============================
//        List<Number> numberList = new ArrayList<>();
//        List<Object> objectList = new ArrayList<>();
//
//        addIntegers(numberList);
//        addIntegers(objectList);

        System.out.println(getNumber(numbers));
    }
    public static <T extends Number> void updateNumberTypeParameters(List<T> numbers, T element, int index) {
//        numbers.add(element);
        numbers.set(index, element);
    }

    public static void process(List<? super Number> list) {
        list.add(10);        // ✅ OK
//        Object obj = list.get(0); // ✅ Only Object
    }

//    Nếu bạn muốn được phép ghi (set/add), thì không dùng extends, mà dùng super:
    public static void updateNumbers(List<? super Number> numbers) {
        numbers.set(0, Integer.valueOf(1)); // OK
    }

    public static <T extends Number> T getNumber(List<T> numbers) {
        return numbers.get(0);
    } ;

    // 1️⃣ Unbounded wildcard — ? (chỉ đọc)
    static void printAll(List<?> list) {
        System.out.println("📦 Unbounded: In ra tất cả phần tử");
        for (Object obj : list) {
            System.out.println(" - " + obj);
        }

        // ❌ list.add("Hello"); // Không thể thêm phần tử vì không biết kiểu cụ thể
        System.out.println();
    }

    // 2️⃣ Upper-bounded wildcard — ? extends Number (chỉ đọc)
    static double sumNumbers(List<? extends Number> numbers) {
        System.out.println("📦 Upper-bounded: Tính tổng các số");
        double sum = 0;
        for (Number n : numbers) { // ✅ có thể đọc vì chắc chắn là Number hoặc con của Number
            sum += n.doubleValue();
        }

        // ❌ numbers.add(10); // Không thể thêm (compiler không biết kiểu cụ thể: Integer, Double, ...)

        return sum;
    }

    // 3️⃣ Lower-bounded wildcard — ? super Integer (chỉ ghi)
    static void addIntegers(List<? super Integer> list) {
        System.out.println("📦 Lower-bounded: Thêm các số nguyên vào list");
        list.add(100);
        list.add(200);
        list.add(300);
        // ✅ có thể thêm Integer vì mọi kiểu cha của Integer (Number, Object) đều chứa được Integer

        // ❌ Integer x = list.get(0); // Không thể đọc vì compiler chỉ biết kiểu trả về là Object
        System.out.println("Sau khi thêm: " + list);
        System.out.println();
    }

}
