package com.studyroutine.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class StudyTask implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String subjectId;
    private String description;
    private LocalDate dueDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean completed;
    private int priority;

    public StudyTask(String id, String subjectId, String description, LocalDate dueDate,
                     LocalTime startTime, LocalTime endTime, int priority) {
        this.id = id;
        this.subjectId = subjectId;
        this.description = description;
        this.dueDate = dueDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.completed = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "[" + (completed ? "✓" : " ") + "] " + description + " - " + dueDate;
    }
}
