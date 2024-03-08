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

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            CourseList cl = new CourseList();
            JsonWriter writer = new JsonWriter("./data/invalidfile:/\0.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCourseList() {
        try {
            CourseList cl = new CourseList();
            JsonWriter writer = new JsonWriter("./data/testfileempty.json");
            writer.open();
            writer.write(cl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testfileempty.json");
            cl = reader.read();
            List<Course> courses = cl.getListOfCourse();
            assertTrue(courses.isEmpty());

        } catch (IOException e) {
            fail("IOException unexpected");
        }
    }

    @Test
    void testWriterNormalCourseList() {
        try {
            CourseList cl = new CourseList();
            Course c1 = new Course("Math");
            Course c2 = new Course("CS");
            PracticeProblem p1 = new PracticeProblem("1", "1");
            PracticeProblem p2 = new PracticeProblem("2", "2");
            c1.addPracticeProblem(p1);
            c2.addPracticeProblem(p2);
            cl.addCourse(c1);
            cl.addCourse(c2);
            JsonWriter writer = new JsonWriter("./data/testfilegeneral.json");
            writer.open();
            writer.write(cl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testfilegeneral.json");
            cl = reader.read();
            List<Course> courses = cl.getListOfCourse();
            assertFalse(courses.isEmpty());
            assertEquals(2, courses.size());
            assertEquals(2, courses.size());
            checkCourse("Math", c1);
            checkCourse("CS", c2);

        } catch (IOException e) {
            fail("IOException unexpected");
        }
    }

}
