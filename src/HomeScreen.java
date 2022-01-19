import java.io.IOException;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

public class HomeScreen {
    static BorderPane layout;

    public static void setCenter(Region pane){
        layout.setCenter(pane);
    }

    public static Text displayPostInfo() {
        for (Post post : Post.allPosts) {
            Text t = new Text(10, 20, post.getDetails());
            return t;
        }

        return null;
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

        if(!User.currUser.getFunder()){
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
