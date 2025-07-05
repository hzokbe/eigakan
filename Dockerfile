FROM maven:3.9.10-amazoncorretto-21-alpine

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

RUN cp target/*.jar eigakan.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "eigakan.jar"]
