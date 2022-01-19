import java.util.ArrayList;

public class Individual extends User{
    protected String profession;
    protected int currentProject;
    protected ArrayList<String> interestedPosts;
    protected ArrayList<String> starredPosts;
    protected ArrayList<ProblemArea> problemAreas = new ArrayList<ProblemArea>();
    protected ArrayList<String> upvotedPosts;
    protected String skills;

    public Individual(){
        super(false);
    }
    public Individual(String name, int id, String email, String profession, String skills, String password) {
        super(false, id, name, email, password);
        this.profession = profession;
        this.skills = skills;
    }
    public Individual(String profession, int currentProject, String skills, String password, String interestedPosts, String starredPosts, String upvotedPosts) {
        super(false);
        this.profession = profession;
        this.currentProject = currentProject;
        this.skills = skills;
        this.password = password;
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
    public int getCurrentProject() {
        return currentProject;
    }
    public void setCurrentProject(int currentProjectId) {
        this.currentProject = currentProjectId;
    }
    public ArrayList<ProblemArea> getProblemAreas() {
        return problemAreas;
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
