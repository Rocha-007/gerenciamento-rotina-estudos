package com.studyroutine.ui;

import com.studyroutine.model.Subject;
import com.studyroutine.model.StudyTask;
import com.studyroutine.service.StudyRoutineService;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Janela principal da aplicação.
 * Utiliza JTabbedPane para organizar as funcionalidades em abas
 * (Disciplinas, Tarefas, Sobre).
 */
public class MainWindow extends JFrame {
    private StudyRoutineService service;
    private JList<Subject> subjectsList;
    private JList<StudyTask> tasksList;

    public MainWindow() {
        service = new StudyRoutineService();
        
        // Configurações básicas da janela
        setTitle("Gerenciador de Rotina de Estudos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null); // Centraliza na tela
        setResizable(true);
        
        setupUI();
        setVisible(true);
    }

    private void setupUI() {
        // Layout com abas foi a melhor forma encontrada para organizar
        // sem deixar a interface poluída
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Disciplinas", setupDisciplinesPanel());
        tabs.addTab("Tarefas", setupTasksPanel());
        tabs.addTab("Sobre", setupAboutPanel());
        add(tabs);
    }

    private JPanel setupDisciplinesPanel() {
        // BorderLayout com list view + botões na parte inferior
        // Escolhi essa abordagem para manter simplicidade e organização
        JPanel p = new JPanel(new BorderLayout(10, 10));
        p.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        subjectsList = new JList<>();
        subjectsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        refreshSubjects();
        
        JPanel btns = new JPanel(new GridLayout(1, 2, 5, 5));
        btns.add(createBtn("Adicionar", e -> addDisciplineDialog()));
        btns.add(createBtn("Remover", e -> removeDiscipline()));
        
        p.add(new JScrollPane(subjectsList), BorderLayout.CENTER);
        p.add(btns, BorderLayout.SOUTH);
        return p;
    }

    private JPanel setupTasksPanel() {
        // Similar ao painel de disciplinas, mas com 3 botões
        // (Adicionar, Concluir, Remover) - mais funcionalidades para tarefas
        JPanel p = new JPanel(new BorderLayout(10, 10));
        p.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        tasksList = new JList<>();
        tasksList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        refreshTasks();
        
        JPanel btns = new JPanel(new GridLayout(1, 3, 5, 5));
        btns.add(createBtn("Adicionar", e -> addTaskDialog()));
        btns.add(createBtn("Concluir", e -> completeTask()));
        btns.add(createBtn("Remover", e -> removeTask()));
        
        p.add(new JScrollPane(tasksList), BorderLayout.CENTER);
        p.add(btns, BorderLayout.SOUTH);
        return p;
    }

    private JPanel setupAboutPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JTextArea txt = new JTextArea();
        txt.setText("Gerenciador de Rotina de Estudos v1.0.0\n\n"
                + "Ajuda estudantes a organizar suas disciplinas e tarefas de estudo.\n\n"
                + "Recursos:\n"
                + "• Gerenciar disciplinas\n"
                + "• Criar tarefas com datas e horários\n"
                + "• Definir prioridades\n"
                + "• Acompanhar progresso\n"
                + "• Dados persistentes em JSON\n\n"
                + "© 2026");
        txt.setEditable(false);
        txt.setLineWrap(true);
        txt.setWrapStyleWord(true);
        
        p.add(new JScrollPane(txt));
        return p;
    }

    private JButton createBtn(String text, java.awt.event.ActionListener action) {
        JButton btn = new JButton(text);
        btn.addActionListener(action);
        return btn;
    }

    private void addDisciplineDialog() {
        JDialog d = new JDialog(this, "Adicionar Disciplina", true);
        d.setSize(400, 250);
        d.setLocationRelativeTo(this);
        
        JPanel p = new JPanel(new GridLayout(4, 2, 5, 5));
        p.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JTextField name = new JTextField();
        JTextField prof = new JTextField();
        JTextArea desc = new JTextArea(3, 20);
        
        p.add(new JLabel("Nome:"));
        p.add(name);
        p.add(new JLabel("Professor:"));
        p.add(prof);
        p.add(new JLabel("Descrição:"));
        p.add(new JScrollPane(desc));
        
        JButton save = createBtn("Salvar", e -> {
            if (!name.getText().trim().isEmpty()) {
                service.addSubject(name.getText(), prof.getText(), desc.getText());
                refreshSubjects();
                d.dispose();
            }
        });
        
        JButton cancel = createBtn("Cancelar", e -> d.dispose());
        p.add(save);
        p.add(cancel);
        
        d.add(p);
        d.setVisible(true);
    }

    private void removeDiscipline() {
        int idx = subjectsList.getSelectedIndex();
        if (idx != -1) {
            Subject s = subjectsList.getSelectedValue();
            service.removeSubject(s.getId());
            refreshSubjects();
            refreshTasks();
        }
    }

    private void addTaskDialog() {
        // Valida se há disciplinas criadas antes de adicionar tarefa
        List<Subject> subjects = service.getAllSubjects();
        if (subjects.isEmpty()) return;
        
        JDialog d = new JDialog(this, "Adicionar Tarefa", true);
        d.setSize(500, 350);
        d.setLocationRelativeTo(this);
        
        JPanel p = new JPanel(new GridLayout(7, 2, 5, 5));
        p.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // JList para selecionar a disciplina associada à tarefa
        JList<Subject> subj = new JList<>(subjects.toArray(new Subject[0]));
        subj.setSelectedIndex(0);
        
        JTextArea desc = new JTextArea(2, 20);
        JTextField date = new JTextField(LocalDate.now().toString());
        JTextField start = new JTextField("09:00");
        JTextField end = new JTextField("10:00");
        JSpinner priority = new JSpinner(new SpinnerNumberModel(3, 1, 5, 1));
        
        p.add(new JLabel("Disciplina:"));
        p.add(new JScrollPane(subj));
        p.add(new JLabel("Tarefa:"));
        p.add(new JScrollPane(desc));
        p.add(new JLabel("Data (YYYY-MM-DD):"));
        p.add(date);
        p.add(new JLabel("Início (HH:MM):"));
        p.add(start);
        p.add(new JLabel("Fim (HH:MM):"));
        p.add(end);
        p.add(new JLabel("Prioridade (1-5):"));
        p.add(priority);
        
        JButton save = createBtn("Salvar", e -> {
            try {
                Subject s = subj.getSelectedValue();
                if (s != null && !desc.getText().trim().isEmpty()) {
                    service.addTask(s.getId(), desc.getText(), 
                        LocalDate.parse(date.getText()),
                        LocalTime.parse(start.getText()),
                        LocalTime.parse(end.getText()),
                        (int) priority.getValue());
                    refreshTasks();
                    d.dispose();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(d, "Erro: " + ex.getMessage());
            }
        });
        
        JButton cancel = createBtn("Cancelar", e -> d.dispose());
        p.add(save);
        p.add(cancel);
        
        d.add(p);
        d.setVisible(true);
    }

    private void completeTask() {
        int idx = tasksList.getSelectedIndex();
        if (idx != -1) {
            StudyTask t = tasksList.getSelectedValue();
            service.markTaskAsCompleted(t.getId());
            refreshTasks();
        }
    }

    private void removeTask() {
        int idx = tasksList.getSelectedIndex();
        if (idx != -1) {
            StudyTask t = tasksList.getSelectedValue();
            service.removeTask(t.getId());
            refreshTasks();
        }
    }

    private void refreshSubjects() {
        subjectsList.setListData(service.getAllSubjects().toArray(new Subject[0]));
    }

    private void refreshTasks() {
        tasksList.setListData(service.getAllTasks().toArray(new StudyTask[0]));
    }

    public static void main(String[] args) {
        // SwingUtilities.invokeLater garante que a GUI é criada
        // na Event Dispatch Thread (EDT) - prática recomendada
        SwingUtilities.invokeLater(MainWindow::new);
    }
}
