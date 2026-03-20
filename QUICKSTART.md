# Quick Start Guide

## ⚡ EASIEST WAY TO RUN (Recommended)

### Just need Java installed? Then do this:

**Windows:**
```bash
Double-click: run.bat
```

**Linux/Mac:**
```bash
chmod +x run.sh
./run.sh
```

That's it! The script will automatically build and run the application.

---

## Initial Setup

### 1. Install Java Development Kit (JDK)

If you don't have Java 11 or higher installed:

**Windows:**
- Download from: https://www.oracle.com/java/technologies/downloads/
- Install JDK 11 LTS or higher
- Verify installation:
  ```
  java -version
  javac -version
  ```

### 2. Install Maven

Maven is required to build and run the project.

**Windows:**
1. Download from: https://maven.apache.org/download.cgi
2. Extract to a folder (e.g., `C:\apache-maven-3.9.0`)
3. Add Maven to PATH:
   - Right-click "This PC" → Properties
   - Click "Advanced system settings"
   - Click "Environment Variables"
   - Add new system variable:
     - Variable name: `MAVEN_HOME`
     - Variable value: `C:\apache-maven-3.9.0`
   - Edit PATH variable and add: `%MAVEN_HOME%\bin`
4. Verify installation:
   ```
   mvn --version
   ```

## Building and Running

### 1. Navigate to Project Directory

```bash
cd study-routine-manager
```

### 2. Clean and Build

```bash
mvn clean package
```

This command:
- Cleans previous builds
- Downloads dependencies
- Compiles source code
- Runs tests
- Creates executable JAR

### 3. Run the Application

**Option A: Direct execution**
```bash
mvn clean compile exec:java -Dexec.mainClass="com.studyroutine.ui.MainWindow"
```

**Option B: Run the JAR**
```bash
java -jar target/study-routine-manager.jar
```

## Testing

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=SubjectTest
mvn test -Dtest=StudyTaskTest
mvn test -Dtest=StudyRoutineServiceTest
```

### View Test Results
Test results are in: `target/surefire-reports/`

## Code Quality

### Run Checkstyle (Linting)
```bash
mvn checkstyle:check
```

### Generate Checkstyle Report
```bash
mvn checkstyle:checkstyle
```

Reports are in: `target/checkstyle-result.xml`

## Common Commands

```bash
# Full build with all checks
mvn clean verify

# Build without running tests
mvn clean package -DskipTests

# Run only unit tests
mvn test

# Check code style
mvn checkstyle:check

# View project dependencies
mvn dependency:tree

# Clean all build artifacts
mvn clean
```

## Troubleshooting

### Problem: "mvn command not found"
**Solution:** Maven is not in PATH. Install Maven and add it to system PATH.

### Problem: "The JAVA_HOME environment variable is not defined"
**Solution:**
1. Install JDK if not already installed
2. Set JAVA_HOME environment variable to JDK installation path
3. Add `%JAVA_HOME%\bin` to PATH

### Problem: Tests fail with "Cannot find symbol"
**Solution:** Dependencies may not be downloaded. Run:
```bash
mvn clean dependency:resolve
```

### Problem: "No tests found"
**Solution:** Ensure test files are in `src/test/java` and run:
```bash
mvn clean test
```

## Project Layout

```
study-routine-manager/
├── src/main/java/          # Main source code
├── src/test/java/          # Test code
├── target/                 # Build output (auto-created)
├── pom.xml                 # Maven configuration
├── checkstyle.xml          # Code style rules
├── README.md               # Full documentation
└── .github/workflows/      # CI/CD configuration
```

## Next Steps

1. ✅ Reviewed all files in the project
2. ✅ Built and tested the application
3. ✅ Verified all tests pass
4. ✅ Checked code quality with linting
5. ⏭️ Push to GitHub public repository
6. ⏭️ Create PDF submission with GitHub link
7. ⏭️ Submit PDF to platform

## Support

For detailed information, see:
- [README.md](README.md) - Full project documentation
- [CONTRIBUTING.md](CONTRIBUTING.md) - Development guidelines
- [CHANGELOG.md](CHANGELOG.md) - Version history
