package com.studyroutine.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the StudyTask class.
 */
public class StudyTaskTest {

    private StudyTask task;

    /**
     * Sets up the test fixtures.
     */
    @BeforeEach
    public void setUp() {
        task = new StudyTask(
                "task1",
                "subject1",
                "Study Chapter 5",
                LocalDate.of(2026, 3, 20),
                LocalTime.of(14, 0),
                LocalTime.of(15, 30),
                3
        );
    }

    /**
     * Tests task creation and getters.
     */
    @Test
    public void testTaskCreation() {
        assertEquals("task1", task.getId());
        assertEquals("subject1", task.getSubjectId());
        assertEquals("Study Chapter 5", task.getDescription());
        assertEquals(LocalDate.of(2026, 3, 20), task.getDueDate());
        assertEquals(LocalTime.of(14, 0), task.getStartTime());
        assertEquals(LocalTime.of(15, 30), task.getEndTime());
        assertEquals(3, task.getPriority());
        assertFalse(task.isCompleted());
    }

    /**
     * Tests task completion status.
     */
    @Test
    public void testTaskCompletion() {
        assertFalse(task.isCompleted());
        task.setCompleted(true);
        assertTrue(task.isCompleted());
    }

    /**
     * Tests task priority validation (should accept 1-5).
     */
    @Test
    public void testTaskPriority() {
        task.setPriority(5);
        assertEquals(5, task.getPriority());

        task.setPriority(1);
        assertEquals(1, task.getPriority());
    }

    /**
     * Tests task equality based on ID.
     */
    @Test
    public void testTaskEquality() {
        StudyTask task2 = new StudyTask(
                "task1",
                "subject2",
                "Different Description",
                LocalDate.of(2026, 4, 1),
                LocalTime.of(16, 0),
                LocalTime.of(17, 0),
                5
        );
        assertEquals(task, task2); // Should be equal based on ID
    }

    /**
     * Tests task inequality.
     */
    @Test
    public void testTaskInequality() {
        StudyTask task2 = new StudyTask(
                "task2",
                "subject1",
                "Study Chapter 5",
                LocalDate.of(2026, 3, 20),
                LocalTime.of(14, 0),
                LocalTime.of(15, 30),
                3
        );
        assertNotEquals(task, task2); // Should not be equal (different ID)
    }

    /**
     * Tests task toString method.
     */
    @Test
    public void testTaskToString() {
        String result = task.toString();
        assertTrue(result.contains("Study Chapter 5"));
        assertTrue(result.contains("2026-03-20"));
    }

    /**
     * Tests task toString with completed status.
     */
    @Test
    public void testTaskToStringCompleted() {
        task.setCompleted(true);
        String result = task.toString();
        assertTrue(result.contains("✓"));
    }

    /**
     * Tests task hash code consistency.
     */
    @Test
    public void testTaskHashCode() {
        StudyTask task2 = new StudyTask(
                "task1",
                "subject1",
                "Study Chapter 5",
                LocalDate.of(2026, 3, 20),
                LocalTime.of(14, 0),
                LocalTime.of(15, 30),
                3
        );
        assertEquals(task.hashCode(), task2.hashCode());
    }
}
