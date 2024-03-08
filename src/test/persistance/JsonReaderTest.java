package persistance;

import model.Course;
import model.CourseList;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/fakeFile.json");
        try {
            CourseList cl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // all good
        }
    }

    @Test
    void testReaderEmptyCourseList() {
        JsonReader reader = new JsonReader("./data/empty.json");
        try {
            CourseList cl = reader.read();
            assertTrue(cl.isEmpty());
        } catch (IOException e) {
            fail("Could not read file");
        }
    }

    @Test
    void testReaderGeneralCourseList() {
        JsonReader reader = new JsonReader("./data/general.json");
        try {
            CourseList cl = reader.read();
            List<Course> courses = cl.getListOfCourse();
            List<String> courseNames = cl.getListOfCourseNames();
            assertEquals(2 ,courses.size());
            assertEquals("Math101", courseNames.get(1));

        } catch (IOException e) {
            fail("IOException unexpected");
        }
    }

}
