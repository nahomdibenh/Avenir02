//Defining the structure for a tye of content called article

public class Article extends UserContent{
    protected String body;

    public Article(ProblemArea problem){
        super(problem.getProblemId(), true);
    }

    public Article(String contentId, String problemAreaId, Boolean article, String title, String body2) {
        super(problemAreaId, article);
        this.contentId = contentId;
        this.problemAreaId = problemAreaId;
        this.title = title;
        this.body = body2;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    //Replace body line breaks with a randomly chosen character sequence that can be swapped back latter when data is fetched
    @Override
    public String toString() {
        return this.getContentId() + " // " + this.getProblemAreaId() + " // " + this.getType()
                + " // " + this.getTitle() + " // " + this.body.replace("\n", "????;");
    }
}
