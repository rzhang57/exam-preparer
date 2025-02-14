package ui;

import model.Course;
import model.CourseList;
import model.PracticeProblem;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Exam preparation application
// Citation: Partially inspired by the WorkRoom project provided by the 210 course staff
public class ExamPrepApp {
    private static final String DIRECTORY = "./data/examprep.json";
    private CourseList courseList;
    private Scanner input;
    private JsonReader reader;
    private JsonWriter writer;

    // EFFECTS: Runs exam preperation app
    public ExamPrepApp() throws FileNotFoundException {
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: runs user interface, allowing users to interact with app
    public void runApp() {
        boolean keepRunning = true;
        int action;
        init();
        while (keepRunning) {
            System.out.println("Select from the following to begin: \n - (1) Add course\n - (2) View list of courses"
                    + "\n - (3) Select course \n - (4) Save current file \n - (5) Load previous file \n - (6) Quit");
            action = input.nextInt();
            if (action == 1) {
                addCourse();
            } else if (action == 2) {
                viewCourses();
            } else if (action == 3) {
                selectCourse();
            } else if (action == 4) {
                saveCourseList();
            } else if (action == 5) {
                loadCourseList();
            } else if (action == 6) {
                keepRunning = false;
            } else {
                System.out.println("Invalid input, try again");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds course to this app's courselist
    private void addCourse() {
        System.out.println("Enter the name of the course you would like to add:");
        String name = input.next();
        Course course = new Course(name);
        courseList.addCourse(course);
        System.out.println("Would you like to add practice problems to this course? (Y/N)");
        String answer = input.next();
        if (answer.equals("Y")) {
            addPracticeProblem(course);
        } else if (answer.equals("N")) {
            System.out.println("Returning to main menu");
        } else {
            System.out.println("Invalid response.");
        }

    }

    // EFFECTS: displays names of all courses to user
    private void viewCourses() {
        if (courseList.isEmpty()) {
            System.out.println("You have no courses.");
        } else {
            System.out.println("Your courses: ");
            for (String s : courseList.getListOfCourseNames()) {
                System.out.println(" - " + s);
            }
        }
    }

    // EFFECTS: selects user inputted course
    private void selectCourse() {
        System.out.println("Select course by name: ");
        String name = input.next();
        Course course = courseList.findCourse(name);
        if (course != null) {
            courseAction(course);
        } else {
            System.out.println("Error, not valid course name");
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts action on selected course in courseList
    public void courseAction(Course c) {
        System.out.println("Select action from options below: \n"
                + " - (1) Add practice problem \n - (2) View practice problems");
        int action = input.nextInt();
        if (action == 1) {
            addPracticeProblem(c);
        } else if (action == 2) {
            int count = 1;
            for (PracticeProblem p : c.getListOfPracticeProblems()) {
                System.out.println("(" + count + ") " + p.getTitle() + ":\n" + "Body: " + p.getBody()
                                + "\nSolution: " + p.getSolution() + "\n" + p.getStatus() + "\n ");
                count++;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds practice problem to the selected course
    private void addPracticeProblem(Course c) {
        System.out.println("Enter title of practice problem");
        String title = input.next();
        System.out.println("Enter body of the practice problem");
        String body = input.next();
        System.out.println("Would you like to add a solution to this problem? (Y/N)");
        String answer = input.next();
        if (answer.equals("Y")) {
            System.out.println("Enter solution: ");
            String solution = input.next();
            PracticeProblem problem = new PracticeProblem(title, body, solution);
            c.addPracticeProblem(problem);
            System.out.println("Problem added to course!");
        } else if (answer.equals("N")) {
            PracticeProblem problem = new PracticeProblem(title, body);
            c.addPracticeProblem(problem);
            System.out.println("Problem added to course!");
        } else {
            System.out.println("Problem failed to be added to course!");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes user's courseList
    public void init() {
        courseList = new CourseList();
        input = new Scanner(System.in).useDelimiter("\n");
        reader = new JsonReader(DIRECTORY);
        writer = new JsonWriter(DIRECTORY);
    }

    // EFFECTS: saves course list and all attributed data
    private void saveCourseList() {
        try {
            writer.open();
            writer.write(courseList);
            writer.close();
            System.out.println("Your progress has been saved.");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file at " + DIRECTORY);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads course list from file directory
    private void loadCourseList() {
        try {
            courseList = reader.read();
            System.out.println("Loaded your courses and practice problems from " + DIRECTORY);
        } catch (IOException e) {
            System.out.println("Unable to read file from directory: " + DIRECTORY);
        }
    }
}
