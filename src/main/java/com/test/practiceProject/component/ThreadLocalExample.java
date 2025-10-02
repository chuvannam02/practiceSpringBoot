package com.test.practiceProject.component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Components  *
 * @Author: ChuVanNam
 * @Date: 10/2/2025
 * @Time: 12:04 AM
 */

public class ThreadLocalExample {
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(2);

		for (int i = 1; i <= 3; i++) {
			int userId = i;
			pool.submit(() -> {
				UserContext.setUser("User-" + userId);
				System.out.println(java.lang.Thread.currentThread().getName() + " -> " + UserContext.getUser());
				UserContext.clear();;
				// ❌ Quên gọi UserContext.clear();
			});
		}

		pool.shutdown();
	}
}
