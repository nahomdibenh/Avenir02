import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.StringTokenizer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application{
    public static Text userName;
    public static Stage window;
    public static Scene homeScene;

    public static void main(String[] args) {
        launch(args);
    }

    public static void setScene(Scene scene){
        App.window.setScene(scene);
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException{

        window = primaryStage;

        primaryStage.setTitle("Life");

        //sets the total users value to the static variable "totalUsers" in the "User" class which is used to calculate userId
        DataServices.getUsers();

        Login.loginDisplay();


        // primaryStage.setScene(scene);
        primaryStage.show();
    }
}
