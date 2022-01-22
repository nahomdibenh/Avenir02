import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


public class Login {
    static User userExists(Form form){
        String name = form.getFormFieldById("name").getText();
        String password = form.getFormFieldById("password").getText();
        ArrayList<User> users;
        try {
            //check if the user exists in the text file by comparing the username and password
            users = DataServices.getUsers();
            for (User user : users) {
                if(user.getName().equals(name) && user.getPassword().equals(password)){
                    return user;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    static void loginDisplay(){
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane, 800, 800);

        scene.getStylesheets().add("style.css");
        pane.getStyleClass().add("test");

        String[] labels = {"Username", "Password"};
        String[] ids = {"name", "password"};
        Form form = new Form(labels, ids, "Login");
        form.displayFormFields(pane);

        Button loginButton = new Button("Login");
        loginButton.getStyleClass().add("submit-button");

        Button signUpButton = new Button("Sign Up");
        signUpButton.getStyleClass().add("neutral-button");
        Text incorrect = new Text("Incorrect Username or Password");
        pane.add(loginButton, 0, labels.length + 1);
        pane.add(signUpButton, 1, labels.length + 1);
        pane.add(incorrect, 0, labels.length + 2);
        pane.add(new Text("Note: Only Login information and Problem Areas will be saved"), 0, labels.length + 3);
        incorrect.setVisible(false);

        loginButton.setOnAction(value -> {
            if(userExists(form) != null){
                //set the current user once logged in and the method will not return null
                User.currUser = userExists(form);
                //set the scene to the Home Screen
                App.setScene(HomeScreen.display());
            }
            else{
                incorrect.setVisible(true);
            }
        });

        //Direct to different pane for sign up
        signUpButton.setOnAction(value -> {
            SignUp.initialQuestion();
        });

        App.setScene(scene);
    }
}
