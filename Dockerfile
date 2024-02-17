FROM eclipse-temurin:21-alpine as base


WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve

COPY src ./src

CMD ["./mvnw", "spring-boot:run", "-Dspring-boot.run.profiles=postgresql", "-Dspring-boot.run.jvmArguments='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000'"]


FROM base as builder

RUN ./mvnw clean package -Dmaven.test.skip

FROM eclipse-temurin:21-alpine
EXPOSE 8080
COPY --from=builder /app/target/docker_practice-0.0.1-SNAPSHOT.jar /docker-practice.jar
CMD ["java", "-jar", "/docker-practice.jar"]
