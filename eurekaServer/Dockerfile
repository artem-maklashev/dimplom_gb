FROM openjdk:20

# Копируем JAR файл из двух уровней вверх относительно Dockerfile
COPY target/eurekaServer-0.0.1-SNAPSHOT.jar /tmp/eurekaServer-0.0.1-SNAPSHOT.jar
WORKDIR /tmp

EXPOSE 8761

CMD ["java", "-jar", "/tmp/eurekaServer-0.0.1-SNAPSHOT.jar"]