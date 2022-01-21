import java.util.ArrayList;

public class ProblemArea {
    protected String name;
    protected ArrayList<Content> content = new ArrayList<>();
    protected String rootCause;
    protected String impact;
    protected String nextStep;
    protected String problemId;
    protected String problemStatement;
    static int problemAreasCount = 0;
    static ProblemArea currProblemArea;

    public ProblemArea(){
        this.problemId = User.currUser.getUserId() + "A" + problemAreasCount;
        problemAreasCount += 1;
        currProblemArea = this;
    }

    //Getters and Setters
    public String getProblemId() {
        return problemId;
    }
    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRootCause() {
        return rootCause;
    }
    public void setRootCause(String rootCause) {
        this.rootCause = rootCause;
    }
    public String getImpact() {
        return impact;
    }
    public void setImpact(String impact) {
        this.impact = impact;
    }
    public String getNextStep() {
        return nextStep;
    }
    public void setNextStep(String nextStep) {
        this.nextStep = nextStep;
    }
    public String getProblemStatement() {
        return problemStatement;
    }
    public void setProblemStatement(String problemStatement) {
        this.problemStatement = problemStatement;
    }
    public ArrayList<Content> getContent() {
        return content;
    }

    public ArrayList<Content> getArticles(){
        ArrayList<Content> content = getContent();
        ArrayList<Content> articles = new ArrayList<>();
        for (Content info : content) {
            if(info.getType()){
                articles.add(info);
            }
        }
        return articles;
    }
    
    public ArrayList<Content> getResources(){
        ArrayList<Content> content = getContent();
        ArrayList<Content> resources = new ArrayList<>();
        for (Content info : content) {
            if(!info.getType()){
                resources.add(info);
            }
        }
        return resources;
    }
    public static ProblemArea getCurrProblemArea() {
        return currProblemArea;
    }
}
