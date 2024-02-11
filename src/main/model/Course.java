package model;

import java.util.ArrayList;

// Represents a given course with a name and its practice problems
public class Course {

    private String courseName;
    private ArrayList<PracticeProblem> listOfPracticeProblems;

    /*
    REQUIRES: courseName should have > 0 characters
    EFFECTS: creates a Course with a set name and empty list of practice problems
     */
    public Course(String courseName) {
        this.courseName = courseName;
        listOfPracticeProblems = new ArrayList<>();
    }

    // EFFECTS: returns course name
    public String getCourseName() {
        return courseName;
    }

    // EFFECTS: returns list of practice problems in this course
    public ArrayList<PracticeProblem> getListOfPracticeProblems() {
        return listOfPracticeProblems;
    }

    // EFFECTS: returns list of the titles of all practice problems in this course
    public ArrayList<String> getListOfPracticeProblemNames() {
        ArrayList<String> names = new ArrayList<>();
        for (PracticeProblem p : listOfPracticeProblems) {
            names.add(p.getTitle());
        }
        return names;
    }

    // MODIFIES: this
    // EFFECTS: adds given practice problem to the list of practice problems in this course
    public void addPracticeProblem(PracticeProblem p) {
        listOfPracticeProblems.add(p);
    }

    // EFFECTS: returns true if list of practice problems is empty, false otherwise
    public boolean isEmpty() {
        return listOfPracticeProblems.isEmpty();
    }
}
