import java.util.ArrayList;

public class Resource extends Content{
    protected ArrayList<Link> links = new ArrayList<>();

    public Resource(ProblemArea problem){
        super(problem, false);
    }
    public ArrayList<Link> getLinks() {
        return links;
    }
}
