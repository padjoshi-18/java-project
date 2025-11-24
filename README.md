# Campus Course & Records Manager (CCRM)

A console-based Java application for managing student records, courses, grades, and academic transcripts.

## Project Overview

CCRM is a comprehensive Java SE application that demonstrates core Java features and object-oriented programming principles. This implementation follows a layered architecture pattern with clean separation of concerns, making the system modular, maintainable, and extensible.

### Implementation Approach

#### 1. Architecture Overview
The project is structured in multiple layers:
- Domain Layer (`edu.ccrm.domain`): Contains the core business entities
- Service Layer (`edu.ccrm.service`): Implements business logic and operations
- I/O Layer (`edu.ccrm.io`): Handles data persistence and file operations
- CLI Layer (`edu.ccrm.cli`): Manages user interaction
- Configuration (`edu.ccrm.config`): Handles application configuration
- Utilities (`edu.ccrm.util`): Provides common utilities and custom exceptions

#### 2. Object-Oriented Design
The implementation leverages key OOP principles:
- **Inheritance Hierarchy**: Abstract `Person` class extended by `Student` and `Instructor`
- **Interface-based Design**: Service interfaces with multiple implementations possible
- **Encapsulation**: Private fields with controlled access through methods
- **Polymorphism**: Service implementations can be swapped without changing client code

#### 3. Design Patterns
- **Singleton**: `AppConfig` for application-wide configuration
- **Builder**: `Course.Builder` for flexible object construction
- **Strategy**: Service interfaces allowing different implementation strategies
- **Factory Methods**: For creating domain objects with proper validation

#### 4. Modern Java Features
The code utilizes:
- Stream API for data processing and filtering
- NIO.2 for efficient file operations
- Date/Time API for temporal data management
- Optional for null-safety
- Lambda expressions and method references

#### 5. Error Handling
Robust error handling through:
- Custom exceptions for business logic violations
- Runtime assertions for invariant checking
- Try-with-resources for resource management
- Comprehensive error messages and logging

#### 6. Data Management
- In-memory data structures with future persistence capability
- CSV import/export functionality
- Automated backup system with recursive operations
- Thread-safe singleton configuration

Core Features:
- Student Management (create/update, enroll/unenroll)
- Course Management (create/update, search, assign instructors)
- Grades & Transcripts (record marks, compute GPA)
- File Operations (import/export, backup)

## How to Run

### Requirements
- JDK 17 or higher
- Eclipse IDE (recommended)

### Running in Eclipse
1. Clone this repository
2. Open Eclipse IDE
3. File -> Import -> General -> Existing Projects into Workspace
4. Select the cloned repository folder
5. Right-click the project -> Run As -> Java Application
6. Select CCRMApplication when prompted

### Running from Command Line
```bash
# Compile
javac -d target src/main/java/edu/ccrm/**/*.java

# Run (with assertions enabled)
java -ea -cp target edu.ccrm.CCRMApplication
```

## Evolution of Java

- 1995: Java 1.0 released by Sun Microsystems
- 2000: Java 2 (J2SE 1.3) with HotSpot JVM
- 2004: Java 5 introduces generics, annotations, autoboxing
- 2006: Java SE 6 with major performance improvements
- 2011: Java 7 adds try-with-resources, diamond operator
- 2014: Java 8 introduces lambdas and Stream API
- 2017: Java 9 introduces modules
- 2018: Java 10 introduces var type inference
- 2018: Java 11 LTS with HTTP Client API
- 2021: Java 17 LTS with sealed classes
- 2023: Java 21 LTS with virtual threads

## Java Editions Comparison

| Feature | Java ME | Java SE | Java EE |
|---------|---------|----------|----------|
| Purpose | Mobile/Embedded | Standard Desktop | Enterprise Applications |
| Scope | Limited APIs | Core Platform | Enterprise Features |
| Platform | Resource-constrained | Desktop/Server | Large-scale Server |
| Use Case | IoT, Mobile | Desktop Apps | Web Apps, Microservices |
| APIs | Subset of SE | Complete Standard | SE + Enterprise APIs |
| Memory | Minimal (<1MB) | Medium (>64MB) | Large (>1GB) |
| Deployment | Embedded Devices | Desktop/Command Line | Application Servers |

## Java Architecture

### JDK (Java Development Kit)
- Complete development kit for Java applications
- Includes compiler (javac), debugger (jdb), documentation generator (javadoc)
- Contains JRE plus development tools
- Required for Java development

### JRE (Java Runtime Environment)
- Minimum requirement to run Java applications
- Contains JVM and core libraries
- End-user component
- No development tools included

### JVM (Java Virtual Machine)
- Executes Java bytecode
- Platform-independent ("Write Once, Run Anywhere")
- Memory management and security
- Just-In-Time compilation for performance

## Windows Installation Steps

1. Download JDK
   - Visit Oracle website or use OpenJDK
   - Choose Windows x64 installer
   - Run the installer

2. Set Environment Variables
   - Right-click Computer -> Properties -> Advanced System Settings
   - Click Environment Variables
   - Add JAVA_HOME: Path to JDK installation
   - Add to Path: %JAVA_HOME%\bin

3. Verify Installation
```bash
java -version
javac -version
```

[Screenshots will be added showing these steps]

## Eclipse Setup Steps

1. Download Eclipse IDE
   - Visit eclipse.org
   - Download "Eclipse IDE for Java Developers"

2. Install Eclipse
   - Extract downloaded archive
   - Run eclipse.exe

3. Create Java Project
   - File -> New -> Java Project
   - Enter project name
   - Select JDK version
   - Click Finish

[Screenshots will be added showing these steps]

## Feature Implementation Map

| Topic | Implementation Location |
|-------|------------------------|
| Inheritance | `edu.ccrm.domain.Person` -> `Student`, `Instructor` |
| Polymorphism | `edu.ccrm.service` interfaces and implementations |
| Encapsulation | Private fields and getters/setters in domain classes |
| Abstraction | `Person` abstract class, service interfaces |
| Exception Handling | Custom exceptions in `edu.ccrm.util` |
| File I/O | `edu.ccrm.io` package using NIO.2 |
| Stream API | Service implementations for filtering and mapping |
| Design Patterns | `AppConfig` (Singleton), `Course.Builder` |
| Enums | `Grade`, `Semester` with constructors and fields |
| Functional Interfaces | Comparators and predicates in services |

## Assertions

To enable assertions when running the application:

```bash
java -ea edu.ccrm.CCRMApplication
```

Example assertions are used to validate:
- Non-null student IDs
- Valid credit limits (0-5 credits per course)
- Grade point ranges (0.0-10.0)
- Maximum credits per semester (<=21)

## Sample Commands

See [USAGE.md](USAGE.md) for detailed examples of commands and data file formats.
- Grades & Transcripts (record marks, compute GPA)
- File Operations (import/export, backup)

## How to Run

### Requirements
- JDK 17 or higher
- Eclipse IDE (recommended)

### Running the Application
1. Clone this repository
2. Open in Eclipse as a Java Project
3. Run the `CCRMApplication` class

## Evolution of Java

- 1995: Java 1.0 released by Sun Microsystems
- 2006: Java SE 6 with major performance improvements
- 2014: Java 8 introduces lambdas and Stream API
- 2017: Java 9 introduces modules
- 2021: Java 17 LTS release
- 2023: Java 21 LTS release

## Java Editions Comparison

| Feature | Java ME | Java SE | Java EE |
|---------|---------|----------|----------|
| Purpose | Mobile/Embedded | Standard Desktop | Enterprise Applications |
| Scope | Limited APIs | Core Platform | Enterprise Features |
| Platform | Resource-constrained | Desktop/Server | Large-scale Server |
| Use Case | IoT, Mobile | Desktop Apps | Web Apps, Microservices |

## Java Architecture

### JDK (Java Development Kit)
- Complete development kit
- Includes compiler (javac)
- Contains JRE plus development tools

### JRE (Java Runtime Environment)
- Minimum requirement to run Java applications
- Contains JVM and core libraries
- End-user component

### JVM (Java Virtual Machine)
- Executes Java bytecode
- Platform-independent
- Memory management and security

## Windows Installation Steps

[Screenshots will be added showing Java installation and verification]

## Eclipse Setup Steps

[Screenshots will be added showing Eclipse project creation and configuration]

## Feature Implementation Map

| Topic | Implementation Location |
|-------|------------------------|
| Inheritance | `edu.ccrm.domain.Person` |
| Polymorphism | `edu.ccrm.service` interfaces |
| Encapsulation | All domain classes |
| Abstraction | `Person` abstract class |
| Exception Handling | Custom exceptions in `edu.ccrm.util` |
| File I/O | `edu.ccrm.io` package |
| Stream API | Service implementations |
| Design Patterns | `AppConfig` (Singleton), `Course.Builder` |

## Assertions

To enable assertions when running the application:

```bash
java -ea edu.ccrm.CCRMApplication
```

Example assertions are used to validate:
- Non-null student IDs
- Valid credit limits
- Grade point ranges