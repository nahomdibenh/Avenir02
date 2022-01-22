import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

//saving work for creating forms
public class Form {
    protected FormField[] formFields;
    protected Text header;
    //possible other ui objects: choicebox, passwordfield, heading, buttons

    public Form(String[] formFieldLabels, String[] formFieldIds, String header){
        this.header = new Text(header);
        this.header.setFont(Font.font("Arail", FontWeight.BOLD, 30));
        createFormFields(formFieldLabels, formFieldIds);
    }

    //create an array of text fields by giving an array of labels and ids/descriptors for an onSetAction methid
    public void createFormFields(String[] labels, String[] ids){
        int numFields = labels.length;
        this.formFields = new FormField[numFields];
        for (int i = 0; i < numFields; i++) {
            formFields[i] = new FormField(labels[i], ids[i]);
        }
    }

    //loops through all formfields in a given form and displays them using the display method belonging to each formfield instance
    public void displayFormFields(GridPane pane){
        pane.add(this.header, 0, 0);
        for (int i = 0; i < formFields.length; i++) {
            formFields[i].getStyleClass().add("text-field");
            formFields[i].displayFormField(pane, i + 1);
        }
    }

    //loops through all formfields in a given form and determines what should be done on submit for each field based on the field labels
    public void formToUser(User user){
        for (FormField formField : formFields) {
            formField.textFieldToUser(user);
        }
    }
    //formfields for posts
    public void formToPost(Post post){
        for (FormField formField : formFields) {
            formField.textFieldToPost(post);
        }
    }
    //store all of a form's text field data to a ProblemArea object
    public void formToProblemArea(ProblemArea problem){
        for (FormField formField : formFields) {
            formField.textFieldToProblemArea(problem);
        }
    }

    public void formToContent(UserContent content){
        for (FormField formField : formFields) {
            formField.textFieldToContent(content);
        }
    }

    //used the specified id on instantiation to get a specific formfield where further data can be extracted
    public FormField getFormFieldById(String id){
        for (int i = 0; i < formFields.length; i++) {
            if (formFields[i].id.equals(id)){
                return formFields[i];
            }
        }
        return null;
    }
}
