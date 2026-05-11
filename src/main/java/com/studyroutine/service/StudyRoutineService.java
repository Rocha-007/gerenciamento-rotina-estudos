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
 * Serviço central para gerenciamento de disciplinas e tarefas.
 * Utiliza Gson para serialização/desserialização JSON.
 * 
 * Escolhi JSON ao invés de banco de dados porque:
 * - Simplicidade: Sem necessidade de configurar BD
 * - Portabilidade: Dados em arquivo, fácil transportar
 * - Adequado para projeto pequeno/médio
 * - Facilita testes
 * 
 * Na Etapa 2 (Entrega Intermediária), integrou-se o HolidayService
 * para consumir dados de uma API pública de Feriados Nacionais Brasileiros.
 * Isso permite alertar o usuário quando agenda tarefas em feriados.
 */
public class StudyRoutineService {
    private static final String DATA_DIR = "data";
    private static final String SUBJECTS_FILE = DATA_DIR + File.separator + "subjects.json";
    private static final String TASKS_FILE = DATA_DIR + File.separator + "tasks.json";

    private List<Subject> subjects;
    private List<StudyTask> tasks;
    private Gson gson;
    private HolidayService holidayService;

    public StudyRoutineService() {
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .registerTypeAdapter(LocalTime.class, new LocalTimeTypeAdapter())
                .setPrettyPrinting()
                .create();
        subjects = new ArrayList<>();
        tasks = new ArrayList<>();
        holidayService = new HolidayService();

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

    // ===== INTEGRAÇÃO COM API DE FERIADOS =====
    
    /**
     * Verifica se uma data é um feriado nacional brasileiro.
     * Utiliza a integração com API pública de Feriados.
     * 
     * @param date Data a verificar
     * @return true se for feriado, false caso contrário
     */
    public boolean isHoliday(LocalDate date) {
        try {
            return holidayService.isHoliday(date);
        } catch (IOException | InterruptedException e) {
            System.err.println("Aviso: Não foi possível verificar feriados. " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtém o nome do feriado para uma data específica.
     * 
     * @param date Data a verificar
     * @return Nome do feriado, ou string vazia se não for feriado
     */
    public String getHolidayName(LocalDate date) {
        try {
            return holidayService.getHolidayName(date);
        } catch (IOException | InterruptedException e) {
            System.err.println("Aviso: Não foi possível obter nome do feriado. " + e.getMessage());
            return "";
        }
    }

    /**
     * Obtém um aviso se a data de uma tarefa for um feriado.
     * Útil para alertar o usuário na UI.
     * 
     * @param dueDate Data de vencimento da tarefa
     * @return Mensagem de aviso se for feriado, ou string vazia caso contrário
     */
    public String getHolidayWarning(LocalDate dueDate) {
        if (dueDate == null) {
            return "";
        }
        
        try {
            if (holidayService.isHoliday(dueDate)) {
                String holidayName = holidayService.getHolidayName(dueDate);
                return String.format("⚠️ Aviso: %s é um feriado nacional!", holidayName);
            }
        } catch (IOException | InterruptedException e) {
            // Silenciosamente falha - não interrompe a funcionalidade
            System.err.println("Aviso: Erro ao verificar feriados: " + e.getMessage());
        }
        
        return "";
    }

    /**
     * Obtém os próximos feriados para ajudar o usuário na planejamento.
     * 
     * @param fromDate Data inicial
     * @param limit Número máximo de feriados a retornar
     * @return Lista de próximos feriados
     */
    public List<String> getUpcomingHolidaysInfo(LocalDate fromDate, int limit) {
        List<String> info = new ArrayList<>();
        try {
            var upcoming = holidayService.getUpcomingHolidays(fromDate, limit);
            for (var holiday : upcoming) {
                info.add(String.format("%s (%s)", holiday.getLocalName(), holiday.getDate()));
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Aviso: Não foi possível obter próximos feriados. " + e.getMessage());
        }
        return info;
    }
}
