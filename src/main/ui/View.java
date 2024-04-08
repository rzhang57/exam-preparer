package ui;

import model.Course;
import model.CourseList;
import model.Event;
import model.EventLog;
import model.PracticeProblem;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// GUI implemented from exam preperation application
public class View extends JFrame implements WindowListener {

    private JPanel mainPanel;
    private CourseList courseList;
    private JButton addButton;
    private JButton loadButton;
    private JButton saveButton;
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final String DIRECTORY = "./data/examprep.json";
    private JPanel courseListPanel;
    private JButton removeButton;
    private JsonWriter writer;
    private JsonReader reader;

    // MODIFIES: this
    // EFFECTS: Initializes main frame for the viewing of the application
    public View() {
        super("Exam prep app");
        courseList = new CourseList();
        windowSetup();
        mainPanel = new JPanel(new BorderLayout());
        addButton = new JButton("Add Course");
        loadButton = new JButton("Load");
        saveButton = new JButton("Save");
        removeButton = new JButton("Remove Course");
        ImageIcon image = new ImageIcon(getClass().getResource("books.png"));
        JLabel imageLabel = new JLabel(image);
        JLabel mainLabel = new JLabel("Select course below to open new window: ");
        JPanel navigationBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        courseListPanel = new JPanel();
        courseListPanel.setLayout(new BoxLayout(courseListPanel, BoxLayout.Y_AXIS));
        addActions(addButton, loadButton, saveButton);
        mainPanel.add(new JScrollPane(courseListPanel), BorderLayout.CENTER);
        navigationBarAdd(navigationBar, imageLabel);
        mainPanel.add(mainLabel, BorderLayout.NORTH);
        add(mainPanel);
        add(navigationBar, BorderLayout.NORTH);
        setVisible(true);
        writer = new JsonWriter(DIRECTORY);
        reader = new JsonReader(DIRECTORY);
    }

    // MODIFIES: this
    // EFFECTS: Sets up basic aspects of main JFrame window
    private void windowSetup() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        addWindowListener(this);
    }

    // MODIFIES: this
    // EFFECTS: adds elements (image + buttons) to navigation bar
    private void navigationBarAdd(JPanel navigationBar, JLabel imageLabel) {
        navigationBar.add(imageLabel, BorderLayout.EAST);
        navigationBar.add(loadButton, BorderLayout.NORTH);
        navigationBar.add(saveButton, BorderLayout.NORTH);
        navigationBar.add(addButton, BorderLayout.NORTH);
        navigationBar.add(removeButton, BorderLayout.NORTH);
    }

    // EFFECTS: establishes action listener for various buttons
    private void addActions(JButton addButton, JButton loadButton, JButton saveButton) {
        addButtonAction(addButton);
        loadButtonAction(loadButton);
        saveButtonAction(saveButton);
        removeButtonAction();
    }

    // MODIFIES: this, courseList, course
    // EFFECTS: Allows a user to add a new course to the courseList while also establishing a new Course
    private void addButtonAction(JButton addButton) {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseName = JOptionPane.showInputDialog(View.this, "Enter the name of the course:");
                if (courseName != null) {
                    Course newCourse = new Course(courseName);
                    courseList.addCourse(newCourse);
                    JOptionPane.showMessageDialog(View.this, "Course added successfully!");
                } else {
                    JOptionPane.showMessageDialog(View.this,
                            "Invalid course name!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                updateCourseList();
            }
        });
    }

    // EFFECTS: this, courseList
    // EFFECTS: removes course based on course name inputted by user if found
    private void removeButtonAction() {
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseName = JOptionPane.showInputDialog(View.this, "Enter the name of the course to remove:");
                if (courseName != null) {
                    boolean removed = courseList.removeCourseByName(courseName);
                    if (removed) {
                        JOptionPane.showMessageDialog(View.this, "Course removed successfully!");
                        updateCourseList();
                    } else {
                        JOptionPane.showMessageDialog(View.this,
                                "Course not found!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(View.this,
                            "Invalid course name!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // EFFECTS: when save button is pressed, saves all user data to JSON
    private void saveButtonAction(JButton saveButton) {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    writer.open();
                    writer.write(courseList);
                    writer.close();
                    JOptionPane.showMessageDialog(View.this,
                            "Course list saved successfully!", "Information", JOptionPane.INFORMATION_MESSAGE);
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(View.this,
                            "Error saving course list to file!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // MODIFIES: this, courseList
    // EFFECTS: when pressed, reads JSON and extracts data to be used in application
    private void loadButtonAction(JButton loadButton) {
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    courseList = reader.read();
                    JOptionPane.showMessageDialog(View.this,
                            "Course list loaded successfully!", "Information", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(View.this,
                            "Error loading course list from file!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                updateCourseList();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: updates courseList on main panel (after loading, adding etc.)
    private void updateCourseList() {
        courseListPanel.removeAll(); // Clear existing components
        for (Course course : courseList.getListOfCourse()) {
            JButton courseButton = new JButton(course.getCourseName());
            //JLabel courseLabel = new JLabel(course.getCourseName());
            courseListPanel.add(courseButton);
            courseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    openCourseFrame(course);
                }
            });
        }
        courseListPanel.revalidate();
        courseListPanel.repaint();
    }

    // EFFECTS: opens new frame dedicated for course
    private void openCourseFrame(Course course) {
        JFrame courseFrame = new JFrame(course.getCourseName());
        courseFrame.setSize(WIDTH, HEIGHT);
        courseFrame.setLocationRelativeTo(null);
        ArrayList<PracticeProblem> problems = course.getListOfPracticeProblems();
        JTextArea practiceTextArea = new JTextArea(20, 50);
        practiceTextArea.setEditable(false);
        JPanel coursePanel = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(practiceTextArea);
        updatePracticeProblems(problems, practiceTextArea);
        JButton addProblemButton = new JButton("+ practice problem");
        coursePanel.add(scrollPane, BorderLayout.CENTER);
        addProblemButtonAction(course, addProblemButton, problems, practiceTextArea);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(addProblemButton, BorderLayout.NORTH);
        buttonPanel.add(scrollPane);

        courseFrame.add(buttonPanel);
        courseFrame.setVisible(true);
    }

    // MODIFIES: this, course
    // EFFECTS: adds new practice problem to course given user input of title, body, and solution
    private void addProblemButtonAction(Course course, JButton addProblemButton,
                                        ArrayList<PracticeProblem> problems, JTextArea practiceTextArea) {
        addProblemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField titleField = new JTextField(20);
                JTextField bodyField = new JTextField(20);
                JTextField solutionField = new JTextField(20);
                JPanel panel = new JPanel(new GridLayout(0, 1));
                addTextFields(panel, titleField, bodyField, solutionField);
                int result = JOptionPane.showConfirmDialog(null, panel, "Enter Practice Problem Details",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    String title = titleField.getText();
                    String body = bodyField.getText();
                    String solution = solutionField.getText();
                    PracticeProblem problem = new PracticeProblem(title, body, solution);
                    course.addPracticeProblem(problem);
                    JOptionPane.showMessageDialog(null, "Practice problem added successfully.");
                    updatePracticeProblems(problems, practiceTextArea);
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: adds fields to panel of new frame
    private static void addTextFields(JPanel panel, JTextField titleField,
                                      JTextField bodyField, JTextField solutionField) {
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Body:"));
        panel.add(bodyField);
        panel.add(new JLabel("Solution:"));
        panel.add(solutionField);
    }

    // MODIFIES: this
    // EFFECTS: updates display of all practice problems in textbox
    private void updatePracticeProblems(ArrayList<PracticeProblem> problems, JTextArea textArea) {
        textArea.setText("");
        StringBuilder sb = new StringBuilder();
        for (PracticeProblem problem : problems) {
            sb.append(problem.getTitle()).append("\n").append(problem.getBody()).append("\n\n");
        }
        textArea.setText(sb.toString());
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new View();
            }
        });
    }

    // EFFECTS: does nothing on this window action
    @Override
    public void windowOpened(WindowEvent e) {

    }

    // EFFECTS: does nothing on this window action
    @Override
    public void windowClosing(WindowEvent e) {
        for (Event next : EventLog.getInstance()) {
            System.out.println(next.toString() + "\n");
        }
    }

    // EFFECTS: does nothing on this window action
    @Override
    public void windowClosed(WindowEvent e) {

    }

    // EFFECTS: does nothing on this window action
    @Override
    public void windowIconified(WindowEvent e) {

    }

    // EFFECTS: does nothing on this window action
    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    // EFFECTS: does nothing on this window action
    @Override
    public void windowActivated(WindowEvent e) {

    }

    // EFFECTS: Prints out all logged events of a session when the main JFrame window is deactivated/ closed
    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
