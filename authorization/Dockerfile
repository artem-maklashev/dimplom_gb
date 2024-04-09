FROM openjdk:20

ENV SPRING_DATASOURCE_URL="jdbc:mysql://192.168.0.9:33006/jwt_authorization"
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=402986

# Копируем JAR файл из двух уровней вверх относительно Dockerfile
COPY target/authorization-0.0.1-SNAPSHOT.jar /tmp/authorization-0.0.1-SNAPSHOT.jar
WORKDIR /tmp

EXPOSE 8079

CMD ["java", "-jar", "/tmp/authorization-0.0.1-SNAPSHOT.jar"]