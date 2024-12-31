# LocalNews Microservice

This project implements a **LocalNews Microservice** that integrates several services to handle news, user management, complaints, comments, and authentication. The microservice includes an API Gateway, Discovery Service, and multiple other services, such as Lajmi, Users, Ankesa, and Komenti. JWT authentication is passed through the API Gateway to ensure secure communication across all services.



## System Architecture

### Microservices:
- **API Gateway**: Acts as the entry point to the system, managing routing, authentication, and traffic distribution between microservices.
- **Discovery Service**: Allows services to register and discover each other dynamically.
- **News Service**: Handles all operations related to news articles.
- **Users Service**: Manages user-related functionality such as registration, login, and updating user profiles.
- **Complaints Service**: Manages complaints filed by users.
- **Comments Service**: Manages the functionality for commenting on news articles.

### JWT Authentication:
- JWT tokens are used for authentication and passed through the API Gateway to all services.
- The gateway verifies the token before routing requests to the appropriate microservice.

## Performance Optimizations

### Caching:
- **Caching** is implemented to reduce the load on services and speed up the response time. Commonly accessed data, such as news articles or user profiles, is cached in-memory.
- **Redis** is used to store this data. This helps prevent repetitive database queries and speeds up data retrieval.
  
### Load Balancing:
- **Load balancing** ensures that incoming requests are distributed evenly across multiple instances of services, preventing overloading any single instance.

## Monitoring and Logging

### Logging:
- **Logging** is implemented across all services to track requests, responses, and errors.
- The logs are collected to help debug issues, analyze performance bottlenecks, and monitor user activity.
- **Logback** and **SLF4J** are used for logging purposes, with logs stored in a centralized location for easy access.
  
### Monitoring:
- **Monitoring** helps ensure the health and performance of services in real-time.
- Tools like **Grafana** and **Prometheus** can be used to collect metrics such as response times, error rates, and resource usage.
- These metrics can be visualized on a dashboard for insights into system performance and potential issues.
  
## Documentation

### OpenAPI Specification:
- The **OpenAPI Specification** is used to describe the API structure and endpoints, enabling developers to easily understand and interact with the microservice.
- It provides a standard format for defining endpoints, request/response types, authentication methods, and more.

### Swagger UI:
- **Swagger UI** generates interactive and user-friendly documentation for the API, based on the OpenAPI specification.
- Developers can easily explore the API, view available endpoints, and even test requests directly from the browser.



