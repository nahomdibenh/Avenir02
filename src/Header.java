import javafx.scene.Group;
import javafx.scene.control.Separator;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

//a class that extends text to more easily create bold heading with dividers
public class Header extends Text{
    public Header(String text){
        super(text);
    }
    //give the header a larger font
    public Header getHeader(){
        this.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        return this;
    }
    //overloaded getter that allows for specific specification of font size and weight
    public Header getHeader(FontWeight fWeight, int fontSize){
        this.setFont(Font.font("Arial", fWeight, fontSize));
        return this;
    }
    //a seperator that extends the entire screen width
    public Separator getSeparator(){
        Separator sep = new Separator();
        sep.setPrefWidth(800);
        return sep;
    }
}
