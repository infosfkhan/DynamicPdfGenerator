
# Dynamic Pdf Generator

Spring Boot Application with REST API to generate PDF using Java Template
Engine.

Requirement
- REST API to accept data and generate a PDF based on the received data.
- Ability to download the above-generated PDF
- Store the above-generated PDF on the local storage and redownload it when the
  same data is provided instead of generating it again.




## Requirements

- Java Development Kit (JDK) 21: Ensure JDK 21 is installed on your system as the project is configured with Java 21 compatibility.
- build automation tool: Gradle

## Dependencies

The project utilizes the following dependencies, which are managed through Gradle:

- **Spring Boot:** Provides essential features for developing Spring applications.
- **Spring Boot Starter Web:** Enables building web applications using Spring MVC.
- **Flying Saucer PDF:** Used for generating PDF documents from HTML/CSS templates.
- **Spring Boot Starter Thymeleaf:** Integrates Thymeleaf templating engine for server-side rendering.
- **Spring Boot Starter Validation:** Facilitates validation of input data.
- **Spring Boot DevTools (Development Only):** Enhances development experience with additional tools and features.
- **Spring Boot Starter Test (Test Implementation):** Includes dependencies for testing Spring Boot applications.

## Running the Application

To run the application locally, ensure you have JDK 21 installed. Then, follow these steps:

1. Clone the repository to your local machine.
2. Open a terminal and navigate to the project directory.
3. Run `./gradlew bootRun` to start the Spring Boot application.
4. Access the application by visiting `http://localhost:8080` in your web browser.

