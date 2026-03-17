package com.studyroutine.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudyTaskTest {
    private StudyTask t;

    @BeforeEach
    public void setUp() {
        t = new StudyTask(
                "task1",
                "subject1",
                "Study Chapter 5",
                LocalDate.of(2026, 3, 20),
                LocalTime.of(14, 0),
                LocalTime.of(15, 30),
                3
        );
    }

    @Test
    public void testCreation() {
        assertEquals("task1", t.getId());
        assertEquals("subject1", t.getSubjectId());
        assertEquals("Study Chapter 5", t.getDescription());
        assertEquals(LocalDate.of(2026, 3, 20), t.getDueDate());
        assertEquals(LocalTime.of(14, 0), t.getStartTime());
        assertEquals(LocalTime.of(15, 30), t.getEndTime());
        assertEquals(3, t.getPriority());
        assertFalse(t.isCompleted());
    }

    @Test
    public void testCompletion() {
        assertFalse(t.isCompleted());
        t.setCompleted(true);
        assertTrue(t.isCompleted());
    }

    @Test
    public void testPriority() {
        t.setPriority(5);
        assertEquals(5, t.getPriority());

        t.setPriority(1);
        assertEquals(1, t.getPriority());
    }

    @Test
    public void testToString() {
        String result = t.toString();
        assertTrue(result.contains("Study Chapter 5"));
        assertTrue(result.contains("2026-03-20"));
    }

    @Test
    public void testToStringCompleted() {
        t.setCompleted(true);
        String result = t.toString();
        assertTrue(result.contains("✓"));
    }
}
