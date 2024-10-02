<h1 align="center" id="title">🛬 Airline Microservices Application 🛫</h1>

<p id="description">The application based on a microservices architecture works as a registration for flights, customers and reservations system for a fictitious airline.</p>

<h2>🧐 Features</h2>
<h3>Three services were created: flight, customer and reservation, each one exposes different endpoints for CRUD functions.</h3>
<h4>Flight Service ✈️</h4>
<p>Manages all the information related to an airline flight</p>
<div><img src="https://github.com/user-attachments/assets/2d32f3c6-16ba-473f-a7cf-f06ead869644" alt="project-screenshot"></div>
<br/>
<div><img src="https://github.com/user-attachments/assets/3c364999-9d3d-4d67-a745-c32e7785c545" alt="project-screenshot"></div>
<h4>Customer Service 🧑‍💼</h4>
<p>Manages all the information related to an airline customer</p>
<div><img src="https://github.com/user-attachments/assets/ccf36bd2-c3a7-4c3d-b201-8efdfae18eb6" alt="project-screenshot"></div>
<br/>
<div><img src="https://github.com/user-attachments/assets/13cb1b5a-8e2d-4e98-9fc8-84386a75b881" alt="project-screenshot"></div>
<h4>Reservation Service 🕗</h4>
<p>Manages all the information related to an airline reservation</p>
<div><img src="https://github.com/user-attachments/assets/631480e6-e426-4873-93d1-e2a26e3d0e45" alt="project-screenshot"></div>
<br/>
<div><img src="https://github.com/user-attachments/assets/a9a0fedf-9876-48d0-8246-adeea37a2d9c" alt="project-screenshot"></div>
<h4>How do they communicate?</h4>
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
<h3>Config Server</h3>
<p>Spring Cloud Config is used to externalize the application configuration, and some endpoints offered by the Spring Actuator module are also used to update configurations in microservices. Finally, a Github repository was used to store the different configuration files and connect it to the Config Server.</p>
<div><img src="https://github.com/user-attachments/assets/ea2ad2aa-ff96-4f0d-8880-ce8c46216cb1" alt="project-screenshot"></div>
<br/>
<p>The following image shows the Eureka Server configurations loaded from the Github repository by the Config Server</p>
<div><img src="https://github.com/user-attachments/assets/9f0f297b-f7a0-499a-80a5-458fdb2d1f11" alt="project-screenshot"></div>
<h3>Async Communication using RabbitMQ</h3>
<p>To achieve asynchronous communication between the reservation and message services, spring cloud stream, spring cloud function and rabbitmq were used. A new service called Message was created, which simulates sending an email and SMS when creating a new reservation. The general scheme is as follows:</p>
<div><img src="https://github.com/user-attachments/assets/adbd71d2-0659-4782-a66d-60a6f9b25357" alt="project-screenshot"></div>
<br/>
<p>In rabbitmq the queues were registered successfully</p>
<div><img src="https://github.com/user-attachments/assets/08f91896-c4a3-4040-8bec-33ba8a7cf079" alt="project-screenshot"></div>
<h3>Async Communication using Kafka</h3>
<p>With the help of Spring Cloud Stream, the application was reconfigured to now use Kafka as a message broker, configurations were added to the Reservation and Message services.</p>
<p>Before executing the business logic of the Reservation and Message services, the Kafka broker had the following information:</p>
<div><img src="https://github.com/user-attachments/assets/92b7357b-ee53-4e71-8e19-ca8e93fba105" alt="project-screenshot"></div>
<div><img src="https://github.com/user-attachments/assets/3a865759-c09e-4045-8517-f768fa9c61aa" alt="project-screenshot"></div>
<br/>
<p>After executing this logic, the messages were successfully received and processed:</p>
<div><img src="https://github.com/user-attachments/assets/7b97b464-577f-4048-bccd-1420761212e9" alt="project-screenshot"></div>

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
