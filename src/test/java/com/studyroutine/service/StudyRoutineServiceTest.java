package com.studyroutine.service;

import com.studyroutine.model.Subject;
import com.studyroutine.model.StudyTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Unit tests for the StudyRoutineService class.
 */
public class StudyRoutineServiceTest {

    private StudyRoutineService service;

    /**
     * Sets up the test fixtures.
     */
    @BeforeEach
    public void setUp() {
        service = new StudyRoutineService();
    }

    /**
     * Tests adding a subject successfully.
     */
    @Test
    public void testAddSubjectSuccess() {
        Subject subject = service.addSubject("Mathematics", "Dr. Smith", "Calculus");

        assertEquals("Mathematics", subject.getName());
        assertEquals("Dr. Smith", subject.getProfessor());
        assertEquals("Calculus", subject.getDescription());

        List<Subject> subjects = service.getAllSubjects();
        assertTrue(subjects.contains(subject));
    }

    /**
     * Tests adding a subject with empty name throws exception.
     */
    @Test
    public void testAddSubjectEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.addSubject("", "Dr. Smith", "Description");
        });
    }

    /**
     * Tests adding a subject with null name throws exception.
     */
    @Test
    public void testAddSubjectNullName() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.addSubject(null, "Dr. Smith", "Description");
        });
    }

    /**
     * Tests removing a subject successfully.
     */
    @Test
    public void testRemoveSubjectSuccess() {
        Subject subject = service.addSubject("Physics", "Dr. Johnson", "Modern Physics");
        assertTrue(service.getAllSubjects().contains(subject));

        boolean removed = service.removeSubject(subject.getId());
        assertTrue(removed);
        assertFalse(service.getAllSubjects().contains(subject));
    }

    /**
     * Tests removing a non-existent subject returns false.
     */
    @Test
    public void testRemoveSubjectNotFound() {
        boolean removed = service.removeSubject("non-existent-id");
        assertFalse(removed);
    }

    /**
     * Tests adding a task successfully.
     */
    @Test
    public void testAddTaskSuccess() {
        Subject subject = service.addSubject("Mathematics", "Dr. Smith", "Calculus");

        StudyTask task = service.addTask(
                subject.getId(),
                "Study Chapter 5",
                LocalDate.of(2026, 3, 20),
                LocalTime.of(14, 0),
                LocalTime.of(15, 30),
                3
        );

        assertEquals("Study Chapter 5", task.getDescription());
        assertEquals(subject.getId(), task.getSubjectId());
        assertEquals(3, task.getPriority());

        List<StudyTask> tasks = service.getAllTasks();
        assertTrue(tasks.contains(task));
    }

    /**
     * Tests adding a task with empty description throws exception.
     */
    @Test
    public void testAddTaskEmptyDescription() {
        Subject subject = service.addSubject("Mathematics", "Dr. Smith", "Calculus");

        assertThrows(IllegalArgumentException.class, () -> {
            service.addTask(
                    subject.getId(),
                    "",
                    LocalDate.now(),
                    LocalTime.of(14, 0),
                    LocalTime.of(15, 0),
                    3
            );
        });
    }

    /**
     * Tests adding a task with invalid priority throws exception.
     */
    @Test
    public void testAddTaskInvalidPriority() {
        Subject subject = service.addSubject("Mathematics", "Dr. Smith", "Calculus");

        assertThrows(IllegalArgumentException.class, () -> {
            service.addTask(
                    subject.getId(),
                    "Study Chapter 5",
                    LocalDate.now(),
                    LocalTime.of(14, 0),
                    LocalTime.of(15, 0),
                    6 // Invalid priority > 5
            );
        });

        assertThrows(IllegalArgumentException.class, () -> {
            service.addTask(
                    subject.getId(),
                    "Study Chapter 5",
                    LocalDate.now(),
                    LocalTime.of(14, 0),
                    LocalTime.of(15, 0),
                    0 // Invalid priority < 1
            );
        });
    }

    /**
     * Tests marking a task as completed.
     */
    @Test
    public void testMarkTaskAsCompleted() {
        Subject subject = service.addSubject("Mathematics", "Dr. Smith", "Calculus");
        StudyTask task = service.addTask(
                subject.getId(),
                "Study Chapter 5",
                LocalDate.now(),
                LocalTime.of(14, 0),
                LocalTime.of(15, 0),
                3
        );

        assertFalse(task.isCompleted());

        service.markTaskAsCompleted(task.getId());

        // Reload task from service
        List<StudyTask> tasks = service.getAllTasks();
        StudyTask reloadedTask = tasks.stream()
                .filter(t -> t.getId().equals(task.getId()))
                .findFirst()
                .orElse(null);

        assertTrue(reloadedTask != null && reloadedTask.isCompleted());
    }

    /**
     * Tests getting tasks by subject.
     */
    @Test
    public void testGetTasksBySubject() {
        Subject subject1 = service.addSubject("Mathematics", "Dr. Smith", "Calculus");
        Subject subject2 = service.addSubject("Physics", "Dr. Johnson", "Mechanics");

        service.addTask(subject1.getId(), "Task 1", LocalDate.now(), LocalTime.of(14, 0), LocalTime.of(15, 0), 1);
        service.addTask(subject1.getId(), "Task 2", LocalDate.now(), LocalTime.of(16, 0), LocalTime.of(17, 0), 2);
        service.addTask(subject2.getId(), "Task 3", LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(11, 0), 3);

        List<StudyTask> subject1Tasks = service.getTasksBySubject(subject1.getId());
        assertEquals(2, subject1Tasks.size());

        List<StudyTask> subject2Tasks = service.getTasksBySubject(subject2.getId());
        assertEquals(1, subject2Tasks.size());
    }

    /**
     * Tests removing a task successfully.
     */
    @Test
    public void testRemoveTaskSuccess() {
        Subject subject = service.addSubject("Mathematics", "Dr. Smith", "Calculus");
        StudyTask task = service.addTask(
                subject.getId(),
                "Study Chapter 5",
                LocalDate.now(),
                LocalTime.of(14, 0),
                LocalTime.of(15, 0),
                3
        );

        assertTrue(service.getAllTasks().contains(task));

        boolean removed = service.removeTask(task.getId());
        assertTrue(removed);
        assertFalse(service.getAllTasks().contains(task));
    }

    /**
     * Tests removing a non-existent task returns false.
     */
    @Test
    public void testRemoveTaskNotFound() {
        boolean removed = service.removeTask("non-existent-task-id");
        assertFalse(removed);
    }

    /**
     * Tests that removing a subject also removes its tasks.
     */
    @Test
    public void testRemoveSubjectRemovesTasks() {
        Subject subject = service.addSubject("Mathematics", "Dr. Smith", "Calculus");
        service.addTask(subject.getId(), "Task 1", LocalDate.now(), LocalTime.of(14, 0), LocalTime.of(15, 0), 1);
        service.addTask(subject.getId(), "Task 2", LocalDate.now(), LocalTime.of(16, 0), LocalTime.of(17, 0), 2);

        List<StudyTask> tasksBeforeRemoval = service.getTasksBySubject(subject.getId());
        assertEquals(2, tasksBeforeRemoval.size());

        service.removeSubject(subject.getId());

        List<StudyTask> tasksAfterRemoval = service.getTasksBySubject(subject.getId());
        assertEquals(0, tasksAfterRemoval.size());
    }
}
