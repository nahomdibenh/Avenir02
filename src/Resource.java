import java.util.ArrayList;

//Content type that composes of the Link Class allowing user to share info and sources related to their learning
public class Resource extends UserContent{
    protected ArrayList<Link> links = new ArrayList<>();

    public Resource(ProblemArea problem){
        super(problem.getProblemId(), false);
    }

    public ArrayList<Link> getLinks() {
        return links;
    }
}
