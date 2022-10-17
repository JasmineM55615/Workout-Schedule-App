package persistence;

import model.Exercise;
import model.Schedule;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//This class tests JsonReader class
// Tests are implemented from Teller application. Link below:
// https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Schedule sch = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptySchedule() {
        JsonReader reader = new JsonReader("./data/testReaderEmptySchedule.json");
        try {
            Schedule sch = reader.read();
            assertEquals("my workout schedule", sch.getName());
            assertEquals("aug 2", sch.getDate());
            assertEquals(0, sch.getLength());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralSchedule() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralSchedule.json");
        try {
            Schedule sch = reader.read();
            assertEquals("my workout schedule", sch.getName());
            assertEquals("aug 2", sch.getDate());
            List<Exercise> theExercises = sch.getExercises();
            assertEquals(2, sch.getLength());
            //CREATE A METHOD IN SCHEDULE TO GET AN EXERCISE BASED ON INDEX
            checkExercise("exercise1", 1, 2, "some notes", 3, theExercises.get(0));
            checkExercise("exercise2", 4, 5, "some more notes", 6, theExercises.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
