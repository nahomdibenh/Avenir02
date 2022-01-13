import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class SignUpDisplay {
    static public Scene companySignUp(User user){

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
            user.name = company.getText();
            profileRoot.add(new Text(user.name), 0, 0);
        });

        return profileScene;
    }
}
