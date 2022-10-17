package ui;

import java.io.FileNotFoundException;

//This class creates a new workout schedule application
// try/catch is implemented from Teller application. Link below:
// https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
public class Main {
    public static void main(String[] args) {
       /* try {
            new WorkoutScheduleApp();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot run application because file cannot be found");
        }*/
        new WorkoutScheduleGUI();
    }
}
