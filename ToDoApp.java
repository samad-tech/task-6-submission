package task6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ToDoApp extends JFrame {
    private DefaultListModel<String> taskModel;
    private JList<String> taskList;
    private JTextField taskInput;
    private JButton addButton, deleteButton;

    public ToDoApp() {
        setTitle("📝 To-Do App");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window

        // Placeholder setup
        taskInput = new JTextField("Enter your task...");
        taskInput.setForeground(Color.GRAY);

        taskInput.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (taskInput.getText().equals("Enter your task...")) {
                    taskInput.setText("");
                    taskInput.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (taskInput.getText().isEmpty()) {
                    taskInput.setText("Enter your task...");
                    taskInput.setForeground(Color.GRAY);
                }
            }
        });

        // Buttons
        addButton = new JButton("Add Task");
        deleteButton = new JButton("Delete Selected");

        // Task list setup
        taskModel = new DefaultListModel<>();
        taskList = new JList<>(taskModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(taskList);

        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        // Layout
        setLayout(new BorderLayout(10, 10));
        add(taskInput, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Listeners
        addButton.addActionListener(e -> addTask());
        deleteButton.addActionListener(e -> deleteTask());
    }

    private void addTask() {
        String task = taskInput.getText().trim();
        if (!task.isEmpty() && !task.equals("Enter your task...")) {
            taskModel.addElement(task);
            taskInput.setText("");
            taskInput.setForeground(Color.GRAY);
            taskInput.setText("Enter your task...");
        } else {
            JOptionPane.showMessageDialog(this, "⚠ Please enter a valid task.");
        }
    }

    private void deleteTask() {
        int selected = taskList.getSelectedIndex();
        if (selected != -1) {
            taskModel.remove(selected);
        } else {
            JOptionPane.showMessageDialog(this, "⚠ Select a task to delete.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ToDoApp().setVisible(true));
    }
}
