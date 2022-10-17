package ui;

import model.Exercise;
import model.Schedule;
import org.json.JSONException;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


//This class represents an application that displays the user's workout schedule
public class WorkoutScheduleGUI extends JPanel implements ListSelectionListener {

    private static int frameWidth = 400;
    private static int frameHeight = 500;
    private static int frameXLocation = 100;
    private static int frameYLocation = 100;

    //for methods from WorkoutScheduleApp:
    private static final String JSON_STORE = "./data/myFile.json";
    private static String scheduleName = "fake";
    private static String scheduleDate = "fake";
    private static Schedule newSchedule = new Schedule(scheduleName, scheduleDate);
    private static JsonWriter jsonWriter;
    private static JsonReader jsonReader;

    //non-j components:
    private static boolean alreadyEnabledAddButton = false;
    private static boolean alreadyEnabledNext1Button = false;
    private static boolean alreadyEnabledNext2Button = false;
    private static DocumentListener myDocListener = new MyDocListener();
    private static Map<String, Exercise> exercisesMap = new HashMap<>(); //implementing Map interface here


    //J components:
    private static JFrame frame = new JFrame();
    private static GridLayout gridLayout = new GridLayout(0, 2);
    private static JPanel leftPanel = new JPanel();
    private static JPanel rightPanel = new JPanel();
    private static Color welcomePanelColor = new Color(102, 178, 255);
    private static JLabel welcomeLabel = new JLabel("welcome!");
    private static JButton newScheduleButton = new JButton("Create New Schedule");
    private static JButton loadButton = new JButton("Load Last Schedule");
    private static JLabel schedNameLabel = new JLabel("Schedule Name:");
    private static JTextField schedNameText = new JTextField(10);
    private static JTextField schedDateText = new JTextField(10);
    private static JButton schedNameNextButton = new JButton("Next");
    private static JLabel schedDateLabel = new JLabel("Schedule Date:");
    private static JButton schedDateNextButton = new JButton("Next");
    private static JPanel schedNamePanel = new JPanel();
    private static JPanel schedDatePanel = new JPanel();
    private static JPanel singlePanel = new JPanel();
    private static DefaultListModel listModel = new DefaultListModel();
    //private static JList<String> exerciseStringList = new JList<>(listModel);
    private static JList exerciseStringList = new JList(listModel);
    private static JButton addExerciseButton = new JButton("Add Exercise");
    private static JButton deleteExerciseButton = new JButton("Delete Exercise");
    private static JPanel namePanel = new JPanel();
    private static JPanel setsPanel = new JPanel();
    private static JPanel repsPanel = new JPanel();
    private static JPanel restTimePanel = new JPanel();
    private static JPanel notesPanel = new JPanel();
    private static JLabel nameLabel = new JLabel("Name:");
    private static JLabel setsLabel = new JLabel("Sets:");
    private static JLabel repsLabel = new JLabel("Reps:");
    private static JLabel restTimeLabel = new JLabel("Rest Time:");
    private static JLabel notesLabel = new JLabel("Notes:");
    private static JTextField nameText = new JTextField(10);
    private static JTextField setsText = new JTextField(10);
    private static JTextField repsText = new JTextField(10);
    private static JTextField restTimeText = new JTextField(10);
    private static JTextField notesText = new JTextField(10);
    //private static String addString = new String("Add");
    private static JButton addThisExerciseButton = new JButton("Add");
    //private static JButton goBackToAddDeletePageButton = new JButton("Go Back");
    private static JButton saveButton = new JButton("Save");


    // constructor
    public WorkoutScheduleGUI() {
        super(new BorderLayout());
        exerciseStringList.addListSelectionListener(this);
        newSchedule = new Schedule("test", "test");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        createAndShowWelcomeGUI();
    }


    // MODIFIES: this
    // EFFECTS: starts the application
    private static void createAndShowWelcomeGUI() {
        //frame.getContentPane().setLayout(new BorderLayout());
        frame.setSize(500, 500);
        frame.setLayout(gridLayout);
        gridLayout.layoutContainer(frame);


        // jp.setPreferredSize(new Dimension(250,200));
        //^^ this one seems to make it perfectly half of the frame
        //leftPanel.setPreferredSize(new Dimension(250, frameHeight));
        //rightPanel.setPreferredSize(new Dimension(250, frameHeight));


        //JButtons
        newScheduleButton.addActionListener(WorkoutScheduleGUI::createNewScheduleClicked);
        loadButton.addActionListener(WorkoutScheduleGUI::loadButtonClicked);

        //adding both buttons to proper panels

        //leftPanel.setLayout(new BorderLayout());
        leftPanel.setBackground(welcomePanelColor);
        leftPanel.add(newScheduleButton);

        //rightPanel.setLayout(new BorderLayout()); // this makes it take up whole part
        rightPanel.setBackground(welcomePanelColor);
        rightPanel.add(loadButton, BorderLayout.CENTER);

        frame.add(leftPanel);
        frame.add(rightPanel);

        frame.setVisible(true);
    }

    // REQUIRES: action event
    // MODIFIES: this
    // EFFECTS: takes user to page when they click create new schedule
    public static void createNewScheduleClicked(ActionEvent e) {
        frame.setLayout(new GridLayout(1, 1));
        newScheduleButton.setVisible(false);
        loadButton.setVisible(false);
        leftPanel.setVisible(false);
        rightPanel.setVisible(false);
        frame.remove(leftPanel);
        frame.remove(rightPanel);

        Color lightPurple = new Color(178, 102, 255);
        singlePanel.setBackground(lightPurple);

        schedNameNextButton.addActionListener(WorkoutScheduleGUI::schedNameNextButtonClicked);
        schedNameNextButton.setEnabled(false);

        schedNameText.getDocument().addDocumentListener(myDocListener);
        schedNameText.requestFocusInWindow();

        schedNamePanel.add(schedNameLabel);
        schedNamePanel.add(schedNameText);
        schedNamePanel.add(schedNameNextButton);
        singlePanel.add(schedNamePanel);
        frame.add(singlePanel);


        //rightPanel.setLayout(new GridLayout(0, 1)); //makes buttons take up entire width
    }


    // REQUIRES: action event
    // MODIFIES: this
    // EFFECTS: takes user to page when they click next after inputting schedule name
    public static void schedNameNextButtonClicked(ActionEvent e) {
        scheduleName = schedNameText.getText();
        singlePanel.setVisible(false);
        singlePanel.remove(schedNamePanel);

        schedDateNextButton.addActionListener(WorkoutScheduleGUI::schedDateNextButtonClicked);
        schedDateNextButton.setEnabled(false);

        schedDateText.getDocument().addDocumentListener(myDocListener);
        schedDateText.requestFocusInWindow();

        schedDatePanel.add(schedDateLabel);
        schedDatePanel.add(schedDateText);
        schedDatePanel.add(schedDateNextButton);
        singlePanel.add(schedDatePanel);
        frame.add(singlePanel);
        singlePanel.setVisible(true);

    }

    // REQUIRES: action event
    // MODIFIES: this
    // EFFECTS: saves user inputs to new schedule name and date
    public static void schedDateNextButtonClicked(ActionEvent e) {
        scheduleDate = schedDateText.getText();

        //create the new schedule with given name and date
        newSchedule = new Schedule(scheduleName, scheduleDate);
        addDeletePage();
    }

    // MODIFIES: this
    // EFFECTS: takes user to page when they click next after inputting schedule date
    public static void addDeletePage() {
        singlePanel.setVisible(false);
        frame.remove(singlePanel);
        Color leftPanelColor = new Color(176, 224, 230);
        Color rightPanelColor = new Color(70, 130, 180);
        leftPanel.setBackground(leftPanelColor);
        rightPanel.setBackground(rightPanelColor);
        leftPanel.add(createNewExerciseList());
        addExerciseButton.addActionListener(WorkoutScheduleGUI::addExerciseClicked);
        deleteExerciseButton.addActionListener(WorkoutScheduleGUI::deleteExerciseClicked);
        saveButton.addActionListener(WorkoutScheduleGUI::saveButtonClicked);
        rightPanel.add(addExerciseButton);
        rightPanel.add(saveButton);
        //rightPanel.add(deleteExerciseButton);

        leftPanel.setVisible(true);
        rightPanel.setVisible(true);

        frame.add(leftPanel);
        frame.add(rightPanel);
    }

    // MODIFIES: this
    // EFFECTS:  creates a list of exercise names
    public static JList<String> createNewExerciseList() {
        exerciseStringList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        exerciseStringList.setSelectedIndex(0);
        //JScrollPane listScrollPane = new JScrollPane(exerciseStringList);
        //listModel.addElement("test exercise");
        return exerciseStringList;
    }

    // REQUIRES: action event
    // MODIFIES: this
    // EFFECTS:  takes user to page after clicking the add exercise button
    public static void addExerciseClicked(ActionEvent e) {
        addExerciseButton.setVisible(false);

        addExercisePanels();

        nameText.getDocument().addDocumentListener(myDocListener);

        //add panels and button to right panel
        rightPanel.add(namePanel);
        rightPanel.add(setsPanel);
        rightPanel.add(repsPanel);
        rightPanel.add(restTimePanel);
        rightPanel.add(notesPanel);

        //setup "add" button
        addThisExerciseButton.addActionListener(WorkoutScheduleGUI::addButtonClicked);
        addThisExerciseButton.setEnabled(false);
        rightPanel.add(addThisExerciseButton);

        //setup "delete" and "save" buttons
        saveButton.setVisible(false);
        rightPanel.remove(saveButton);
        if (listModel.getSize() == 0) {
            deleteExerciseButton.setEnabled(false);
        }
        rightPanel.add(deleteExerciseButton);
        rightPanel.add(saveButton);
        saveButton.setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS:  adds appropriate labels and texts to their corresponding panels
    public static void addExercisePanels() {
        namePanel.add(nameLabel);
        namePanel.add(nameText);
        setsPanel.add(setsLabel);
        setsPanel.add(setsText);
        repsPanel.add(repsLabel);
        repsPanel.add(repsText);
        restTimePanel.add(restTimeLabel);
        restTimePanel.add(restTimeText);
        notesPanel.add(notesLabel);
        notesPanel.add(notesText);
    }

    // REQUIRES: action event
    // MODIFIES: this
    // EFFECTS:  adds exercise to list
    public static void addButtonClicked(ActionEvent e) {

        //add a try/catch for numberformatexception ?
        String name = nameText.getText();
        int sets = Integer.parseInt(setsText.getText());
        int reps = Integer.parseInt(repsText.getText());
        int restTime = Integer.parseInt(restTimeText.getText());
        String notes = notesText.getText();

        checkForInvalidInput(name, sets, reps, restTime);

        //insert default sets, reps, notes, and restTime values if not entered by user
        if (setsText.getText().equals("")) {
            sets = 1;
        }
        if (repsText.getText().equals("")) {
            reps = 1;
        }
        if (restTimeText.getText().equals("")) {
            restTime = 0;
        }
        if (notesText.getText().equals("")) {
            notes = "";
        }

        Exercise addedExercise = new Exercise(name, sets, reps, notes, restTime);
        exercisesMap.put(name, addedExercise);
        listModel.addElement(name);
        newSchedule.addExercise(exercisesMap.get(name));

        setupForNextTime();
    }

    // REQUIRES: valid string and 3 integers
    // EFFECTS:  checks for invalid input
    public static void checkForInvalidInput(String name, int sets, int reps, int restTime) {
        //make sound when user puts invalid input
        if (name.equals("") || listModel.contains(name)) {
            Toolkit.getDefaultToolkit().beep();
            return; //adding returns disallows it from continuing (so doesn't
            //add exercise to list)
        }
        if (!(sets > 0)) {
            Toolkit.getDefaultToolkit().beep();
            return;
        }
        if (!(reps > 0)) {
            Toolkit.getDefaultToolkit().beep();
            return;
        }
        if (!(restTime >= 0)) {
            Toolkit.getDefaultToolkit().beep();
            return;
        }
    }

    // MODIFIES: this
    // EFFECTS:  resets texts for next use
    public static void setupForNextTime() {
        //set up for next time
        nameText.requestFocusInWindow();
        nameText.setText("");
        setsText.setText("");
        repsText.setText("");
        restTimeText.setText("");
        notesText.setText("");
    }


    // REQUIRES: action event
    // MODIFIES: this
    // EFFECTS:  saves user input to schedule
    public static void saveButtonClicked(ActionEvent e) {
        saveSchedule();
    }

    // REQUIRES: action event
    // MODIFIES: this
    // EFFECTS:  deletes selected exercise from list and the schedule
    public static void deleteExerciseClicked(ActionEvent e) {
        int index = exerciseStringList.getSelectedIndex();
        String deletingExerciseName = listModel.get(index).toString();
        listModel.remove(index);
        newSchedule.deleteExercise(deletingExerciseName);

        int size = listModel.getSize();

        if (size == 0) { //No exercises left, disable deleting
            deleteExerciseButton.setEnabled(false);

        } else { //Select an index.
            if (index == listModel.getSize()) {
                //removed item in last position
                index--;
            }

            exerciseStringList.setSelectedIndex(index);
            exerciseStringList.ensureIndexIsVisible(index);
        }
    }

    // REQUIRES: action event
    // MODIFIES: this
    // EFFECTS:  takes user to page when they click load button with their loaded data
    public static void loadButtonClicked(ActionEvent e) {
        newScheduleButton.setVisible(false);
        loadButton.setVisible(false);
        loadSchedule();

        //Add saved exercises to the current list
        //create an if isnt empty loop here?
        for (Exercise ex : newSchedule.getExercises()) {
            listModel.addElement(ex.getName());
        }
        addDeletePage();
        if (listModel.getSize() == 0) {
            deleteExerciseButton.setEnabled(false);
        }
        rightPanel.add(deleteExerciseButton);
        rightPanel.add(saveButton);
    }


    // MODIFIES: this
    // EFFECTS:  saves user data
    private static void saveSchedule() {
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
    private static void loadSchedule() {
        try {
            newSchedule = jsonReader.read();
            System.out.println("Loaded " + newSchedule.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        } catch (JSONException e) {
            System.out.println("You have no existing schedule!");
        }
    }

    // REQUIRES: list selection event
    // MODIFIES: this
    // EFFECTS:  properly deletes exercises based on remaining number of exercises
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (exerciseStringList.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                deleteExerciseButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                deleteExerciseButton.setEnabled(true);
            }
        }
    }

    //This class represents a doc listenere that allows buttons to be enables based on user input
    private static class MyDocListener implements DocumentListener {


        // REQUIRES: document event
        // MODIFIES: this
        // EFFECTS:  inserts update
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        // REQUIRES: document event
        // MODIFIES: this
        // EFFECTS:  removes update
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        // REQUIRES: document event
        // MODIFIES: this
        // EFFECTS:  changes update
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }


        // MODIFIES: this
        // EFFECTS:  enables buttons when appropriate
        private void enableButton() {
            //for Add button
            if (!alreadyEnabledAddButton) {
                addThisExerciseButton.setEnabled(true);
            }

            //for Next (first one) button
            if (!alreadyEnabledNext1Button) {
                schedNameNextButton.setEnabled(true);
            }

            //for Next (second one) button
            if (!alreadyEnabledNext2Button) {
                schedDateNextButton.setEnabled(true);
            }
        }

        // REQUIRES: document event
        // MODIFIES: this
        // EFFECTS:  handles empty texts fields
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                //for Add button
                addThisExerciseButton.setEnabled(false);
                alreadyEnabledAddButton = false;

                //for Next (first one) button
                schedNameNextButton.setEnabled(false);
                alreadyEnabledNext1Button = false;

                //for Next (second one) button
                schedDateNextButton.setEnabled(false);
                alreadyEnabledNext2Button = false;

                return true;
            }
            return false;
        }

    }
}
