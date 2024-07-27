# Bookstore Microservices

This repository contains the code for a bookstore application built using a microservices architecture. The project includes multiple services such as Product Service, Order Service, Inventory Service, and Discovery Service.

## Services

### 1. Product Service
Manages the products available in the bookstore.

### 2. Order Service
Handles customer orders and transactions.

### 3. Inventory Service
Manages the stock of products.

### 4. Discovery Service
Service discovery using Netflix Eureka.

## Getting Started

### Prerequisites
- Java 11 or higher
- Maven
- MongoDB
- MySql
- Spring Boot

### Setup
1. Clone the repository:
   ```sh
   git clone https://github.com/your-username/your-repo-name.git
   cd your-repo-name
2. Build the project using Maven:
   ```sh
   mvn clean install
3. Run the services:
Each service can be run individually. Navigate to the service directory and run the Spring Boot application.
   ```sh
   cd discovery-service
   mvn spring-boot:run
   ```
Repeat the above steps for product-service, order-service, and inventory-service.


Configuration
Each service has its own configuration file located in the src/main/resources directory. You can configure database connections, server ports, and other settings there.

Discovery Service
discovery-service/src/main/resources/application.properties

Product Service
product-service/src/main/resources/application.properties

Order Service
order-service/src/main/resources/application.properties

Inventory Service
inventory-service/src/main/resources/application.properties

Database
MongoDB and MySql is used as the database for this project. Ensure MongoDB and MySql are running and accessible.

Usage
Once all the services are running, you can interact with them using their respective endpoints. For example, you can use tools like Postman to send HTTP requests to the services.

Contributing
- Fork the repository.
- Create a new branch (git checkout -b feature/your-feature).
- Commit your changes (git commit -am 'Add some feature').
- Push to the branch (git push origin feature/your-feature).
- Create a new Pull Request.
