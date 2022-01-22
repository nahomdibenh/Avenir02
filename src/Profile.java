import java.util.Objects;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

//display user related data colled on sign in
public class Profile {

    public static GridPane userBio(User user){
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        Header name = new Header(user.getName());
        Text email = new Text(user.getEmail());

        pane.add(user.getAvatar(), 0, 0);
        pane.add(name.getHeader(), 1, 0);

        //only display all fields if they are not null
        if (user.getEmail() != null){
            pane.add(email, 1, 1);
        }
        
        //if user is funder, get specific data by casting 
        if (user.getFunder()){
            Text website = new Text(((Funder) user).getUrl());
            if (website != null)
                pane.add(website, 1, 2);
        }
        else{
            Text skills = new Text(((Individual) user).getSkills());
            Text profession = new Text(((Individual) user).getProfession());
            Text currPorject = new Text(((Individual) user).getCurrentProject());
            if (skills != null)
                pane.add(skills, 1, 2);
            if (profession != null)
                pane.add(profession, 1, 3);
            if (currPorject != null){
                pane.add(currPorject, 1, 4);
            }
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
