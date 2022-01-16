import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class DisplayPostForm {
    //refer to signupdisplay for potential view 
    static public Scene postForm(){
        GridPane profileRoot = new GridPane();
        Scene profileScene = new Scene(profileRoot, 800, 800);

        //make text field 
        //post title 
        //problem Area
        //money
        //desired Proffesion
        //details(specifics)

        //Form title = new Form
        profileScene.getStylesheets().add("style.css");
        profileRoot.getStyleClass().add("test");

        // FormField email = new FormField("Email", "email");
        // FormField company = new FormField("Company Name", "name");
        // FormField url = new FormField("Company URL", "url");
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
            
        });
        return profileScene;
    }
}

