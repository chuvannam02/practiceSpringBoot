package com.test.practiceProject.component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Components  *
 * @Author: ChuVanNam
 * @Date: 10/1/2025
 * @Time: 10:36 PM
 */

//ExecutorService trong Java không implement AutoCloseable.
//Nghĩa là bạn không thể dùng nó trong try-with-resources:
//
//try (ExecutorService pool = Executors.newFixedThreadPool(5)) { // ❌ compile error
//    pool.submit(...);
//}
//
//
//Muốn tắt ExecutorService bạn phải nhớ gọi thủ công:
//
//ExecutorService pool = Executors.newFixedThreadPool(5);
//try {
//    pool.submit(...);
//} finally {
//    pool.shutdown();
//    pool.awaitTermination(...);
//}
//
//
//→ Dễ bị quên shutdown hoặc shutdown sai cách → gây rò rỉ thread (ứng dụng không thoát được).

//	✅ Giải pháp: AutoCloseableExecutor
//
//Bạn viết ra class bọc (wrapper) quanh ExecutorService, implement AutoCloseable.
//
//Nó giúp:
//
//Dùng được với try-with-resources → luôn shutdown đúng cách, ngay cả khi có exception.
//
//Tự động gọi shutdown() + awaitTermination() + fallback shutdownNow() nếu quá hạn.
//
//Giúp code gọn gàng, an toàn, tránh leak thread pool.

//💡 Ví dụ trước và sau
//
//Truyền thống (dễ quên shutdown):
//
//ExecutorService pool = Executors.newFixedThreadPool(10);
//try {
//    pool.submit(() -> System.out.println("Task running..."));
//} finally {
//    pool.shutdown();
//    if (!pool.awaitTermination(5, TimeUnit.SECONDS)) {
//        pool.shutdownNow();
//    }
//}
//
//
//Với AutoCloseableExecutor:
//
//try (AutoCloseableExecutor pool = new AutoCloseableExecutor(Executors.newFixedThreadPool(10))) {
//    pool.submit(() -> System.out.println("Task running..."));
//} // ✅ Tự động shutdown + awaitTermination
public class AutoCloseableExecutor implements AutoCloseable {
	private final ExecutorService executor;

	public AutoCloseableExecutor(ExecutorService executor) {
		this.executor = executor;
	}

	public ExecutorService get() {
		return executor;
	}

	// Cho phép submit trực tiếp
	public Future<?> submit(Runnable task) {
		return executor.submit(task);
	}

	public <T> Future<T> submit(java.util.concurrent.Callable<T> task) {
		return executor.submit(task);
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
