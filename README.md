![Visitor Count](https://profile-counter.glitch.me/harikrishna553/count.svg)


# Spring Boot Example Projects

This repository is a comprehensive collection of Spring Boot example applications. Each project demonstrates a specific concept or integration pattern in Spring Boot and related technologies. These examples are intended for developers who want to learn how to build robust, scalable, and production-ready Spring Boot applications through practical, real-world use cases.

## Project Structure

The repository is organized into multiple directories by category. Each folder contains a self-contained Maven project:

- `webapplications/` – Spring MVC examples using REST controllers, JSP, and Thymeleaf
- `crud/` – CRUD operations with Spring Data JPA and databases like MySQL, PostgreSQL, and H2
- `batch/` – Spring Batch use cases, including tasklet and chunk-based jobs
- `microservices/` – Microservice patterns using Spring Cloud (Eureka, Config Server, Gateway)
- `security/` – Authentication and authorization using Spring Security, JWT, and OAuth2
- `integration/` – Integration with Redis, Kafka, RabbitMQ, MongoDB, Elasticsearch, Minio, JMS
- `fileupload/` – Upload and download files using local storage, database, or object storage
- `devops/` – Docker, GitHub Actions for CI/CD, external configuration examples

Each sub-project has its own source folder and `pom.xml`. Most projects are configured to run as standalone Spring Boot applications.

## How to Run a Project

1. Clone the repository:

   ```bash
   git clone https://github.com/harikrishna553/springboot.git
   cd springboot

2. Navigate to the example you want to run:
cd crud/springboot-crud-restapi

3. Start the application:
mvn spring-boot:run

Open a browser or use Postman to test the endpoints as documented in the source files or submodule README files.
