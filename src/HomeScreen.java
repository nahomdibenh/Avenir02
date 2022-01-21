import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class HomeScreen {
    static BorderPane layout;

    public static void setCenter(Region pane) {
        layout.setCenter(pane);
    }
    //comment this method fully
    public static Pane displayPostInfo() {
        GridPane v2 = new GridPane();
        v2.setVgap(5);
        v2.setPadding(new Insets(10));
        int counter = 0;
        for (Post post : Post.allPosts) {
            GridPane text = new GridPane();
            User user = User.getUserById(post.userID);
            //this is all the texts that appear on the sides of the post
            Header payment = new Header("Payment: ");
            Header description = new Header("Problem Area: ");
            Header details = new Header("Details: ");
            Header desiredskills = new Header("Desired skills: ");
            //this is the placement for the side titles 
            text.add(payment.getHeader(FontWeight.NORMAL, 15), 0, 3);
            text.add(description.getHeader(FontWeight.NORMAL, 15), 0, 5);
            text.add(details.getHeader(FontWeight.NORMAL, 15), 0, 7);
            text.add(desiredskills.getHeader(FontWeight.NORMAL, 15), 0, 9);

            //These are buttons that will appear at the bottom of each post
            Button upvote = new Button("Upvote");
            upvote.getStyleClass().add("neutral-button");
            //String valueof will turn the int you get from num of 
            //upvotes to a String for the new text
            Text numUpvotes = new Text(String.valueOf(post.getNumUpvotes()));
            Button workOn = new Button("Work On");
            workOn.getStyleClass().add("neutral-button");

            //the Hbox allows you to create multiple buttons on the same row, 
            //10 mean the spacing between the buttons 
            HBox hbox = new HBox(10, numUpvotes, upvote, workOn);
            //this adds the hbox to the screen
            text.add(hbox, 0, 10);

            // make a set action for the upvote button, when pressed increase num of upvotes
            upvote.setOnAction(event -> {

                post.setNumUpvotes();
                // the num of upvotes only updates after the user creates a new post,
                // and wont see the update unless they create a new post
                numUpvotes.setText(String.valueOf(post.getNumUpvotes()));

            });

            // make a set action for the work on button, you just add it to
            // current projects using the individual class and user (similiar to previous
            // vbutton)
            workOn.setOnAction(value -> {
                ((Individual) User.currUser).setCurrentProject(post.getTitle());

            });
            //this will show the inputed user-specified values from the post 
            //and this shows the position of where the values will show on the screen
            text.add(new Header(post.getTitle()).getHeader(), 0, 0);
            //user contact
            text.add(new Header (user.getName()), 0, 1);
            text.add(new Header (user.getEmail()), 0, 1);
            //String value of converts the money inputed by the user into a string from an int, 
            //so that you can add the label, which requires a string
            String prizeAmount = String.valueOf(post.getPrizeAmount()).isEmpty() ? "None" : String.valueOf(post.getPrizeAmount());
            String problemArea = post.getProblemArea().isEmpty() ? "None" : post.getProblemArea();
            String postDetails = post.getDetails().isEmpty() ? "None" : post.getDetails();
            String skills = post.getDesiredSkills().isEmpty() ? "None" :post.getDesiredSkills();
            text.add(new Label(prizeAmount), 0, 4);
            text.add(new Label(problemArea), 0, 6);
            text.add(new Label(postDetails), 0, 6);
            text.add(new Label(skills), 0, 8);

            // Text detailsPost = new Text(10, 20, post.getDetails());
            // Text titlePost = new Text (10,30, post.getTitle());

            //this is the logic behind moving the post position 
            //downward after creating a new one by using
            // the counter varibale for where it is placed 
            v2.add(text, 0, counter);
            counter++;
        }

        return v2;
    }

    public static Scene display() {

        layout = new BorderPane();

        Scene homeScene = new Scene(layout, 800, 800);

        Button posts = new Button("Posts");
        posts.setMaxWidth(230);
        Button profile = new Button("Profile");
        profile.setMaxWidth(230);
        Button create = new Button("Create");
        create.setMaxWidth(230);
        Button logout = new Button("Logout");
        logout.setMaxWidth(100);
        HBox menuBar = new HBox(10, posts, profile);
        menuBar.setStyle("-fx-background-color: #000");
        menuBar.setPadding(new Insets(10));

        if (!User.currUser.getFunder()) {
            menuBar.getChildren().add(create);
        }
        menuBar.getChildren().add(logout);

        HBox.setHgrow(posts, Priority.ALWAYS);
        HBox.setHgrow(profile, Priority.ALWAYS);
        HBox.setHgrow(create, Priority.ALWAYS);

        layout.getStylesheets().add("style.css");
        posts.getStyleClass().add("menu-buttons");
        profile.getStyleClass().add("menu-buttons");
        create.getStyleClass().add("menu-buttons");
        logout.getStyleClass().add("menu-buttons");

        Button postButton = new Button("New Post");
        postButton.getStyleClass().add("submit-button");

        logout.setOnAction(value -> {
            Login.loginDisplay();
        });

        posts.setOnAction(value -> {
            layout.setTop(menuBar);
            layout.setLeft(postButton);
            ScrollPane sPane = new ScrollPane();
            sPane.setContent(displayPostInfo());
            layout.setCenter(sPane);
        });

        postButton.setOnAction(event -> {
            App.setScene(DisplayPostForm.postForm());
        });
        profile.setOnAction(value -> {
            layout.setLeft(null);
            layout.setCenter(Profile.profilePage(User.currUser));
        });
        create.setOnAction(value -> {
            layout.setLeft(null);
            //the curr user should only be able to press on the create page to find their content
            //start by displaying a grid view of all the current users problem areas
            CreatePage.problemAreaGrid(User.currUser);
        });

        layout.setTop(menuBar);
        layout.setLeft(postButton);
        ScrollPane sPane = new ScrollPane();
        sPane.setContent(displayPostInfo());
        layout.setCenter(sPane);
        return homeScene;
    }
}
