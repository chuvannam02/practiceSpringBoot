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
