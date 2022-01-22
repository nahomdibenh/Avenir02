import java.io.IOException;
import java.util.ArrayList;

//A classed used by resources in a composition relationship

public class Link {
    protected String linkId;
    protected String url;
    protected String description;
    protected static int numLinks = 0;

    //using numLinks as the unqiue identifier since it changes with every link creation
    public Link(String url, String description){
        this.url = url;
        this.description = description;
        this.linkId = "L" + numLinks;
        numLinks++;
    }

    //getters and setters
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    };
}
