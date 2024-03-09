package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

// A reader that reads/interprets course list from JSON file
// Citation: Some parts adapted from Workroom project provided by 210 course staff
public class JsonReader {
    private String directory;

    // EFFECTS: constructs JSON reader with a given directory to read from
    public JsonReader(String directory) {
        this.directory = directory;
    }

    // EFFECTS: Reads file from directory, returns courseList data
    // throws IOException if there is an error reading the file
    public CourseList read() throws IOException {
        String jsonData = readFile(directory);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCourseList(jsonObject);
    }

    // EFFECTS: reads source file, returning it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Course List from JSONObject and returns it
    private CourseList parseCourseList(JSONObject jsonObject) {
        CourseList cl = new CourseList();
        addCourses(cl, jsonObject);
        return cl;

    }

    // MODIFIES: cl
    // EFFECTS: adds courses from JSONArray to course list object
    private void addCourses(CourseList cl, JSONObject json) {
        JSONArray jsonArray =  json.getJSONArray("courseList");
        for (Object course : jsonArray) {
            JSONObject nextCourse = (JSONObject) course;
            addCourse(cl, nextCourse);
        }
    }

    // MODIFIES: cl
    // EFFECTS: takes a parsed course and adds it to the course list
    private void addCourse(CourseList cl, JSONObject json) {
        cl.addCourse(parseCourse(json));

    }

    // EFFECTS: parses course from JSON Object and returns course
    private Course parseCourse(JSONObject json) {
        String name = json.getString("courseName");
        Course course = new Course(name);
        addPracticeProblems(course, json);
        return course;
    }

    // MODIFIES: c
    // EFFECTS: given a course, adds array of practice problem to the course
    private void addPracticeProblems(Course c, JSONObject json) {
        JSONArray jsonArray =  json.getJSONArray("pProblems");
        for (Object problem : jsonArray) {
            JSONObject nextProblem = (JSONObject) problem;
            addPracticeProblem(c, nextProblem);
        }
    }

    // MODIFIES: c
    // EFFECTS: reads data of a practice problem, adds problem to Course
    private void addPracticeProblem(Course c, JSONObject json) {
        String title = json.getString("pTitle");
        String body = json.getString("pBody");
        String solution = json.getString("pSolution");
        int status = json.getInt("pStatus");
        PracticeProblem practiceProblem = new PracticeProblem(title, body, solution, status);
        c.addPracticeProblem(practiceProblem);
    }

}
