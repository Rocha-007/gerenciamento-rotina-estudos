# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.1.0-intermediaria] - 2026-05-11

### Added (Etapa 2 - Entrega Intermediária)

- **Public API Integration**: Integration with Nager.Date API for Brazilian national holidays
  - `HolidayService.java`: Service for consuming public holidays API
  - `Holiday.java`: Data model for holiday information
  - Automatic caching to optimize API calls
  - Graceful error handling for API failures

- **Comprehensive Testing**:
  - `HolidayServiceIntegrationTest.java`: Integration tests with real API calls
    - 11 test cases covering API communication, caching, and error handling
    - Validates JSON deserialization
    - Tests date validation and range checks
  - `HolidayServiceUnitTest.java`: Unit tests with mocking for offline testing
  - Mockito 5.2.0 for test mocking
  - Tests validate both success and error scenarios

- **Service Enhancement**:
  - `StudyRoutineService.java` now integrates `HolidayService`
  - Methods: `isHoliday()`, `getHolidayName()`, `getHolidayWarning()`, `getUpcomingHolidaysInfo()`
  - Alerts users when scheduling tasks on holidays

- **Documentation**:
  - `ENTREGA_INTERMEDIARIA.md`: Complete guide for Etapa 2 deliverables
  - Updated README with API integration details
  - Usage examples and troubleshooting guide

### Changed

- **pom.xml**: Added Mockito dependencies for testing
- **README.md**: 
  - Added API Integration section
  - Updated version to 1.1.0-intermediaria
  - Added new technologies (Nager.Date API, Mockito)
- **StudyRoutineService.java**: Now includes holiday checking methods

### Technical Details

- **New Dependencies**:
  - Mockito Core 5.2.0 (test)
  - Mockito JUnit Jupiter 5.2.0 (test)
  
- **API Used**: 
  - Nager.Date Public Holidays API (https://api.nager.date)
  - Brazilian holidays (country code: BR)
  
- **Network Integration**:
  - Uses Java 11 built-in HttpClient
  - Configurable timeouts (5s connection, 10s read)
  - User-Agent header set for API compatibility
  
- **Error Handling**:
  - IOException handling for network failures
  - InterruptedException handling for thread interruption
  - Graceful degradation - API failures don't break application

### Testing Coverage

- Integration tests: 11 test cases with real API calls
- Unit tests: 8 test cases with mocking
- Validation tests for:
  - Date ranges
  - Null checking
  - HTTP error codes
  - Cache behavior
  - Holiday detection accuracy

### Branch & PR

- Branch: `entrega-intermediaria`
- Ready for Pull Request to main
- GitHub Actions CI/CD pipeline validates all changes

---

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
