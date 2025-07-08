## TicketManagementSystem Backend

### 1. Project Overview

`TicketManagementSystem` is a Spring Boot–based backend application designed to serve as the core for a support ticket management system. It follows a layered architecture, separating concerns across configuration, domain modeling, data persistence, business logic, and RESTful web interfaces.

**Key Features (Planned)**

- User and role management
- Ticket creation, assignment, status tracking, and resolution
- Permission-based access control
- Audit logging
- OpenAPI documentation generation via SpringDoc

### 2. Technology Stack

- **Language**: Java 21 (via JDK toolchain)
- **Framework**: Spring Boot 3.4.7
- **Build Tool**: Gradle (via `gradlew` wrapper)
- **ORM**: Hibernate 6.6.18
- **API Documentation**: SpringDoc OpenAPI plugin
- **Testing**: JUnit 5
- **Native Image Support**: GraalVM build tools plugin

### 3. Architecture & Module Structure

```
|-- src/main/java/com/fongfox/ticketmanagementsystem
|   |-- config/             # Application-level configuration beans
|   |-- domain/             # Entity classes and domain models
|   |-- repository/         # Spring Data repositories (DAO layer)
|   |-- service/            # Business logic services
|   |   |-- dto/            # Data Transfer Objects
|   |   |-- implement/      # Service implementations
|   |   |-- mapper/         # Entity/DTO mappers
|   |-- web/                # Web layer
|       |-- rest/           # REST controllers
|           |-- HelloController.java  # Sample endpoint
|-- src/main/resources
|   |-- application.properties  # Application properties
|-- build.gradle.kts             # Build configuration
|-- settings.gradle.kts          # Project settings
|-- gradlew / gradlew.bat        # Gradle wrapper scripts
```

This layered approach promotes separation of concerns, testability, and maintainability.

### 4. Prerequisites

- Java 21 JDK installed or use the Gradle toolchain
- Gradle (optional, since wrapper is included)
- Git (for source control)

### 5. Setup & Build

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd TicketManagementSystem
   ```
2. **Build the project**
   ```bash
   ./gradlew clean build
   ```
3. **Run tests & generate documentation snippets**
   ```bash
   ./gradlew test asciidoctor
   ```

### 6. Running the Application

You can start the application using the Gradle wrapper:

```bash
./gradlew bootRun
```

By default, the server will start on `http://localhost:8080`.

### 7. Configuration

Application settings are located in `src/main/resources/application.properties`. Example properties:

```properties
spring.application.name=TicketManagementSystem
# datasource.url=jdbc:postgresql://localhost:5432/tickets
# spring.datasource.username=postgres
# spring.datasource.password=secret
```

### 8. API Endpoints

- **GET** `/api/Hello`\
  Returns a simple "Hello World" message (sample endpoint).

> **Note:** More endpoints (e.g., `/api/tickets`, `/api/users`) will be added as controllers and services are implemented.

### 9. Next Steps / To Do

- Define domain entities (`domain/`) for User, Role, Ticket, Comment, etc.
- Implement repositories for CRUD operations.
- Create service interfaces and implementations for business workflows.
- Add REST controllers for user management and ticket operations.
- Configure security (Spring Security) and permission checks.
- Integrate a database (e.g., PostgreSQL) and configure connection pooling.
- Expand automated tests for service and controller layers.
- Finalize API documentation with OpenAPI UI.

---

*Generated on July 8, 2025*

