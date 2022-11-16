# API REST FaunaGalicia
BackEnd side of this REST API built with Spring Boot.

It uses an embedded HSQLDB database that contains around more than one hundred animal species present in the region of Galicia (Spain).

The API has been documented (and its services can be consumed) using Swagger UI. You can build your own frontend to consume the API services.

## Getting Started
To start the application you must have Maven installed. If you don't have it, you can follow these [instructions](https://mkyong.com/maven/how-to-install-maven-in-windows) to do it.

Once you have Maven installed on your local computer, follow the next steps:
1. ```$ git clone https://github.com/rlastresvillar/springboot-apirest-faunagalicia.git```
2. ```$ cd springboot-apirest-faunagalicia```
3. ```$ mvn spring-boot:run```:

The port of embedded Tomcat Server in this Spring Boot application is 8085, then you can open your browser and access the next URL:

```http://localhost:8085/```

## Built with
* Eclipse IDE.
* Maven.
* Spring Boot.
* Swagger.
* HSQLBD.

## Author
* Rubén Lastres Villar.
