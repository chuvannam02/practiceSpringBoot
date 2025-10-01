package com.test.practiceProject.Components;

import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Components  *
 * @Author: ChuVanNam
 * @Date: 10/1/2025
 * @Time: 9:17 PM
 */

public class ThreadPool {
	// Tạo Thread thì tốn tài nguyên
	// Huỷ Thread cũng mất thời gian
	// Quên HUỷ Thread => gây ra vấn đề hiệu năng => giảm hiệu quả ứng dụng
	// => Phát hành ra ThreadPool
	// = > POOL ở đây là tạo ra các Thread và tái sử dụng lại thay vì cứ liên tục tạo ra các Thread mới

	private int totalMoneyInWallet = 0;

	private synchronized Integer getMoney(Integer money) {
		System.out.println(java.lang.Thread.currentThread().getName() + ": Khách hàng đưa: " + money);
		totalMoneyInWallet += money;
		System.out.println(java.lang.Thread.currentThread().getName() + ": Tổng số tiền trong ví: " + totalMoneyInWallet + ", TIME | " + LocalTime.now());
		return money;
	}

	public static void main(String[] args) {
		ThreadPool obj = new ThreadPool();
		// Tạo ra một ThreadPool với 10 luồng cố định
		// Mỗi lần tạo 10 Thread
//		ExecutorService threadPool = Executors.newFixedThreadPool(10);
//
//		for (int i = 1; i <= 100; i++) {
//			threadPool.submit(() -> { // Sử dụng method .submit() thay vì trực tiếp tạo ra Thread
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
		// Pool tạo ra 10 Thread và tái sử dụng lại các Thread này thay vì tạo ra các Thread mới

		// Nhược điểm: vẫn chia sẻ dữ liệu chung là ví tiền của siêu thị
		// Có cách nào chia sẻ tài nguyên mỗi Thread quản lý 1 ví riêng k? thay vì để các Thread tranh giành nhau quyền truy cập vào 1 biến chung như totalMoneyInWallet
		// = > sinh ra từ khoá ThreadLocal
		// Thay vì để các luồng tranh giành nhau quyền truy cập vào một biến chung => ThreadLocal cho phép mỗi Thread có một bản sao riêng biệt của biến đó
		// => Thread có thể thao tác trên dữ liệu của mình mà không cần phải chờ đợi => không sử dụng keyword synchronized nữa => tăng tốc hệ thống

		// Nhược điểm:
		// - ThreadPool phải biết kích thước của ThreadPool = ? thì mới phù hợp nhất
		// - DeadLock

		// Cung tên
//		Object bow = new Object();
		// Mũi tên
//		Object arrow = new Object();

//		ExecutorService archery = Executors.newFixedThreadPool(2);
//		// Để bắn được tên thì cần bow (cái cung) + arrow (mũi tên)
//		// CR7 và M10 nằm trong 2 luồng ngồi chờ nhau mà không chịu nhường cho nhau = > Deadlock
//		// CR7: got bow => waiting arrow from M10
//		archery.submit(() -> {
//			synchronized (bow) {
//				System.out.println("CR7: got bow => waiting arrow from M10");
//				try {
//					java.lang.Thread.sleep(100);
//				} catch (InterruptedException exception) {
//					throw new RuntimeException(exception);
//				}
//
//				synchronized (arrow) {
//					System.out.println("CR7: OK ---> archery");
//				}
//			}
//		});
//
//		// M10: got arrow => waiting bow from CR7
//		archery.submit(() -> {
//			synchronized (arrow) {
//				System.out.println("M10: got arrow => waiting bow from CR7");
//				try {
//					java.lang.Thread.sleep(100);
//				} catch (InterruptedException exception) {
//					throw new RuntimeException(exception);
//				}
//
//				synchronized (bow) {
//					System.out.println("M10: OK ---> archery");
//				}
//			}
//		});
//
//		System.out.println("Starting executor...");
//		archery.shutdown();;

		// => KQ: Starting executor...
		//CR7: got bow => waiting arrow from M10
		//M10: got arrow => waiting bow from CR7

//		✅ Cách tránh: dùng ReentrantLock với tryLock thay vì synchronized
//		tryLock() cho phép thử lấy lock, nếu không lấy được thì thả ra, làm việc khác, hoặc thử lại sau → tránh bị block vô thời hạn như synchronized.
//		Ví dụ code refactor:
		// Cung và tên thay vì Object -> dùng Lock
		Lock bow = new ReentrantLock();
		Lock arrow = new ReentrantLock();

		ExecutorService archery = Executors.newFixedThreadPool(2);

		// CR7: muốn bow + arrow
//		archery.submit(() -> {
//			try {
//				if (bow.tryLock()) {
//					System.out.println("CR7: got bow => waiting arrow from M10");
//					java.lang.Thread.sleep(100);
//
//					if (arrow.tryLock()) {
//						try {
//							System.out.println("CR7: OK ---> archery");
//						} finally {
//							arrow.unlock();
//						}
//					} else {
//						System.out.println("CR7: couldn't get arrow, give up!");
//					}
//				} else {
//					System.out.println("CR7: couldn't get bow, give up!");
//				}
//			} catch (InterruptedException e) {
//				java.lang.Thread.currentThread().interrupt();
//			} finally {
//				if (bow.tryLock()) { // chỉ unlock nếu đã lock được
//					bow.unlock();
//				}
//			}
//		});

		// M10: muốn arrow + bow
//		archery.submit(() -> {
//			try {
//				if (arrow.tryLock()) {
//					System.out.println("M10: got arrow => waiting bow from CR7");
//					java.lang.Thread.sleep(100);
//
//					if (bow.tryLock()) {
//						try {
//							System.out.println("M10: OK ---> archery");
//						} finally {
//							bow.unlock();
//						}
//					} else {
//						System.out.println("M10: couldn't get bow, give up!");
//					}
//				} else {
//					System.out.println("M10: couldn't get arrow, give up!");
//				}
//			} catch (InterruptedException e) {
//				java.lang.Thread.currentThread().interrupt();
//			} finally {
//				if (arrow.tryLock()) {
//					arrow.unlock();
//				}
//			}
//		});
		// CR7
		archery.submit(() -> {
			try {
				if (bow.tryLock(500, TimeUnit.MILLISECONDS)) {
					System.out.println("CR7: got bow => waiting arrow from M10");
					java.lang.Thread.sleep(100);

					try {
						if (arrow.tryLock(500, TimeUnit.MILLISECONDS)) {
							try {
								System.out.println("CR7: OK ---> archery");
							} finally {
								arrow.unlock();
							}
						} else {
							System.out.println("CR7: couldn't get arrow, give up!");
						}
					} finally {
						bow.unlock();
					}
				} else {
					System.out.println("CR7: couldn't get bow, give up!");
				}
			} catch (InterruptedException e) {
				java.lang.Thread.currentThread().interrupt();
			}
		});

		// M10
		archery.submit(() -> {
			try {
				if (arrow.tryLock(500, TimeUnit.MILLISECONDS)) {
					System.out.println("M10: got arrow => waiting bow from CR7");
					java.lang.Thread.sleep(100);

					try {
						if (bow.tryLock(500, TimeUnit.MILLISECONDS)) {
							try {
								System.out.println("M10: OK ---> archery");
							} finally {
								bow.unlock();
							}
						} else {
							System.out.println("M10: couldn't get bow, give up!");
						}
					} finally {
						arrow.unlock();
					}
				} else {
					System.out.println("M10: couldn't get arrow, give up!");
				}
			} catch (InterruptedException e) {
				java.lang.Thread.currentThread().interrupt();
			}
		});

		System.out.println("Starting executor...");
		archery.shutdown();
	}
}
