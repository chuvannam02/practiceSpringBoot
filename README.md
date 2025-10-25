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


# Hướng dẫn cấu hình Redis
### Khởi tạo Redis server bằng docker
docker run -d --name redis -p 6379:6379 redis:8.0-alpine
- Tuy nhiên cần tạo volumn vì nếu tắt container => dữ liệu không được lưu vào đâu => mỗi lần khởi chạy là dữ lệu không còn gì
1. Tạo Docker volume (xuống dòng bằng cách gõ 2 lần phím space ở cuối dòng)  
docker volume create redis_data
2. Chạy Redis với volume mount  
   docker run -d \
   --name redis \
   -p 6379:6379 \
   -v redis_data:/data \
   redis:7.2 \
   redis-server --appendonly yes  
   docker run -d --name redis -p 6379:6379 -v redis_data:/data redis:8.0-alpine redis-server --appendonly yes  
+ -v redis_data:/data → mount volume redis_data vào thư mục /data trong Redis container.
+ --appendonly yes → bật AOF persistence, Redis sẽ ghi dữ liệu ra file /data/appendonly.aof.  
🔍 Kiểm tra
Xem container đang chạy:
docker ps

Xem volume đã mount:
docker inspect redis
Trong phần Mounts sẽ thấy Source: redis_data, Destination: /data.

🔄 Khi bạn stop & remove container
docker stop redis && docker rm redis

### 3️⃣ Set TTL (Time To Live) cho Redis key trong Spring Boot
Có 2 cách
1. (a) Dùng RedisTemplate
   - @Service  
   public class CacheService {  

       @Autowired  
       private RedisTemplate<String, String> redisTemplate;     
              
       public void saveWithTTL(String key, String value, long ttlSeconds) {  
            redisTemplate.opsForValue().set(key, value, ttlSeconds, TimeUnit.SECONDS);  
       }  

       public String get(String key) {  
           return redisTemplate.opsForValue().get(key);  
       }  
   }  
   - 👉 Khi gọi saveWithTTL("user:1", "Nam", 120), key sẽ tự động hết hạn sau 120s.
2. (b) Dùng Spring Cache abstraction
- Khai báo trong application.yml:

spring:  
  cache:  
    type: redis  
  
- Thêm config TTL mặc định trong RedisCacheConfiguration:  
@Configuration    
@EnableCaching    
public class RedisConfig {  

    @Bean  
    public RedisCacheManager cacheManager(RedisConnectionFactory factory) {  
        return RedisCacheManager.builder(factory)  
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig()  
                        .entryTtl(Duration.ofMinutes(5))) // TTL mặc định 5 phút  
                .build();  
    }  
}  
- @Service  
public class UserService {  
  
    @Cacheable(value = "users", key = "#id")  
    public String getUserById(String id) {  
        // Lấy từ DB giả định  
        return "User-" + id;  
    }  
}  
👉 Cache users::id sẽ tự động expire sau 5 phút

=============================================================================================================================================

# 📧 Gửi Email Tùy Chỉnh với Thymeleaf và Java Mail Sender

## 17.5.1. Cài đặt và cấu hình Java Mail Sender

### 🧩 17.5.1.1. Bước 1: Thêm dependencies cần thiết vào `pom.xml`

Thêm các dependency sau vào bên trong cặp thẻ `<dependencies>` trong file `pom.xml`:

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-mail</artifactId>
</dependency>

<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

⚙️ 17.5.1.2. Bước 2: Cấu hình email trong file application.yml hoặc .properties

Mở file application.yml hoặc .properties trong thư mục resources (root project) và thêm cấu hình mail như sau:

```java
spring:
  mail:
    host: smtp.gmail.com
    port: 587
    username: email@gmail.com   # (email của bạn)
    password: xxxx xxxx xxxx xxxx  # (App password, KHÔNG phải mật khẩu Gmail thật)
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
```

🛠️ 17.5.1.3. Bước 3: Tạo file EmailConfig.java và cấu hình Java Mail Sender truyền env từ application.yml

Tạo file EmailConfig.java để cấu hình Thymeleaf và tạo thư mục email-templates trong src/main/resources để chứa các template email.

```java
@Configuration
public class EmailConfig {
 @Bean
    public TemplateEngine emailTemplateEngine() {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("email-templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setCacheable(false);

        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(resolver);
        return engine;
    }
}
```
Giải thích:

Java8TimeDialect: là extension giúp Thymeleaf hỗ trợ các kiểu dữ liệu ngày tháng như LocalDate, LocalDateTime, LocalTime thông qua biến #temporals.

Ví dụ sử dụng:
```html
<span th:text="${#temporals.format(reportDate, 'yyyy-MM-dd')}"></span>
```
Để dùng được Java8TimeDialect, thêm dependency sau vào pom.xml:
```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.8.2</version>
</dependency>
```

🔒 17.5.2. Cấu hình thông tin xác thực SMTP với Gmail

Ứng dụng cần có khả năng gửi email cho người dùng — ví dụ như:

- Reset mật khẩu

- Xác thực tài khoản

- Gửi thông báo quan trọng

Vì vậy, ta cần thiết lập SMTP server để gửi email thông qua Gmail.

17.5.2.1. Bước 1: Đăng nhập tài khoản Gmail

Truy cập https://www.gmail.com, đăng nhập bằng tài khoản Gmail của bạn (không cần gõ phần @gmail.com).

🔐 17.5.2.2. Bước 2: Bật xác thực 2 bước (Two-Factor Authentication)

Click vào avatar cá nhân góc trên phải → “Quản lý Tài khoản Google của bạn”
Hệ thống sẽ điều hướng tới:
👉 https://myaccount.google.com/

Chọn menu “Bảo mật” → “Xác minh 2 bước”
Bật tính năng này (nếu chưa bật).

🔑 17.5.2.3. Bước 3: Tạo mật khẩu ứng dụng (App Password)

Trong mục Bảo mật (Security) → click “Xác minh 2 bước (2-Step verification)”.
Sau đó cuộn xuống mục “Mật khẩu ứng dụng (App passwords)”.

Nhập tên ứng dụng (ví dụ: SpringBootEmailService).

Click Tạo (Generate).

Hệ thống hiển thị 16 ký tự — sao chép và lưu lại cẩn thận.

Ví dụ mật khẩu ứng dụng được tạo:
```
xxxx xxxx xxxx xxxx
```

⚠️ Lưu ý: Mật khẩu này chỉ hiển thị một lần duy nhất, không thể xem lại.

⚡ 17.5.2.4. Bước 4: Cấu hình SMTP cho Gmail trong application.yml

Thông tin cấu hình Gmail SMTP:

Thuộc tính	Giá trị
Máy chủ SMTP	smtp.gmail.com
Cổng (TLS)	587
Cổng (SSL)	465
Username	Địa chỉ Gmail của bạn
Password	App Password (16 ký tự)
Encryption	TLS hoặc SSL tương ứng

Ví dụ cấu hình:
```yml
spring:
  mail:
    host: smtp.gmail.com
    port: 587
    username: your.email@gmail.com
    password: pgxmbfajgcxwxvjp
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
```
