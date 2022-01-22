import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

//Base Parent class that lays out the specific capabilities of a user

public class User {
    public static Color[] colors = {Color.PALETURQUOISE, Color.ROYALBLUE, Color.GOLD, Color.SEAGREEN};
    public static int totalUsers;
    public static User currUser;
    protected boolean funder = false;
    protected int userId;
    protected String name;
    protected String email;
    protected String password;
    //Posts were chosen itilially without a backend in my mind
    //Given there may duplicate objects between posts and upvotedPosts, this could be optimized
    protected ArrayList<Post> posts = new ArrayList<>();
    protected ArrayList<Post> upvotedPosts = new ArrayList<>();

    //Contructor used on sign in / up, and therefore will correlate to one more totalUser
    public User(boolean funder) {
        this.funder = funder;
        this.userId = totalUsers;
        totalUsers++;
    }

    //constructor used to convert backend stored strings to nearly full user objects
    public User(boolean funder, int userId, String name, String email, String password, String postIds, String upvotedPostIds){
        this.funder = funder;
        this.name = name;
        this.email = email;
        this.userId = userId;
        this.password = password;
    }

    //Getters and Setter

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public boolean getFunder(){
        return this.funder;
    }
    public void setFunder(boolean funder) {
        this.funder = funder;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public static User getCurrUser() {
        return currUser;
    }
    public static void setCurrUser(User currUser) {
        User.currUser = currUser;
    }
    public ArrayList<Post> getPosts() {
        return posts;
    }
    public ArrayList<Post> getUpvotedPosts() {
        return upvotedPosts;
    }

    //get post ids in String ArrayList to abuse the .toString() method
    public ArrayList<String> getUpvotedPostsIds(){
        ArrayList<String> ret = new ArrayList<>();
        for (Post post : this.upvotedPosts) {
            ret.add(post.postID);
        }
        return ret;
    }
    public ArrayList<String> getPostsIds(){
        ArrayList<String> ret = new ArrayList<>();
        for (Post post : this.posts) {
            ret.add(post.postID);
        }
        return ret;
    }

    //choose what color an avatar should be based on modulo of its id
    public Shape getAvatar(){
        if (userId % 5 == 0){
            return new Circle(20, colors[0]);
        }
        if (userId % 9 == 0){
            return new Circle(20, colors[1]);
        }
        if (userId % 2 == 0){
            return new Circle(20, colors[2]);
        }
        else{
            return new Circle(20, colors[3]);
        }
    }

    //Get a user by a specified id 
    public static User getUserById(int id){
        ArrayList<User> allUsers;
        try {
            allUsers = DataServices.getUsers();
            //loop through all users and check if the ids match
            for (User user : allUsers) {
                if(user.userId == id){
                    return user;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }
    
}
