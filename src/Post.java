import java.util.ArrayList;

public class Post {
    // methods from uml diagrams
    // protetcted variables from UMl diagrams in
    protected int postID = 0;
    protected String title = null;
    protected int userID = 0;
    protected String problemArea = null;
    protected int numUpvotes = 0;
    protected int prizeAmount = 0;
    protected String details = null;
    static ArrayList<Post> allPosts= new ArrayList<>();

    public Post() {
        super();
        allPosts.add(this);
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public String getProblemArea() {
        return problemArea;
    }
    public void setProblemArea(String newproblemArea) {
        this.problemArea = newproblemArea;
    }

    public int getNumUpvotes() {
        return numUpvotes;
    }

    public void setNumUpvotes(int numUpvotes) {
        this.numUpvotes = numUpvotes;
    }
    public int getPrizeAmount() {
        return prizeAmount;
    }
    public void setPrizeAmount(int prizeAmount) {
        this.prizeAmount = prizeAmount;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }

}

