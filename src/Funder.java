import java.util.ArrayList;

public class Funder extends User{
    String URL;
    // public ArrayList<Posts> posts;

    public Funder(){
        super(true);
    }

    public Funder(String Url, String name, int id, String email, String password){
        super(true, id, name, email, password);
        this.URL = Url;
    }

    public String getUrl() {
        return URL;
    }
    public void setUrl(String Url) {
        URL = Url;
    }
    
}

