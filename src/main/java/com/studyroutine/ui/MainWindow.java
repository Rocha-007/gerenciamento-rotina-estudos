package com.studyroutine.ui;

import com.studyroutine.model.Subject;
import com.studyroutine.model.StudyTask;
import com.studyroutine.service.StudyRoutineService;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Main GUI window for the Study Routine Manager application.
 */
public class MainWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    private final StudyRoutineService service;
    private JList<Subject> subjectsList;
    private JList<StudyTask> tasksList;

    /**
     * Constructs the MainWindow.
     */
    public MainWindow() {
        this.service = new StudyRoutineService();

        setTitle("Study Routine Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setResizable(true);

        initializeUI();
        setVisible(true);
    }

    /**
     * Initializes the user interface.
     */
    private void initializeUI() {
        JTabbedPane tabbedPane = new JTabbedPane();

        // Subjects Tab
        tabbedPane.addTab("Subjects", createSubjectsPanel());

        // Tasks Tab
        tabbedPane.addTab("Tasks", createTasksPanel());

        // About Tab
        tabbedPane.addTab("About", createAboutPanel());

        add(tabbedPane, BorderLayout.CENTER);
    }

    /**
     * Creates the subjects management panel.
     *
     * @return the subjects panel
     */
    private JPanel createSubjectsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // List of subjects
        subjectsList = new JList<>();
        subjectsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        refreshSubjectsList();

        JScrollPane scrollPane = new JScrollPane(subjectsList);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        JButton addButton = new JButton("Add Subject");
        JButton removeButton = new JButton("Remove Subject");

        addButton.addActionListener(e -> addSubjectDialog());
        removeButton.addActionListener(e -> removeSubject());

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Creates the tasks management panel.
     *
     * @return the tasks panel
     */
    private JPanel createTasksPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // List of tasks
        tasksList = new JList<>();
        tasksList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        refreshTasksList();

        JScrollPane scrollPane = new JScrollPane(tasksList);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        JButton addButton = new JButton("Add Task");
        JButton completeButton = new JButton("Mark as Completed");
        JButton removeButton = new JButton("Remove Task");

        addButton.addActionListener(e -> addTaskDialog());
        completeButton.addActionListener(e -> completeTask());
        removeButton.addActionListener(e -> removeTask());

        buttonPanel.add(addButton);
        buttonPanel.add(completeButton);
        buttonPanel.add(removeButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Creates the about panel.
     *
     * @return the about panel
     */
    private JPanel createAboutPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JTextArea textArea = new JTextArea();
        textArea.setText("Study Routine Manager\nVersion 1.0.0\n\n"
                + "A simple and effective application to help students and university students\n"
                + "organize and manage their study routine.\n\n"
                + "Features:\n"
                + "- Manage subjects/disciplines\n"
                + "- Create and organize study tasks\n"
                + "- Set priorities and due dates\n"
                + "- Track task completion\n"
                + "- Persistent data storage\n\n"
                + "© 2026 Study Routine Manager");
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        return panel;
    }

    /**
     * Opens a dialog to add a new subject.
     */
    private void addSubjectDialog() {
        JDialog dialog = new JDialog(this, "Add Subject", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Subject Name:");
        JTextField nameField = new JTextField();

        JLabel profLabel = new JLabel("Professor:");
        JTextField profField = new JTextField();

        JLabel descLabel = new JLabel("Description:");
        JTextArea descArea = new JTextArea(3, 20);

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(profLabel);
        panel.add(profField);
        panel.add(descLabel);
        panel.add(new JScrollPane(descArea));

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        saveButton.addActionListener(e -> {
            if (nameField.getText().trim().isEmpty()) {
                return;
            }
            service.addSubject(nameField.getText(), profField.getText(), descArea.getText());
            refreshSubjectsList();
            dialog.dispose();
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        panel.add(saveButton);
        panel.add(cancelButton);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    /**
     * Removes the selected subject.
     */
    private void removeSubject() {
        int selected = subjectsList.getSelectedIndex();
        if (selected != -1) {
            Subject subject = subjectsList.getSelectedValue();
            service.removeSubject(subject.getId());
            refreshSubjectsList();
            refreshTasksList();
        }
    }

    /**
     * Opens a dialog to add a new task.
     */
    private void addTaskDialog() {
        List<Subject> subjects = service.getAllSubjects();
        if (subjects.isEmpty()) {
            return;
        }

        JDialog dialog = new JDialog(this, "Add Task", true);
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel subjectLabel = new JLabel("Subject:");
        JList<Subject> subjectField = new JList<>(subjects.toArray(new Subject[0]));
        subjectField.setSelectedIndex(0);

        JLabel descLabel = new JLabel("Description:");
        JTextArea descField = new JTextArea(2, 20);

        JLabel dateLabel = new JLabel("Due Date (YYYY-MM-DD):");
        JTextField dateField = new JTextField(LocalDate.now().toString());

        JLabel startLabel = new JLabel("Start Time (HH:MM):");
        JTextField startField = new JTextField("09:00");

        JLabel endLabel = new JLabel("End Time (HH:MM):");
        JTextField endField = new JTextField("10:00");

        JLabel priorityLabel = new JLabel("Priority (1-5):");
        JSpinner prioritySpinner = new JSpinner(new SpinnerNumberModel(3, 1, 5, 1));

        panel.add(subjectLabel);
        panel.add(new JScrollPane(subjectField));
        panel.add(descLabel);
        panel.add(new JScrollPane(descField));
        panel.add(dateLabel);
        panel.add(dateField);
        panel.add(startLabel);
        panel.add(startField);
        panel.add(endLabel);
        panel.add(endField);
        panel.add(priorityLabel);
        panel.add(prioritySpinner);

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        saveButton.addActionListener(e -> {
            try {
                Subject selected = subjectField.getSelectedValue();
                if (selected != null && !descField.getText().trim().isEmpty()) {
                    LocalDate date = LocalDate.parse(dateField.getText());
                    LocalTime start = LocalTime.parse(startField.getText());
                    LocalTime end = LocalTime.parse(endField.getText());
                    int priority = (int) prioritySpinner.getValue();

                    service.addTask(selected.getId(), descField.getText(), date, start, end, priority);
                    refreshTasksList();
                    dialog.dispose();
                }
            } catch (Exception ex) {
                // Handle parse error silently
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        panel.add(saveButton);
        panel.add(cancelButton);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    /**
     * Marks the selected task as completed.
     */
    private void completeTask() {
        int selected = tasksList.getSelectedIndex();
        if (selected != -1) {
            StudyTask task = tasksList.getSelectedValue();
            service.markTaskAsCompleted(task.getId());
            refreshTasksList();
        }
    }

    /**
     * Removes the selected task.
     */
    private void removeTask() {
        int selected = tasksList.getSelectedIndex();
        if (selected != -1) {
            StudyTask task = tasksList.getSelectedValue();
            service.removeTask(task.getId());
            refreshTasksList();
        }
    }

    /**
     * Refreshes the subjects list display.
     */
    private void refreshSubjectsList() {
        subjectsList.setListData(service.getAllSubjects().toArray(new Subject[0]));
    }

    /**
     * Refreshes the tasks list display.
     */
    private void refreshTasksList() {
        tasksList.setListData(service.getAllTasks().toArray(new StudyTask[0]));
    }

    /**
     * Main method to start the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}
