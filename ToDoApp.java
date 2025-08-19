import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.DefaultListModel;

public class ToDoApp extends JFrame {
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskField;
    private JButton addButton, deleteButton;

    public ToDoApp() {
        // Frame setup
        setTitle("To-Do App");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel for input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        taskField = new JTextField();
        addButton = new JButton("Add Task");
        inputPanel.add(taskField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        // Task list
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        // Delete button
        deleteButton = new JButton("Delete Task");

        // Layout
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(deleteButton, BorderLayout.SOUTH);

        // Add button action
        addButton.addActionListener(e -> addTask());

        // Enter key also adds task
        taskField.addActionListener(e -> addTask());

        // Delete button action
        deleteButton.addActionListener(e -> deleteTask());
    }

    private void addTask() {
        String task = taskField.getText().trim();
        if (!task.isEmpty()) {
            taskListModel.addElement(task);
            taskField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Task cannot be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            taskListModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a task to delete!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ToDoApp app = new ToDoApp();
            app.setVisible(true);
        });
    }
}
