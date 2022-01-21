import java.io.IOException;
import java.util.ArrayList;

public class ProblemArea {
    protected String name;
    protected ArrayList<UserContent> content = new ArrayList<>();
    protected String rootCause;
    protected String impact;
    protected String nextStep;
    protected String problemId;
    protected String problemStatement;
    protected int userId;
    static int problemAreasCount = 0;
    static ProblemArea currProblemArea;

    public ProblemArea(){
        this.userId = User.currUser.getUserId();
        this.problemId = User.currUser.getUserId() + "A" + problemAreasCount;
        problemAreasCount += 1;
        currProblemArea = this;
    }

    public ProblemArea(String problemId2, int userId2, String name2, String rootCause2, String impact2,
            String nextStep2, String problemStatement2, String contentIds) {
        this.problemId = problemId2;
        this.userId = userId2;
        this.name = name2;
        this.rootCause = rootCause2;
        this.impact = impact2;
        this.nextStep = nextStep2;
        this.problemStatement = problemStatement2;
        if (contentIds != null){
            for (String str: contentIds.split(", ")) {
                content.add(UserContent.getContentById(str));
            }
        }
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
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public ArrayList<UserContent> getContent() {
        return content;
    }

    public ArrayList<UserContent> getArticles(){
        ArrayList<UserContent> content = getContent();
        ArrayList<UserContent> articles = new ArrayList<>();
        for (UserContent info : content) {
            if(info.getType()){
                articles.add(info);
            }
        }
        return articles;
    }
    
    public ArrayList<UserContent> getResources(){
        ArrayList<UserContent> content = getContent();
        ArrayList<UserContent> resources = new ArrayList<>();
        for (UserContent info : content) {
            if(!info.getType()){
                resources.add(info);
            }
        }
        return resources;
    }
    public static ProblemArea getCurrProblemArea() {
        return currProblemArea;
    }
    public ArrayList<String> getContentIds(){
        ArrayList<String> ret = new ArrayList<>();
        for (UserContent content : this.content) {
            ret.add(content.contentId);
        }
        return ret;
    }

    @Override
    public String toString(){
        String nextStep = this.getNextStep() == null ? null : this.getNextStep().replace("\n", "????;");
        String problemStatement = this.getProblemStatement() == null ? null : this.getProblemStatement().replace("\n", "????;");
        return this.getProblemId() + " // " + this.getUserId() + " // " + this.getName() + " // " + this.getRootCause()
                + " // " + this.getImpact() + " // " + nextStep + " // " + problemStatement
                + " // " + this.getContentIds().toString();
    }

    public static ProblemArea getProblemById(String id){
        ArrayList<ProblemArea> allProblems;
        try {
            allProblems = DataServices.getProblemAreas();
            for (ProblemArea problem : allProblems) {
                if(problem.problemId.equals(id)){
                    return problem;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }
}
