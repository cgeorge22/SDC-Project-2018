package application;

import java.io.FileInputStream;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JDK 11, Eclipse 2019-06 https://gluonhq.com/products/javafx/ https://openjfx.io/openjfx-docs/
 *
 * @author Debra Deppeler
 */
public class Main extends Application {
  // store any command-line arguments that were entered.
  // NOTE: this.getParameters().getRaw() will get these also
  private List<String> args;

  private static final int WINDOW_WIDTH = 700;
  private static final int WINDOW_HEIGHT = 500;
  private static final String APP_TITLE = "Hello World!";



  @Override
  public void start(Stage primaryStage) throws Exception {
    // save args example
    args = this.getParameters().getRaw();

    // Create a vertical box with Hello labels for each args
    VBox vbox = new VBox();
    for (String arg : args) {
      vbox.getChildren().add(new Label("hello " + arg));
    }

    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();
    // 1. the label
    root.setTop(new Label("CS400 MyFirstJavaFX"));
    // 2. the comboBox with 3 items
    ObservableList<String> threeOptions = FXCollections.observableArrayList("1", "2", "3");
    root.setLeft(new ComboBox(threeOptions));
    // 3. the imageView of my face
    Image face = new Image(new FileInputStream("myFace.png"));
    root.setCenter(new ImageView(face));
    // 4. the button which reads "Done"
    root.setBottom(new Button("Done"));
    // 5. the right panel-- I chose a textField
    root.setRight(new TextField());


    Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

    // Add the stuff and set the primary stage
    primaryStage.setTitle(APP_TITLE);
    primaryStage.setScene(mainScene);
    primaryStage.show();
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}