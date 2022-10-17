package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//This class represents tests that test the Schedule class
public class ScheduleTest {

    private Schedule testSchedule;
    private Exercise testExercise1;
    private Exercise testExercise2;

    @BeforeEach
    public void setup(){
        testSchedule = new Schedule("test name", "Aug 2, Monday");
        testExercise1 = new Exercise("", 1, 2, "", 3);
        testExercise2 = new Exercise("test", 4, 5, "test", 6);
    }

    @Test
    public void testGetName(){
        assertEquals("test name", testSchedule.getName());
    }

    @Test
    public void testGetDate(){
        assertEquals("Aug 2, Monday", testSchedule.getDate());
    }

    @Test
    public void testConstructor(){
        assertEquals("test name", testSchedule.getName());
        assertEquals("Aug 2, Monday", testSchedule.getDate());
        assertEquals(0, testSchedule.getLength());
    }

    @Test
    public void testGetLength(){
        assertEquals(0, testSchedule.getLength());
        testSchedule.addExercise(testExercise1);
        assertEquals(1, testSchedule.getLength());
    }

    @Test
    public void testAddExercise(){
        testSchedule.addExercise(testExercise1);
        assertEquals(1, testSchedule.getLength());
        assertTrue(testSchedule.containsExercise(testExercise1));
        testSchedule.addExercise(testExercise2);
        assertEquals(2, testSchedule.getLength());
        assertTrue(testSchedule.containsExercise(testExercise2));
    }

    @Test
    public void testDeleteExercise(){
        assertFalse(testSchedule.deleteExercise(testExercise1.getName()));
        testSchedule.addExercise(testExercise1);
        testSchedule.addExercise(testExercise2);
        assertTrue(testSchedule.deleteExercise(testExercise1.getName()));
        assertFalse(testSchedule.containsExercise(testExercise1));
        assertTrue(testSchedule.containsExercise(testExercise2));
    }

    @Test
    public void testDeleteExerciseNotThere(){
        assertFalse(testSchedule.deleteExercise(testExercise1.getName()));
        testSchedule.addExercise(testExercise1);
        testSchedule.addExercise(testExercise2);
        assertTrue(testSchedule.deleteExercise(testExercise1.getName()));
        assertFalse(testSchedule.containsExercise(testExercise1));
        assertFalse(testSchedule.deleteExercise(testExercise1.getName()));
        assertTrue(testSchedule.containsExercise(testExercise2));
    }


    @Test
    public void testGetNextExercise(){
        testSchedule.addExercise(testExercise1);
        testSchedule.addExercise(testExercise2);
        assertEquals(testExercise1, testSchedule.getNextExercise());
        testSchedule.deleteExercise(testExercise1.getName());
        assertEquals(testExercise2, testSchedule.getNextExercise());
    }

    @Test
    public void testMoveExercise(){
        assertFalse(testSchedule.moveExercise(3, testExercise1));
        testSchedule.addExercise(testExercise1);
        testSchedule.addExercise(testExercise2);
        assertTrue(testSchedule.moveExercise(2, testExercise1));
        assertEquals(testExercise2, testSchedule.getNextExercise());
    }

    @Test
    public void testContainsExercise(){
        assertFalse(testSchedule.containsExercise(testExercise1));
        testSchedule.addExercise(testExercise1);
        assertTrue(testSchedule.containsExercise(testExercise1));
    }

    @Test
    public void testCheckOffExercise(){
        testSchedule.addExercise(testExercise1);
        testSchedule.addExercise(testExercise2);
        testSchedule.checkOffExercise(testExercise1);
        assertTrue(testExercise1.isCheckedOff());
        assertEquals(testExercise2, testSchedule.getNextExercise());
    }

    @Test
    public void testGetExercises(){
        //List<Exercise> testExerciseList = new Collections.unmodifiableList(testSchedule);
        List<Exercise> theExercises = testSchedule.getExercises();
        testSchedule.addExercise(testExercise1);
        testSchedule.addExercise(testExercise2);
        assertEquals(testExercise1, theExercises.get(0));
        assertEquals(testExercise2, theExercises.get(1));
    }

}
