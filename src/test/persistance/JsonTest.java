package persistance;

import model.Course;
import model.CourseList;
import model.PracticeProblem;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkCourse(String name, Course c) {
        assertEquals(name, c.getCourseName());
    }
}
