# Study Routine Manager

![CI Status](https://github.com/Rocha-007/gerenciamento-rotina-estudos/actions/workflows/ci.yml/badge.svg)

A simple and effective desktop application to help students and university students organize and manage their study routine efficiently.

---

## 🚀 GET STARTED IN 30 SECONDS

**Requirements:** Only Java 11+ installed

**Step 1:** Clone the repository
```bash
git clone https://github.com/Rocha-007/gerenciamento-rotina-estudos.git
cd gerenciamento-rotina-estudos
```

**Step 2:** Run the application
- **Windows:** Double-click `run.bat`
- **Linux/Mac:** `./run.sh` (then `chmod +x run.sh` if needed)

✅ Done! The application will start automatically.

---

## Table of Contents

- [Problem Statement](#problem-statement)
- [Solution](#solution)
- [Target Audience](#target-audience)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [System Requirements](#system-requirements)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [Running Tests](#running-tests)
- [Running Linting](#running-linting)
- [Project Structure](#project-structure)
- [Usage Guide](#usage-guide)
- [Version](#version)
- [Author](#author)
- [Repository](#repository)
- [License](#license)

## Problem Statement

Many students struggle with organizing their studies effectively. Common challenges include:

- **Lack of organization**: Without a centralized system, students often lose track of subjects, deadlines, and study schedules
- **Poor time management**: Difficulty allocating sufficient study time for each subject
- **Forgotten deadlines**: Missing important assignment dates and exam preparation periods
- **No priority management**: Inability to distinguish between high-priority and low-priority tasks
- **Inconsistent study habits**: Lack of motivation and structure leads to procrastination

This application directly addresses these pain points by providing a centralized, user-friendly platform for managing study routines.

## Solution

**Study Routine Manager** is a desktop application that enables students to:

1. **Organize Subjects**: Create and manage all their subjects/disciplines with professor information
2. **Schedule Tasks**: Add study tasks with due dates, time slots, and priority levels
3. **Track Progress**: Mark tasks as completed and visualize their study progress
4. **Prioritize Work**: Set priority levels (1-5) to focus on the most important tasks
5. **Persistent Storage**: All data is automatically saved and retrieved

The application uses a simple GUI (Graphical User Interface) for easy interaction and requires no database setup—data is stored locally in JSON format.

## Target Audience

- Secondary school students
- University undergraduates
- Graduate students
- Any student seeking better study organization
- Students with attention difficulties who need structured planning

## Features

- ✅ **Subject Management**: Add, view, and remove subjects with professor names
- ✅ **Task Management**: Create study tasks linked to subjects
- ✅ **Scheduling**: Set due dates and time slots for study sessions
- ✅ **Priority System**: Assign priority levels (1-5) to tasks
- ✅ **Task Completion Tracking**: Mark tasks as completed and track progress
- ✅ **Persistent Data Storage**: Automatic saving to local JSON files
- ✅ **User-Friendly GUI**: Simple and intuitive interface using Swing
- ✅ **Cross-Platform**: Works on Windows, macOS, and Linux

## Technologies Used

- **Language**: Java 11
- **GUI Framework**: Swing (standard Java library)
- **Build Tool**: Maven 3.9+
- **Testing Framework**: JUnit 5
- **Code Quality**: Checkstyle
- **Data Persistence**: Gson (JSON processing)
- **CI/CD**: GitHub Actions

## System Requirements

- Java Development Kit (JDK) 11 or higher
- Maven 3.6 or higher
- Operating System: Windows, macOS, or Linux

## Installation

### 1. Clone the Repository

```bash
git clone https://github.com/Rocha-007/gerenciamento-rotina-estudos.git
cd gerenciamento-rotina-estudos
```

### 2. Verify Prerequisites

Ensure you have Java 11+ and Maven installed:

```bash
java -version
mvn --version
```

### 3. Install Dependencies

```bash
mvn clean dependency:resolve
```

### 4. Build the Project

```bash
mvn clean package
```

## Running the Application

### ⚡ EASIEST WAY (Recommended - Just One Click!)

If you have Java installed, simply run:

**Windows:**
```bash
Double-click: run.bat
```

**Linux/Mac:**
```bash
chmod +x run.sh
./run.sh
```

That's it! The script will handle everything automatically.

---

### Option 1: Using Maven

```bash
mvn clean compile exec:java -Dexec.mainClass="com.studyroutine.ui.MainWindow"
```

### Option 2: Using the Built JAR

After building with `mvn clean package`, run:

```bash
java -jar target/study-routine-manager.jar
```

## Running Tests

Run all automated tests:

```bash
mvn test
```

To run specific test classes:

```bash
mvn test -Dtest=SubjectTest
mvn test -Dtest=StudyTaskTest
mvn test -Dtest=StudyRoutineServiceTest
```

### Test Coverage

The project includes comprehensive tests covering:

1. **Model Tests**: Subject and StudyTask creation, validation, and behavior
2. **Service Tests**: Business logic for adding/removing subjects and tasks
3. **Edge Cases**: Invalid inputs, empty values, and boundary conditions
4. **Data Persistence**: Task and subject management lifecycle

## Running Linting

Check code quality using Checkstyle:

```bash
mvn checkstyle:check
```

To view detailed linting results:

```bash
mvn checkstyle:checkstyle
```

## Project Structure

```
study-routine-manager/
├── .github/
│   └── workflows/
│       └── ci.yml                 # GitHub Actions CI/CD pipeline
├── src/
│   ├── main/
│   │   └── java/com/studyroutine/
│   │       ├── model/
│   │       │   ├── Subject.java           # Subject entity
│   │       │   └── StudyTask.java         # StudyTask entity
│   │       ├── service/
│   │       │   ├── StudyRoutineService.java      # Main business logic
│   │       │   ├── LocalDateTypeAdapter.java     # JSON date adapter
│   │       │   └── LocalTimeTypeAdapter.java     # JSON time adapter
│   │       └── ui/
│   │           └── MainWindow.java        # Swing GUI implementation
│   └── test/
│       └── java/com/studyroutine/
│           ├── model/
│           │   ├── SubjectTest.java       # Subject tests
│           │   └── StudyTaskTest.java     # StudyTask tests
│           └── service/
│               └── StudyRoutineServiceTest.java  # Service tests
├── data/                          # Local storage directory (auto-created)
│   ├── subjects.json             # Subjects data file
│   └── tasks.json                # Tasks data file
├── pom.xml                        # Maven configuration and dependencies
├── checkstyle.xml                 # Checkstyle configuration
├── .gitignore                     # Git ignore rules
├── README.md                      # This file
├── CHANGELOG.md                   # Version history
├── LICENSE                        # License file
└── CONTRIBUTING.md                # Contribution guidelines

```

## Usage Guide

### 1. Starting the Application

Run the application using one of the methods described in the [Running the Application](#running-the-application) section.

### 2. Managing Subjects

**To add a subject:**

1. Click the "Subjects" tab
2. Click the "Add Subject" button
3. Enter the subject name (required)
4. Enter the professor name (optional)
5. Enter the description (optional)
6. Click "Save"

**To remove a subject:**

1. Select a subject from the list
2. Click the "Remove Subject" button
3. The subject and all associated tasks will be removed

### 3. Managing Study Tasks

**To add a task:**

1. Click the "Tasks" tab
2. Click the "Add Task" button
3. Select a subject from the dropdown
4. Enter the task description
5. Set the due date (format: YYYY-MM-DD)
6. Set the start time (format: HH:MM)
7. Set the end time (format: HH:MM)
8. Set the priority level (1-5, where 5 is highest)
9. Click "Save"

**To mark a task as completed:**

1. Select a task from the list
2. Click the "Mark as Completed" button
3. The task status will be updated

**To remove a task:**

1. Select a task from the list
2. Click the "Remove Task" button
3. The task will be deleted

### 4. Data Persistence

All subjects and tasks are automatically saved to the `data/` directory in JSON format:

- `data/subjects.json` - Contains all subjects
- `data/tasks.json` - Contains all tasks

Your data is preserved between application sessions.

## Design Decisions

Durante o desenvolvimento, foram tomadas as seguintes decisões de design:

### UI Framework
- **Swing**: Escolhido por ser incluído no JDK, sem dependências externas. Desenvolvimento mais rápido.

### Data Persistence
- **JSON com Gson**: Alternativa simplificada ao banco de dados relacional. Adequado para projeto de escala pequena/média e facilita portabilidade dos dados.

### Architecture Pattern
- **Service Pattern**: Separação clara entre UI (MainWindow), lógica de negócio (StudyRoutineService) e modelos de dados (Subject, StudyTask). Facilita testes e manutenção.

### Testing Strategy
- **JUnit 5**: Framework padrão da indústria. Testes abrangem modelos, serviço e casos limítrofes.

## Version

Current Version: **1.0.0** (Semantic Versioning - MAJOR.MINOR.PATCH)

- MAJOR: Breaking changes
- MINOR: New features (backwards compatible)
- PATCH: Bug fixes

## Author

[Your Name] - github.com/Rocha-007

## Repository

**GitHub Repository**: https://github.com/Rocha-007/gerenciamento-rotina-estudos

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Additional Resources

- [Java Documentation](https://docs.oracle.com/en/java/)
- [Maven Guide](https://maven.apache.org/)
- [JUnit 5 Documentation](https://junit.org/junit5/docs/current/user-guide/)
- [Checkstyle Documentation](https://checkstyle.sourceforge.io/)

## Support

For issues, feature requests, or contributions, please open an issue on the GitHub repository.

---

**Note**: This project is part of a bootcamp activity designed to demonstrate professional software development practices including version control, testing, linting, and CI/CD automation.
