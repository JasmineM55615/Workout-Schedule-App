package ui;

import model.Exercise;
import model.Schedule;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//This class represents a workout schedule application
// UI Functionality and methods are implemented from Teller App. Link below:
// https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
public class WorkoutScheduleApp {

    private static final String JSON_STORE = "./data/myFile.json"; //
    private Schedule newSchedule; //
    private Scanner input;
    private JsonWriter jsonWriter; //
    private JsonReader jsonReader; //
    private boolean keepGoing;

    //EFFECTS: runs the workout schedule application
    public WorkoutScheduleApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        keepGoing = true;
        runWorkoutSchedule();
    }


    //MODIFIES: this
    //EFFECTS: processes user input
    private void runWorkoutSchedule() {
        input = new Scanner(System.in);
        input.useDelimiter("\n"); //allows us to use "nextLine" when getting string input
        //                          and allows input to have spaces
        String command = null;

        displayMenu();
        command = input.next().toLowerCase();
        handleCommand(command);
        System.out.println("\nSee you later! :)");
    }


    private void displayMenu() {
        System.out.println("\nWelcome!");
        System.out.println("\tc -> Create a new workout schedule");
        System.out.println("\tl -> Load your last workout schedule from file");
    }

    private void handleCommand(String command) {
        if (command.equals("c")) {
            createNewSchedule();
        } else if (command.equals("l")) {
            loadLastSchedule();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void createNewSchedule() {
        System.out.println("\tFirst, enter a name for your schedule below:");
        String theName = input.next();
        System.out.println("\tNow, enter a date for your schedule below:");
        String theDate = input.next();
        initialize(theName, theDate);

        while (keepGoing == true) {
            displaySchedule();
            String theCommand = input.next().toLowerCase();

            if (theCommand.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(theCommand);
            }
        }
    }

    private void loadLastSchedule() {
        loadSchedule();
        while (keepGoing == true) {
            displaySchedule();
            String theCommand = input.next().toLowerCase();

            if (theCommand.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(theCommand);
            }
        }
    }



    //REQUIRES: string as name and string as date
    //MODIFIES: this
    //EFFECTS: creates new workout schedule with given name and date
    private void initialize(String name, String date) {
        newSchedule = new Schedule(name, date);

    }

    //EFFECTS: displays menu of options regarding the schedule
    private void displaySchedule() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add an exercise");
        System.out.println("\td -> delete an exercise");
        System.out.println("\tg -> get number of exercises");
        System.out.println("\ts -> save work room to file");
        System.out.println("\tq -> quit");
    }

    //REQUIRES: string for command
    //MODIFIES: this
    //EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addExercise();
        } else if (command.equals("d")) {
            deleteExercise();
        } else if (command.equals("g")) {
            getLength();
        } else if (command.equals("s")) {
            saveSchedule();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //MODIFIES: this
    //EFFECTS: adds user's exercise to schedule
    private void addExercise() {
        // Make sure all inputs are valid
        System.out.print("Enter name of exercise:");
        String name = input.next();
        System.out.print("Enter any notes for exercise:");
        String notes = input.next();
        System.out.print("Enter number of sets for exercise:");
        Integer sets = input.nextInt();
        System.out.print("Enter number of reps for exercise:");
        Integer reps = input.nextInt();
        System.out.print("Enter rest time for exercise:");
        Integer restTime = input.nextInt();

        Exercise newExercise = new Exercise(name, sets, reps, notes, restTime);
        newSchedule.addExercise(newExercise);
        System.out.print(" \n Exercise added!");
    }

    //Use JOptionPane****************

    //MODIFIES: this
    //EFFECTS: deletes exercise given from user if valid
    private void deleteExercise() {
        System.out.print("Enter the name of the exercise you wish to delete:");

        // Make sure input is valid
        String name = input.next();

        String exerciseNameToDelete = name;
        if (newSchedule.deleteExercise(exerciseNameToDelete) == false) {
            System.out.print("Couldn't remove because exercise wasn't in schedule!");
        } else {
            System.out.print("Exercise deleted!");
        }
    }

    //EFFECTS: returns length of schedule to user
    private void getLength() {
        int length = newSchedule.getLength();
        System.out.print(length);
    }


    //ADDED METHODS BELOW --- PHASE 2

    // EFFECTS: saves the schedule to file
    private void saveSchedule() {
        try {
            jsonWriter.open();
            jsonWriter.write(newSchedule);
            jsonWriter.close();
            System.out.println("Saved " + newSchedule.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the schedule from file
    private void loadSchedule() {
        try {
            newSchedule = jsonReader.read();
            System.out.println("Loaded " + newSchedule.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
