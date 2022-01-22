import java.io.IOException;
import java.util.ArrayList;

//Specifying the structure for a post object
//Users can create posts to share resquests about problems they're working on

public class Post {
    // methods from uml diagrams
    // protetcted variables from UMl diagrams in
    protected String postID;
    protected String title = null;
    protected int userID = 0;
    protected String problemArea = null;
    protected int numUpvotes = 0;
    protected int prizeAmount = 0;
    protected String details = null;
    protected String desiredSkills = null;
    static ArrayList<Post> allPosts= new ArrayList<>();

    //getting identifiers through the number of posts since it will always be a unique identitifier
    public Post() {
        super();
        this.userID = User.currUser.getUserId();
        this.postID = this.userID + "P" + allPosts.size();
        allPosts.add(this);
    }

    //Getters and Setters
    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
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
    public String getDesiredSkills() {
        return desiredSkills;
    }
    public void setDesiredSkills(String desiredSkills) {
        this.desiredSkills = desiredSkills;
    }

    public int getNumUpvotes() {
        return numUpvotes;
    }

    public void setNumUpvotes() {
        boolean found = false;
        //check if numUpvots is in the currUser upvoted array
        for (Post post : User.currUser.upvotedPosts) {
            if (post.getPostID().equals(this.postID)){
                found = true;
            }
        }
        //if it is not found, the user has not upvoted the post yet and should be allowed to do so
        if (!found){
            //increase the upvotes by 1
            this.numUpvotes += 1;
            //add this post to the current user's upvoted post
            User.currUser.getUpvotedPosts().add(this);
        }
    }

}

