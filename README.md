# Spring Boot Example (Full Project)
This project use Spring Boot
## Commands

**Install**

```
mvn clean test install
```

**Run it**

Move to folder module/api
```
mvn spring-bot:run
```

**Usage**

On console, find this line
```
Using default security password: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
```

Access the API with username 'user' and use the provided password above
```
$ curl -u user:xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx http://localhost:8080/student/1
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