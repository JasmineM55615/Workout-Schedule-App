package persistence;

import org.json.JSONObject;

// This class represents an interface and returns a JSON object
// Implemented from Teller application. Link below:
// https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
public interface Writable {

    //EFFECTS: returns this as JSON object
    JSONObject toJson();
}
