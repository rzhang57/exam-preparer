package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PracticeProblemTest {

    PracticeProblem p1;
    PracticeProblem p2;

    @BeforeEach
    public void setup() {
        p1 = new PracticeProblem("t1", "b1", "s1");
        p2 = new PracticeProblem("t2", "b2");
    }

    @Test
    public void testGetTitle() {
        assertEquals("t1", p1.getTitle());
        assertEquals("t2", p2.getTitle());
    }

    @Test
    public void testGetBody() {
        assertEquals("b1", p1.getBody());
        assertEquals("b2", p2.getBody());
    }

    @Test
    public void testGetSolution() {
        assertEquals("s1", p1.getSolution());
        assertEquals("", p2.getSolution());
    }

    @Test
    public void testGetStatus() {
        assertEquals( 0, p1.getStatus());
        assertEquals(0, p2.getStatus());
    }

    @Test
    public void testSetStatus() {
        assertEquals(0, p1.getStatus());
        assertEquals(0, p2.getStatus());
        p1.setStatus(1);
        assertEquals(1, p1.getStatus());
        p1.setStatus(2);
        assertEquals(2, p1.getStatus());
        p2.setStatus(2);
        assertEquals(2, p2.getStatus());
    }


}
