import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class DisplayPostForm {//comment this whole file
    // refer to signupdisplay for potential view
    //This method checks if the input in the textfield of the 
    //money section when creating post is an int
    private static boolean moneyChecker(Form form) {
        //this line gets the text inputted in the money section of the post creation page,
        //and sets the text to the variable String money
        String money = form.getFormFieldById("money").getText();
        try {

            Integer.parseInt(money);
            return true;
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        return false;
    }
    private static boolean nameIsValid(Form form){
        return !form.getFormFieldById("title").getText().isEmpty();
    }


    //
    static public Scene postForm(){
        GridPane profileRoot = new GridPane();
        Scene profileScene = new Scene(profileRoot, 800, 800);
        profileScene.getStylesheets().add("style.css");
        profileRoot.getStyleClass().add("test");

        
        //stores text that will appear inside the textfields
        String[] labels = {"Title", "Problem Area", "Money","Desired Skills"};
        //developer specicfied ids 
        //stores the ids for each textfield that will be later used to get the
        //input from each textfield after submit button is pressed.
        String[] ids = {"title", "problemarea", "money","desiredskills"};
        //create formfields with specified text, ids, and headings
        Form form = new Form(labels, ids, "New Post");
        //This adds the newly created formfield onto the gridpane
        form.displayFormFields(profileRoot);

        TextArea details = new TextArea();
        Button submit = new Button("Post");
        submit.getStyleClass().add("submit-button");

        //these 2 lines add the specified buttons below the position of the textfields
        profileRoot.add(details, 0, labels.length + 1);
        profileRoot.add(submit, 0, labels.length + 2);

        Post post = new Post();

        submit.setOnAction(event -> {
            
            //if sttatment that checks if input is int, if not print "error"
            //checks if the input on textfield money is an int
            // and if title has been given
            if (moneyChecker(form) && nameIsValid(form)){
                //converts the textfield user input to an post object 
                form.formToPost(post);
                //because details is an text area and isn't part of the formfield, it needs to be added seperatly
                post.setDetails(details.getText().isEmpty() ? null : details.getText());
                
                //changes scene
                App.setScene(HomeScreen.display());
            }
            else{
                
                Text moneyError = new Text("Money Should Be A Number and Title Must Be Included");
                //prints the error message 3 rows below the textfields
                //(because the detials and submit button are on top of it)
                profileRoot.add(moneyError, 0, labels.length + 3);
            }
            
            post.setUserID(User.currUser.getUserId());
            
            
        });
        App.setScene(profileScene);
        return profileScene;
    }
}
