@echo off
REM Study Routine Manager - Windows Launcher
REM This script runs the Study Routine Manager application

echo ========================================
echo   Study Routine Manager v1.0.0
echo ========================================
echo.

REM Add Maven to PATH if not already present
set "PATH=%USERPROFILE%\tools\apache-maven-3.9.6\bin;%PATH%"

REM Check if Java is installed
java -version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java 11 or higher from https://www.oracle.com/java/technologies/downloads/
    pause
    exit /b 1
)

REM Check if JAR file exists
if not exist "target\study-routine-manager.jar" (
    echo ERROR: study-routine-manager.jar not found
    echo Building the application...
    call mvn clean package
    if errorlevel 1 (
        echo Failed to build application. Make sure Maven is installed.
        pause
        exit /b 1
    )
)

REM Run the application
echo Starting application...
java -jar target\study-routine-manager.jar

if errorlevel 1 (
    echo.
    echo Application encountered an error
    pause
)
