package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a list of added courses
public class CourseList implements Writable {
    private ArrayList<Course> listOfCourse;

    /*
    EFFECTS: Creates empty list of courses
     */
    public CourseList() {
        listOfCourse = new ArrayList<>();
    }

    /*
    MODIFIES: this
    EFFECTS: adds a given course to the list of courses
     */
    public void addCourse(Course c) {
        listOfCourse.add(c);

        Event e = new Event("Added new course (X) '" + c.getCourseName() + "' to CourseList (Y)");
        EventLog.getInstance().logEvent(e);
    }

    // EFFECTS: returns list of courses
    public ArrayList<Course> getListOfCourse() {
        return listOfCourse;
    }

    // EFFECTS: returns all names of courses in this list of course
    public ArrayList<String> getListOfCourseNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Course c : listOfCourse) {
            names.add(c.getCourseName());
        }
        return names;
    }

    // EFFECTS: checks if this list of courses is empty
    public boolean isEmpty() {
        return listOfCourse.isEmpty();
    }

    // REQUIRES: String name is the name of a Course within list of Course
    // EFFECTS: returns course object given name of a course
    public Course findCourse(String name) {
        for (Course c : listOfCourse) {
            if (c.getCourseName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: removes course given name
    public boolean removeCourseByName(String courseName) {
        for (Course c : listOfCourse) {
            if (courseName.equals(c.getCourseName())) {
                listOfCourse.remove(c);
                Event e = new Event("Removed course (X) '" + courseName + "' from CourseList(Y)");
                EventLog.getInstance().logEvent(e);
                return true;
            }
        }
        Event e = new Event("Failed to remove course (X) '" + courseName + "' from CourseList (Y)");
        EventLog.getInstance().logEvent(e);
        return false;
    }

    //EFFECTS: returns this course list as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Course c : listOfCourse) {
            jsonArray.put(c.toJson());
        }
        json.put("courseList", jsonArray);

        return json;
    }


}
