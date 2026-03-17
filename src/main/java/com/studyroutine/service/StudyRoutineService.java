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

public class StudyRoutineService {
    private static final String DATA_DIR = "data";
    private static final String SUBJECTS_FILE = DATA_DIR + File.separator + "subjects.json";
    private static final String TASKS_FILE = DATA_DIR + File.separator + "tasks.json";

    private List<Subject> subjects;
    private List<StudyTask> tasks;
    private Gson gson;

    public StudyRoutineService() {
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .registerTypeAdapter(LocalTime.class, new LocalTimeTypeAdapter())
                .setPrettyPrinting()
                .create();
        subjects = new ArrayList<>();
        tasks = new ArrayList<>();

        ensureDataDirectory();
        loadSubjects();
        loadTasks();
    }

    private void ensureDataDirectory() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    public Subject addSubject(String name, String professor, String description) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }

        String id = UUID.randomUUID().toString();
        Subject s = new Subject(id, name, professor, description);
        subjects.add(s);
        saveSubjects();
        return s;
    }

    public boolean removeSubject(String subjectId) {
        boolean removed = subjects.removeIf(s -> s.getId().equals(subjectId));
        if (removed) {
            tasks.removeIf(t -> t.getSubjectId().equals(subjectId));
            saveSubjects();
            saveTasks();
        }
        return removed;
    }

    public List<Subject> getAllSubjects() {
        return new ArrayList<>(subjects);
    }

    public StudyTask addTask(String subjectId, String description, LocalDate dueDate,
                              LocalTime startTime, LocalTime endTime, int priority) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição não pode ser vazia");
        }
        if (priority < 1 || priority > 5) {
            throw new IllegalArgumentException("Prioridade de 1 a 5");
        }

        String id = UUID.randomUUID().toString();
        StudyTask t = new StudyTask(id, subjectId, description, dueDate, startTime, endTime, priority);
        tasks.add(t);
        saveTasks();
        return t;
    }

    public boolean removeTask(String taskId) {
        boolean removed = tasks.removeIf(t -> t.getId().equals(taskId));
        if (removed) {
            saveTasks();
        }
        return removed;
    }

    public void markTaskAsCompleted(String taskId) {
        tasks.stream()
                .filter(t -> t.getId().equals(taskId))
                .findFirst()
                .ifPresent(t -> {
                    t.setCompleted(true);
                    saveTasks();
                });
    }

    public List<StudyTask> getTasksBySubject(String subjectId) {
        List<StudyTask> result = new ArrayList<>();
        for (StudyTask t : tasks) {
            if (t.getSubjectId().equals(subjectId)) {
                result.add(t);
            }
        }
        return result;
    }

    public List<StudyTask> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    private void saveSubjects() {
        try (FileWriter w = new FileWriter(SUBJECTS_FILE)) {
            gson.toJson(subjects, w);
        } catch (IOException e) {
            System.err.println("Erro salvando disciplinas: " + e.getMessage());
        }
    }

    private void loadSubjects() {
        File f = new File(SUBJECTS_FILE);
        if (!f.exists()) return;

        try (FileReader r = new FileReader(f)) {
            List<Subject> loaded = gson.fromJson(r, new TypeToken<List<Subject>>(){}.getType());
            if (loaded != null) {
                subjects = loaded;
            }
        } catch (IOException e) {
            System.err.println("Erro carregando disciplinas: " + e.getMessage());
        }
    }

    private void saveTasks() {
        try (FileWriter w = new FileWriter(TASKS_FILE)) {
            gson.toJson(tasks, w);
        } catch (IOException e) {
            System.err.println("Erro salvando tarefas: " + e.getMessage());
        }
    }

    private void loadTasks() {
        File f = new File(TASKS_FILE);
        if (!f.exists()) return;

        try (FileReader r = new FileReader(f)) {
            List<StudyTask> loaded = gson.fromJson(r, new TypeToken<List<StudyTask>>(){}.getType());
            if (loaded != null) {
                tasks = loaded;
            }
        } catch (IOException e) {
            System.err.println("Erro carregando tarefas: " + e.getMessage());
        }
    }
}
