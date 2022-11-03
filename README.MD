## Required Tools and Equipment
* Java 11
* Docker
* Terraform 1.3.0 or higher
* AWS CLI & AWS Account

## How to

* ### Run the app locally

```shell
./gradlew clean build
```
```shell
java -jar -Dspring.profiles.active=dev  build/libs/spring-boot-features-exploration-0.0.1-SNAPSHOT.jar
```

* ### Build docker image and run a container locally

Notice, for ARM64 platforms(e.g. M1 processor) the following flag 
has to be applied for build and run commands: `--platform=linux/amd64`.
Another option is to replace Dockerfile in root directory with the one in arm64 directory.

```shell
./gradlew clean build
```
```shell
docker build -t spring-boot-feature-exploration:1.0 .
```
```shell
docker run -d -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=dev" spring-boot-feature-exploration:1.0
```

* ### Deploy the app to AWS EC2

    * Prepare docker image in a repository
    
       Create a repository by [the instruction](https://docs.docker.com/docker-hub/#step-2-create-your-first-repository)
       and execute the following commands:
    ```shell
    ./gradlew clean build
    ```
    ```shell
    docker build -t <your_username>/<your_repository_name>:1.0 .
    ```
    ```shell
    docker push <your_username>/<your_repository_name>:1.0
    ```

    * Generate public key and private key to ssh to the EC2 instance.
       Put the keys into terraform directory. Public key has to be named `id_rsa.pub`
    ```shell
    ssh-keygen -t rsa
    ```

    * Configure AWS CLI to interact with AWS.<br>
      More information is [here](https://docs.aws.amazon.com/cli/latest/userguide/cli-configure-quickstart.html).
    ```shell
    aws configure
    ```

    * Deploy the app

       Open terraform directory in a terminal and run the following commands:
    ```shell
    terraform init
    ```
    ```shell
    terraform plan
    ```
    ```shell
    terraform apply
    ```
    To open the app use public IP assigned to the EC2 instance.<br><br>
    For destroying:
    ```shell
    terraform destroy
    ```
    Command example for connecting to the instance using SSH:
    ```shell
    ssh -i "private_key_path" ec2-user@ec2-34-242-152-56.eu-west-1.compute.amazonaws.com
    ```

## Features

* **Profiles**

>`spring.profiles.active` and `SPRING_PROFILES_ACTIVE` - allow to specify active profiles to be used by an app.

* **Actuator**

> Spring Boot actuator module provides production-ready features. 
> The recommended way to enable the features is to add 
> a dependency on the spring-boot-starter-actuator “Starter”. 
> More details are [here](https://docs.spring.io/spring-boot/docs/current/actuator-api/htmlsingle/) 
> and [here](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#actuator).

