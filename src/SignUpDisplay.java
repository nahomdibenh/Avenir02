import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class SignUpDisplay {
    static public Scene companySignUp(Funder user){

        GridPane profileRoot = new GridPane();
        Scene profileScene = new Scene(profileRoot, 800, 800);

        profileScene.getStylesheets().add("style.css");
        profileRoot.getStyleClass().add("test");

        // FormField email = new FormField("Email", "email");
        // FormField company = new FormField("Company Name", "name");
        // FormField url = new FormField("Company URL", "url");
        String[] labels = {"Email", "Company Name", "Company URL"};
        String[] ids = {"email", "name", "url"};
        Form form = new Form(3, labels, ids);
        form.displayFormFields(profileRoot);

        Button submit = new Button("Sign Up");
        // profileRoot.add(company, 0, 1);
        // profileRoot.add(email, 0, 2);
        // profileRoot.add(url, 0, 3);
        profileRoot.add(submit, 0, 4);


        submit.setOnAction(event -> {
            
            //do this for all FormField
            // user.setEmail(email.getText());
            // user.setName(company.getText());
            // user.setUrl(url.getText());

            form.actionBasedOnLabels(user);
            profileRoot.add(new Text(user.name), 0, 0);
            // profileScene.setScene(App.homeScene);
        });

        return profileScene;
    }
    //this is the individual method for their personal window 
    static public Scene individualSignUp(Individual user){

        GridPane profileRoot = new GridPane();
        Scene profileScene = new Scene(profileRoot, 800, 800);
        profileScene.getStylesheets().add("style.css");
        profileRoot.getStyleClass().add("test");

        FormField name = new FormField("Name", "name");
        FormField email = new FormField("Email", "email");
        FormField skills = new FormField("Company Skills", "skill");
        FormField proffesion = new FormField("Current proffesion", "job");
        Button submit = new Button("Sign Up");

        profileRoot.add(name, 0, 1);
        profileRoot.add(email, 0, 2);
        profileRoot.add(proffesion, 0, 3);
        profileRoot.add(skills, 0, 4);
        profileRoot.add(submit, 0, 5);


        submit.setOnAction(event -> {
            
            //do this for all FormField
            user.setName(name.getText());
            user.setEmail(email.getText());
            user.setProfession(proffesion.getText());
            user.setSkills(skills.getText());
            profileRoot.add(new Text(user.name), 0, 0);
        });

        return profileScene;
    }
}
