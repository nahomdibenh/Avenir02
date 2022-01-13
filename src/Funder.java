import java.util.ArrayList;

public class Funder extends User{
    String URL;
    // public ArrayList<Posts> posts;

    public Funder(){
        super(true);
    }


    public String getURL() {
        return URL;
    }
    public void setURL(String uRL) {
        URL = uRL;
    }
    
}

