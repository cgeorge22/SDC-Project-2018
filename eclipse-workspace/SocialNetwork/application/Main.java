//////////////////////////////// FILE HEADER //////////////////////////////////
//
// Project Name: Social Network
// Name: Chris George, Shlok Patel, Ali Adam, Eric Tian, Michael He
// Email: crgeorge@wisc.edu, sppatel4@wisc.edu, aadam4@wisc.edu,
// eytian@wisc.edu, mzhe@wisc.edu
// Lecture Number: 001, 002
// Due Date: 12/11/19
// Description: Main Class-- GUI funcionality
//
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * GUI functionality for the Social Network application
 * 
 * @author Chris George, Shlok Patel, Ali Adam, Eric Tian, Michael He
 *
 */
public class Main extends Application {

  SocialNetwork sn = new SocialNetwork();

  private Stage stage = new Stage();
  private static final int WINDOW_WIDTH = 1400;
  private static final int WINDOW_HEIGHT = 700;
  private static final String APP_TITLE = "Social Network";
  public VBox signUpBox = new VBox();
  public VBox twoInputBox = new VBox();
  public VBox centerBox = new VBox();
  public VBox bottomBox = new VBox();
  public HBox topBox = new HBox();
  private BorderPane root = new BorderPane();

  /**
   * setup for the stage to display the GUI
   * 
   * @param primaryStage stage to show in the GUI
   * @throws Exception if setup cannot be executed properly for any reason
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    // set up all sides of the GUI
    setUpCenterBox();
    setUpTopBox();
    setUpSignUpBox();
    setUpRightBox();
    setUpBottomBox("", "");

    Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    root.setStyle("-fx-background-color: #9e9eff;"); // set background color for GUI

    primaryStage.setTitle(APP_TITLE);
    primaryStage.setScene(mainScene);
    this.stage = primaryStage;
    stage.show();
  }

  /**
   * contains the items in the middle of the stage-- the user buttons and the friendsList when the
   * user button is clicked, its friends list is displayed
   */
  public void setUpCenterBox() {
    HBox hbox1 = new HBox();
    VBox friendsList = new VBox();
    HBox hbox2 = new HBox();

    int componentCount = 1;
    for (Graph g : sn.getConnectedComponents()) { // index through all the nodes
      VBox component = new VBox();
      component.getChildren().add(new Label("    Component " + componentCount + ":    "));
      for (Person u : g.getAllVertices()) { // index through all the nodes on each connected comp.
        Button user = new Button(u.getName()); // create a button for each user
        component.getChildren().add(user);
        component.setAlignment(Pos.CENTER);
        user.setOnMouseClicked(e -> showFriendsList(user, friendsList));
      }
      hbox2.getChildren().add(component);
      componentCount++;
    }
    // add everything to the display box
    hbox1.getChildren().add(
        new Label("Total users: " + sn.graph.order() + "\nTotal friendships: " + sn.graph.size()));
    hbox1.getChildren().add(hbox2);
    hbox1.getChildren().add(friendsList);

    root.setCenter(hbox1); // display goes in the center of the BorderPane
    // set formatting
    hbox2.setAlignment(Pos.CENTER);
    hbox1.setAlignment(Pos.CENTER);
    hbox2.setStyle("-fx-font-weight: bold");
    hbox1.setStyle("-fx-font-weight: bold");
  }

  /**
   * displays the list of friends for the user whose button is clicked
   * 
   * @param button - the user button that was clicked
   * @param vbox - display box to display the friends in
   *
   */
  private void showFriendsList(Button button, VBox vbox) {
    sn.central = sn.graph.getNode(button.getText()); // set central user
    vbox.getChildren().clear(); // clears out previous friends list
    vbox.getChildren().add(new Label("Central user: " + button.getText()));
    vbox.getChildren().add(new Label("Friends: "));
    ArrayList<Person> friendsList = new ArrayList<Person>();
    friendsList.addAll(sn.getFriends(button.getText()));

    for (int i = 0; i < friendsList.size(); i++) {
      Button friend = new Button(friendsList.get(i).getName()); // add all friends as buttons
      vbox.getChildren().add(friend);
      friend.setOnAction(e -> showFriendsList(friend, vbox)); // each friend button is usable
    }
    vbox.setAlignment(Pos.CENTER);
  }

  /**
   * Initializes buttons and adds them to the box in the top pane, also initializes event handlers
   * for when the buttons are clicked on
   */
  public void setUpTopBox() {
    // VBox for organizing buttons
    VBox vbox = new VBox();

    // add Label to the top
    Label title = new Label("SOCIAL NETWORK");
    title.setFont(new Font("Impact", 80));
    BorderPane.setAlignment(title, Pos.CENTER);

    // add clear button that calls clear method
    Button clear = new Button("Clear");
    clear.setOnMouseClicked(e -> clear(clear));

    // add load button that loads a new file
    Button load = new Button("Load data file");
    Stage loadStage = loadDataFile();
    load.setOnMouseClicked(e -> loadStage.show());

    // add export button that creates and exports a data file
    Button export = new Button("Export");
    Stage exportStage = setExportStage();
    export.setOnMouseClicked(e -> exportStage.show());

    // add exit button that prompts user to save or exit
    Button exit = new Button("Exit");
    Stage newStage = setExitStage();
    exit.setOnMouseClicked(e -> newStage.show());

    // add the buttons to a horizontal box
    topBox.getChildren().addAll(clear, load, export, exit);

    topBox.setAlignment(Pos.CENTER);
    topBox.setStyle("-fx-font-weight: bold");

    Label instructions =
        new Label("\nClick on a User to set as Central User and to see their friends");
    instructions.setFont(new Font("Verdana", 18));
    vbox.getChildren().addAll(topBox, title, instructions);
    vbox.setAlignment(Pos.CENTER);

    // add the organizational vbox to the top pane of the root
    root.setTop(vbox);
  }

  /**
   * Clears the current social network and updates the display
   * 
   * @param button that was pressed
   */
  private void clear(Button button) {
    if (button.getText().equals("Clear")) {
      // assign sn to a new instance of SocialNetwork
      sn = new SocialNetwork();
      // update the status and refresh center box
      setUpBottomBox("Social network cleared.", "");
      setUpCenterBox();
    }
  }

  /**
   * Method that creates a new window for the export button
   * 
   * @return Stage
   */
  private Stage setExportStage() {
    // creates new stage and vbox and hbox to hold elements
    Stage exportStage = new Stage();
    VBox vbox = new VBox();
    HBox hbox = new HBox();

    // initializes text field element as well as button
    TextField fileName = new TextField();
    Button export = new Button("Export");
    // adds elements to their respective hbox and vbox and
    // styles them
    hbox.getChildren().add(new Label("Name the file: "));
    hbox.getChildren().add(fileName);
    hbox.setAlignment(Pos.CENTER);
    vbox.getChildren().addAll(hbox, export);
    vbox.setAlignment(Pos.CENTER);
    vbox.setSpacing(10);
    // handles action when button is clicked
    export.setOnMouseClicked(e -> exportSave(exportStage, fileName));

    // initializes and sets size of window
    Scene newScene = new Scene(vbox, 300, 100);
    exportStage.setScene(newScene);
    return exportStage;
  }

  /**
   * Private helper method to handle the action for the export button
   * 
   * @param exportStage
   * @param fileName
   */
  private void exportSave(Stage exportStage, TextField fileName) {
    // initializes a new file with the user file name that was entered
    File file = new File(fileName.getText());
    // handles exception thrown if file name is incorrect
    try {
      sn.saveToFile(file);
    } catch (FileNotFoundException e) {
      // updates status field with error message
      setUpBottomBox("Enter a valid file name", "");
      return;
    }
    // closes window and updates status when file is exported
    exportStage.close();
    setUpBottomBox("Exported current social network to: " + fileName.getText(), "");
  }

  /**
   * This method creates the screen that appears when the user clicks the "Load data file" button
   * and handles saving and exiting and exit without saving actions.
   * 
   * @return newStage , The screen that should pop up
   */
  private Stage setExitStage() {
    Stage newStage = new Stage(); // The stage to be returned
    VBox vbox = new VBox();
    HBox hbox1 = new HBox(); // The first row of the stage
    hbox1.setAlignment(Pos.CENTER);
    // Adds the content to the top row
    hbox1.getChildren().add(new Label("You can either 'Save and Exit' or 'Exit without saving'"));
    HBox hbox2 = new HBox(); // The second row of the stage
    hbox2.setAlignment(Pos.CENTER);
    Button button1 = new Button("Save and Exit");
    TextField textField = new TextField();
    // Adds the content to the middle row
    hbox2.getChildren().addAll(new Label("Name the file: "), textField, button1);
    hbox2.setSpacing(5);
    HBox hbox3 = new HBox(); // The third row of the stage
    Button button2 = new Button("Exit without saving");
    hbox3.getChildren().add(button2); // Adds the content to the third row
    hbox3.setAlignment(Pos.CENTER);
    vbox.getChildren().addAll(hbox1, hbox2, hbox3); // Adds all the rows to a box
    // Formatting the box of rows
    vbox.setAlignment(Pos.CENTER);
    vbox.setSpacing(10);
    // Creates the scene with all the rows and sizes it
    Scene newScene = new Scene(vbox, 400, 100);
    // Creates the stage with all the content from the scene
    newStage.setScene(newScene);
    // On the "Save and Exit" button click, calls the method to perform the buttons actions
    button1.setOnMouseClicked(e -> saveAndExit(newStage, textField));
    // On the "Exit without saving" button click, calls the method to perform the buttons actions
    button2.setOnMouseClicked(e -> exit(newStage));
    return newStage;
  }

  /**
   * This method handles the case of the "Save and Exit" button being clicked by closing the exit
   * window and social network screen. This also saves the current graph into a text file that could
   * recreate it when loaded back.
   * 
   * @param newStage , screen to be closed
   * @param filename , the name of the file to save to
   */
  private void saveAndExit(Stage newStage, TextField filename) {
    File file = new File(filename.getText()); // File to be saved to
    // Handles the file name not being valid.
    try {
      sn.saveToFile(file);
    } catch (FileNotFoundException e) {
      setUpBottomBox("Enter a valid file name", "");
      return;
    }
    // Closes the exit screen and overall social network screen
    newStage.close();
    stage.close();
  }

  /**
   * The method closes both the exit screen and social network screen.
   * 
   * @param newStage , the exit screen to be closed
   */
  private void exit(Stage newStage) {
    newStage.close();
    stage.close();
  }

  /**
   * This method creates the screen for loading a data file and performs all actions of button.
   * 
   * @return newStage , The screen that should pop up
   */
  private Stage loadDataFile() {
    Stage newStage = new Stage(); // The screen to be returned
    // All the contents of the screen
    HBox hbox = new HBox();
    hbox.setAlignment(Pos.CENTER);
    hbox.setSpacing(3);
    TextField textField = new TextField();
    Button button = new Button("Load Data");
    // Adding all the contents to a box
    hbox.getChildren().addAll(new Label("Enter Name of File: "), textField, button);
    // Creating the scene from the contents and sizing it
    Scene newScene = new Scene(hbox, 400, 100);
    newStage.setScene(newScene);
    // On the "Load Data" button click, calls the method to perform the buttons actions
    button.setOnMouseClicked(e -> loadData(newStage, textField));
    return newStage;
  }

  /**
   * This method actually loads the data to the social network and handles invalid inputs
   * 
   * @param newStage , The load data screen
   * @param filename , The name of the file to load from
   */
  private void loadData(Stage newStage, TextField filename) {
    // The file created from using the name passed in
    File file = new File(filename.getText());
    // Handles the file not being found or the file containing invalid input
    try {
      sn.loadFromFile(file);
    } catch (FileNotFoundException e) {
      setUpBottomBox("Enter a valid file name", "");
      return;
    } catch (IllegalArgumentException e) {
      setUpBottomBox("Enter a valid file format", "");
      return;
    }
    // Updates the graph and status
    setUpCenterBox();
    setUpBottomBox("Loaded social network from: " + filename.getText(), "");
    newStage.close();
  }


  /**
   * Displays a status label at the bottom of the GUI that indicates the most recent action
   * performed by the user
   *
   * @param status message based on user action
   * @param networkInfo informative message about action
   */
  public void setUpBottomBox(String status, String networkInfo) {

    // Creates new Vbox for right pane
    VBox vbox = new VBox();

    // Creates label for informative message with font and size
    Label networkInfoPane = new Label(networkInfo);
    networkInfoPane.setFont(new Font("Verdana", 30));

    // Creates a status label with font and size
    Label statusBar = new Label("Status: " + status);
    statusBar.setFont(new Font("Verdana", 18));

    // Adds both labels to Vbox
    vbox.getChildren().add(networkInfoPane);
    vbox.getChildren().add(statusBar);

    // Sets the alignment and sets entire bottom pane to the Vbox
    vbox.setAlignment(Pos.TOP_LEFT);
    root.setBottom(vbox);

  }

  /**
   * Displays interface on left side of GUI to add/remove users
   */
  public void setUpSignUpBox() {
    // Creates boxes representing add/remove actions
    VBox vbox = new VBox();
    HBox hbox = new HBox();
    Button button = new Button("Add New User");
    Button removeButton = new Button("Remove User");

    // Creates text field for adding a new user
    TextField input = new TextField();
    input.setPrefWidth(100);

    // prepends a label to the text field within the hbox
    // adds the hbox to the available buttons in the vbox
    hbox.getChildren().addAll(new Label("Username: "), input);
    vbox.getChildren().addAll(hbox, button, removeButton);

    // Aligns the contents in the center of the box
    vbox.setAlignment(Pos.CENTER);
    hbox.setPadding(new Insets(10, 10, 10, 10));
    root.setLeft(vbox);
    vbox.setStyle("-fx-font-weight: bold");
    hbox.setStyle("-fx-font-weight: bold");

    // lambda expressions that call the helper methods when button is clicked
    button.setOnMouseClicked(e -> addNewUser(button, input));
    removeButton.setOnMouseClicked(e -> removeNewUser(removeButton, input));
  }

  /**
   * adds a new user when button is pressed
   * 
   * @param button that is pressed
   * @param textfield input
   */
  private void addNewUser(Button button, TextField input) {
    if (button.getText().equals("Add New User")) {
      // if button is pressed, and user was successfully added, update status label
      if (sn.addUser(input.getText()))
        setUpBottomBox("New user '" + input.getText() + "' was added to the network.", "");
      else
        // if user was already in social network, display error message
        setUpBottomBox("Invalid - user is already in the network.", "");
      setUpCenterBox();
    }
  }

  /**
   * adds a new user when button is pressed
   * 
   * @param button that is pressed
   * @param textfield input
   */
  private void removeNewUser(Button button, TextField input) {
    if (button.getText().equals("Remove User")) {
      // if removeNewUser button is pressed, and is successfully removed, update
      // status, otherwise, display invalid input message
      boolean complete = sn.removeUser(input.getText());
      if (complete) {
        setUpBottomBox("User '" + input.getText() + "' removed", "");
      } else {
        setUpBottomBox("User '" + input.getText() + "' does not exist", "");
      }
      // updates social visual in center
      setUpCenterBox();
    }
  }

  /**
   * Private helper method that sets up the VBox that allows the user to enter the name of two
   * people and add a friendship between them
   *
   */
  public void setUpRightBox() {
    // Friendship text fields and buttons
    HBox hbox = new HBox();
    HBox hbox2 = new HBox();
    hbox.setPadding(new Insets(0, 10, 10, 0));
    hbox2.setPadding(new Insets(0, 10, 10, 0));

    // Mutual Friends text fields and buttons
    HBox hbox3 = new HBox();
    HBox hbox4 = new HBox();
    hbox3.setPadding(new Insets(0, 10, 10, 0));
    hbox4.setPadding(new Insets(0, 10, 10, 0));

    // creates text fields for friendships
    TextField t1 = new TextField();
    TextField t2 = new TextField();
    t1.setPrefWidth(100);
    t2.setPrefWidth(100);

    // adds label and text field to it's respective hbox
    hbox.getChildren().addAll(new Label("Person One: "), t1);
    hbox2.getChildren().addAll(new Label("Person Two: "), t2);

    // creates buttons to add, remove, show mutual, and get shortest path between
    // friends
    Button addF = new Button("Add Friendship");
    Button removeF = new Button("Remove Friendship");
    Button mutualF = new Button("Show Mutual Friends");
    Button shortest = new Button("Find Shortest Path");
    addF.setAlignment(Pos.BASELINE_RIGHT);

    // adds user text fields, buttons, and actions to a vbox
    twoInputBox.getChildren().addAll(hbox, hbox2, addF, removeF, mutualF, shortest);
    twoInputBox.setAlignment(Pos.CENTER);

    // Bolds test and sets the combined Vbox to right side
    twoInputBox.setStyle("-fx-font-weight: bold");
    root.setRight(twoInputBox);

    // Lambda expressions that call helper methods when buttons are pushed
    addF.setOnMouseClicked(e -> addFriendship(addF, t1, t2));
    removeF.setOnMouseClicked(e -> removeFriendship(removeF, t1, t2));
    mutualF.setOnMouseClicked(e -> getMutualFriends(mutualF, t1, t2));
    shortest.setOnMouseClicked(e -> showShortestPath(shortest, t1, t2));
  }

  /**
   * Private helper method that adds a friendship between users and updates status message
   * 
   * @param button that was pressed
   * @param user1 first user
   * @param user2 second user
   */
  private void addFriendship(Button button, TextField user1, TextField user2) {
    if (button.getText().equals("Add Friendship")) {
      // if button was pressed and friendship was successfully added, update status.
      // Otherwise, display invalid input message
      if (sn.addFriends(user1.getText(), user2.getText()))
        setUpBottomBox(
            "Friendship added between '" + user1.getText() + "' and '" + user2.getText() + "'", "");
      else
        setUpBottomBox("Invalid input - friendship already exists", "");

      // update social visual in the center
      setUpCenterBox();
    }
  }

  /**
   * Private helper method that removes a friendship between users and updates status message
   * 
   * @param button that was pressed
   * @param user1 first user
   * @param user2 second user
   */
  private void removeFriendship(Button button, TextField user1, TextField user2) {
    if (button.getText().equals("Remove Friendship")) {
      // if button was pressed and friendship was successfully removed, update status.
      // Otherwise, display invalid input message
      boolean complete = sn.removeFriends(user1.getText(), user2.getText());
      if (complete) {
        setUpBottomBox(
            "Friendship between '" + user1.getText() + "' and '" + user2.getText() + "' removed",
            "");
      } else {
        setUpBottomBox("Invalid input - make sure both users exist and are friends.", "");
      }

      // update social visual in the center
      setUpCenterBox();
    }
  }

  /**
   * Get the list of mutual friends between 2 users and update the bottom box
   * 
   * @param button that was pressed
   * @param user1 input field
   * @param user2 input field
   */
  private void getMutualFriends(Button button, TextField user1, TextField user2) {
    if (button.getText().equals("Show Mutual Friends")) {
      // create a new set of mutual friends between 2 users
      Set<Person> mutualList = sn.getMutualFriends(user1.getText(), user2.getText());
      String s = "List of mutual friends between '" + user1.getText() + "' and '" + user2.getText()
          + "': ";
      if (mutualList.size() == 0)
        s = "No mutual friends.";
      else {
        // append the name of the mutual friend to the string
        for (Person u : mutualList)
          s += u.getName() + ", ";
        // trim off the last comma
        s = s.substring(0, s.length() - 2);
      }
      // update the bottom box and reload the display
      setUpBottomBox("Completed.", s);
      setUpCenterBox();
    }
  }

  /**
   * displays the shortest path of friends between any two connected users in the network
   * 
   * @param button the shortestPath button in the GUI that was clicked
   * @param user1 - TextField of the first user
   * @param user2 - TextField of the second user
   */
  private void showShortestPath(Button button, TextField user1, TextField user2) {
    // text that appears before the shortest path
    String blankStatus =
        "Shortest Path between " + user1.getText() + " and " + user2.getText() + ": ";
    String status = blankStatus;
    List<Person> shortestList = sn.getShortestPath(user1.getText(), user2.getText());

    for (Person i : shortestList) { // output the shortest list as text
      status = status + i.getName() + "->";
    }
    if (status.equals(blankStatus)) { // if no path was output by the method
      setUpBottomBox("Completed.",
          "Error: no path between users " + user1.getText() + " and " + user2.getText());
    } else { // show the path in the status
      status = status.substring(0, status.length() - 2); // truncate the last arrow
      setUpBottomBox("Completed.", status);
    }
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    launch(args);

  }

}
