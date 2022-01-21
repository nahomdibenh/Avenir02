import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class FormField extends TextField {
    protected String id = "";

    public FormField(String label, String id) {
        super(label);
        this.id = id;
        this.setPromptText(label);
    }

    // decide onSubmit action based on id of a formfield for sign in and sign up
    public void textFieldToUser(User user) {
        // id is identifier for the formfield, not the user
        String id = this.id;
        String response = this.getText().equals("") ? null
                : this.getText().equals(this.getPromptText()) ? null : this.getText();

        // both funders and users
        if (id.equals("name")) {
            user.setName(response);
        }
        if (id.equals("email")) {
            user.setEmail(response);
        }
        if (id.equals("password")) {
            user.setPassword(response);
        }

        // individual only (fields that will only be set by a formfield)
        if (id.equals("skills")) {
            ((Individual) user).setSkills(response);
        }

        if (id.equals("profession")) {
            ((Individual) user).setProfession(response);
        }

        // funders only
        if (id.equals("url")) {
            ((Funder) user).setUrl(response);
        }

    }
    // comment this method

    public void textFieldToPost(Post post) {
        // this line gets the id of the textfield
        String id = this.id;
        // gets the text inputted from the post
        String response = this.getText();
        // This method simply gets the input from the currentuser when creating a new
        // post and
        // sets those inputs to the respective setter method based on specified id.
        if (id.equals("title")) {
            post.setTitle(response);
        }
        if (id.equals("problemarea")) {
            post.setProblemArea(response);
            System.out.println(1);
        }
        if (id.equals("money")) {
            post.setPrizeAmount(Integer.parseInt(response));//
        }
        if (id.equals("desiredskills")) {
            post.setDesiredSkills(response);
        }

    }

    public void textFieldToProblemArea(ProblemArea problem) {
        String id = this.id;
        String response = this.getText();

        // both funders and users
        if (id.equals("name")) {
            problem.setName(response);
        }
        if (id.equals("impact")) {
            problem.setImpact(response);
        }
        if (id.equals("cause")) {
            problem.setRootCause(response);//
        }
    }

    // display textfield
    public void displayFormField(GridPane pane, int row) {
        pane.add(this, 0, row);
    }
}
