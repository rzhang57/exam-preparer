package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonReader {
    private String directory;

    public JsonReader(String directory) {
        this.directory = directory;
    }

    public CourseList read() throws IOException {
        String jsonData = readFile(directory);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCourseList(jsonObject);
    }

    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    private CourseList parseCourseList(JSONObject jsonObject) {
        CourseList courseList = new CourseList();
        addCourses(courseList, jsonObject);
        return courseList;

    }

    private void addCourses(CourseList cl, JSONObject json) {
        JSONArray jsonArray =  json.getJSONArray("courseList");
        for (Object course : jsonArray) {
            JSONObject nextCourse = (JSONObject) course;
            addCourse(cl, nextCourse);
        }
    }

    private void addCourse(CourseList cl, JSONObject json) {
        cl.addCourse(parseCourse(json));

    }

    private Course parseCourse(JSONObject json) {
        String name = json.getString("courseName");
        Course course = new Course(name);
        addPracticeProblems(course, json);
        return course;
    }



    private void addPracticeProblems(Course c, JSONObject json) {
        JSONArray jsonArray =  json.getJSONArray("pProblems");
        for (Object problem : jsonArray) {
            JSONObject nextProblem = (JSONObject) problem;
            addPracticeProblem(c, nextProblem);
        }
    }

    private void addPracticeProblem(Course c, JSONObject json) {
        String title = json.getString("pTitle");
        String body = json.getString("pBody");
        String solution = json.getString("pSolution");
        int status = json.getInt("pStatus");
        PracticeProblem practiceProblem = new PracticeProblem(title, body, solution, status);
        c.addPracticeProblem(practiceProblem);
    }

}
