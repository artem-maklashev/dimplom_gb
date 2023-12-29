FROM openjdk:20

ENV SPRING_DATASOURCE_URL="jdbc:mysql://host.docker.internal:3306/goldengroup"
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=402986

# Копируем JAR файл из двух уровней вверх относительно Dockerfile
COPY target/goldengroup-0.0.1-SNAPSHOT.jar /tmp/goldengroup-0.0.1-SNAPSHOT.jar

WORKDIR /tmp

CMD ["java", "-jar", "/tmp/goldengroup-0.0.1-SNAPSHOT.jar"]