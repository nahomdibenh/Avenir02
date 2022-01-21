import java.util.Objects;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Profile {

    public static GridPane userBio(User user){
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        Text name = new Text(user.getName());
        name.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        Text email = new Text(user.getEmail());

        pane.add(user.getAvatar(), 0, 0);
        pane.add(name, 1, 0);
        if (user.getEmail() != null){
            pane.add(email, 1, 1);
        }
        
        if (user.getFunder()){
            Text website = new Text(((Funder) user).URL);
            if (((Funder)user).getUrl() != null)
                pane.add(website, 1, 2);
        }
        else{
            Text skills = new Text(((Individual) user).skills);
            Text profession = new Text(((Individual) user).profession);
            if (((Individual) user).getSkills() != null)
                pane.add(skills, 1, 2);
            if (((Individual) user).getProfession() != null)
                pane.add(profession, 1, 3);
        }

        return pane;
    }

    public static BorderPane profilePage(User user){
        
        BorderPane pane = new BorderPane();
        pane.setTop(userBio(user));
        pane.setPadding(new Insets(10));
        return pane;
    }
}
