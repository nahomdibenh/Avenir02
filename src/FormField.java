import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class FormField extends TextField{
    protected String id = "";

    public FormField(String label, String id){
        super(label);
        this.id = id;
    }

    //decide onSubmit action based on id of a formfield
    public void actionBasedOnLabel(User user){
        String id = this.id;
        String response = this.getText();

        //both funders and users
        if (id.equals("name")){
            user.setName(response);
        }
        if (id.equals("email")){
            user.setEmail(response);
        }

        //individual only (fields that will only be set by a formfield)
        if (id.equals("skills")){
            ((Individual) user).setSkills(response);
        }

        if (id.equals("profession")){
            ((Individual) user).setProfession(response);
        }

        //funders only
        if (id.equals("url")){
            ((Funder)user).setUrl(response);
        }
    
    }

    //display textfield
    public void displayFormField(GridPane pane, int row){
        pane.add(this, 0, row);
    }
}