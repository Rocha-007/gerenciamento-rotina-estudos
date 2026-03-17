package com.studyroutine.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Represents a study task.
 */
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

    /**
     * Constructs a StudyTask with the given parameters.
     *
     * @param id the unique identifier
     * @param subjectId the subject id this task belongs to
     * @param description the description of the task
     * @param dueDate the due date
     * @param startTime the start time
     * @param endTime the end time
     * @param priority the priority level (1-5)
     */
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

    // Getters and Setters

    /**
     * Gets the unique identifier.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier.
     *
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the subject id.
     *
     * @return the subjectId
     */
    public String getSubjectId() {
        return subjectId;
    }

    /**
     * Sets the subject id.
     *
     * @param subjectId the subjectId to set
     */
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the due date.
     *
     * @return the dueDate
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date.
     *
     * @param dueDate the dueDate to set
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Gets the start time.
     *
     * @return the startTime
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time.
     *
     * @param startTime the startTime to set
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets the end time.
     *
     * @return the endTime
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time.
     *
     * @param endTime the endTime to set
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Checks if the task is completed.
     *
     * @return true if completed, false otherwise
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Sets the completion status.
     *
     * @param completed the completion status to set
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Gets the priority level.
     *
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the priority level.
     *
     * @param priority the priority to set
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StudyTask that = (StudyTask) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "[" + (completed ? "✓" : " ") + "] " + description + " - " + dueDate;
    }
}
