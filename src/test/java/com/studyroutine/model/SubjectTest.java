package com.studyroutine.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubjectTest {
    private Subject s;

    @BeforeEach
    public void setUp() {
        s = new Subject("id1", "Mathematics", "Dr. Smith", "Advanced Calculus");
    }

    @Test
    public void testCreation() {
        assertEquals("id1", s.getId());
        assertEquals("Mathematics", s.getName());
        assertEquals("Dr. Smith", s.getProfessor());
        assertEquals("Advanced Calculus", s.getDescription());
    }

    @Test
    public void testSetters() {
        s.setName("Physics");
        s.setProfessor("Dr. Johnson");
        s.setDescription("Modern Physics");

        assertEquals("Physics", s.getName());
        assertEquals("Dr. Johnson", s.getProfessor());
        assertEquals("Modern Physics", s.getDescription());
    }

    @Test
    public void testToString() {
        String result = s.toString();
        assertTrue(result.contains("Mathematics"));
        assertTrue(result.contains("Dr. Smith"));
    }

    @Test
    public void testToStringEmptyProfessor() {
        Subject s2 = new Subject("id2", "Biology", "", "Basic Biology");
        String result = s2.toString();
        assertEquals("Biology", result);
    }
}
