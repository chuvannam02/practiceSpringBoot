FROM openjdk:17
ADD target/practiceProject-0.0.1-SNAPSHOT.jar practiceProject-0.0.1-SNAPSHOT.jar
ADD ./ ./
EXPOSE 8080
ENTRYPOINT ["java", "-Duser.timezone=GMT+7", "-jar", "practiceProject-0.0.1-SNAPSHOT.jar"]
