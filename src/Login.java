import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Login {
    static boolean userExists(Form form){
        String name = form.getFormFieldById("name").getText();
        String password = form.getFormFieldById("password").getText();
        ArrayList<User> users;
        try {
            users = DataServices.getUsers();
            for (User user : users) {
                if(user.getName().equals(name) && user.getPassword().equals(password)){
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }

    static void loginDisplay(){
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane, 800, 800);

        scene.getStylesheets().add("style.css");
        pane.getStyleClass().add("test");

        String[] labels = {"Username", "Password"};
        String[] ids = {"name", "password"};
        Form form = new Form(labels, ids);
        form.displayFormFields(pane);

        Button loginButton = new Button("Login");
        Button signUpButton = new Button("Sign Up");
        Text incorrect = new Text("Incorrect Username or Password");
        pane.add(loginButton, 0, labels.length);
        pane.add(signUpButton, 1, labels.length);
        pane.add(incorrect, 0, labels.length + 1);
        incorrect.setVisible(false);

        loginButton.setOnAction(value -> {
            if(userExists(form)){
                App.setScene(HomeScreen.display());
            }
            else{
                incorrect.setVisible(true);;
            }
        });

        signUpButton.setOnAction(value -> {
            SignUp.initialQuestion();;
        });

        App.setScene(scene);
    }
}
