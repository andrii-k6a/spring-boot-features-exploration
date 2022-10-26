FROM amazoncorretto:11-alpine

RUN addgroup -S hugeCorpGroup && adduser -S hugeCorpUser -G hugeCorpGroup
USER hugeCorpUser:hugeCorpGroup

ARG JAR_FILE=build/libs/spring-boot-features-exploration-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080