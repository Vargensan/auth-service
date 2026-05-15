This is Microservice is Authorization Service (AuthService)

Authorization Service is responsible for handling login, registration and activation of user at the backend.
Propagation of user data to other microservices.

This microservice is written in hexagonal architecture.

Module auth-api is responsible for providing REST API. It contains DTOs (Data Transfer Objects).
This module is intended to be exposed to the outside world, as well to be used by internal auth-entry module,
to handle api requests.

Module auth-entry is responsible for handling api requests. 

Module auth-core is responsible for handling business logic. It has any dependencies with other modules.
It only contains the way of handling business logic, and does not contain any implementation.

Module auth-app is the key module of the application, responsible for running the application, 
resolving dependencies and providing configuration between other modules on a specific technology stack used.

Module auth-infrastructure is responsible for providing implementation of interfaces defined in the auth-core module.
It has concrete implementations and is bounded by a specific technology stack (Spring, PostgreSQL, etc.).

It is intended to be used with other microservices.
It can be run as a standalone application, for testing purposes after some docker-compose file modification.

Technology Stack:

- Java
- Spring Boot
- PostgreSQL
- Kafka
- Lombok
- JWTT
- Jackson
- etc.

More information about libraries and versions can be found gradle/libs.versions.toml

## License

This project is licensed under the Apache License 2.0.

See the [LICENSE](LICENSE) file for details.