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

- [LinkedIn](https://www.linkedin.com/in/chuvannam02/) <!-- Update this link if needed -->
- Email: chuvannam02@gmail.com

---

*Always learning, always growing!*
