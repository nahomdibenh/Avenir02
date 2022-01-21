import java.io.IOException;
import java.util.ArrayList;

public class Link {
    protected String linkId;
    protected String url;
    protected String description;
    protected static int numLinks = 0;

    public Link(String url, String description){
        this.url = url;
        this.description = description;
        this.linkId = "L" + numLinks;
        numLinks++;
    }

    public Link(String linkId, String url2, String description2) {
        this.linkId = linkId;
        this.url = url2;
        this.description = description2;
        numLinks++;
    }

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
    
    @Override
    public String toString() {
        return this.linkId + " // " + this.url + " // " + this.description.replace("\n", "????;");
    }

    public static Link getLinkById(String id){
        ArrayList<Link> allLinks;
        try {
            allLinks = DataServices.getLinks();
            for (Link link : allLinks) {
                if(link.linkId.equals(id)){
                    return link;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }
}
