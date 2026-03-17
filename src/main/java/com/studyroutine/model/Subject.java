package com.studyroutine.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a study subject/discipline.
 */
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String professor;
    private String description;

    /**
     * Constructs a Subject with the given parameters.
     *
     * @param id the unique identifier of the subject
     * @param name the name of the subject
     * @param professor the professor's name
     * @param description a description of the subject
     */
    public Subject(String id, String name, String professor, String description) {
        this.id = id;
        this.name = name;
        this.professor = professor;
        this.description = description;
    }

    /**
     * Gets the unique identifier of the subject.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the subject.
     *
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the name of the subject.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the subject.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the professor's name.
     *
     * @return the professor
     */
    public String getProfessor() {
        return professor;
    }

    /**
     * Sets the professor's name.
     *
     * @param professor the professor to set
     */
    public void setProfessor(String professor) {
        this.professor = professor;
    }

    /**
     * Gets the description of the subject.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the subject.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Subject subject = (Subject) o;
        return Objects.equals(id, subject.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name + (professor != null && !professor.isEmpty() ? " (" + professor + ")" : "");
    }
}
