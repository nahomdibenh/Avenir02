import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class SignUpDisplay {
    static public Scene companySignUp(Funder user){

        GridPane profileRoot = new GridPane();
        Scene profileScene = new Scene(profileRoot, 800, 800);

        TextField email = new TextField("Enter Email");
        TextField company = new TextField("Enter Company Name");
        TextField url = new TextField("Company URL");
        Button submit = new Button("Sign Up");
        int xVal = 2;
        profileRoot.add(company, xVal, 1);
        profileRoot.add(email, xVal, 2);
        profileRoot.add(url, xVal, 3);
        profileRoot.add(submit, xVal, 4);


        submit.setOnAction(event -> {
            
            //do this for all textfield
            user.setEmail(email.getText());
            user.setName(company.getText());
            user.setURL(url.getText());
            profileRoot.add(new Text(user.name), 0, 0);
        });

        return profileScene;
    }
    //this is the individual method for their personal window 
    static public Scene companySignUp(Individual user){

        GridPane profileRoot = new GridPane();
        Scene profileScene = new Scene(profileRoot, 800, 800);

        TextField name = new TextField("Enter Name");
        TextField email = new TextField("Enter Email");
        TextField skills = new TextField("Company Skills");
        TextField proffesion = new TextField("Current proffesion");
        Button submit = new Button("Sign Up");
        int xVal = 2;
        profileRoot.add(name, xVal, 1);
        profileRoot.add(email, xVal, 2);
        profileRoot.add(proffesion, xVal, 3);
        profileRoot.add(skills, xVal, 4);
        profileRoot.add(submit, xVal, 5);


        submit.setOnAction(event -> {
            
            //do this for all textfield
            user.setName(name.getText());
            user.setEmail(email.getText());
            user.setProfession(proffesion.getText());
            user.setSkills(skills.getText());
            profileRoot.add(new Text(user.name), 0, 0);
        });

        return profileScene;
    }
}
