public class Article extends Content{
    protected String body;

    public Article(ProblemArea problem){
        super(problem, true);
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
}
