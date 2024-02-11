package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CourseListTest {
    CourseList courseList;
    Course c1;
    Course c2;
    Course c3;
    Course c4;

    @BeforeEach
    public void setup() {
        courseList = new CourseList();
        c1 = new Course("C1");
        c2 = new Course("C2");
        c3 = new Course("C3");
        c4 = new Course("C4");
    }

    @Test
    public void testAddCourse() {
        ArrayList<Course> testArray = new ArrayList<>();
        assertTrue(courseList.isEmpty());
        courseList.addCourse(c1);
        assertFalse(courseList.isEmpty());
        testArray.add(c1);
        assertEquals(testArray, courseList.getListOfCourse());

        courseList.addCourse(c2);
        testArray.add(c2);
        assertEquals(testArray, courseList.getListOfCourse());
    }

    @Test
    public void testGetListOfCourse() {
        ArrayList<Course> testArray = new ArrayList<>();
        assertEquals(testArray, courseList.getListOfCourse());
        courseList.addCourse(c3);
        testArray.add(c3);
        assertEquals(testArray, courseList.getListOfCourse());
        courseList.addCourse(c4);
        testArray.add(c4);
        assertEquals(testArray, courseList.getListOfCourse());
    }

    @Test
    public void testGetListOfCourseNames() {
        ArrayList<String> testArray = new ArrayList<>();
        assertEquals(testArray, courseList.getListOfCourseNames());
        courseList.addCourse(c1);
        testArray.add("C1");
        assertEquals(testArray, courseList.getListOfCourseNames());
        courseList.addCourse(c2);
        testArray.add("C2");
        assertEquals(testArray, courseList.getListOfCourseNames());
        courseList.addCourse(c4);
        testArray.add("C4");
        assertEquals(testArray, courseList.getListOfCourseNames());
        courseList.addCourse(c3);
        testArray.add("C3");
        assertEquals(testArray, courseList.getListOfCourseNames());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(courseList.isEmpty());
        courseList.addCourse(c1);
        assertFalse(courseList.isEmpty());
    }

    @Test
    public void testFindCourse() {
        courseList.addCourse(c2);
        courseList.addCourse(c1);
        courseList.addCourse(c4);
        courseList.addCourse(c3);
        assertEquals(c1, courseList.findCourse("C1"));
        assertEquals(c2, courseList.findCourse("C2"));
        assertEquals(c3, courseList.findCourse("C3"));
        assertEquals(c4, courseList.findCourse("C4"));
    }
}
