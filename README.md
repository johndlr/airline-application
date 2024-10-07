<h1 align="center" id="title">Airline Microservices Application</h1>

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

Spring Cloud Config is used to **externalize the application configuration**, adhering to the principles of cloud-native applications, such as the Twelve-Factor App methodology. This approach allows for centralized management of configuration properties, making the application more flexible and easier to manage across different environments. Additionally, some endpoints offered by the Spring Actuator module are utilized to dynamically update configurations in the microservices. The configuration files are stored in a GitHub repository, which is connected to the Config Server to provide version-controlled configuration management.

The repository that contains the configurations of the different services is: [Configurations Repository](https://github.com/johndlr/airline-config)

As a simple example, below is a screenshot showing the Eureka server configurations that have been loaded from the GitHub repository by the Config Server:

![ConfigServer ScreenShot](https://github.com/user-attachments/assets/9f0f297b-f7a0-499a-80a5-458fdb2d1f11)


<h4>How do they communicate? Synchronous Communication</h4>
<div><img src="https://github.com/user-attachments/assets/fa079621-6475-41d9-9d17-7cf701753b96" alt="project-screenshot" width="240" height="240"></div>
<p>Spring Cloud OpenFeign was used to communicate the reservation service with the other two services. In this way, complete reservation information is obtained, as well as flight and client data.</p>
<h3>Service Discovery</h3>
<p>Spring Cloud Eureka Server was used for the discovery service.</p>
<div><img src="https://github.com/user-attachments/assets/6f25aee4-2338-4748-b85e-2034888e5e0c" alt="project-screenshot"></div>
<h3>Edge Server</h3>
<div><img src="https://github.com/user-attachments/assets/50a659a2-563f-4708-b324-249d83ef16ac" alt="project-screenshot"></div>
<p>Spring Cloud Gateway was used as an edge server, taking advantage of the default load balancer.</p>
<p>The following image shows the routes configuration from the Gateway Server</p>
<div><img src="https://github.com/user-attachments/assets/6331f865-f821-4ac8-ab00-f14b01b6f3fa" alt="project-screenshot"></div>

<h3>Async Communication</h3>
<p>To achieve asynchronous communication between the reservation and message services, spring cloud stream, spring cloud function,rabbitmq and kafka were used. A new service called Message was created, which simulates sending an email when creating a new reservation. The general scheme is as follows:</p>
<div><img src="https://github.com/user-attachments/assets/adbd71d2-0659-4782-a66d-60a6f9b25357" alt="project-screenshot"></div>
<br/>
<h4>Async Communication using RabbitMQ</h4>
<p>In rabbitmq the queues were registered successfully</p>
<div><img src="https://github.com/user-attachments/assets/08f91896-c4a3-4040-8bec-33ba8a7cf079" alt="project-screenshot"></div>
<h4>Async Communication using Kafka</h4>
<p>With the help of Spring Cloud Stream, the application was reconfigured to now use Kafka as a message broker, configurations were added to the Reservation and Message services.</p>
<p>Before executing the business logic of the Reservation and Message services, the Kafka broker had the following information:</p>
<div><img src="https://github.com/user-attachments/assets/92b7357b-ee53-4e71-8e19-ca8e93fba105" alt="project-screenshot"></div>
<br/>
<div><img src="https://github.com/user-attachments/assets/3a865759-c09e-4045-8517-f768fa9c61aa" alt="project-screenshot"></div>
<br/>
<p>After executing this logic, the messages were successfully received and processed:</p>
<div><img src="https://github.com/user-attachments/assets/7b97b464-577f-4048-bccd-1420761212e9" alt="project-screenshot"></div>
<h4>Email sending</h4>
<p>The following technologies were used to send the email when creating the reservation: Spring Boot Starter Mail, SMTP Gmail and JavaMailSender.
In addition, when the email is sent successfully, an event is sent to the Reservation service to update the status in the database as sent.
The results are as follows:
</p>

<p>In Postman we created the reservation successfully</p>
<div><img src="https://github.com/user-attachments/assets/45bd01d6-8e92-406d-bccc-4632d71d9680" alt="project-screenshot"></div>
<br/>
<p>Inbox with the received email</p>
<div><img src="https://github.com/user-attachments/assets/c5a40e13-5ad1-45f4-9586-0595fb04d9d4" alt="project-screenshot"></div>
<br/>
<p>The email</p>
<div><img src="https://github.com/user-attachments/assets/107db83d-4a51-4bef-9159-e58a334ba217" alt="project-screenshot"></div>
<br/>
<p>The updated column in the Reservations table</p>
<div><img src="https://github.com/user-attachments/assets/e607ceb7-4229-4bc6-9d58-2114f94b7483" alt="project-screenshot"></div>

<h3>Implementing security in the application</h3>
<p>
  To implement application security, the OAuth2 protocol was used for authorization, OpenID Connect for authentication, and Keycloak for Identity and Access Management (IAM) processes.
  
  The OAuth2 authorization flow chosen for implementation in the application is the client credentials type.
  
  The Edge Server was established as the resource server, and Keycloak as the authorization server.
  
  Within the Edge Server, permissions were configured based on roles.
  
  In the application context, there are 3 application users:

  * Airline Call Center, with the roles RESERVATION, CUSTOMER, FLIGHT, the user with all permissions, meaning they can create flights, customers, and reservations.
  * Airline Customer Operators, with the only role CUSTOMER, this user is limited to managing airline customers.
  * Airline Flight Operators, with the only role FLIGHT, this user is limited to managing airline flights."
</p>
<div><img src="https://github.com/user-attachments/assets/2fb41d83-69c2-40b8-9949-4039afd31103" alt="project-screenshot"></div>
<br/>
<p>
  For this documentation, some tests performed on the flight service using the three application users are presented.  
  Endpoint from flight service:
  <div><img src="https://github.com/user-attachments/assets/f45b5cc3-fb9d-4748-8cd1-87b5e72787b4" alt="project-screenshot"></div>
  <br/>
  Claims from JWT Token for Airline Call Center:
  <div><img src="https://github.com/user-attachments/assets/4c299fae-1db3-497a-9f41-acd9c6ef3d57" alt="project-screenshot"></div>
  <br/>
  Claims from JWT Token for Airline Customer Operators:
  <div><img src="https://github.com/user-attachments/assets/c8d9cce1-8b43-40db-be8f-cf3d172a3697" alt="project-screenshot"></div>
  <br/>
  Claims from JWT Token for Airline Flight Operators:
  <div><img src="https://github.com/user-attachments/assets/966556e7-ed95-4c35-9bca-051dcdf04415" alt="project-screenshot"></div>
  <br/>

  Airline Call Center, access credentials provided to Postman and the corresponding response
  <div><img src="https://github.com/user-attachments/assets/ffac1ce1-b563-4e40-bf5c-bd8e87e007e0" alt="project-screenshot"></div>
  <div><img src="https://github.com/user-attachments/assets/0a446557-9077-4b0e-9ec0-9c2001f381c2" alt="project-screenshot"></div>
  <br/>

  Airline Customer Operators, access credentials provided to Postman and the corresponding response
  <div><img src="https://github.com/user-attachments/assets/1db0338d-62a3-4e7b-9cf9-73a3d9da81a0" alt="project-screenshot"></div>
  <div><img src="https://github.com/user-attachments/assets/8d530df9-5e37-4397-a536-e9ab0027260f" alt="project-screenshot"></div>
  <br/>

  Airline Flight Operators, access credentials provided to Postman and the corresponding response
  <div><img src="https://github.com/user-attachments/assets/be428abc-90b5-4919-9e51-f82e5e467683" alt="project-screenshot"></div>
  <div><img src="https://github.com/user-attachments/assets/b807d156-b2eb-4c2d-9c57-2e98e3b02f22" alt="project-screenshot"></div>
  <br/>

  In all three cases, the behavior is as expected. The role-based authorization system is functioning correctly.
</p>




<h2>💻 Built with</h2>

Technologies used in the project:

*   Spring Boot
*   Spring MVC
*   Spring Data
*   Lombok
*   H2 Data Base
*   Spring Cloud
*   Keycloak
*   Docker
*   Postman
*   Swagger
*   GitHub
*   RabbitMQ
*   Kafka
*   Spring Mail
*   SMTP Gmail
