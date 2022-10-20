## About
The project objectives are to explore various features of Spring Boot.

## Features

* **Profiles**

>`spring.profiles.active` - allows to specify active profiles to be used by an app.

```shell
./gradlew clean build

java -jar -Dspring.profiles.active=qa  build/libs/spring-boot-features-exploration-0.0.1-SNAPSHOT.jar
```

* **Actuator**

> Spring Boot actuator module provides production-ready features. 
> The recommended way to enable the features is to add 
> a dependency on the spring-boot-starter-actuator “Starter”. 
> More details [here](https://docs.spring.io/spring-boot/docs/current/actuator-api/htmlsingle/) 
> and [here](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#actuator).

