import java.io.IOException;
import java.util.ArrayList;

public class ProblemArea {
    protected String name;
    //sub-content is variable so store in an ArrayList
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

    //contrsuctor used when converting from textfile given it has all the necessary arguments
    public ProblemArea(String problemId2, int userId2, String name2, String rootCause2, String impact2,
            String nextStep2, String problemStatement2, String contentIds) {
        this.problemId = problemId2;
        this.userId = userId2;
        this.name = name2;
        this.rootCause = rootCause2;
        this.impact = impact2;
        this.nextStep = nextStep2;
        this.problemStatement = problemStatement2;
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

    //Loop through all the content and return an arrayList that only contains article content
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
    
    //only returns resource content
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
    //Convert the content arrayList instance variable to a string arrayList for back backend
    //formatting the the ability to use the build in .toString for Strin arrayLists
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

    //get problems by a specified id
    public static ProblemArea getProblemById(String id){
        ArrayList<ProblemArea> allProblems;
        try {
            //get all the problems in the backend
            allProblems = DataServices.getProblemAreas();
            //see if the argument patches a problem id
            for (ProblemArea problem : allProblems) {
                if(problem.problemId.equals(id)){
                    //return the problem instance if its does
                    return problem;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }
}
