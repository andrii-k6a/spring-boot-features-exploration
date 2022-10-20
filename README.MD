## About
The project objectives are to explore various features of Spring Boot.

## Features

* **Profiles**

>`spring.profiles.active` - allows to specify active profiles to be used by an app.

```shell
./gradlew clean build

java -jar -Dspring.profiles.active=qa  build/libs/spring-boot-features-exploration-0.0.1-SNAPSHOT.jar
```
