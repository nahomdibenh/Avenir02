import java.io.IOException;
import java.util.ArrayList;

public class UserContent {
    protected String contentId;
    protected String problemAreaId;
    protected Boolean article;
    protected String title;
    protected static UserContent currContent;
    protected static int totalContent = 0;

    public UserContent(String problemId, Boolean type){
        this.problemAreaId = problemId;
        this.contentId = problemId + "B" + totalContent;
        totalContent++;
        this.article = type;
        if(ProblemArea.currProblemArea != null){
            ProblemArea.currProblemArea.getContent().add(this);
        }
    }

    public String getContentId() {
        return contentId;
    }
    public void setContentId(String contentId) {
        this.contentId = contentId;
    }
    //return true if type is article and false if type is a resource
    public Boolean getType() {
        return article;
    }
    public void setType(Boolean type) {
        this.article = type;
    }
    public String getTitle() {
        return title;
    };
    public void setTitle(String title) {
        this.title = title;
    };
    public String getProblemAreaId() {
        return problemAreaId;
    }
    public void setProblemAreaId(String problemAreaId) {
        this.problemAreaId = problemAreaId;
    }

    public static UserContent getContentById(String id){
        ArrayList<UserContent> allContent;
        try {
            allContent = DataServices.getContent();
            for (UserContent content : allContent) {
                if(content.contentId.equals(id)){
                    return content;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }
}
