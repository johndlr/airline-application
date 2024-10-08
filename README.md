<h1 align="center" id="title">Airline Reservation System Using Microservices</h1>

<p id="description">This application, based on a microservices architecture, serves as a comprehensive registration system for flights, customers, and reservations for a fictitious airline. It demonstrates the use of modern technologies and best practices in building scalable and maintainable systems.</p>

<h2>Features</h2>
<h3 align="center">General application architecture</h3>
<div align="center"><img src="https://github.com/user-attachments/assets/9f5ef269-eb7f-4c0a-a160-0d63c27917f5" alt="project-screenshot" width="800"></div>

### Core of the Application

At the core of the application, there are three REST services: __flight__ , **reservation**, and **customer**. Each service performs the standard CRUD operations (Create, Retrieve, Update, Delete) within its specific context:

- **Flight Service**: Manages flight information and schedules.
- **Reservation Service**: Handles booking and reservation details.
- **Customer Service**: Manages customer profiles and information.

For the creation of these REST services, several cloud-native application development methodologies were followed, including:

- **API First**: Designing APIs before implementing the services to ensure a clear contract and better collaboration.
- **One Codebase – One Application**: Maintaining a single codebase per service to ensure modularity and ease of maintenance.
- **Dependency Management**: Using tools like Maven or Gradle to manage dependencies efficiently.

The primary technology used is **Spring Boot**, leveraging its powerful features for building robust and scalable REST services. For API documentation, the **OpenAPI Specification** was used, providing a standard way to describe the APIs and enabling easy integration with tools like Swagger for interactive API documentation.

#### Swagger UI
Below are screenshots of the Swagger UI for the REST services:

![Swagger UI Screenshot](https://github.com/user-attachments/assets/4d3fd3c1-70e0-4e23-adf9-645c9071c070)

![Swagger UI Screenshot](https://github.com/user-attachments/assets/b241e7cb-a32a-4a61-8c4d-f4ae77c3aa9b)

![Swagger UI Screenshot](https://github.com/user-attachments/assets/381e70a3-d322-483d-917a-24161be145ea)

### Configurations

Spring Cloud Config is used to **externalize the application configuration**, adhering to the principles of cloud-native applications, such as the Twelve-Factor App methodology. This approach allows for centralized management of configuration properties, making the application more flexible and easier to manage across different environments. Additionally, some endpoints offered by the **Spring Actuator module** are utilized to dynamically update configurations in the microservices. The configuration files are stored in a GitHub [repository](https://github.com/johndlr/airline-config), which is connected to the Config Server to provide version-controlled configuration management.

As a simple example, below is a screenshot showing the Eureka server configurations that have been loaded from the GitHub repository by the Config Server:

![ConfigServer ScreenShot](https://github.com/user-attachments/assets/9f0f297b-f7a0-499a-80a5-458fdb2d1f11)

### Discovery Service

The discovery service is implemented using **Spring Cloud Eureka Server**. Eureka Server acts as a registry for microservices, enabling them to discover and communicate with each other without hardcoding their locations. This dynamic service discovery mechanism is crucial for maintaining the flexibility and scalability of a microservices architecture.

By registering with Eureka, each microservice provides its metadata, such as host and port, health indicator URL, and other service-specific information. Eureka clients, which are the microservices themselves, use this registry to look up other services and make remote procedure calls (RPCs) to them.

The use of Eureka Server simplifies the management of service instances, supports load balancing, and enhances fault tolerance by rerouting requests to healthy instances in case of failures.

Below is a screenshot of the discovery service running:

<img src="https://github.com/user-attachments/assets/6f25aee4-2338-4748-b85e-2034888e5e0c" alt="Eureka Server ScreenShot" width="800"/>



### Inter-Service Communication

To facilitate communication between services, **Spring Cloud OpenFeign** is used for synchronous communication, and **Kafka** is used for asynchronous communication.

#### Synchronous Communication

The **Reservation** service uses Feign clients to fetch information from the **Flight** and **Customer** services. This allows the Reservation service to gather all necessary details to create a complete reservation.

#### Asynchronous Communication

For asynchronous communication, **Kafka** and **Spring Cloud Stream** are used to connect the **Reservation** service with a new service called **Message**. The Message service uses **Spring Cloud Function** to send an email to the customer when a reservation is made. The email functionality is implemented using **Spring Mail**, **SMTP Gmail**, and **JavaMailSender**.

Below is the general scheme of asynchronous communication between the message and reservation services:

![Kafka Async Diagram ScreenShot](https://github.com/user-attachments/assets/ca36f84d-e102-4bc0-b7a5-e518a39b2d84)

#### Kafka Topics

To facilitate asynchronous communication between services, Kafka topics are used. Two topics were successfully created:

- **send_communication**: This topic is used to send messages from the **Reservation** service to the **Message** service, indicating that a reservation has been made and an email needs to be sent to the customer.
- **communication_sent**: This topic is used by the **Message** service to notify the **Reservation** service that the email has been successfully sent. Upon receiving this notification, the Reservation service updates the `communication_switch` column in the reservations table.

These topics enable reliable and scalable communication between services, ensuring that messages are delivered and processed asynchronously.

Topics:

![Async Communication ScreenShot](https://github.com/user-attachments/assets/92b7357b-ee53-4e71-8e19-ca8e93fba105)

Consumers:

![Async Communication ScreenShot](https://github.com/user-attachments/assets/3a865759-c09e-4045-8517-f768fa9c61aa)

Recorded events:

![Async Communication ScreenShot](https://github.com/user-attachments/assets/7b97b464-577f-4048-bccd-1420761212e9)

#### Email sending logic in action

When creating a new reservation, the event to send an email is triggered:

![Async Communication ScreenShot](https://github.com/user-attachments/assets/45bd01d6-8e92-406d-bccc-4632d71d9680)

Inbox:

![Async Communication ScreenShot](https://github.com/user-attachments/assets/c5a40e13-5ad1-45f4-9586-0595fb04d9d4)

The content of the email:

![Async Communication ScreenShot](https://github.com/user-attachments/assets/107db83d-4a51-4bef-9159-e58a334ba217)

Update the `communication_switch` column as true in the reservations table:

![Async Communication ScreenShot](https://github.com/user-attachments/assets/e607ceb7-4229-4bc6-9d58-2114f94b7483)



### Edge Server

**Spring Cloud Gateway** was used as an edge server, taking advantage of its integration with **Spring Cloud Eureka** to utilize **Spring Cloud LoadBalancer** for distributing incoming traffic across multiple instances of the microservices. Spring Cloud Gateway provides a simple, yet powerful way to route requests, handle cross-cutting concerns such as security, monitoring, and resiliency, and manage traffic.

Key features utilized include:

- **Routing**: Configured routes to direct incoming requests to the appropriate microservices based on the request path and other criteria.
- **Load Balancing**: Leveraged the integration with Spring Cloud Eureka to use Spring Cloud LoadBalancer, distributing requests evenly across service instances and enhancing the system's scalability and reliability.
- **Security**: Implemented security measures such as authentication and authorization at the gateway level to protect the backend services.
- **Monitoring and Metrics**: Integrated with monitoring tools to track the performance and health of the routes and services.

The following image shows the routes configuration from the Gateway Server:

![Gateway Routes Configuration](https://github.com/user-attachments/assets/6331f865-f821-4ac8-ab00-f14b01b6f3fa)

### Resilience and Fault Tolerance

To enhance the resilience and fault tolerance of the application, **Resilience4j** was used to implement the circuit breaker pattern in both the edge server and the Reservation service.

#### Edge Server

In the **Spring Cloud Gateway** (edge server), Resilience4j is used to apply the circuit breaker pattern to manage and control the flow of traffic to the backend services. This helps to prevent cascading failures and improve the overall stability of the system by temporarily blocking requests to failing services and allowing them to recover.

#### Reservation Service

In the **Reservation** service, Resilience4j is used to implement circuit breakers with fallback mechanisms. Specifically, it functions as a fallback class in case of errors when attempting to communicate with the **Customer** and **Flight** services. This ensures that the Reservation service can handle failures gracefully and provide a default response or alternative processing logic when the dependent services are unavailable.

Key features utilized include:

- **Circuit Breaker**: Monitors the calls to external services and opens the circuit if the failure rate exceeds a configured threshold, preventing further calls to the failing service.
- **Fallback Mechanism**: Provides a fallback method or class to handle failures and return a default response or perform alternative processing when the circuit is open.

### Implementing Security in the Application

To implement application security, the **OAuth2** protocol was used for authorization, **OpenID Connect** for authentication, and **Keycloak** for Identity and Access Management (IAM) processes.

#### Authorization Flow

The OAuth2 authorization flow chosen for implementation in the application is the **client credentials** type. This flow is suitable for machine-to-machine communication where the client is acting on its own behalf.

#### Components

- **Edge Server**: Established as the resource server, responsible for serving the protected resources.
- **Keycloak**: Configured as the authorization server, handling the authentication and authorization processes.

#### Role-Based Access Control

Within the Edge Server, permissions were configured based on roles. In the application context, there are three types of users, each with specific roles and permissions:

1. **Airline Call Center**
   - **Roles**: `RESERVATION`, `CUSTOMER`, `FLIGHT`
   - **Permissions**: This user has all permissions, meaning they can create and manage flights, customers, and reservations.

2. **Airline Customer Operators**
   - **Role**: `CUSTOMER`
   - **Permissions**: This user is limited to managing airline customers.

3. **Airline Flight Operators**
   - **Role**: `FLIGHT`
   - **Permissions**: This user is limited to managing airline flights.

By using Keycloak, the application benefits from a robust and flexible IAM solution that supports OAuth2 and OpenID Connect standards, ensuring secure and scalable authentication and authorization.

Below are some screenshots of the authentication and authorization process:

Clients created in keycloak:

![Keycloack Clients](https://github.com/user-attachments/assets/2fb41d83-69c2-40b8-9949-4039afd31103)

The flight creation endpoint of the Flight service is used to generally test JWT token-based authentication and role-based authorization for the three clients:

![Flight Endpoint](https://github.com/user-attachments/assets/f45b5cc3-fb9d-4748-8cd1-87b5e72787b4)

Claims from JWT Token for Airline Call Center:

![Flight Endpoint](https://github.com/user-attachments/assets/4c299fae-1db3-497a-9f41-acd9c6ef3d57)
  
Claims from JWT Token for Airline Customer Operators:

![Flight Endpoint](https://github.com/user-attachments/assets/c8d9cce1-8b43-40db-be8f-cf3d172a3697)
  
Claims from JWT Token for Airline Flight Operators:

![Flight Endpoint](https://github.com/user-attachments/assets/966556e7-ed95-4c35-9bca-051dcdf04415)

Airline Call Center, access credentials provided to Postman and the corresponding response

![Flight Endpoint](https://github.com/user-attachments/assets/ffac1ce1-b563-4e40-bf5c-bd8e87e007e0)

![Flight Endpoint](https://github.com/user-attachments/assets/0a446557-9077-4b0e-9ec0-9c2001f381c2)

Airline Customer Operators, access credentials provided to Postman and the corresponding response

![Flight Endpoint](https://github.com/user-attachments/assets/1db0338d-62a3-4e7b-9cf9-73a3d9da81a0)

![Flight Endpoint](https://github.com/user-attachments/assets/8d530df9-5e37-4397-a536-e9ab0027260f) 

Airline Flight Operators, access credentials provided to Postman and the corresponding response

![Flight Endpoint](https://github.com/user-attachments/assets/be428abc-90b5-4919-9e51-f82e5e467683)

![Flight Endpoint](https://github.com/user-attachments/assets/b807d156-b2eb-4c2d-9c57-2e98e3b02f22) 

In all three cases, the behavior is as expected. The role-based authorization system is functioning correctly.

### Observability

Observability is a crucial aspect of cloud-native applications, enabling you to monitor, log, and trace the behavior of your services. This project implements observability using various tools and technologies to ensure comprehensive monitoring and troubleshooting capabilities.

#### Logs

For logging, the project uses Loki and Alloy:

***Loki***: A log aggregation system designed to store and query logs from all your applications and infrastructure. Loki is optimized for efficiency and scalability, making it a great choice for handling large volumes of log data.

***Alloy*** A tool that integrates with Loki to enhance log management and visualization capabilities.

#### Metrics

For metrics, the project uses Spring Actuator, Micrometer, and Prometheus:

***Spring Actuator***: Provides production-ready features for Spring Boot applications, including various metrics, health checks, and other monitoring capabilities.

***Micrometer***: A metrics collection library that integrates with Spring Boot and provides a facade over different metrics backends, including Prometheus.

***Prometheus***: An open-source monitoring and alerting toolkit designed for reliability and scalability. Prometheus scrapes metrics from instrumented jobs, stores them efficiently, and provides a powerful query language to analyze the data.

#### Tracing

For distributed tracing, the project uses OpenTelemetry and Tempo:

***OpenTelemetry***: A set of APIs, libraries, agents, and instrumentation to provide observability into cloud-native applications. OpenTelemetry supports distributed tracing, metrics, and logging.

***Tempo***: A high-scale, easy-to-use, and cost-effective distributed tracing backend. Tempo is designed to integrate seamlessly with Grafana for trace visualization.

#### Visualization

For all three pillars of observability (logs, metrics, and tracing), Grafana is used to visualize the collected data:

**Grafana**: An open-source platform for monitoring and observability. Grafana allows you to query, visualize, alert on, and understand your metrics, logs, and traces no matter where they are stored.
Implementation Details
By implementing these tools, the project adheres to one of the key methodologies of cloud-native applications: observability and monitoring. This ensures that the system is not only functional but also reliable and maintainable.

Below are some screen shots:

Logs from reservation service:

![Screenshot 2024-10-07 191212](https://github.com/user-attachments/assets/54a51819-94a4-4a87-9bbe-09c2d8576ca6)

Metrics from reservation service (System CPU Usage):

![Screenshot 2024-10-07 192001](https://github.com/user-attachments/assets/c479e85d-1a07-4269-a392-bc88e25bac22)

Metrics from all services (System CPU Usage):

![Screenshot 2024-10-07 192106](https://github.com/user-attachments/assets/4761f7f5-e8eb-4f06-8f4f-63201ebf0ccd)

Distributed tracing 

We followed the flow of a reservation request in the system, with the trace ID `233d335ed81397c58717837fe23b871f`. This was an excellent opportunity to observe how the involved microservices—`flight`, `customer`, and `reservation`—communicate with each other. Additionally, we monitored the asynchronous communication, which involved generating an event to send an email to the customer in the `message` service. This trace provided valuable insights into the interactions and dependencies between the services, highlighting the effectiveness of our observability setup.

From Loki we get the logs related to the reservation request:

![Screenshot 2024-10-07 192443](https://github.com/user-attachments/assets/9ad45760-24a7-4f70-b823-1ddf9eb6aadf)

The query is generated in grafana using the trace id:

![Screenshot 2024-10-07 192602](https://github.com/user-attachments/assets/92224fe3-e02d-4d9b-a7e0-01867cb185f1)

Route of the request through the different services:

![Screenshot 2024-10-07 192755](https://github.com/user-attachments/assets/60b71508-8089-4de0-a2f4-7fae2a4aa6f3)

![Screenshot 2024-10-07 192815](https://github.com/user-attachments/assets/c6f5e97b-e0cb-4ec7-967b-702b3670273c)

![Screenshot 2024-10-07 192825](https://github.com/user-attachments/assets/fcb188fc-8018-4060-b739-79ddca0ea514)

### Docker Compose

Docker Compose is used to orchestrate the various services of the application, including `flight`, `customer`, `reservation`, `message`, `configserver`, `eureka server`, `gateway server`, as well as the corresponding images for `keycloak`, `kafka`, and the Grafana ecosystem (`grafana`, `loki`, `prometheus`, `tempo`). The images for `flight`, `customer`, `reservation`, `message`, `configserver`, `eureka server`, and `gateway server` were generated using Google Jib.

#### Building the Images

The images for the services were built using [Google Jib](https://github.com/GoogleContainerTools/jib), which allows for containerizing Java applications without needing a Dockerfile.

#### An example of what the execution of the different services looks like using docker compose

![Screenshot 2024-10-07 190647](https://github.com/user-attachments/assets/61638e25-49dc-4436-94d3-b7779d204e77)

<h2>💻 Built with</h2>

Technologies used in the project:

*   Spring Boot
*   Spring MVC
*   Spring Data
*   Lombok
*   H2 Data Base
*   Spring Cloud
*   Spring Security
*   Keycloak
*   Docker
*   Postman
*   Swagger
*   GitHub
*   Kafka
*   Spring Mail
*   SMTP Gmail
*   Grafana


## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

© 2024 Juan de la Rosa. All rights reserved.
