import java.io.IOException;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

public class HomeScreen {
    static BorderPane layout;

    public static void setCenter(Region pane) {
        layout.setCenter(pane);
    }
    //comment this method fully
    public static Pane displayPostInfo() {
        GridPane v2 = new GridPane();
        int counter = 0;
        for (Post post : Post.allPosts) {
            GridPane text = new GridPane();

            //this is all the texts that appear on the sides of the post
            Text payment = new Text("Payment : ");
            Text description = new Text("Description : ");
            Text details = new Text("Details : ");
            Text desiredskills = new Text("Desired skills : ");
            //this is the placement for the side titles 
            text.add(payment, 4, 3);
            text.add(description, 2, 4);
            text.add(details, 2, 5);
            text.add(desiredskills, 2, 6);

            //These are buttons that will appear at the bottom of each post
            Button upvote = new Button("Upvote");
            upvote.getStyleClass().add("neutral-button");
            //String valueof will turn the int you get from num of 
            //upvotes to a String for the new text
            Text numUpvotes = new Text(String.valueOf(post.getNumUpvotes()));
            Button savePost = new Button("Save Post");
            savePost.getStyleClass().add("neutral-button");
            Button workOn = new Button("Work On");
            workOn.getStyleClass().add("neutral-button");
            Button seeUser = new Button("See User");
            seeUser.getStyleClass().add("neutral-button");

            //the Hbox allows you to create multiple buttons on the same row and coloumn, 
            //10 mean the spacing between the buttons 
            HBox hbox = new HBox(10, numUpvotes, upvote, savePost, workOn, seeUser);
            //this adds the hbox to the screen
            text.add(hbox, 2, 7);

            seeUser.setOnAction(value -> {
                // Profile.profilePage();
            });

            // make a set action for the upvote button, when pressed increase num of upvotes
            upvote.setOnAction(event -> {

                post.setNumUpvotes(1);
                // the num of upvotes only updates after the user creates a new post,
                // and wont see the update unless they create a new post
                numUpvotes.setText(String.valueOf(post.getNumUpvotes()));

            });
            // make a set action for the save post

            savePost.setOnAction(value -> {
                // use this as a basis to get the users saved post, and add that post -
                // text.add(new Label(String.valueOf(post.getUserID())),0,0);
                ((Individual) User.currUser).setStarredpost(false, post);

            });
            // make a set action for the work on button, you just add it to
            // current projects using the individual class and user (similiar to previous
            // vbutton)
            workOn.setOnAction(value -> {
                ((Individual) User.currUser).setCurrentProject(post.getPostID());

            });
            //this will show the inputed user-specified values from the post 
            //and this shows the position of where the values will show on the screen
            text.add(new Label(post.getTitle()), 2, 3);
            //String value of converts the money inputed by the user into a string from an int, 
            //so that you can add the label, which requires a string
            text.add(new Label(String.valueOf(post.getPrizeAmount())), 5, 3);
            text.add(new Label(post.getProblemArea()), 3, 4);
            text.add(new Label(post.getDetails()), 3, 5);
            text.add(new Label(post.getDesiredSkills()), 3, 6);

            // Text detailsPost = new Text(10, 20, post.getDetails());
            // Text titlePost = new Text (10,30, post.getTitle());

            //this is the logic behind moving the post position 
            //downward after creating a new one by using
            // the counter varibale for where it is placed 
            v2.add(text, 0, counter);
            counter++;
        }

        return v2;
    }

    public static Scene display() {

        layout = new BorderPane();

        Scene homeScene = new Scene(layout, 800, 800);

        Button posts = new Button("Posts");
        posts.setMaxWidth(230);
        Button profile = new Button("Profile");
        profile.setMaxWidth(230);
        Button create = new Button("Create");
        create.setMaxWidth(230);
        Button logout = new Button("Logout");
        logout.setMaxWidth(100);
        HBox menuBar = new HBox(10, posts, profile);
        menuBar.setStyle("-fx-background-color: #000");
        menuBar.setPadding(new Insets(10));

        if (!User.currUser.getFunder()) {
            menuBar.getChildren().add(create);
        }
        menuBar.getChildren().add(logout);

        HBox.setHgrow(posts, Priority.ALWAYS);
        HBox.setHgrow(profile, Priority.ALWAYS);
        HBox.setHgrow(create, Priority.ALWAYS);

        layout.getStylesheets().add("style.css");
        posts.getStyleClass().add("menu-buttons");
        profile.getStyleClass().add("menu-buttons");
        create.getStyleClass().add("menu-buttons");
        logout.getStyleClass().add("menu-buttons");

        Button postButton = new Button("New Post");

        logout.setOnAction(value -> {
            Login.loginDisplay();
        });

        posts.setOnAction(value -> {
            layout.setTop(menuBar);
            layout.setLeft(postButton);
            layout.setCenter(displayPostInfo());
        });

        postButton.setOnAction(event -> {
            App.setScene(DisplayPostForm.postForm());
        });
        profile.setOnAction(value -> {
            layout.setLeft(null);
            layout.setCenter(Profile.profilePage(User.currUser));
        });
        create.setOnAction(value -> {
            layout.setLeft(null);
            CreatePage.problemAreaGrid();
        });

        layout.setTop(menuBar);
        layout.setLeft(postButton);
        layout.setCenter(displayPostInfo());
        return homeScene;
    }
}
