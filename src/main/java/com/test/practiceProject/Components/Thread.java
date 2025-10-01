package com.test.practiceProject.Components;

import java.time.LocalTime;
import java.util.Random;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Components  *
 * @Author: ChuVanNam
 * @Date: 10/1/2025
 * @Time: 4:57 PM
 */

public class Thread {
    // Ví dụ tạp hoá nhỏ = > chỉ có 1 người bán hàng
    // Giả sử đây là ví tiền của người bán hàng
//    private int totalMoneyInWallet = 0;

    // => Để tránh tình trạng nhều luồng cùng sửa đổi 1 biến chung => Có 2 phương án
    // 1 Là sử dụng Lock()
    // 2 là sử dụng từ khoá synchronized()
    private int totalMoneyInWallet = 0;

    private synchronized Integer getMoney(int money) {
        System.out.println(java.lang.Thread.currentThread().getName() + ": Khách hàng đưa " + money);
        totalMoneyInWallet += money;
        System.out.println(java.lang.Thread.currentThread().getName() + ": Tổng tiền trong ví:  " + totalMoneyInWallet + " TIME | " + LocalTime.now());
        return money;
    }
    public static void main(String[] args) {
        Thread main = new Thread();
        // Chỉ có 1 người thu ngân thôi
//        java.lang.Thread cashier1 = new java.lang.Thread(() -> {
//            // Người khác hàng 1 mua hàng => ví tăng random 1 số lượng
//            main.getMoney(new Random().nextInt(100)); // customer 1
//            try {
//                // Sau 2 giây khách hàng 1 mua => đến khách hàng 2 mua
//                java.lang.Thread.sleep(2*1000);
//            } catch (InterruptedException ex) {
//                throw new RuntimeException(ex);
//            }

            // Người khác hàng 2 mua hàng => ví tăng random 1 số lượng
//            main.getMoney(new Random().nextInt(100)); // customer 2

            // Tuy nhiên trong thực tế thì k chỉ có 2 khách hàng
            // Giả sử số lượng khách hàng tăng thêm 2
//            try {
//                // Sau 2 giây khách hàng 1 mua => đến khách hàng 2 mua
//                java.lang.Thread.sleep(2*1000);
//            } catch (InterruptedException ex) {
//                throw new RuntimeException(ex);
//            }
//
//            main.getMoney(new Random().nextInt(100)); // customer 1
//            try {
//                // Sau 2 giây khách hàng 1 mua => đến khách hàng 2 mua
//                java.lang.Thread.sleep(2*1000);
//            } catch (InterruptedException ex) {
//                throw new RuntimeException(ex);
//            }
//            main.getMoney(new Random().nextInt(100)); // customer 1
            // => Tổng cộng mất 6 giây để phục vụ cho 4 khác hàng
            // => Lúc này cần mở rộng thêm số luồng Thread để phục vụ cho nhiều khách hàng hơn
//        }, "cashier1");
        // Khai báo Thread => chưa chạy nhưng đã được cấp phát bộ nhớ

        // Khi nào gọi start => Thread mới bắt đầu chạy
//        cashier1.start();

        // Người thu ngân thứ 2
//        java.lang.Thread cashier2 = new java.lang.Thread(() -> {
//            main.getMoney(new Random().nextInt(100));
//            try {
//                java.lang.Thread.sleep(2*1000);
//            } catch (InterruptedException ex) {
//                throw new RuntimeException(ex);
//            }
//
//            main.getMoney(new Random().nextInt(100));
//        }, "cashier2");
//        cashier2.start();;
        // Dùng 2 Thread => Giảm thời gian xuống 4s
//        cashier2: Khách hàng đưa 0
//        cashier1: Khách hàng đưa 84
//        cashier2: Tổng tiền trong ví:  0 TIME | 20:56:18.288095300
//        cashier1: Tổng tiền trong ví:  84 TIME | 20:56:18.288095300
//        cashier2: Khách hàng đưa 61
//        cashier1: Khách hàng đưa 25
//        cashier1: Tổng tiền trong ví:  170 TIME | 20:56:20.307315
//        cashier2: Tổng tiền trong ví:  145 TIME | 20:56:20.307315

        // Tuy nhiên dùng nhiều Thread => dữ liệu totalMoneyInWallet bị không nhất quán
        // Lập trình đồng thời cao đi vô => sửa đổi cùng 1 biến => số lượng tồn kho không nhất quán với mysql => Sai con số cuối cùng

        // => Để tránh tình trạng nhều luồng cùng sửa đổi 1 biến chung => Có 2 phương án
        // 1 Là sử dụng Lock()
        // 2 là sử dụng từ khoá synchronized()

        // => KQ
//        cashier1: Khách hàng đưa 48
//        cashier2: Khách hàng đưa 1
//        cashier1: Tổng tiền trong ví:  48 TIME | 21:02:19.611839600
//        cashier2: Tổng tiền trong ví:  49 TIME | 21:02:19.611839600
//        cashier2: Khách hàng đưa 41
//        cashier1: Khách hàng đưa 96
//        cashier2: Tổng tiền trong ví:  90 TIME | 21:02:21.619046
//        cashier1: Tổng tiền trong ví:  186 TIME | 21:02:21.619046

        // => Đưa về chạy tuần tự => cashier1 xử lý => Khoá totalMoneyInWallet lại không cho ai sử dụng
        // Sau khi xử lý nhận số tiền từ khách hàng xong thì mới nhả tài nguyên => cashier2 tiếp tục xử lý
        // => Không gây ảnh hưởng truy cập ví

        // Tuy nhiên trong thực tế k chỉ có 2 Thread mà có thể có cả 100, 1000 Thread đồng thời cao
        // => Các luồng Thread sẽ cạnh tranh lẫn nhau để khoá bản ghi cùng 1 lúc
        // => Cần chờ thời gian mở khoá của Thread đã sử dụng trước đó
        // Tuần tự Thread 1 xong => Thread 2 .....=> Biết bao giờ mới giải quyết xong bài toán.
        // Tiếp theo mô phỏng 100 Thread bằng vòng lặp for
        for (int i = 1; i <= 100; i++) {
            java.lang.Thread cashierI = new java.lang.Thread(() -> {
                main.getMoney(new Random().nextInt(100));
                try {
                    java.lang.Thread.sleep(2*1000);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                main.getMoney(new Random().nextInt(100));
            }, "cashier" + i);
            cashierI.start();;
        }

        // Tuy nhiên việc khởi tạo mới quá nhiều Thread 1 lúc như vậy = > hiệu suất thấp
        // => Gom lại thành các nhóm luồng => ThreadPool ra đời
    }
}
