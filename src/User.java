import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class User {
    public static Color[] colors = {Color.PALETURQUOISE, Color.ROYALBLUE, Color.GOLD, Color.SEAGREEN};
    public static int totalUsers;
    protected boolean funder = false;
    protected int userId = 0;
    protected String name =  null;
    protected String email = null;
    protected String currentPage = "feed";

    public User(boolean funder) {
        this.funder = funder;
        User.totalUsers += 1;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void getFunder(boolean getFunder){
        this.funder = getFunder;
    }
    public void setFunder(boolean funder) {
        this.funder = funder;
    }
    
    public String getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    //choose what color an avatar should be based on modulo of its id
    public Shape getAvatar(){
        if (userId % 5 == 0){
            return new Circle(5, colors[0]);
        }
        if (userId % 9 == 0){
            return new Circle(5, colors[1]);
        }
        if (userId % 2 == 0){
            return new Circle(5, colors[2]);
        }
        else{
            return new Circle(5, colors[4]);
        }
    }
    
}
