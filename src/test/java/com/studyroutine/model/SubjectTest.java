package com.studyroutine.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the Subject class.
 */
public class SubjectTest {

    private Subject subject;

    /**
     * Sets up the test fixtures.
     */
    @BeforeEach
    public void setUp() {
        subject = new Subject("id1", "Mathematics", "Dr. Smith", "Advanced Calculus");
    }

    /**
     * Tests subject creation and getters.
     */
    @Test
    public void testSubjectCreation() {
        assertEquals("id1", subject.getId());
        assertEquals("Mathematics", subject.getName());
        assertEquals("Dr. Smith", subject.getProfessor());
        assertEquals("Advanced Calculus", subject.getDescription());
    }

    /**
     * Tests subject setters.
     */
    @Test
    public void testSubjectSetters() {
        subject.setName("Physics");
        subject.setProfessor("Dr. Johnson");
        subject.setDescription("Modern Physics");

        assertEquals("Physics", subject.getName());
        assertEquals("Dr. Johnson", subject.getProfessor());
        assertEquals("Modern Physics", subject.getDescription());
    }

    /**
     * Tests subject equality based on ID.
     */
    @Test
    public void testSubjectEquality() {
        Subject subject2 = new Subject("id1", "Different Name", "Different Prof", "Different Desc");
        assertEquals(subject, subject2); // Should be equal based on ID
    }

    /**
     * Tests subject inequality.
     */
    @Test
    public void testSubjectInequality() {
        Subject subject2 = new Subject("id2", "Mathematics", "Dr. Smith", "Advanced Calculus");
        assertNotEquals(subject, subject2); // Should not be equal (different ID)
    }

    /**
     * Tests subject toString method.
     */
    @Test
    public void testSubjectToString() {
        String result = subject.toString();
        assertTrue(result.contains("Mathematics"));
        assertTrue(result.contains("Dr. Smith"));
    }

    /**
     * Tests subject toString with empty professor.
     */
    @Test
    public void testSubjectToStringEmptyProfessor() {
        Subject subject2 = new Subject("id2", "Biology", "", "Basic Biology");
        String result = subject2.toString();
        assertEquals("Biology", result);
    }

    /**
     * Tests subject hash code consistency.
     */
    @Test
    public void testSubjectHashCode() {
        Subject subject2 = new Subject("id1", "Mathematics", "Dr. Smith", "Advanced Calculus");
        assertEquals(subject.hashCode(), subject2.hashCode());
    }
}
