package com.test.practiceProject.Controller;

import com.test.practiceProject.Interfaces.impl.LogContext;
import com.test.practiceProject.Interfaces.impl.LogFactory;
import com.test.practiceProject.Interfaces.impl.TextLog;

import java.util.ArrayList;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Controller  *
 * @Author: ChuVanNam
 * @Date: 8/2/2025
 * @Time: 10:26 PM
 */

public class Test {
    public static void main(String[] args) {
//        System.out.println("Hello, World!");
//        int[] arr = {10, 20, 10, 5, 15};
//        int k = 3;
//        Test test = new Test();
//        int maxSum = test.maxSubArray(arr, k);
//        System.out.println("Tổng lớn nhất của mảng con có độ dài " + k + " là: " + maxSum);

//        LogContext context = new LogContext(new TextLog("Hello World"));
//        context.log(); // dùng TextLog
        LogContext context = new LogContext();

        context.setLoggable(LogFactory.create("TEXT", "Hello World"));
        context.log();

        context.setLoggable(LogFactory.create("CONSOLE", "Hello CONSOLE"));
        context.log();

    }

    private ArrayList<Integer> prefixSum(int[] nums) {
        ArrayList<Integer> result = new ArrayList<>();
        int sum = 0;
        for (int num : nums) {
            sum += num;
            result.add(sum);
        }
        return result;
    }

    private int findMax(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    // Bài toán: Tìm tổng của mảng con lớn nhất k = x
    private int maxSubArraySum(int[] nums, int k) {
        if (k <= 0 || k > nums.length) {
            throw new IllegalArgumentException("k must be between 1 and the length of the array");
        }
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i <= nums.length - k; i++) {
            int currentSum = 0;
            for (int j = i; j < i + k; j++) {
                currentSum += nums[j];
            }
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }
        return maxSum;
    }

    private int maxSubArray(int[] arr, int k) {
        int n = arr.length;
        if (k <= 0 || k > n) {
            throw new IllegalArgumentException("k must be between 1 and the length of the array");
        }

        int windowSum = 0;
        // Tính tổng của cửa sổ đầu tiên
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }

        int maxSum = windowSum;

        // Trượt cửa sổ từ trái sang phải
        for (int i = k; i < n; i++) {
            windowSum = windowSum - arr[i - k] + arr[i];
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }

}
