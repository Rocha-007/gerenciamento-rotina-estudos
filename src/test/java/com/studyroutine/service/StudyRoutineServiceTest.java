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

public class StudyRoutineServiceTest {
    private StudyRoutineService svc;

    @BeforeEach
    public void setUp() {
        svc = new StudyRoutineService();
    }

    @Test
    public void testAddSubject() {
        Subject s = svc.addSubject("Mathematics", "Dr. Smith", "Calculus");
        assertEquals("Mathematics", s.getName());
        assertEquals("Dr. Smith", s.getProfessor());
        assertEquals("Calculus", s.getDescription());
        assertTrue(svc.getAllSubjects().contains(s));
    }

    @Test
    public void testAddSubjectEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> {
            svc.addSubject("", "Dr. Smith", "Description");
        });
    }

    @Test
    public void testAddSubjectNullName() {
        assertThrows(IllegalArgumentException.class, () -> {
            svc.addSubject(null, "Dr. Smith", "Description");
        });
    }

    @Test
    public void testRemoveSubject() {
        Subject s = svc.addSubject("Physics", "Dr. Johnson", "Modern Physics");
        assertTrue(svc.getAllSubjects().contains(s));
        
        boolean removed = svc.removeSubject(s.getId());
        assertTrue(removed);
        assertFalse(svc.getAllSubjects().contains(s));
    }

    @Test
    public void testRemoveSubjectNotFound() {
        boolean removed = svc.removeSubject("non-existent-id");
        assertFalse(removed);
    }

    @Test
    public void testAddTask() {
        Subject s = svc.addSubject("Mathematics", "Dr. Smith", "Calculus");
        StudyTask t = svc.addTask(
                s.getId(),
                "Study Chapter 5",
                LocalDate.of(2026, 3, 20),
                LocalTime.of(14, 0),
                LocalTime.of(15, 30),
                3
        );

        assertEquals("Study Chapter 5", t.getDescription());
        assertEquals(s.getId(), t.getSubjectId());
        assertEquals(3, t.getPriority());
        assertTrue(svc.getAllTasks().contains(t));
    }

    @Test
    public void testAddTaskEmptyDescription() {
        Subject s = svc.addSubject("Mathematics", "Dr. Smith", "Calculus");
        assertThrows(IllegalArgumentException.class, () -> {
            svc.addTask(s.getId(), "", LocalDate.now(), LocalTime.of(14, 0), LocalTime.of(15, 0), 3);
        });
    }

    @Test
    public void testAddTaskInvalidPriority() {
        Subject s = svc.addSubject("Mathematics", "Dr. Smith", "Calculus");
        
        assertThrows(IllegalArgumentException.class, () -> {
            svc.addTask(s.getId(), "Study", LocalDate.now(), LocalTime.of(14, 0), LocalTime.of(15, 0), 6);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            svc.addTask(s.getId(), "Study", LocalDate.now(), LocalTime.of(14, 0), LocalTime.of(15, 0), 0);
        });
    }

    @Test
    public void testMarkTaskCompleted() {
        Subject s = svc.addSubject("Mathematics", "Dr. Smith", "Calculus");
        StudyTask t = svc.addTask(s.getId(), "Study Chapter 5", LocalDate.now(), LocalTime.of(14, 0), LocalTime.of(15, 0), 3);
        
        assertFalse(t.isCompleted());
        svc.markTaskAsCompleted(t.getId());

        List<StudyTask> tasks = svc.getAllTasks();
        StudyTask reloaded = tasks.stream().filter(x -> x.getId().equals(t.getId())).findFirst().orElse(null);
        assertTrue(reloaded != null && reloaded.isCompleted());
    }

    @Test
    public void testGetTasksBySubject() {
        Subject s1 = svc.addSubject("Mathematics", "Dr. Smith", "Calculus");
        Subject s2 = svc.addSubject("Physics", "Dr. Johnson", "Mechanics");

        svc.addTask(s1.getId(), "Task 1", LocalDate.now(), LocalTime.of(14, 0), LocalTime.of(15, 0), 1);
        svc.addTask(s1.getId(), "Task 2", LocalDate.now(), LocalTime.of(16, 0), LocalTime.of(17, 0), 2);
        svc.addTask(s2.getId(), "Task 3", LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(11, 0), 3);

        List<StudyTask> s1Tasks = svc.getTasksBySubject(s1.getId());
        assertEquals(2, s1Tasks.size());

        List<StudyTask> s2Tasks = svc.getTasksBySubject(s2.getId());
        assertEquals(1, s2Tasks.size());
    }

    @Test
    public void testRemoveTask() {
        Subject s = svc.addSubject("Mathematics", "Dr. Smith", "Calculus");
        StudyTask t = svc.addTask(s.getId(), "Study Chapter 5", LocalDate.now(), LocalTime.of(14, 0), LocalTime.of(15, 0), 3);

        assertTrue(svc.getAllTasks().contains(t));
        boolean removed = svc.removeTask(t.getId());
        assertTrue(removed);
        assertFalse(svc.getAllTasks().contains(t));
    }

    @Test
    public void testRemoveTaskNotFound() {
        boolean removed = svc.removeTask("non-existent-task-id");
        assertFalse(removed);
    }

    @Test
    public void testRemoveSubjectRemovesTasks() {
        Subject s = svc.addSubject("Mathematics", "Dr. Smith", "Calculus");
        svc.addTask(s.getId(), "Task 1", LocalDate.now(), LocalTime.of(14, 0), LocalTime.of(15, 0), 1);
        svc.addTask(s.getId(), "Task 2", LocalDate.now(), LocalTime.of(16, 0), LocalTime.of(17, 0), 2);

        List<StudyTask> before = svc.getTasksBySubject(s.getId());
        assertEquals(2, before.size());

        svc.removeSubject(s.getId());

        List<StudyTask> after = svc.getTasksBySubject(s.getId());
        assertEquals(0, after.size());
    }
}
