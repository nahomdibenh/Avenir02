//Defining the structure for a type of content called article

public class Article extends UserContent{
    //body text for the article
    protected String body;

    //contructor takes in a Problem Area to associate its self with
    //other variables are set as more data about the object is collected
    public Article(ProblemArea problem){
        super(problem.getProblemId(), true);
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
}
