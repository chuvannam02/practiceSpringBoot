# Stage 1: Build
FROM maven:3-eclipse-temurin-17 as build

# Create project directory and copy source code
RUN mkdir -p /usr/src/project
COPY . /usr/src/project
WORKDIR /usr/src/project

# Build the project and skip tests
RUN mvn clean package -DskipTests

# Unpack the JAR and analyze module dependencies
RUN jar xf target/practiceProject-0.0.1-SNAPSHOT.jar
RUN jdeps --ignore-missing-deps \
    --recursive \
    --multi-release 17 \
    --print-module-deps \
    --class-path 'BOOT-INF/lib/*' \
    target/practiceProject-0.0.1-SNAPSHOT.jar > deps.info

# Create a custom JRE with required modules
RUN jlink \
    --add-modules $(cat deps.info) \
    --strip-debug \
    --compress 2 \
    --no-header-files \
    --no-man-pages \
    --output /myjre

# Stage 2: Runtime
FROM debian:bookworm-slim

# Set JAVA_HOME and PATH
ENV JAVA_HOME=/usr/lib/jvm/jdk-17
ENV PATH="$JAVA_HOME/bin:$PATH"

# Copy custom JRE from build stage
COPY --from=build /myjre $JAVA_HOME

# Create project directory and copy the JAR file
RUN mkdir -p /project
COPY --from=build /usr/src/project/target/practiceProject-0.0.1-SNAPSHOT.jar /project/

COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
CMD ["/wait-for-it.sh", "db", "3306", "java", "-Duser.timezone=GMT+7", "-jar", "practiceProject-0.0.1-SNAPSHOT.jar"]

# Set working directory and expose port
WORKDIR /project
EXPOSE 8080

# Start the Spring Boot application
ENTRYPOINT ["java", "-Duser.timezone=GMT+7", "-jar", "practiceProject-0.0.1-SNAPSHOT.jar"]