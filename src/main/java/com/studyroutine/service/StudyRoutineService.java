package com.studyroutine.service;

import com.studyroutine.model.Subject;
import com.studyroutine.model.StudyTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Service class for managing subjects and study tasks.
 * Handles persistence to JSON files.
 */
public class StudyRoutineService {

    private static final String DATA_DIR = "data";
    private static final String SUBJECTS_FILE = DATA_DIR + File.separator + "subjects.json";
    private static final String TASKS_FILE = DATA_DIR + File.separator + "tasks.json";

    private List<Subject> subjects;
    private List<StudyTask> tasks;
    private final Gson gson;

    /**
     * Constructs the StudyRoutineService and initializes data.
     */
    public StudyRoutineService() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .registerTypeAdapter(LocalTime.class, new LocalTimeTypeAdapter())
                .setPrettyPrinting()
                .create();
        this.subjects = new ArrayList<>();
        this.tasks = new ArrayList<>();

        ensureDataDirectory();
        loadSubjects();
        loadTasks();
    }

    /**
     * Ensures the data directory exists.
     */
    private void ensureDataDirectory() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    /**
     * Adds a new subject.
     *
     * @param name the subject name
     * @param professor the professor name
     * @param description the subject description
     * @return the created Subject
     */
    public Subject addSubject(String name, String professor, String description) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Subject name cannot be empty");
        }

        String id = UUID.randomUUID().toString();
        Subject subject = new Subject(id, name, professor, description);
        subjects.add(subject);
        saveSubjects();
        return subject;
    }

    /**
     * Removes a subject and all associated tasks.
     *
     * @param subjectId the subject id
     * @return true if removed, false if not found
     */
    public boolean removeSubject(String subjectId) {
        boolean removed = subjects.removeIf(s -> s.getId().equals(subjectId));
        if (removed) {
            tasks.removeIf(t -> t.getSubjectId().equals(subjectId));
            saveSubjects();
            saveTasks();
        }
        return removed;
    }

    /**
     * Gets all subjects.
     *
     * @return list of subjects
     */
    public List<Subject> getAllSubjects() {
        return new ArrayList<>(subjects);
    }

    /**
     * Adds a new study task.
     *
     * @param subjectId the subject id
     * @param description the task description
     * @param dueDate the due date
     * @param startTime the start time
     * @param endTime the end time
     * @param priority the priority level
     * @return the created StudyTask
     */
    public StudyTask addTask(String subjectId, String description, LocalDate dueDate,
                              LocalTime startTime, LocalTime endTime, int priority) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Task description cannot be empty");
        }
        if (priority < 1 || priority > 5) {
            throw new IllegalArgumentException("Priority must be between 1 and 5");
        }

        String id = UUID.randomUUID().toString();
        StudyTask task = new StudyTask(id, subjectId, description, dueDate, startTime, endTime, priority);
        tasks.add(task);
        saveTasks();
        return task;
    }

    /**
     * Removes a study task.
     *
     * @param taskId the task id
     * @return true if removed, false if not found
     */
    public boolean removeTask(String taskId) {
        boolean removed = tasks.removeIf(t -> t.getId().equals(taskId));
        if (removed) {
            saveTasks();
        }
        return removed;
    }

    /**
     * Marks a task as completed.
     *
     * @param taskId the task id
     */
    public void markTaskAsCompleted(String taskId) {
        tasks.stream()
                .filter(t -> t.getId().equals(taskId))
                .findFirst()
                .ifPresent(t -> {
                    t.setCompleted(true);
                    saveTasks();
                });
    }

    /**
     * Gets all tasks for a subject.
     *
     * @param subjectId the subject id
     * @return list of tasks
     */
    public List<StudyTask> getTasksBySubject(String subjectId) {
        List<StudyTask> result = new ArrayList<>();
        for (StudyTask task : tasks) {
            if (task.getSubjectId().equals(subjectId)) {
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Gets all tasks.
     *
     * @return list of all tasks
     */
    public List<StudyTask> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Saves subjects to JSON file.
     */
    private void saveSubjects() {
        try (FileWriter writer = new FileWriter(SUBJECTS_FILE)) {
            gson.toJson(subjects, writer);
        } catch (IOException e) {
            System.err.println("Error saving subjects: " + e.getMessage());
        }
    }

    /**
     * Loads subjects from JSON file.
     */
    private void loadSubjects() {
        File file = new File(SUBJECTS_FILE);
        if (!file.exists()) {
            return;
        }

        try (FileReader reader = new FileReader(file)) {
            List<Subject> loaded = gson.fromJson(reader, new TypeToken<List<Subject>>(){}.getType());
            if (loaded != null) {
                subjects = loaded;
            }
        } catch (IOException e) {
            System.err.println("Error loading subjects: " + e.getMessage());
        }
    }

    /**
     * Saves tasks to JSON file.
     */
    private void saveTasks() {
        try (FileWriter writer = new FileWriter(TASKS_FILE)) {
            gson.toJson(tasks, writer);
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from JSON file.
     */
    private void loadTasks() {
        File file = new File(TASKS_FILE);
        if (!file.exists()) {
            return;
        }

        try (FileReader reader = new FileReader(file)) {
            List<StudyTask> loaded = gson.fromJson(reader, new TypeToken<List<StudyTask>>(){}.getType());
            if (loaded != null) {
                tasks = loaded;
            }
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
        }
    }
}
