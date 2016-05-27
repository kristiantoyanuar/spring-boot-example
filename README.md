# Spring Boot Example (Full Project)
This project use Spring Boot
## Commands

**Configure**

Open `application.properties` located on `modules/api/src/main/resources/` and change the database url, username and password to your local database

**Install**

Maven:
```
mvn clean test install
```

Gradle:
```
gradle clean test build
```

**Run it**

Execute this on folder module/api
Maven:
```
mvn spring-bot:run
```

Gradle:
```
gradle clean test build
```

**Usage**

On console, find this line
```
Using default security password: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
```

Access the API with username 'user' and use the provided password above
```
$ curl -u user:xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx http://localhost:8080/api/student/1
```

will return 
```
{"createdDate":null,"createdBy":null,"lastModified":null,"modifiedBy":null,"firstName":"John","middleName":null,"lastName":"Doe","birthDate":null,"gender":null,"id":1,"code":"S_01"}
```

## Project Structure
```
+ ROOT (Parent)
+-----api (rest controllers)
+-----core (models, repos, and logics)
```