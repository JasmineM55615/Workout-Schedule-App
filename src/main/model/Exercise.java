package model;

import org.json.JSONObject;
import persistence.Writable;

//This class represents an exercise having a name, number of sets, reps, notes, rest time, and boolean representing
// its checked-off status
// Some methods are implemented from Teller application. Link below:
// https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
public class Exercise implements Writable {

    private String name;
    private int sets;
    private int reps;
    private String notes;
    private int restTime;
    private boolean checkedOff;


    //REQUIRES: name has a non-zero length, sets and reps be integers > 0, restTime be an integer >= 0, notes
    //          be a string <= 100 characters
    //MODIFIES: this
    //EFFECTS: creates a new unchecked-off exercise with given name, sets, reps, notes, and rest time
    public Exercise(String name, int sets, int reps, String notes, int restTime) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.notes = notes;
        this.restTime = restTime;
        checkedOff = false;
    }

    public String getName() {
        return name;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    public String getNotes() {
        return notes;
    }

    public int getRestTime() {
        return restTime;
    }

    //EFFECTS: returns true if exercise is checked off
    //         false otherwise
    public boolean isCheckedOff() {
        return checkedOff;
    }


    //I don't use these methods (yet)

/*    //REQUIRES: string <= 30 characters
    //MODIFIES: this
    //EFFECTS: changes exercise name to the given string
    public void setName(String theName) {
        name = theName;
    }

    //REQUIRES: integer > 0
    //MODIFIES: this
    //EFFECTS: changes exercise sets to the given integer
    public void setSets(int numSets) {
        sets = numSets;
    }

    //REQUIRES: integer > 0
    //MODIFIES: this
    //EFFECTS: changes exercise reps to the given integer
    public void setReps(int numReps) {
        reps = numReps;
    }

    //REQUIRES: string <= 100 characters
    //MODIFIES: this
    //EFFECTS: changes exercise notes to the given string
    public void setNotes(String theNotes) {
        notes = theNotes;
    }

    //REQUIRES: integer >= 0
    //MODIFIES: this
    //EFFECTS: changes exercise restTime to the given integer
    public void setRestTime(int theRestTime) {
        restTime = theRestTime;
    }*/

    //MODIFIES: this (the variable checkedOff)
    //EFFECTS: if checkedOff is false, it changes to true, and vice versa
    public void changeCheckedOffStatus() {
        checkedOff = !checkedOff;
    }


    //ADDED METHOD BELOW --- PHASE 2

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("sets", sets);
        json.put("reps", reps);
        json.put("notes", notes);
        json.put("restTime", restTime);
        return json;
    }

}
