package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List; //ADDED

//This class represents a schedule having a name and a date
// Some methods are implemented from Teller application. Link below:
// https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
public class Schedule implements Writable {


    private List<Exercise> schedule; //ADDED: changed from ArrayList to List
    private String name;
    private String date;


    //REQUIRES: string <= 30 characters, date <= 30 characters
    //MODIFIES: this
    //EFFECTS: creates a new empty schedule
    public Schedule(String name, String date) {
        this.name = name;
        this.date = date;
        schedule = new ArrayList<Exercise>();
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }


    //I don't use these methods (yet)

/*    //REQUIRES: string <= 30 characters
    //MODIFIES: this
    //EFFECTS: changes schedule name to the given string
    public void setName(String theName) {
        name = theName;
    }

    //REQUIRES: string <= 30 characters
    //MODIFIES: this
    //EFFECTS: changes schedule date to the given string
    public void setDate(String theDate) {
        date = theDate;
    }*/


    //EFFECTS: returns the size of the schedule (how many exercises there are currently in it)
    public int getLength() {
        return schedule.size();
    }

    //REQUIRES: a valid exercise
    //MODIFIES: this
    //EFFECTS: adds the exercise to the schedule
    public void addExercise(Exercise exercise) {
        schedule.add(exercise);
    }

    //REQUIRES: a valid exercise name
    //MODIFIES: this
    //EFFECTS: if exercise is in schedule, it removes it and returns true
    //         else it returns false
    public boolean deleteExercise(String exerciseName) {
        for (Exercise e : schedule) {
            if (e.getName().equals(exerciseName)) {
                schedule.remove(e);
                return true;
            }
        }
        return false;
    }


    //REQUIRES: length of schedule != 0
    //EFFECTS: returns the exercise at the front of the list (schedule)
    public Exercise getNextExercise() {
        return schedule.get(0);
    }

    //REQUIRES: integer between 1 and (length of schedule + 1), inclusive
    //MODIFIES: this
    //EFFECTS: if exercise is in schedule, it moves it to the given position (index: position - 1) and returns true
    //         else, returns false
    public boolean moveExercise(int position, Exercise exercise) {
        if (schedule.contains(exercise)) {
            deleteExercise(exercise.getName());
            schedule.add((position - 1), exercise);
            return true;
        }
        return false;
    }

    //REQUIRES: a valid exercise
    //EFFECTS: returns true if the given exercise is in the schedule
    //         else, returns false
    public boolean containsExercise(Exercise exercise) {
        return schedule.contains(exercise);
    }

    //REQUIRES: an exercise that is in the schedule
    //MODIFIES: this
    //EFFECTS: - changes the status of checkedOff variable: if checkedOff is true, it will change to false
    //                                                      if checkedOff is false, it will change to true
    //         - if the exercise is not already at the end of the schedule, it moves it to the end
    //           else, keeps the exercise at the end
    public void checkOffExercise(Exercise exercise) {
        exercise.changeCheckedOffStatus();
        moveExercise(getLength(), exercise);
    }

    // EFFECTS: returns an unmodifiable list of exercises in this schedule
    public List<Exercise> getExercises() {
        return Collections.unmodifiableList(schedule);
    }

    //******** Unsure if I should still add this method, how I would implement it

    //REQUIRES: two exercises that are in the schedule
    //MODIFIES: this
    //EFFECTS: if both exercises have not been supersetted already, it will superset them in the schedule and return
    //         true
    //         else, it will return false
    //public boolean superset() {
    //stub
    //return false;
    //}


    //ADDED METHODS BELOW --- PHASE 2
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("date", date);
        json.put("schedule", exercisesToJson());
        return json;
    }

    private JSONArray exercisesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Exercise e : schedule) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }


}
