package persistence;

import model.Exercise;
import model.Schedule;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

//This class represents a reader that reads schedule from JSON data stored in file
// Methods are implemented from WorkRoom application, JsonReader class. Link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {

    private String source;

    //EFFECTS: reads schedule from file and returns it
    //         throws IOException if an error occurs reading data from file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads schedule from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Schedule read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSchedule(jsonObject);
    }

    //EFFECTS: reads source file as string and return it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    //EFFECTS: parses schedule from JSON object and returns it
    private Schedule parseSchedule(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String date = jsonObject.getString("date");
        Schedule sch = new Schedule(name, date);
        addExercises(sch, jsonObject);
        return sch;
    }

    //MODIFIES: sch
    //EFFECTS: parses exercises from JSON object and adds them to schedule
    private void addExercises(Schedule sch, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("schedule"); //CHANGED
        for (Object json : jsonArray) {
            JSONObject nextExercise = (JSONObject) json;
            addExercise(sch, nextExercise);
        }
    }

    //MODIFIES: sch
    //EFFECTS: parses exercise from JSON object and adds it to schedule
    private void addExercise(Schedule sch, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int sets = jsonObject.getInt("sets");
        int reps = jsonObject.getInt("reps");
        String notes = jsonObject.getString("notes");
        int restTime = jsonObject.getInt("restTime");
        Exercise exercise = new Exercise(name, sets, reps, notes, restTime);
        sch.addExercise(exercise);
    }


}
