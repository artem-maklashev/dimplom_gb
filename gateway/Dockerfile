FROM openjdk:20

# Копируем JAR файл из двух уровней вверх относительно Dockerfile
COPY target/gateway-0.0.1-SNAPSHOT.jar /tmp/gateway-0.0.1-SNAPSHOT.jar
WORKDIR /tmp

EXPOSE 8080

CMD ["java", "-jar", "/tmp/gateway-0.0.1-SNAPSHOT.jar"]