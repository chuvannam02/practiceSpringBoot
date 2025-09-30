Trước khi chạy build docker image từ dockerfile rồi build thành docker container thì cần cài đặt mvn.
Sau đó kiểm tra xem đã cài đặt mavn (maven) thành công hay chưa bằng câu lệnh mvn -v.
Tiếp tục chạy lệnh mvn package để build project tạo file jar trong thư mục target.

Để build Docker image nhưng chỉ định Dockerfile có tên khác, bạn dùng flag -f.
docker build -t practice-app -f <tên-file-dockerfile> <đường-dẫn-build-context>

🧠 Ví dụ:
Giả sử bạn có file tên là Dockerfile.prod, bạn chạy:
docker build -t practice-app -f Dockerfile.prod .
. là build context — thư mục chứa source code (thường là thư mục hiện tại).

docker tag practice-app chuvannam/practice_springboot
docker push chuvannam/practice_springboot

# Ứng dụng Design Pattern vào thực tế
## Sử dụng Creational Design Pattern - Factory Pattern
-- Tạo ra một Factory đóng vai trò khởi tạo ra các object tuỳ thuộc vào type của chúng
### Ví dụ: Có rất nhiều kiểu thông báo khác nhau NotificationType: Slack, Email, Telegram, .... Viết service tương ứng với từng loại service
- Bước 1: Tạo ra enum NotificationType quản lý các Type
  public enum NotificationType {
  EMAIL,
  SMS,
  TEGEGRAM,
  SLACK,
  ZALO,
  MESSENGER,
  PUSH
  }
- Bước 2: Tạo ra interface chung cho các loại service Notification
  public interface NotificationService {
  NotificationType getType(String type);
  String send(String message);
  }
- Bước 3: Viết các implementation từ interface
- Bước 4: Tạo ra NotificationFactory
