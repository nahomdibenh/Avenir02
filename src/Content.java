public class Content {
    String contentId;
    Boolean article;
    String title;
    protected static int totalContent = 0;

    public Content(ProblemArea problem){
        this.contentId = problem.getProblemId() + "B" + totalContent;
        totalContent++;
        ProblemArea.currProblemArea.getContent().add(this);
    }
    public Content(ProblemArea problem, Boolean type){
        this.contentId = problem.getProblemId() + "B" + totalContent;
        totalContent++;
        this.article = type;
        ProblemArea.currProblemArea.getContent().add(this);
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
}
