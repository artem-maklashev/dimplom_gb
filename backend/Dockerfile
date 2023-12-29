FROM openjdk:20

# Копируем JAR файл из двух уровней вверх относительно Dockerfile
COPY target/goldengroup-0.0.1-SNAPSHOT.jar /tmp/goldengroup-0.0.1-SNAPSHOT.jar

WORKDIR /tmp

CMD ["java", "-jar", "/tmp/goldengroup-0.0.1-SNAPSHOT.jar"]