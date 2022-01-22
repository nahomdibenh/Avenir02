import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SignUp {
    static String labels;
    static String ids;

    static boolean userNameExists(String name){
        ArrayList<User> users;
        try {
            //get all the users
            users = DataServices.getUsers();
            //see if the argument matches a username in the database
            for (User user : users) {
                if (user.getName().equals(name)){
                    return true;
                }
            } 
        }
        catch(IOException e){
            System.out.println(e);
        }
        return false;
    }

    //check if the name or password are empty since they are needed to check if the user exists
    static boolean checkIfEmpty(Form form){
        String name = form.getFormFieldById("name").getText();
        String password = form.getFormFieldById("password").getText();
        return name.equals("") || name.equals(form.getFormFieldById("name").getPromptText()) || 
            password.equals("") || password.equals(form.getFormFieldById("password").getPromptText());

    }

    //tell if the user is either a funder or an individual
    static void initialQuestion(){
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 800, 800);
        scene.getStylesheets().add("style.css");
        root.getStyleClass().add("test");

        Button funder = new Button("Company / VC");
        funder.getStyleClass().add("menu-buttons");
        Button individual = new Button("Individual");
        individual.getStyleClass().add("menu-buttons");
        Button login = new Button("Login");
        login.getStyleClass().add("neutral-button");
        Label initialQuestion = new Label("Are you a");

        root.add(initialQuestion, 1, 0);
        root.add(funder, 0, 1);
        root.add(individual, 2, 1);
        root.add(login, 1, 2);


        GridPane homeRoot = new GridPane();
        Scene homeScene = new Scene(homeRoot, 800, 800);
        homeScene.getStylesheets().add("style.css");
        homeRoot.getStyleClass().add("test");

        //pressing individual button moves you to new scene
        individual.setOnAction(event -> {
            User.currUser = new Individual();
            labels = "Name, Email, Skills, Current Profession, Password";
            ids = "name, email, skill, job, password";
            SignUp.signUpDisplay((Individual)User.currUser);
        });

        funder.setOnAction(event -> {
            User.currUser = new Funder();
            labels = "Company Name, Email, Company URL, Password";
            ids = "name, email, url, password";
            SignUp.signUpDisplay((Funder)User.currUser);
        });
        

        login.setOnAction(value -> {
            Login.loginDisplay();
        });

        App.setScene(scene);
    }

    static void signUpDisplay(User user){
        GridPane profileRoot = new GridPane();
        Scene profileScene = new Scene(profileRoot, 800, 800);

        profileScene.getStylesheets().add("style.css");
        profileRoot.getStyleClass().add("test");

        int length = labels.split(", ").length;
        Form form = new Form(labels.split(", "), ids.split(", "), "Signup");
        form.displayFormFields(profileRoot);

        Button submit = new Button("Sign Up");
        submit.getStyleClass().add("submit-button");

        Button back = new Button("Back");
        back.getStyleClass().add("neutral-button");

        Text restriction = new Text("Username is taken");
        Text noValue = new Text("Password and Name must contain a value");
        profileRoot.add(submit, 0, length + 1);
        profileRoot.add(back, 1, length + 1);
        profileRoot.add(noValue, 0, length + 2);
        profileRoot.add(restriction, 0, length + 3);
        restriction.setVisible(false);
        noValue.setVisible(false);


        submit.setOnAction(event -> {
            //first check if the username or password fields are empty
            if(checkIfEmpty(form)){
                noValue.setVisible(true);
            }
            // if not empty then check if the username already exists
            else{
                noValue.setVisible(false);
                if(userNameExists(form.getFormFieldById("name").getText())){
                    restriction.setVisible(true);
                }
                //if the user name does not already exist, the user can be created and the sign up is complete
                else{
                    //convert user enetered data to a User instance
                    form.formToUser(user);
                    try {
                        if(user.funder){
                            //write a new funder to the database
                            DataServices.writeNewFunder((Funder)user);
                        }
                        else{
                            DataServices.writeNewIndividual((Individual)user);
                        }
                    } catch (IOException e) {
                        System.out.println("exception on write during sign-up");
                        System.out.println(e);
                    }
                    //set the current user to the one that just signed in
                    User.currUser = user;
                    App.setScene(HomeScreen.display());
                }
            }
        });

        back.setOnAction(value -> {
            initialQuestion();
        });

        App.setScene(profileScene);
    }
}
