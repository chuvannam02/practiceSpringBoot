package com.test.practiceProject.component;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Components  *
 * @Author: ChuVanNam
 * @Date: 10/1/2025
 * @Time: 10:49 PM
 */

public class ThreadLocal {
	// each Thread will have its own copy of this variable
	private  java.lang.ThreadLocal<Integer> totalMoneyInWallet = new java.lang.ThreadLocal<>() {
		// Mỗi Thread sẽ tạo ra dữ liệu riêng của nó
		// Mỗi thu ngân của siêu thị sẽ bắt đầu ngày làm việc với ví tiền = 0
		@Override
		protected Integer initialValue() {
			return 0; // Initial wallet value for each thread
		}
	};

	// Quỹ chung của siêu thị
//	🚀 AtomicInteger là gì?
//	AtomicInteger là một class trong gói java.util.concurrent.atomic.
//	Nó cung cấp một biến số nguyên (int) mà các thread có thể cùng đọc/ghi một cách an toàn (thread-safe) mà không cần dùng từ khóa synchronized.
//	👉 "Atomic" nghĩa là: mỗi thao tác trên nó (tăng, giảm, cộng, so sánh-đổi giá trị) đều là nguyên tử → không bị chia nhỏ, không bị "chen ngang" bởi thread khác.

//	📌 Khi nào dùng AtomicInteger?
//	Khi cần một biến số nguyên dùng chung cho nhiều thread.
//	VD: đếm số request, quỹ chung trong ví dụ siêu thị của bạn, ID generator.
//	Khi muốn tránh synchronized để tăng hiệu năng.
//	Khi thao tác chỉ là cộng, trừ, set, compare-and-swap đơn giản.
//	🔎 So sánh nhanh
//	int bình thường → không thread-safe.
//	AtomicInteger → thread-safe, lock-free (CAS).
//	synchronized → thread-safe nhưng chậm hơn vì lock/unlock.
	private final AtomicInteger supermarketFund = new AtomicInteger(0);

	// NO need for synchronized anymore
	private int getMoney(Integer money) {
		System.out.println(java.lang.Thread.currentThread().getName() + ": Customer give: " + money + "$");
		// Get current wallet balance of the current thread
		// Tự quản lý ví tiền của mình thông qua get set
		// Ví riêng của từng thu ngân
		int currentMoney = totalMoneyInWallet.get();
		totalMoneyInWallet.set(currentMoney + money);

		// Cập nhật quỹ chung
		supermarketFund.addAndGet(money);

		// Update the wallet balance for the current thread
		totalMoneyInWallet.set(currentMoney + money);

		System.out.println(java.lang.Thread.currentThread().getName() + ": Total money in wallet is: " + totalMoneyInWallet.get() + ", TIME | " + LocalTime.now());

		return money;

	}
	public static void main(String[] args) {
		ThreadLocal obj = new ThreadLocal();
//		ExecutorService threadPool = Executors.newFixedThreadPool(10);
//
//		for (int i = 1; i <= 100; i++) {
//			threadPool.submit(() -> {
//				obj.getMoney(new Random().nextInt(100));
//				try {
//					java.lang.Thread.sleep(2 * 2000);
//				} catch (InterruptedException ex) {
////                    throw new RuntimeException(ex);
//					java.lang.Thread.currentThread().interrupt();
//				}
//				obj.getMoney(new Random().nextInt(100));
//			});
//		}
//
//		threadPool.shutdown();

		try (AutoCloseableExecutor threadPool = new AutoCloseableExecutor(Executors.newFixedThreadPool(10))) {
//			archery.get().submit(() -> System.out.println("CR7: shoot!"));
//			archery.get().submit(() -> System.out.println("M10: shoot!"));

			for (int i = 1; i <= 100; i++) {
				threadPool.submit(() -> {
					obj.getMoney(new Random().nextInt(100));
					try {
						java.lang.Thread.sleep(2 * 2000);
					} catch (InterruptedException ex) {
//                    throw new RuntimeException(ex);
						java.lang.Thread.currentThread().interrupt();
					}
					obj.getMoney(new Random().nextInt(100));
				});
			}
		} // ✅ auto shutdown + awaitTermination ở đây

		// ✅ In ra tổng thu sau khi đóng quầy
		System.out.println("=== Supermarket total fund = " + obj.supermarketFund.get() + " $ ===");

		// Nhược điểm: => dễ bị rò rỉ bộ nhớ nếu không biết kill, stop Thread
	}
}
