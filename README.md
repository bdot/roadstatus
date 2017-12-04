# roadstatus

A command line app to fetch status of Road from [TfL API](https://api.tfl.gov.uk/)

## Prerequisites
- JDK 1.8
- App Id & Key from [TfL API Portal](https://api-portal.tfl.gov.uk/login)
- Internet connection to be able to download Maven dependencies
 
## App Id & Key

Before building the application App Id & Key should be updated in `src/main/resources/application.properties`

> (Note: API does seem to work without this information! But to avoid any surprises, it is strongly recommended to add this info)

## Assumptions
- Even though Operating System is not a constraint, application is only tested on Mac OS. 

## Building application

```
cd <application_directory>
./mvnw clean package -DskipTests
```

## Execution

```
cd <application_directory>
java -jar target/roadstatus-1.0.jar <road_id>
 
Ex: 
java -jar target/roadstatus-1.0.jar A2 
```

> (Note: Additional arguments passed are ignored)

## Running Tests

- Unit Tests
    ```
    ./mvnw clean test
    ```    
- Integration Tests
    ```
    ./mvnw clean install -Pintegration-test
    ```    

## ToDo
- Suppressing application log while running integration tests

## References
- https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html
- https://sdqali.in/blog/2015/12/10/integration-testing-challenges-for-non-web-spring-applications/