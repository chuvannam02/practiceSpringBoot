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

# 👋 Hi, I'm Chu Van Nam

## Web Developer | Top Skill: Angular & Java Spring Boot

### 🔹 Tech Stack

- **Frontend:**  
  ![Angular](https://img.shields.io/badge/-Angular-DD0031?logo=angular&logoColor=white)  
  ![ReactJS](https://img.shields.io/badge/-ReactJS-61DAFB?logo=react&logoColor=black)

- **Backend:**  
  ![Java Spring Boot](https://img.shields.io/badge/-Spring%20Boot-6DB33F?logo=spring-boot&logoColor=white)  
  ![NodeJS](https://img.shields.io/badge/-NodeJS-339933?logo=node.js&logoColor=white)

- **Database:**  
  ![PostgreSQL](https://img.shields.io/badge/-PostgreSQL-4169E1?logo=postgresql&logoColor=white)  
  ![Oracle](https://img.shields.io/badge/-Oracle-F80000?logo=oracle&logoColor=white)  
  ![MongoDB](https://img.shields.io/badge/-MongoDB-47A248?logo=mongodb&logoColor=white)

- **DevOps:**  
  ![Docker](https://img.shields.io/badge/-Docker-2496ED?logo=docker&logoColor=white)  
  ![Kubernetes](https://img.shields.io/badge/-Kubernetes-326CE5?logo=kubernetes&logoColor=white)  
  ![Linux](https://img.shields.io/badge/-Linux-FCC624?logo=linux&logoColor=black)  
  ![Jenkins](https://img.shields.io/badge/-Jenkins-D24939?logo=jenkins&logoColor=white)  
  ![GitLab](https://img.shields.io/badge/-GitLab-FC6D26?logo=gitlab&logoColor=white)  
  ![Git](https://img.shields.io/badge/-Git-F05032?logo=git&logoColor=white)  
  ![VM](https://img.shields.io/badge/-Virtual%20Machine-0078D6?logo=windows&logoColor=white)

### 🔹 About Me

- 💻 Passionate about building modern, scalable web applications.
- 🌱 Strong experience developing Single Page Applications (SPA) with Angular and robust backend services with Spring Boot.
- 🔗 Skilled in designing & optimizing databases (PostgreSQL, Oracle, MongoDB).
- 🚀 Hands-on with CI/CD, containerization, and cloud-native tools (Docker, K8s, Jenkins, GitLab).
- 🏆 Led multiple projects from scratch to deployment in enterprise environments.

### 🔹 Contact

- [LinkedIn](https://www.linkedin.com/in/chuvannam02/)
- Email: chuvannam02@gmail.com

---

*Always learning, always growing!*

---

🚀 Quản lý nhiều phiên bản Java JDK với SDKMAN!
✅ Giới thiệu

SDKMAN!
 là công cụ quản lý nhiều phiên bản JDK và các SDK khác (Gradle, Maven, Kotlin, Scala, v.v…).
Thay vì phải gỡ cài đặt và cấu hình thủ công biến môi trường, bạn có thể dễ dàng cài đặt, chuyển đổi, gỡ bỏ chỉ bằng vài lệnh đơn giản.

🛠️ Bước 1: Gỡ cài đặt Java JDK cũ
Trước khi dùng SDKMAN, nên gỡ bản JDK cài thủ công để tránh xung đột:
Nhấn tổ hợp phím: Windows + I → Apps → Installed apps → Tìm Java JDK → Uninstall.

🧹 Bước 2: Xoá các biến môi trường Java
Mở Environment Variables (Win + R → sysdm.cpl → Advanced → Environment Variables).

Xoá:
JAVA_HOME
Các dòng trong Path có chứa %JAVA_HOME% hoặc C:\Program Files\Java\...
📌 Ví dụ:
JAVA_HOME = C:\Program Files\Java\jdk-17
Path = ...;%JAVA_HOME%\bin;...
<img width="425" height="457" alt="image" src="https://github.com/user-attachments/assets/18837da3-33ef-43b2-8173-027ad79b2148" />
<img width="3350" height="1347" alt="image" src="https://github.com/user-attachments/assets/8fbb8155-8b11-4e2b-8f77-f56412e06d89" />
<img width="613" height="706" alt="image" src="https://github.com/user-attachments/assets/4002069c-519e-4edc-bc81-e2bdc73bc11c" />
<img width="886" height="972" alt="image" src="https://github.com/user-attachments/assets/9945288e-c5b4-4daf-a0ef-fcb86141a16f" />
👉 Xoá đi để sau này SDKMAN quản lý.

🐧 Bước 3: Cài đặt SDKMAN!
SDKMAN không chạy trực tiếp trong CMD/PowerShell, mà cần dùng Git Bash hoặc WSL.
Mở Git Bash
<img width="319" height="382" alt="image" src="https://github.com/user-attachments/assets/1f48dcb3-3a08-41db-a66e-fd714f3d2dbf" />
Chạy lệnh:
curl -s "https://get.sdkman.io" | bash
<img width="644" height="375" alt="image" src="https://github.com/user-attachments/assets/8066c3df-026a-47c9-957d-72c226458279" />
Sau khi cài xong, tải lại config:
source "$HOME/.sdkman/bin/sdkman-init.sh"

Kiểm tra:
sdk version
<img width="439" height="158" alt="image" src="https://github.com/user-attachments/assets/94426252-3ad3-46b7-a320-2fcf54544e33" />
✅ Nếu thấy version hiện ra tức là cài thành công.

☕ Bước 4: Cài đặt và quản lý JDK bằng SDKMAN
🔹 Liệt kê các phiên bản JDK có sẵn
sdk list java

🔹 Cài JDK 21 (Eclipse Temurin)
sdk install java 21.0.4-tem

🔹 Chuyển mặc định sang JDK 21
sdk default java 21.0.4-tem


Hoặc chỉ dùng tạm trong shell hiện tại:

sdk use java 21.0.4-tem

🔹 Kiểm tra phiên bản hiện tại
java --version

🧰 Một số lệnh hữu ích
Lệnh	Chức năng
sdk list java	Xem các phiên bản JDK khả dụng
sdk install java <version>	Cài đặt JDK mới
sdk uninstall java <version>	Gỡ bỏ JDK
sdk use java <version>	Dùng JDK tạm thời trong shell
sdk default java <version>	Đặt JDK mặc định cho tất cả shell
⚡ Kết luận

Với SDKMAN!, bạn có thể:
Cài nhiều bản JDK (8, 11, 17, 21, …) song song.
Chuyển đổi nhanh chóng bằng một lệnh.

---
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
