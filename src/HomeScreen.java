import java.io.IOException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class HomeScreen {
    public static Scene display(){

        BorderPane layout = new BorderPane();

        GridPane homeRoot = new GridPane();
        Scene homeScene = new Scene(layout, 800, 800);
        homeScene.getStylesheets().add("style.css");
        homeRoot.getStyleClass().add("test");

        Button logout = new Button("Logout");
        Button post = new Button("New Post");
        homeRoot.add(post, 0, 1);
        homeRoot.add(logout, 0, 0);

        logout.setOnAction(value -> {
            Login.loginDisplay();
        });

        post.setOnAction(event -> {
            App.setScene(DisplayPostForm.postForm());
        });

        layout.setBottom(homeRoot);


        return homeScene;
    }
}
