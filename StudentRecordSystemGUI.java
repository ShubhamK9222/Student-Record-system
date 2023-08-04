import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Student {
    private String name;
    private int age;
    private double cgpa;

    public Student(String name, int age, double cgpa) {
        this.name = name;
        this.age = age;
        this.cgpa = cgpa;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getcgpa() {
        return cgpa;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", cgpa: " + cgpa;
    }
}

public class StudentRecordSystemGUI {

    private ArrayList<Student> students;
    private JFrame frame;
    private DefaultListModel<Student> studentListModel;
    private JList<Student> studentList;

    public StudentRecordSystemGUI() {
        students = new ArrayList<>();
        studentListModel = new DefaultListModel<>();
        frame = new JFrame("Student Record System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        studentList = new JList<>(studentListModel);
        studentList.setCellRenderer(new StudentListRenderer());
        JScrollPane scrollPane = new JScrollPane(studentList);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
        buttonPanel.add(addButton);

        JButton removeButton = new JButton("Remove Selected");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = studentList.getSelectedIndex();
                if (selectedIndex != -1) {
                    students.remove(selectedIndex);
                    studentListModel.removeElementAt(selectedIndex);
                }
            }
        });
        buttonPanel.add(removeButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void addStudent() {
        JTextField nameField = new JTextField(10);
        JTextField ageField = new JTextField(3);
        JTextField cgpaField = new JTextField(3);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Age:"));
        inputPanel.add(ageField);
        inputPanel.add(new JLabel("cgpa:"));
        inputPanel.add(cgpaField);

        int result = JOptionPane.showConfirmDialog(frame, inputPanel, "Add Student",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            double cgpa = Double.parseDouble(cgpaField.getText());

            Student student = new Student(name, age, cgpa);
            students.add(student);
            studentListModel.addElement(student);
        }
    }

    class StudentListRenderer extends JPanel implements ListCellRenderer<Student> {
        private JLabel label;

        public StudentListRenderer() {
            setLayout(new BorderLayout());
            label = new JLabel();
            add(label, BorderLayout.CENTER);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Student> list, Student value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {
            label.setText(value.toString());
            return this;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentRecordSystemGUI());
    }
}
