# roadstatus

A command line app to fetch status of Road from [TfL API](https://api.tfl.gov.uk/)

## Prerequisites
- JDK 1.8
- Internet connection to be able to download Maven dependencies
 
## App Id & Key

Before building the application App Id & Key should be updated in `src/main/resources/application.properties`

## Building application

```
cd <application_directory>
./mvnw clean package
```

## Execution

```
cd <application_directory>
java -jar target/roadstatus-1.0.jar <road_id>
 
Ex: 
java -jar target/roadstatus-1.0.jar A2 
```

**Note:** Additional arguments passed are ignored