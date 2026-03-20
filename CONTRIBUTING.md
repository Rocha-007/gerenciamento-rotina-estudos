# Contributing to Study Routine Manager

Thank you for your interest in contributing to Study Routine Manager! This document provides guidelines and instructions for contributing to this project.

## Code of Conduct

We are committed to providing a welcoming and inclusive environment for all contributors. Please:

- Be respectful and professional in all interactions
- Welcome diverse perspectives and experiences
- Focus on constructive feedback
- Report inappropriate behavior to the project maintainers

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 11 or higher
- Maven 3.6 or higher
- Git

### Setting Up Your Development Environment

1. **Fork the repository** on GitHub
2. **Clone your fork** locally:
   ```bash
   git clone https://github.com/Rocha-007/gerenciamento-rotina-estudos.git
   cd gerenciamento-rotina-estudos
   ```

3. **Create a new branch** for your feature:
   ```bash
   git checkout -b feature/your-feature-name
   ```

4. **Install dependencies**:
   ```bash
   mvn clean dependency:resolve
   ```

5. **Build the project**:
   ```bash
   mvn clean package
   ```

## Development Workflow

### Making Changes

1. **Create a feature branch**: Always create a new branch for your changes
   ```bash
   git checkout -b feature/descriptive-name
   ```

2. **Write clear, focused commits**:
   ```bash
   git commit -m "Brief description of changes"
   ```

3. **Follow Java coding conventions**:
   - Use meaningful variable and method names
   - Add Javadoc comments for public classes and methods
   - Keep methods focused and concise
   - Maintain consistent indentation (4 spaces)

### Code Quality Standards

- **Checkstyle Compliance**: All code must pass `mvn checkstyle:check`
- **Test Coverage**: Write tests for new features
- **Documentation**: Update README and comments as needed
- **No Warnings**: Code should compile without warnings

### Testing Your Changes

1. **Run all tests**:
   ```bash
   mvn test
   ```

2. **Run checkstyle**:
   ```bash
   mvn checkstyle:check
   ```

3. **Build the project**:
   ```bash
   mvn clean package
   ```

4. **Test the application manually**:
   ```bash
   mvn clean compile exec:java -Dexec.mainClass="com.studyroutine.ui.MainWindow"
   ```

## Submitting Changes

### Pull Request Process

1. **Push your branch** to your fork:
   ```bash
   git push origin feature/your-feature-name
   ```

2. **Create a Pull Request** on GitHub with:
   - Clear title describing the changes
   - Detailed description of what and why
   - Reference to any related issues (e.g., "Closes #123")
   - Evidence that tests pass and linting passes

3. **Wait for review**: Maintainers will review your changes
4. **Address feedback**: Make requested changes and push updates
5. **Merge**: Once approved, your changes will be merged

### Pull Request Template

```markdown
## Description
Brief description of the changes

## Type of Change
- [ ] Bug fix
- [ ] New feature
- [ ] Documentation update
- [ ] Code refactoring

## Related Issues
Closes #(issue number)

## Testing
Describe testing performed:
- [ ] Unit tests added/updated
- [ ] Manual testing completed
- [ ] Linting passed

## Checklist
- [ ] Code follows project style guidelines
- [ ] No new warnings generated
- [ ] Tests pass locally
- [ ] README updated (if needed)
```

## Project Structure

```
study-routine-manager/
├── src/main/java/com/studyroutine/
│   ├── model/          # Data models (Subject, StudyTask)
│   ├── service/        # Business logic layer
│   └── ui/             # User interface (Swing GUI)
├── src/test/java/      # Unit tests
├── pom.xml             # Maven configuration
└── checkstyle.xml      # Code style rules
```

## Coding Guidelines

### Java Conventions

```java
// Good: Clear, well-documented method
/**
 * Adds a new subject to the system.
 *
 * @param name the subject name
 * @param professor the professor name
 * @param description the subject description
 * @return the created Subject
 * @throws IllegalArgumentException if name is empty
 */
public Subject addSubject(String name, String professor, String description) {
    if (name == null || name.trim().isEmpty()) {
        throw new IllegalArgumentException("Subject name cannot be empty");
    }
    // Implementation
}
```

### Naming Conventions

- **Classes**: PascalCase (e.g., `MainWindow`, `StudyRoutineService`)
- **Methods**: camelCase (e.g., `addSubject`, `getTasksBySubject`)
- **Constants**: UPPER_SNAKE_CASE (e.g., `DATA_DIR`, `SUBJECTS_FILE`)
- **Variables**: camelCase (e.g., `subject`, `taskList`)

### Comment Style

- Use Javadoc for public classes and methods
- Use single-line comments (`//`) for implementation details
- Avoid obvious comments; explain the "why", not the "what"

## Issue Reporting

### How to Report a Bug

1. **Check existing issues** to avoid duplicates
2. **Use a clear, descriptive title**
3. **Provide a description** including:
   - Steps to reproduce
   - Expected behavior
   - Actual behavior
   - Environment (OS, Java version)
4. **Attach screenshots or logs** if applicable

### Feature Request

1. **Describe the feature** clearly
2. **Explain the problem** it solves
3. **Suggest an implementation** if possible
4. **Note any related features** or dependencies

## Version Management

This project uses [Semantic Versioning](https://semver.org/):

- **MAJOR**: Breaking changes
- **MINOR**: New features (backwards compatible)
- **PATCH**: Bug fixes

Version is defined in `pom.xml` and should be updated when releasing.

## Release Process

1. Update version in `pom.xml`
2. Update `CHANGELOG.md`
3. Create a git tag: `git tag v1.x.x`
4. Push the tag: `git push origin v1.x.x`
5. Create a Release on GitHub

## Questions or Need Help?

- **Open an issue** for questions or confusions
- **Check existing documentation** in README.md
- **Review test files** for usage examples

## License

By contributing to this project, you agree that your contributions will be licensed under the MIT License.

---

Thank you for contributing to Study Routine Manager! 🎉
