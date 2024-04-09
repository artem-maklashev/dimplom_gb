FROM openjdk:20

ENV SPRING_DATASOURCE_URL="jdbc:mysql://192.168.0.9:33006/goldengroup"
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=402986

# Копируем JAR файл из двух уровней вверх относительно Dockerfile
COPY target/goldengroup-0.0.1-SNAPSHOT.jar /tmp/goldengroup-0.0.1-SNAPSHOT.jar

WORKDIR /tmp

EXPOSE 8081

CMD ["java", "-jar", "/tmp/goldengroup-0.0.1-SNAPSHOT.jar"]