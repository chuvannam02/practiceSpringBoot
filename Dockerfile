# Stage 1: Build
# 🧱 Dùng image Maven (đã có Java 17) để build source code Java. Gán tên stage là build.
FROM maven:3-eclipse-temurin-17 as build

# Create project directory and copy source code
# 📂 Tạo thư mục /usr/src/project trong container và copy toàn bộ source code vào đó. Sau đó set thư mục làm việc hiện tại là thư mục chứa project.
RUN mkdir -p /usr/src/project
COPY . /usr/src/project
WORKDIR /usr/src/project

# Build the project and skip tests
# ⚙️ Chạy lệnh Maven để build project và bỏ qua test để giảm thời gian.
RUN mvn clean package -DskipTests

# Unpack the JAR and analyze module dependencies
# 📦 Giải nén file JAR sau khi build xong. Mục đích là để lấy các class và thư viện ra phục vụ cho phân tích dependency ở bước tiếp theo.
RUN jar xf target/practiceProject-0.0.1-SNAPSHOT.jar
# 🕵️ Phân tích các module JDK mà ứng dụng bạn thực sự cần, ghi vào deps.info. Sử dụng jdeps là bước tối ưu để chỉ đóng gói những module Java cần thiết.
RUN jdeps --ignore-missing-deps \
    --recursive \
    --multi-release 17 \
    --print-module-deps \
    --class-path 'BOOT-INF/lib/*' \
    target/practiceProject-0.0.1-SNAPSHOT.jar > deps.info

# Create a custom JRE with required modules
#🎯 Dùng jlink để tạo JRE tùy chỉnh chỉ chứa các module cần thiết → giảm kích thước image xuống rất nhiều (~40–80MB thay vì 300MB full JDK).
RUN jlink \
    --add-modules $(cat deps.info) \
    --strip-debug \
    --compress 2 \
    --no-header-files \
    --no-man-pages \
    --output /myjre

# Stage 2: Runtime
#🐧 Image Debian tối giản để chạy app → nhẹ, chỉ chứa những gì cần thiết để chạy ứng dụng.
FROM debian:bookworm-slim

# Tạo user không phải root
RUN useradd -r -u 1001 -g root appuser

# Cài các thư viện cơ bản và dọn dẹp cache
RUN apt-get update && apt-get install -y ca-certificates curl && \
    rm -rf /var/lib/apt/lists/*

# Set JAVA_HOME and PATH
#📍 Định nghĩa biến môi trường để chỉ ra vị trí JRE tùy chỉnh bạn đã tạo ở stage trước.
#ENV JAVA_HOME=/usr/lib/jvm/jdk-17
ENV JAVA_HOME=/opt/jdk
ENV PATH="$JAVA_HOME/bin:$PATH"

# Copy custom JRE from build stage
#📦 Copy custom JRE từ stage build sang runtime image.
COPY --from=build /myjre $JAVA_HOME

# Create project directory and copy the JAR file
#📂 Tạo thư mục chạy ứng dụng, copy JAR đã build từ stage build sang đây.
RUN mkdir -p /project
COPY --from=build /usr/src/project/target/practiceProject-0.0.1-SNAPSHOT.jar /project/

#📜 Copy script wait-for-it.sh dùng để chờ DB khởi động trước khi chạy app. Dùng trong CMD.
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
#🕒 Đây là lệnh mặc định nếu không ghi ENTRYPOINT. Script sẽ đợi DB (host db, port 3306) sẵn sàng, rồi mới khởi động app bằng java.
CMD ["/wait-for-it.sh", "db", "3306", "java", "-Duser.timezone=GMT+7", "-jar", "practiceProject-0.0.1-SNAPSHOT.jar"]

RUN chown -R appuser:root /project /wait-for-it.sh

# Switch user
USER appuser

# Set working directory and expose port
#📍 Chỉ định thư mục làm việc chính thức.
#🌐 EXPOSE 8080: cho biết app chạy cổng nào (mặc dù vẫn cần docker run -p hoặc docker-compose để publish cổng ra ngoài).
WORKDIR /project
EXPOSE 8080

# Start the Spring Boot application
#🔥 Đây mới là lệnh chạy thực tế khi container start (ghi đè CMD).
#Sẽ chạy app với:
#Timezone GMT+7
#Profile Spring Boot là prod
ENTRYPOINT ["java", "-Duser.timezone=GMT+7", "-Dspring.profiles.active=prod", "-jar", "practiceProject-0.0.1-SNAPSHOT.jar"]
