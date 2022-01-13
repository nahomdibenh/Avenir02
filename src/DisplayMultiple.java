import javafx.scene.control.TextField;

public class DisplayMultiple {

    public TextField[] displayFormField(int numFields, String[] labels){
        TextField[] ret = new TextField[numFields];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = new TextField(labels[i]);
        }
        return ret;
    }
}
