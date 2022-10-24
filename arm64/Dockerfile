FROM arm64v8/amazoncorretto:11
USER nobody
ARG JAR_FILE=build/libs/spring-boot-features-exploration-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]