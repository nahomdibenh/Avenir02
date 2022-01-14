import java.util.ArrayList;

public class Funder extends User{
    String URL;
    // public ArrayList<Posts> posts;

    public Funder(){
        super(true);
    }


    public String getUrl() {
        return URL;
    }
    public void setUrl(String uRL) {
        URL = uRL;
    }
    
}

