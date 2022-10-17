package persistence;

import model.Exercise;
import model.Schedule;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//This class tests JsonWriter class
// Tests are implemented from Teller application. Link below:
// https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            Schedule wr = new Schedule("My schedule", "Sept 1");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptySchedule() {
        try {
            Schedule sch = new Schedule("My schedule", "Sept 1");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptySchedule.json");
            writer.open();
            writer.write(sch);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptySchedule.json");
            sch = reader.read();
            assertEquals("My schedule", sch.getName());
            assertEquals("Sept 1", sch.getDate());
            assertEquals(0, sch.getLength());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralSchedule() {
        try {
            Schedule sch = new Schedule("My schedule", "Sept 1");
            sch.addExercise(new Exercise("an exercise", 1, 2, "notes", 0));
            sch.addExercise(new Exercise("another exercise", 4, 5, "more notes", 6));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralSchedule.json");
            writer.open();
            writer.write(sch);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralSchedule.json");
            sch = reader.read();
            assertEquals("My schedule", sch.getName());
            assertEquals("Sept 1", sch.getDate());
            List<Exercise> theExercises = sch.getExercises();
            assertEquals(2, theExercises.size());
            checkExercise("an exercise", 1, 2, "notes", 0, theExercises.get(0));
            checkExercise("another exercise", 4, 5, "more notes", 6, theExercises.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
