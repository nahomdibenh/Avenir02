import java.util.ArrayList;

public class Individual extends User{
    protected String profession;
    protected String currentProject;
    //could use a Stirng ArrayList of problemIds, but only one user can have a specific problem area
    protected ArrayList<ProblemArea> problemAreas = new ArrayList<ProblemArea>();
    protected String skills;

    public Individual(){
        super(false);
    }

    public Individual(String name, int id, String email, String profession, String skills, String password, String currentProject, String postIds, String upvotedPosts, String problemAreaIds) {
        super(false, id, name, email, password, postIds, upvotedPosts);
        this.profession = profession;
        this.currentProject = currentProject;
        this.skills = skills;

        //convert the ids storred in the txt file to problem area objects
        if(problemAreaIds != null){
            for (String str : problemAreaIds.split(", ")) {
                problemAreas.add(ProblemArea.getProblemById(str));
            }
        }
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
    public void setCurrentProject(String currentProjectId) {
        this.currentProject = currentProjectId;
    }
    public ArrayList<ProblemArea> getProblemAreas() {
        return problemAreas;
    }
    public ArrayList<String> getProblemIds(){
        ArrayList<String> ret = new ArrayList<>();
        for (ProblemArea problem : this.problemAreas) {
            ret.add(problem.problemId);
        }
        return ret;
    }

    //To string method to convert all instance data into a single line savable string for text files
    @Override
    public String toString(){
        return this.getUserId() + " // " + this.getName() + " // " + this.getPassword() + " // " + this.getEmail() + " // " 
                + this.getFunder() + " // " + this.getProfession() + " // " + this.getSkills() + " // " 
                + this.getCurrentProject() + " // " + this.getPostsIds().toString() + " // " 
                //use the default toString method for String arrayLists '[element1, 2, ...]'
                + this.getUpvotedPostsIds().toString() + " // " + this.getProblemIds().toString();
    }
}
