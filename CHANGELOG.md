# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2026-03-17

### Added

- **Initial Release**: First version of Study Routine Manager
- **Subject Management**: Create, view, and delete subjects with professor information
- **Task Management**: Add, complete, and remove study tasks
- **Task Scheduling**: Set due dates, time slots (start/end times), and priority levels
- **GUI Interface**: User-friendly Swing-based graphical interface with tabbed navigation
- **Data Persistence**: JSON-based local storage for subjects and tasks
- **Automated Tests**: Comprehensive test suite with JUnit 5 covering:
  - Subject entity tests
  - StudyTask entity tests
  - StudyRoutineService business logic tests
  - Edge case and validation tests
- **Code Quality**: Checkstyle configuration for static code analysis
- **CI/CD Pipeline**: GitHub Actions workflow for automated testing and linting
- **Documentation**: Complete README with installation, usage, and development instructions
- **Project Configuration**: Maven-based build system with all necessary dependencies

### Features

- Subject/discipline organization
- Priority-based task management (levels 1-5)
- Completion tracking for tasks
- Persistent data storage
- Clean and intuitive user interface
- Cross-platform compatibility (Windows, macOS, Linux)

### Technical Details

- **Java Version**: 11
- **Build Tool**: Maven 3.9+
- **Testing Framework**: JUnit 5
- **Code Analysis**: Checkstyle
- **JSON Processing**: Gson
- **GUI Framework**: Swing

---

## Version Format

This changelog follows [Semantic Versioning](https://semver.org/):

- **MAJOR** version for breaking changes
- **MINOR** version for new functionality (backwards compatible)
- **PATCH** version for bug fixes

## Future Enhancements

Planned features for future releases:

- [ ] Export tasks to PDF or calendar format
- [ ] Study timer/Pomodoro integration
- [ ] Statistics and progress dashboard
- [ ] Recurring tasks support
- [ ] Tag-based task filtering
- [ ] Study group collaboration features
- [ ] Mobile app version
- [ ] Database backend option
- [ ] Email reminders for upcoming tasks
- [ ] Dark theme support
