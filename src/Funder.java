//A type of user that specifically focuses on identifying talent

public class Funder extends User{
    String URL;

    public Funder(){
        super(true);
    }

    public Funder(String Url, String name, int id, String email, String password, String postIds, String upvotedPosts){
        super(true, id, name, email, password, postIds, upvotedPosts);
        this.URL = Url;
    }

    public String getUrl() {
        return URL;
    }
    public void setUrl(String Url) {
        URL = Url;
    }

    @Override 
    public String toString(){
        return this.getUserId() + " // " + this.getName() + " // " + this.getPassword() + " // " 
                + this.getEmail() + " // " + this.getFunder() + " // " + this.getUrl() + " // " 
                + this.getPostsIds().toString() + " // " + this.getUpvotedPostsIds().toString();
    }
    
}

