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
    public static Stage window;
    public static Stage primaryStage;
    public static Scene homeScene;
    public static void main(String[] args) {
        launch(args);
    }

    public static void setScene(Scene scene){
        App.window.setScene(scene);
    }
    
    @Override
    public void start(Stage primaryStage){

        window = primaryStage;

        primaryStage.setTitle("Life");
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 800, 800);
        scene.getStylesheets().add("style.css");
        root.getStyleClass().add("test");

        Button funder = new Button("Company / VC");
        Button individual = new Button("Individual");
        Button newPost = new Button("New Post");
        Label initialQuestion = new Label("Are you a");

        root.add(initialQuestion, 1, 0);
        root.add(funder, 0, 1);
        root.add(newPost, 1, 1);
        root.add(individual, 2, 1);


        GridPane homeRoot = new GridPane();
        Scene homeScene = new Scene(homeRoot, 800, 800);
        homeScene.getStylesheets().add("style.css");
        homeRoot.getStyleClass().add("test");

        //this is the new input

        // GridPane postRoot = new GridPane();
        // Scene postScene = new Scene(postRoot, 800, 800);

        // //Form title = new Form
        // postScene.getStylesheets().add("style.css");
        // postRoot.getStyleClass().add("test");

        
        // String[] labels = {"Title", "ProblemArea", "Money","DesiredProffesion", "Details"};
        // String[] ids = {"title", "problemarea", "money","desiredproffesion","details"};
        // Form form = new Form(5, labels, ids);
        // form.displayFormFields(postRoot);

        // Button submit = new Button("Post");
        // postRoot.add(submit, 0, 6);

        // submit.setOnAction(event -> {
            
        //     //d
        // });

        //pressing individual button moves you to new scene
        individual.setOnAction(event -> {
            user = new Individual();
            SignUpDisplay.individualSignUp((Individual)user);
            // primaryStage.setScene(postScene);
        });

        funder.setOnAction(event -> {
            user = new Funder();
            SignUpDisplay.companySignUp((Funder)user);
            // primaryStage.setScene(postScene);
        });
        newPost.setOnAction(event -> {
            // user = new Funder();
            primaryStage.setScene(DisplayPostForm.postForm());
            user = new Funder();
            DisplayPostForm.postForm();
            // primaryStage.setScene(postScene);
        });


        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
