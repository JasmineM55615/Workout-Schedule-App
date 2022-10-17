package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//This class represents tests that test the Exercise class
public class ExerciseTest {

    private Exercise testExercise;

    @BeforeEach
    public void setup(){
        testExercise = new Exercise("test name", 1, 2, "test notes", 0);
    }

    @Test
    public void testConstructor(){
        assertEquals("test name", testExercise.getName());
        assertEquals(1, testExercise.getSets());
        assertEquals(2, testExercise.getReps());
        assertEquals("test notes", testExercise.getNotes());
        assertEquals(0, testExercise.getRestTime());
        assertFalse(testExercise.isCheckedOff());

    }

    @Test
    public void testChangeCheckedOffStatus(){
        assertFalse(testExercise.isCheckedOff());
        testExercise.changeCheckedOffStatus();
        assertTrue(testExercise.isCheckedOff());
        testExercise.changeCheckedOffStatus();
        assertFalse(testExercise.isCheckedOff());
    }

}
