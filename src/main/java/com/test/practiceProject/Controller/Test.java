package com.test.practiceProject.Controller;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Controller  *
 * @Author: ChuVanNam
 * @Date: 8/2/2025
 * @Time: 10:26 PM
 */

@Slf4j
public class Test {
//    public static void main(String[] args) {
//        System.out.println("Hello, World!");
//        int[] arr = {10, 20, 10, 5, 15};
//        int k = 3;
//        Test test = new Test();
//        int maxSum = test.maxSubArray(arr, k);
//        System.out.println("Tổng lớn nhất của mảng con có độ dài " + k + " là: " + maxSum);

//        LogContext context = new LogContext(new TextLog("Hello World"));
//        context.log(); // dùng TextLog
//        LogContext context = new LogContext();
//
//        context.setLoggable(LogFactory.create("TEXT", "Hello World"));
//        context.log();
//
//        context.setLoggable(LogFactory.create("CONSOLE", "Hello CONSOLE"));
//        context.log();
//        String sentence = "Hello world this is a Java example";
//        List<String> result = extractTopThreeLongWords(sentence);
//        List<String> result2 = extractTopThreeLongWordsV2(sentence);
//        System.out.println(result);
//        System.out.println(result2);
//    }

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

    // Cách 1: For + Sort
    public static List<String> extractTopThreeLongWords(String text) {
        long startNano = System.nanoTime();

        if (text == null || text.isBlank()) {
            System.out.println("Input rỗng, thời gian chạy: " + (System.nanoTime() - startNano));
            return List.of();
        }

        String[] listOfWords = text.trim().split("\\s+");
        List<String> topThreeLongWords = new ArrayList<>();
        for (String word : listOfWords) {
            if (word.length() > 4) {
                topThreeLongWords.add(word);
            }
        }

        topThreeLongWords.sort((w1, w2) -> Integer.compare(w2.length(), w1.length()));

        List<String> result = topThreeLongWords.size() > 3
                ? topThreeLongWords.subList(0, 3)
                : topThreeLongWords;

        long endNano = System.nanoTime();
        System.out.printf("For+Sort: %d ns (~%d ms)%n", (endNano - startNano), (endNano - startNano) / 1_000_000);

        return result;
    }

    // Cách 2: Stream
    public static List<String> extractTopThreeLongWordsStream(String text) {
        long startNano = System.nanoTime();

        if (text == null || text.isBlank()) {
            System.out.println("Input rỗng, thời gian chạy: " + (System.nanoTime() - startNano));
            return List.of();
        }

        List<String> result = Arrays.stream(text.trim().split("\\s+"))
                .filter(word -> word.length() > 4)
                .sorted(Comparator.comparingInt(String::length).reversed())
                .limit(3)
                .collect(Collectors.toCollection(() -> new ArrayList<>(3)));
//        List<String> result = Arrays.stream(text.trim().split("\\s+"))
//                .filter(word -> word.length() > 4)
//                .collect(
//                        Collector.of(
//                                () -> new PriorityQueue<>(3, Comparator.comparingInt(String::length)), // supplier
//                                (pq, word) -> {
//                                    pq.offer(word);
//                                    if (pq.size() > 3) pq.poll(); // chỉ giữ 3 phần tử dài nhất
//                                },
//                                (pq1, pq2) -> { // combiner (cho parallel stream)
//                                    pq2.forEach(w -> {
//                                        pq1.offer(w);
//                                        if (pq1.size() > 3) pq1.poll();
//                                    });
//                                    return pq1;
//                                },
//                                pq -> {
//                                    List<String> list = new ArrayList<>(pq);
//                                    list.sort(Comparator.comparingInt(String::length).reversed());
//                                    return list;
//                                }
//                        )
//                );

        long endNano = System.nanoTime();
        System.out.printf("Stream: %d ns (~%d ms)%n", (endNano - startNano), (endNano - startNano) / 1_000_000);

        return result;
    }

    // Cách 3: PriorityQueue
    public static List<String> extractTopThreeLongWordsPQ(String text) {
        long startNano = System.nanoTime();

        if (text == null || text.isBlank()) {
            System.out.println("Input rỗng, thời gian chạy: " + (System.nanoTime() - startNano));
            return List.of();
        }

        String[] listOfWords = text.trim().split("\\s+");
        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(String::length));

        for (String word : listOfWords) {
            if (word.length() > 4) {
                pq.offer(word);
                if (pq.size() > 3) {
                    pq.poll();
                }
            }
        }

        List<String> result = new ArrayList<>(pq);
        result.sort((a, b) -> b.length() - a.length());

        long endNano = System.nanoTime();
        System.out.printf("PriorityQueue: %d ns (~%d ms)%n", (endNano - startNano), (endNano - startNano) / 1_000_000);

        return result;
    }

    public static void main(String[] args) throws Exception {
        String baseSentence = "Hello world this is a Java example";
        // tạo chuỗi dài gấp 100_000 lần
        String sentence = generateLongSentence(baseSentence, 100_000);

        System.out.println("Input length: " + sentence.length());
        System.out.println("For+Sort -> " + extractTopThreeLongWords(sentence));
        System.out.println("Stream   -> " + extractTopThreeLongWordsStream(sentence));
        System.out.println("PQ       -> " + extractTopThreeLongWordsPQ(sentence));

        Test demo = new Test();

        Thread t1 = new Thread(demo::task1, "Thread-1");
        Thread t2 = new Thread(demo::task2, "Thread-2");

        t1.start();
        t2.start();
    }

    public static int getDefaultCapacity(List<?> list) throws Exception {
        if (list == null) return 0;

        Field field = ArrayList.class.getDeclaredField("DEFAULTCAPACITY_EMPTY_ELEMENTDATA");
        field.setAccessible(true);

        return ((Object[]) field.get(list)).length;
    }

    public static String generateLongSentence(String base, int repeat) {
        StringBuilder sb = new StringBuilder(base.length() * repeat);
        for (int i = 0; i < repeat; i++) {
            sb.append(base).append(" ");
        }
        return sb.toString().trim();
    }

//    private final Object lock1 = new Object();
//    private final Object lock2 = new Object();

//    Thread-2 got lock2, waiting for lock1...
//    Thread-1 got lock1, waiting for lock2...
//    public void task1() {
//        synchronized (lock1) {
//            System.out.println(Thread.currentThread().getName() + " got lock1, waiting for lock2...");
//            sleep(100); // chờ để thread khác kịp lấy lock2
//            synchronized (lock2) {
//                System.out.println(Thread.currentThread().getName() + " got lock2!");
//            }
//        }
//    }
//
//    public void task2() {
//        synchronized (lock2) {
//            System.out.println(Thread.currentThread().getName() + " got lock2, waiting for lock1...");
//            sleep(100);
//            synchronized (lock1) {
//                System.out.println(Thread.currentThread().getName() + " got lock1!");
//            }
//        }
//    }

    private void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
    private final ReentrantLock lock1 = new ReentrantLock();
    private final ReentrantLock lock2 = new ReentrantLock();

    public void task1() {
        try {
            if (lock1.tryLock(100, TimeUnit.MILLISECONDS)) {
                System.out.println(Thread.currentThread().getName() + " got lock1, trying for lock2...");
                try {
                    if (lock2.tryLock(100, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " got lock2!");
                        } finally {
                            lock2.unlock();
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + " could not get lock2, giving up!");
                    }
                } finally {
                    lock1.unlock();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void task2() {
        try {
            if (lock2.tryLock(100, TimeUnit.MILLISECONDS)) {
                System.out.println(Thread.currentThread().getName() + " got lock2, trying for lock1...");
                try {
                    if (lock1.tryLock(100, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " got lock1!");
                        } finally {
                            lock1.unlock();
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + " could not get lock1, giving up!");
                    }
                } finally {
                    lock2.unlock();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
