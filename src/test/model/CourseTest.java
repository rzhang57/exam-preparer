package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {
    Course course;
    Course course2;
    PracticeProblem p1;
    PracticeProblem p2;
    PracticeProblem p3;
    PracticeProblem p4;

    @BeforeEach
    public void setup() {
        course = new Course("Test Course");
        course2 = new Course("Test Course 2");
        p1 = new PracticeProblem("t1", "b1");
        p2 = new PracticeProblem("t2", "b2", "s2");
        p3 = new PracticeProblem("t3", "b3");
        p4 = new PracticeProblem("t4", "b4", "s4");
    }

    @Test
    public void testGetCourseName() {
        assertEquals("Test Course", course.getCourseName());
        assertEquals("Test Course 2", course2.getCourseName());
    }

    @Test
    public void testGetListOfPracticeProblems() {
        ArrayList<PracticeProblem> testArray = new ArrayList<>();
        assertEquals(testArray, course.getListOfPracticeProblems());
        course.addPracticeProblem(p1);
        testArray.add(p1);
        assertEquals(testArray, course.getListOfPracticeProblems());
        course.addPracticeProblem(p2);
        testArray.add(p2);
        assertEquals(testArray, course.getListOfPracticeProblems());
        course.addPracticeProblem(p4);
        testArray.add(p4);
        assertEquals(testArray, course.getListOfPracticeProblems());
        course.addPracticeProblem(p3);
        testArray.add(p3);
        assertEquals(testArray, course.getListOfPracticeProblems());
    }

    @Test
    public void testGetListOfPracticeProblemNames() {
        ArrayList<String> testArray = new ArrayList<>();
        assertEquals(testArray, course.getListOfPracticeProblemNames());
        course.addPracticeProblem(p1);
        testArray.add("t1");
        assertEquals(testArray, course.getListOfPracticeProblemNames());
        course.addPracticeProblem(p2);
        testArray.add("t2");
        assertEquals(testArray, course.getListOfPracticeProblemNames());
        course.addPracticeProblem(p4);
        testArray.add("t4");
        assertEquals(testArray, course.getListOfPracticeProblemNames());
        course.addPracticeProblem(p3);
        testArray.add("t3");
        assertEquals(testArray, course.getListOfPracticeProblemNames());
    }

    @Test
    public void testAddPracticeProblem() {
        ArrayList<PracticeProblem> testArray = new ArrayList<>();
        assertTrue(course.isEmpty());
        course.addPracticeProblem(p1);
        testArray.add(p1);
        assertFalse(course.isEmpty());
        assertEquals(testArray, course.getListOfPracticeProblems());

        course.addPracticeProblem(p2);
        testArray.add(p2);
        assertEquals(testArray, course.getListOfPracticeProblems());

        course.addPracticeProblem(p3);
        testArray.add(p3);
        assertEquals(testArray, course.getListOfPracticeProblems());

        course.addPracticeProblem(p4);
        testArray.add(p4);
        assertEquals(testArray, course.getListOfPracticeProblems());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(course2.isEmpty());
        assertTrue(course.isEmpty());

        course.addPracticeProblem(p4);
        assertFalse(course.isEmpty());
        assertTrue(course2.isEmpty());

        course2.addPracticeProblem(p3);
        assertFalse(course2.isEmpty());
    }

}
