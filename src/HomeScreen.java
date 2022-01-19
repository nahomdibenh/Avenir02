import java.io.IOException;
import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class HomeScreen {
    public static Text displayPostInfo() {
        for (Post post : Post.allPosts) {
            Text t = new Text(10, 20, post.getDetails());
            return t;
        }

        return null;
    }

    public static Scene display() {

        BorderPane layout = new BorderPane();
        GridPane homeRoot = new GridPane();

        Scene homeScene = new Scene(layout, 600, 600);
        homeScene.getStylesheets().add("style.css");
        homeRoot.getStyleClass().add("test");

        Button logout = new Button("Logout");
        Button postButton = new Button("New Post");
        homeRoot.add(postButton, 1, 0);
        homeRoot.add(logout, 0, 0);

        logout.setOnAction(value -> {
            Login.loginDisplay();
        });

        postButton.setOnAction(event -> {
            App.setScene(DisplayPostForm.postForm());
        });

        layout.setBottom(homeRoot);
        layout.setCenter(displayPostInfo());
        return homeScene;
    }
}
