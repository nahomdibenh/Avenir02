import java.util.ArrayList;

public class Resource extends UserContent{
    protected ArrayList<Link> links = new ArrayList<>();

    public Resource(ProblemArea problem){
        super(problem.getProblemId(), false);
    }
    public Resource(String contentId, String problemAreaId, Boolean article, String title, String linkIds) {
        super(problemAreaId, article);
        this.contentId = contentId;
        this.problemAreaId = problemAreaId;
        this.title = title;
        if(linkIds != null){
            for (String str : linkIds.split(", ")) {
                links.add(Link.getLinkById(str));
            }
        }
    }
    public ArrayList<Link> getLinks() {
        return links;
    }
    public ArrayList<String> getLinkIds(){
        ArrayList<String> ret = new ArrayList<>();
        for (Link link : this.links) {
            ret.add(link.linkId);
        }
        return ret;
    }
    @Override
    public String toString() {
        return this.getContentId() + " // " + this.getProblemAreaId() + " // " + this.getType()
                + " // " + this.getTitle() + " // " + this.getLinkIds().toString();
    }
}
