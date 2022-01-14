import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application{
    public static User user;
    public static Text userName;
    public static Stage primaryStage;
    public static void main(String[] args) {
        launch(args);
    }

    public static void setScene(){

    }
    
    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Life");
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 800, 800);
        scene.getStylesheets().add("style.css");
        root.getStyleClass().add("test");

        Button funder = new Button("Company / VC");
        Button individual = new Button("Individual");
        Label initialQuestion = new Label("Are you a");

        root.add(initialQuestion, 1, 0);
        root.add(funder, 0, 1);
        root.add(individual, 2, 1);

        //pressing individual button moves you to new scene
        individual.setOnAction(event -> {
            user = new Individual();
            primaryStage.setScene(SignUpDisplay.individualSignUp((Individual)user));
        });

        funder.setOnAction(event -> {
            user = new Funder();
            primaryStage.setScene(SignUpDisplay.companySignUp((Funder)user));
        });


        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
