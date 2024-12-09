# Secret Santa Gift Exchange API 🎁
This project provides a RESTful API to help a group of friends or family organize a Secret Santa gift exchange event. The API manages participants, assigns gifts randomly, and ensures that each participant can view only their assigned recipient without revealing others.

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.5-brightgreen)
![Build](https://img.shields.io/github/workflow/status/your-username/halloween-trivia-api/Build%20API%20Project)
![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Contributions](https://img.shields.io/badge/contributions-welcome-brightgreen.svg)
![Swagger](https://img.shields.io/badge/documented%20with-Swagger-orange.svg)

<p align="center">
    <a href="https://git.io/typing-svg">
    <img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=600&pause=1000&color=F3042FD4&background=FF235100&center=true&width=435&lines=%F0%9F%8E%85+Secret+Santa+%F0%9F%8E%85;%F0%9F%8E%81++Gift+Exchange+API+%F0%9F%8E%81"/></a><br>
</p>


## 🧧 Project Structure
The project is organized into layers based on Clean Architecture principles:

- `application/`: Contains DTOs (data transfer objects) and use cases (business logic).
- `domain/`: Contains the core business models and domain services.
- `infrastructure/`: Contains infrastructure-related configurations, such as security, repositories, and Swagger documentation.
- `exception/`: Defines custom exceptions to handle various error scenarios.
- `mapper/`: Contains classes to map between domain models and database entities.
- `persistence/`: Defines entities for the database and repositories for interacting with the data.
- `interfaces/`: Contains the controllers that handle HTTP requests.
- `test/`: Contains unit tests to ensure proper functionality of services and use cases.

## ✨ Endpoints
The following are the key API endpoints provided:

1. Register Participant
- URL: `/participants`
- Method: POST
- Request body:
```
{
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```
- Response: Returns the created participant with their details.

2. Generate Gift Assignments
- URL: `/assignments/generate`
- Method: POST
- Request body: None
- Response: Returns a success message, and gift assignments are stored in the system.

3. Get Assigned Recipient
- URL: `/participants/{email}`
- Method: GET
- Request params:
- email: The email of the participant.
- Response: Returns the recipient's name and email assigned to the participant.

4. List All Participants (Admin Only)
- URL: `/participants`
- Method: GET
- Response: Returns a list of all registered participants.

### 🌟 Validations
- No Duplicate Participants: The API ensures no two participants can register with the same email.
- No Self-Assignments: A participant cannot be assigned to themselves.
- Email Format: Validates that emails are in a proper format (using the @Email annotation).

## 💟 Authentication
- Basic Authentication or JWT token-based authentication is required for endpoints that retrieve sensitive data (e.g., the recipient's information).
- Admin Access: The /participants endpoint for listing all participants is accessible only by an admin. Admin credentials should be configured and authenticated.

```
erDiagram
    PARTICIPANT {
        int id PK
        string name
        string email
    }
    ASSIGNMENT {
        int id PK
        int giver_id FK
        int recipient_id FK
        string gift_description
    }
    PARTICIPANT ||--o| ASSIGNMENT: "giver"
    PARTICIPANT ||--o| ASSIGNMENT: "recipient"tExchange.mmd…]()
```
## 🦌 API Documentation
Swagger/OpenAPI: The API is documented using Swagger, which can be accessed via http://localhost:8080/swagger-ui.html.

![swagger](https://github.com/user-attachments/assets/f173de0b-86fe-422e-ba5c-d2b181f02d00)

## ❄️ Bonus Feature: Email Notifications
As a bonus feature, participants will receive an email notification with the details of their assigned recipient. The email includes:
- The name of the recipient.
- A suggestion for a gift.
- A friendly message reminding the participant of the event.
- The email is sent via SMTP using Spring Email Services.

## 🎄 Testing
Unit tests are included for all key services and use cases, ensuring proper behavior of the application:
`GiftExchangeServiceTest.java`: Tests the functionality of generating assignments and sending emails.
`ParticipantServiceTest.java`: Tests the creation and lookup of participants.
The tests are located under the test/domain/service/ directory and can be run using Maven or your IDE's testing framework.

## 📈 Initial setup
### Prerequisites
- Java 21 or higher.
- Maven for dependency management.
- PostgreSQL configured and running.
- Spring Boot.
- Spring Data JPA for database access
- Spring Security for authentication
- Swagger/OpenAPI for API documentation
- JUnit 5 for unit testing
- Mockito for mocking dependencies in tests
  
### Steps to run the application:
1. Clone the repository:
```
git clone https://github.com/Orliluq/exchange.git
cd exchange
```
2. Configure the database in application.properties:
spring.datasource.url=jdbc:postgresql://localhost:5432/db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update

3. Run the application:
`mvn spring-boot:run`

## 🏷️ License
This project is licensed under the MIT License. See the LICENSE file for details.

## 🍁 Contributions
Contributions are welcome! Please open a pull request or report issues in the repository.
