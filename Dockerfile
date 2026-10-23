FROM maven:3.9.9-eclipse-temurin-21

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn --batch-mode clean package

ENV LANG=C.UTF-8
ENV LC_ALL=C.UTF-8

CMD ["java", "-Dfile.encoding=UTF-8", "-jar", "target/otp_jutska.jar"]