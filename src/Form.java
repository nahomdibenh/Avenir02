import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Form {
    public FormField[] formFields;
    //possible ui objects: choicebox, passwordfield, heading, buttons

    public Form(String[] formFieldLabels, String[] formFieldIds){
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
        for (int i = 0; i < formFields.length; i++) {
            formFields[i].displayFormField(pane, i);
        }
    }

    //loops through all formfields in a given form and determines what should be done on submit for each field based on the field labels
    public void actionBasedOnLabels(User user){
        for (FormField formField : formFields) {
            formField.actionBasedOnLabel(user);
        }
    }

    public FormField getFormFieldById(String id){
        for (int i = 0; i < formFields.length; i++) {
            if (formFields[i].id.equals(id)){
                return formFields[i];
            }
        }
        return null;
    }
}
