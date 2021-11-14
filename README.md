# test-europeana
## Running the project
This project needs at least JDK11 to run.

The following maven command will start up the project

    mvn spring-boot:run
## End points

    GET http://localhost:8080/test-europeana/rest/ping
    POST http://localhost:8080/test-europeana/rest/upper/{value}
    GET http://localhost:8080/test-europeana/rest/calculate/{value}

