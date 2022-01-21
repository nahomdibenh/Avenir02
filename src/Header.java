import javafx.scene.Group;
import javafx.scene.control.Separator;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Header extends Text{
    public Header(String text){
        super(text);
    }
    public Header getHeader(){
        this.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        return this;
    }
    public Header getHeader(FontWeight fWeight, int fontSize){
        this.setFont(Font.font("Arial", fWeight, fontSize));
        return this;
    }
    public Separator getSeparator(){
        Separator sep = new Separator();
        sep.setPrefWidth(800);
        return sep;
    }
}
