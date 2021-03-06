import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application{
    public static Text userName;
    public static Stage window;
    public static Scene homeScene;

    public static void main(String[] args) {
        launch(args);
    }

    //helper function to easily adjust the scene from different files
    public static void setScene(Scene scene){
        App.window.setScene(scene);
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException{

        //aliasing
        window = primaryStage;

        primaryStage.setTitle("Life");

        //sets the total users value to the static variable "totalUsers" in the "User" class which is used to calculate userId
        DataServices.getUsers();
        //displays any previously stored posts

        //displays the first Pane on scene, a login screen
        Login.loginDisplay();


        primaryStage.show();
    }
}
