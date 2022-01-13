import java.util.ArrayList;

public class Individual extends User{
    protected String profession;
    protected String currentProject;
    protected ArrayList<String> interestedPosts;
    protected ArrayList<String> starredPosts;
    // protected ArrayList<ProblemArea> problemAreas;
    protected ArrayList<String> upvotedPosts;
    protected String skills;

    public Individual(){
        super(false);
    }

    public String getProfession() {
        return profession;
    }
    public void setProfession(String profession) {
        this.profession = profession;
    }
    public String getSkills() {
        return skills;
    }
    public void setSkills(String skills) {
        this.skills = skills;
    }
    public String getCurrentProject() {
        return currentProject;
    }
    public void setCurrentProject(String currentProject) {
        this.currentProject = currentProject;
    }

    // public void setStarredpost(boolean delete, Post post){
        
    // }
    // public ArrayList<String> getStarredPosts() {
    //     return starredPosts;
    // }

    // public ArrayList<String> getInterestedPosts(){
    //     return interestedPosts;
    // }
    // public void setInterestedPosts(ArrayList<String> interestedPosts) {
    //     this.interestedPosts = interestedPosts;
    // }

    // public void setUpvotedPosts(ArrayList<String> upvotedPosts) {
    //     this.upvotedPosts = upvotedPosts;
    // }

    // public ArrayList<String> getUpvotedPosts() {
    //     return upvotedPosts;
    // }

    // public ArrayList<String> setProblemAreas(boolean delete, String problem){

    // }
    // public ArrayList<String> getProblemArea(){

    // }


}
