import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class DisplayPostForm {
    //refer to signupdisplay for potential view
    
    static public Scene postForm(){
        GridPane profileRoot = new GridPane();
        Scene profileScene = new Scene(profileRoot, 800, 800);
        profileScene.getStylesheets().add("style.css");
        profileRoot.getStyleClass().add("test");

        String[] labels = {"Title", "ProblemArea", "Money","DesiredProffesion"};
        String[] ids = {"title", "problemarea", "money","desiredproffesion"};
        Form form = new Form(labels, ids);
        form.displayFormFields(profileRoot);
        TextArea details = new TextArea();
        Button submit = new Button("Post");
        profileRoot.add(submit, 0, 6);
        profileRoot.add(details, 0, 7);

        Post post = new Post();
        
        submit.setOnAction(event -> {
            
            //do this for all FormField
            // user.setEmail(email.getText());
            // user.setName(company.getText());
            // user.setUrl(url.getText());
            form.actionBasedOnLabels(post);
            post.setDetails(details.getText());
            App.setScene(HomeScreen.display());
            
        });
        App.setScene(profileScene);;
        return profileScene;
    }
}

