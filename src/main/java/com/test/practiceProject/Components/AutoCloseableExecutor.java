package com.test.practiceProject.Components;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Components  *
 * @Author: ChuVanNam
 * @Date: 10/1/2025
 * @Time: 10:36 PM
 */

public class AutoCloseableExecutor implements AutoCloseable {
	private final ExecutorService executor;

	public AutoCloseableExecutor(ExecutorService executor) {
		this.executor = executor;
	}

	public ExecutorService get() {
		return executor;
	}

	@Override
	public void close() {
		executor.shutdown();
		try {
			if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
				executor.shutdownNow();
			}
		} catch (InterruptedException e) {
			executor.shutdownNow();
			java.lang.Thread.currentThread().interrupt();
		}
	}
}
