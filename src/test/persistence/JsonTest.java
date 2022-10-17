package persistence;

import model.Exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

//This class tests Json
// Tests are implemented from Teller application. Link below:
// https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
public class JsonTest {

    protected void checkExercise(String name, int sets, int reps, String notes, int restTime, Exercise exercise) {
        assertEquals(name, exercise.getName());
        assertEquals(sets, exercise.getSets());
        assertEquals(reps, exercise.getReps());
        assertEquals(notes, exercise.getNotes());
        assertEquals(restTime, exercise.getRestTime());
    }
}
