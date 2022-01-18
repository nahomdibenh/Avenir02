import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class DisplayPostForm {
    //refer to signupdisplay for potential view 
    static public Scene homeScreen(){
        //a way to access data in both methdos (better)

        GridPane homeRoot = new GridPane();
        Scene homeScene = new Scene(homeRoot, 400, 700);
        homeScene.getStylesheets().add("style.css");
        homeRoot.getStyleClass().add("test");

        Button submit = new Button("Post");
        // String postTitle = post.getTitle();
        Text postTitle = new Text (10, 20, "This is a text sample");
        homeRoot.add(submit, 0, 6);
        homeRoot.add(postTitle, 2, 7);

        return homeScene;
    }
    
    static public Scene postForm(){
        GridPane profileRoot = new GridPane();
        Scene profileScene = new Scene(profileRoot, 800, 800);
        profileScene.getStylesheets().add("style.css");
        profileRoot.getStyleClass().add("test");

        String[] labels = {"Title", "ProblemArea", "Money","DesiredProffesion", "Details"};
        String[] ids = {"title", "problemarea", "money","desiredproffesion","details"};
        Form form = new Form(5, labels, ids);
        form.displayFormFields(profileRoot);

        Button submit = new Button("Post");
        profileRoot.add(submit, 0, 6);

        submit.setOnAction(event -> {
            
            //do this for all FormField
            // user.setEmail(email.getText());
            // user.setName(company.getText());
            // user.setUrl(url.getText());
            Post post = new Post();
            // form.
            App.setScene(DisplayPostForm.homeScreen());
        });
        App.setScene(profileScene);;
        return profileScene;
    }
}

