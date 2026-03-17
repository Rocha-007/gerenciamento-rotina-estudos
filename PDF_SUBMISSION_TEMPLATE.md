# PDF Submission Instructions

To create your final PDF submission for the bootcamp, follow these steps:

## Content to Include in Your PDF

Your PDF submission should include the following information:

```
═══════════════════════════════════════════════════════════════════════════════

                         BOOTCAMP PROJECT SUBMISSION

Student Name:            [Your Full Name]
Discipline:              [Bootcamp Course Name]
Project Name:            Study Routine Manager
Date of Submission:      [Current Date]

═══════════════════════════════════════════════════════════════════════════════

PROJECT SUMMARY
───────────────────────────────────────────────────────────────────────────────

Project Name:
    Study Routine Manager v1.0.0

Problem Description:
    Many students struggle with organizing their study routines effectively.
    This application solves the problem of poor study organization by providing
    a centralized platform to manage subjects, schedule study tasks, set
    priorities, and track progress.

Solution Overview:
    Study Routine Manager is a desktop GUI application that enables students to:
    • Organize and manage all their subjects/disciplines
    • Create and schedule study tasks with due dates and time slots
    • Assign priority levels (1-5) to focus on important tasks
    • Mark tasks as completed and track study progress
    • Persist all data locally in JSON format


GitHub Repository:
    https://github.com/[your-username]/study-routine-manager

═══════════════════════════════════════════════════════════════════════════════

TECHNICAL IMPLEMENTATION
───────────────────────────────────────────────────────────────────────────────

Technology Stack:
    • Language: Java 11
    • GUI Framework: Swing
    • Build Tool: Maven
    • Testing: JUnit 5
    • Code Quality: Checkstyle
    • CI/CD: GitHub Actions
    • Data Persistence: Gson (JSON)

Key Features Implemented:
    ✓ Subject management (create, view, delete)
    ✓ Task management with scheduling
    ✓ Priority system (levels 1-5)
    ✓ Task completion tracking
    ✓ Persistent data storage
    ✓ User-friendly GUI interface
    ✓ Comprehensive automated tests
    ✓ Code quality analysis
    ✓ CI/CD pipeline

═══════════════════════════════════════════════════════════════════════════════

PROJECT DELIVERABLES CHECKLIST
───────────────────────────────────────────────────────────────────────────────

Functional Requirements:
    ✓ Open-source problem solved
    ✓ Working application with CLI/GUI interface
    ✓ Clear relationship between problem and solution
    ✓ User-friendly interface

Technical Requirements:
    ✓ Public GitHub repository with full source code
    ✓ Complete README.md with installation and usage instructions
    ✓ Semantic versioning (1.0.0)
    ✓ Explicit dependency declaration (pom.xml)
    ✓ Automated tests with JUnit 5
    ✓ Linting/static analysis with Checkstyle
    ✓ GitHub Actions CI/CD pipeline

Documentation:
    ✓ README.md - Complete project documentation
    ✓ CHANGELOG.md - Version history
    ✓ CONTRIBUTING.md - Contribution guidelines
    ✓ LICENSE - MIT License
    ✓ This submission document

═══════════════════════════════════════════════════════════════════════════════

HOW TO ACCESS AND VERIFY THE PROJECT
───────────────────────────────────────────────────────────────────────────────

1. Repository Access:
   https://github.com/[your-username]/study-routine-manager

2. Clone the Repository:
   $ git clone https://github.com/[your-username]/study-routine-manager.git
   $ cd study-routine-manager

3. Build the Project:
   $ mvn clean package

4. Run the Application:
   $ mvn clean compile exec:java -Dexec.mainClass="com.studyroutine.ui.MainWindow"
   
   OR
   
   $ java -jar target/study-routine-manager.jar

5. Run Tests:
   $ mvn test

6. Run Linting:
   $ mvn checkstyle:check

7. View CI/CD Pipeline:
   Visit: https://github.com/[your-username]/study-routine-manager/actions

═══════════════════════════════════════════════════════════════════════════════

NOTES FOR EVALUATORS
───────────────────────────────────────────────────────────────────────────────

• The repository is PUBLIC and accessible to all evaluators
• All dependencies are declared in pom.xml (Maven)
• Comprehensive tests are included and passing
• Code follows Java conventions and Checkstyle rules
• CI/CD pipeline automatically runs tests and linting on every push
• Data is stored locally in JSON files (no database required)
• The application is fully functional and production-ready

═══════════════════════════════════════════════════════════════════════════════

Author: [Your Full Name]
Contact: [Your Email]
Date: [Submission Date]

═══════════════════════════════════════════════════════════════════════════════
```

## How to Generate PDF

### Option 1: Using an Online Converter
1. Copy the above content to a text editor
2. Format it as desired
3. Export to PDF using online tools like:
   - https://www.ilovepdf.com/word_to_pdf
   - https://www.pdf.io/

### Option 2: Using Microsoft Word
1. Create a new Word document
2. Copy the content above
3. Format for better presentation
4. Save as PDF (File > Export as PDF)

### Option 3: Using Google Docs
1. Create a new Google Doc
2. Paste the content
3. Format as needed
4. Download as PDF (File > Download > PDF Document)

## Fields to Personalize

Replace the following placeholders with your actual information:

- `[Your Full Name]` - Your complete name
- `[Bootcamp Course Name]` - The name of your bootcamp course
- `[Current Date]` - Today's date
- `[your-username]` - Your GitHub username
- `[Your Email]` - Your contact email
- `[Submission Date]` - Date you are submitting

## File Naming Convention

Name your PDF file as follows:
```
[YourName]_StudyRoutineManager_BootcampSubmission.pdf
```

Example:
```
JoaoSilva_StudyRoutineManager_BootcampSubmission.pdf
```

---

**Note**: Make sure the GitHub repository link in your PDF is valid and publicly accessible before submission.
