## About
The project objectives are to explore various features of Spring Boot.

## How to

* Run the app locally

```shell
./gradlew clean build
```
```shell
java -jar -Dspring.profiles.active=dev  build/libs/spring-boot-features-exploration-0.0.1-SNAPSHOT.jar
```

* Build docker image and run a container locally

Notice, for ARM64 platforms(e.g. M1 processor) the following flag 
has to be applied for build and run commands: `--platform=linux/amd64`.
Another option is to replace Dockerfile in root directory with the one in arm64 directory.

```shell
./gradlew clean build
```
```shell
docker build -t huge-corp/spring-boot-feature-exploration:1.0 .
```
```shell
docker run -d -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=dev" huge-corp/spring-boot-feature-exploration:1.0
```

## Features

* **Profiles**

>`spring.profiles.active` and `SPRING_PROFILES_ACTIVE` - allow to specify active profiles to be used by an app.

* **Actuator**

> Spring Boot actuator module provides production-ready features. 
> The recommended way to enable the features is to add 
> a dependency on the spring-boot-starter-actuator “Starter”. 
> More details [here](https://docs.spring.io/spring-boot/docs/current/actuator-api/htmlsingle/) 
> and [here](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#actuator).

