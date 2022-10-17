package persistence;

import model.Schedule;
import org.json.JSONObject;

import java.io.*;

//This class represents a writer that writes JSON representation of schedule to file
// Methods are implemented from WorkRoom application, JsonWriter class. Link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    //EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECTS: opens writer; throws FileNotFoundException if destination file cannot be opened
    //         for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    //MODIFIES: this
    //EFFECTS: writes JSON representation of schedule to file
    public void write(Schedule sch) {
        JSONObject json = sch.toJson();
        saveToFile(json.toString(TAB));
    }

    //MODIFIES: this
    //EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    //MODIFIES: this
    //EFFECTS: writes string to file
    public void saveToFile(String json) {
        writer.print(json);
    }

}
