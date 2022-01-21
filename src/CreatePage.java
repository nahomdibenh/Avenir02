import java.io.IOException;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

//this class handles everything to do on the create tab of avenir
//1. creating content and problem areas
//2. displaying all problem areas and a single problem area's content
//3. displaying individual content pieces

//though for future development
//all create functions can be passed in a ProblemArea or Content object that data is saved to and textfields are prefilled
//with object data and then on submit it edits the onject instance allowing for updating the problem areas

public class CreatePage {

    public static boolean validName(Form form){
        FormField field = form.getFormFieldById("name");
        return !field.getText().equals("");
    }

    //Create a singular rectangular block with a title and specified styles. Returning a transparent overlapping
    //rectangle for onClick events
    public static Rectangle block(String text, String styles, Color color, FontWeight weight, int FontSize, Pane pane){
        //Using a stack pane since things will overlap
        StackPane stack = new StackPane();
        Button title = new Button(text);
        title.setFont(Font.font("Arial", weight, FontSize));
        title.setTextFill(Color.WHITE);

        //if title overflow, it overflows
        Rectangle rect = new Rectangle(200, 200);
        rect.setFill(color);


        String r = Integer.toHexString((int) (color.getRed() * 255));
        String g = Integer.toHexString((int) (color.getGreen() * 255));
        String b = Integer.toHexString((int) (color.getBlue() * 255));
        r = r.length() == 1 ? r + "0" : r;
        g = g.length() == 1 ? g + "0" : g;
        b = b.length() == 1 ? b + "0" : b;

        //Set the button background color equal to the rectangle background color by taking rectangle color and converting to RGB
        title.setStyle(styles + "-fx-background-color: #" + r + g + b);
        Rectangle test = new Rectangle(200, 200);
        test.setFill(Color.TRANSPARENT);
        stack.getChildren().addAll(rect, title, test);
        pane.getChildren().add(stack);
        return test;
    }

    //Given a piece of content, blocks will be displayed and a pane is returned for display and different onClick 
    //control for different types of content
    public static FlowPane displayContentGrid(ArrayList<UserContent> content, Insets padding){
        FlowPane fPane = new FlowPane(10, 10);
        fPane.setPadding(padding);
        fPane.setPrefWrapLength(800);

        Rectangle[] contentRectangles = new Rectangle[content.size()];
        for (int i = 0; i < contentRectangles.length; i++) {
            contentRectangles[i] = block(content.get(i).getTitle(), "", User.colors[i % User.colors.length], FontWeight.NORMAL, 25, fPane);
        }

        //CREATE ADD BUTTON
        Rectangle createRectangle = block("+", "", Color.BLACK, FontWeight.BOLD, 80, fPane);

        return fPane;
    }

    //display the given articles and resources for a problem area
    public static void displayContent(ProblemArea problem){
        GridPane gPane = new GridPane();
        gPane.setPadding(new Insets(10));

        Text articleHeading = new Text("Articles");
        articleHeading.setFont(Font.font("Arail", FontWeight.MEDIUM, 25));

        Text resourceHeading = new Text("Resources");
        resourceHeading.setFont(Font.font("Arail", FontWeight.MEDIUM, 25));

        //Using the children of the flowplane, we can each give them an onClick action
        ArrayList<UserContent> articles = problem.getArticles();
        FlowPane articlePane = displayContentGrid(articles, new Insets(15, 0, 15, 0));
        for (int i = 0; i < articlePane.getChildren().size(); i++) {
            int iterator = i;
            articlePane.getChildren().get(i).setOnMouseClicked(value -> {
                if (iterator == articlePane.getChildren().size() - 1){
                    //if its the final rectangle, it will be the create a new piece of content rectangle
                    createArticle();
                }
                else{
                    displayArticle((Article) articles.get(iterator));
                }
            });
        }

        //Same as above repeated for the other type of content, resources
        ArrayList<UserContent> resources = problem.getResources();
        FlowPane resourcePane = displayContentGrid(resources, new Insets(15, 0, 15, 0));
        for (int i = 0; i < resourcePane.getChildren().size(); i++) {
            int iterator = i;
            resourcePane.getChildren().get(i).setOnMouseClicked(value -> {
                if (iterator == resourcePane.getChildren().size() - 1){
                    createResource();
                }
                else{
                    displayResource((Resource) resources.get(iterator));
                }
            });
        }

        gPane.add(problemAreaHeader(problem), 0, 0);
        gPane.add(articleHeading, 0, 1);
        gPane.add(articlePane, 0, 2);
        gPane.add(resourceHeading, 0, 3);
        gPane.add(resourcePane, 0, 4);


        // VBox pane = new VBox(10, artsicleHeaderBox, articleBox);
        HomeScreen.setCenter(gPane);
    }

    //the header for a given problem area
    public static GridPane problemAreaHeader(ProblemArea problemArea){
        GridPane gPane = new GridPane();
        String[] text = {problemArea.getName(), problemArea.getProblemStatement(), problemArea.getImpact(), 
                        problemArea.getRootCause(), problemArea.getNextStep()};
        String[] labels = {"", "Problem Statement", "Potential Impact", "Root Cause", "Next Steps"};

        for (int i = 0; i < text.length; i++) {
            //display as long as the string has a character in it
            if (text[i] != null && !text[i].isEmpty()){
                if (i == 0){
                    //display the first element (the name of the problem) in large letters
                    Header header = new Header(text[i]);
                    gPane.add(header.getHeader(), 0, 0);
                    gPane.add(header.getSeparator(), 0, 1);
                }
                else{
                    Label label = new Label(labels[i]);
                    label.setFont(Font.font("Arail", FontWeight.BOLD, 20));
                    Text temp = new Text(text[i]);
                    gPane.add(label, 0, i * 2);
                    gPane.add(temp, 0, i * 2 + 1);
                }
            }
        }
        return gPane;
    }
    
    //Display the grid of problem areas specified with a different onClick action
    public static GridPane problemAreaGrid(User user){
        GridPane gPane = new GridPane();
        gPane.setPadding(new Insets(15));
        gPane.setVgap(10);
        
        FlowPane fPane = new FlowPane(10, 10);
        fPane.setPrefWrapLength(800);

        ArrayList<ProblemArea> problemAreas = ((Individual) user).getProblemAreas();
        Rectangle[] problemRectangles = new Rectangle[problemAreas.size()];
        for (int i = 0; i < problemAreas.size(); i++) {
            problemRectangles[i] = block(problemAreas.get(i).getName(), "", User.colors[i % User.colors.length], FontWeight.MEDIUM, 25, fPane);

        }

        for (int i = 0; i < problemRectangles.length; i++) {
            int iterator = i;
            problemRectangles[i].setOnMouseClicked(value -> {
                //set the current problem area as the box being clicked so its contents can be displayed
                ProblemArea.currProblemArea = problemAreas.get(iterator);
                displayContent(ProblemArea.currProblemArea);
            });
        }

        //CREATE ADD BUTTON
        Rectangle createRectangle = block("+", "", Color.BLACK, FontWeight.BOLD, 80, fPane);

        //rect.setonmouseclicked
        createRectangle.setOnMouseClicked(value -> {
            createProblemArea();
        });

        Header heading = new Header("Problem Areas");
        gPane.add(heading.getHeader(), 0, 0);
        gPane.add(heading.getSeparator(), 0, 1);
        gPane.add(fPane, 0, 2);

        HomeScreen.setCenter(gPane);
        return gPane;
    }

    //Form for creating a new article
    public static void createArticle(){
        GridPane gPane = new GridPane();
        gPane.getStyleClass().add("test");

        String[] labels = {"Title"};
        String[] ids = {"name"};

        Form form = new Form(labels, ids, "New Article");
        form.displayFormFields(gPane);
        TextArea body = new TextArea();
        body.setPromptText("Type regularly or place '***'' at the very beggining of a line to create a sub heading\n Ex. ***Sub Heading");
        gPane.add(body, 0, labels.length + 2);

        Text warning = new Text("Both Title and Body Must be Specified");
        gPane.add(warning, 0, labels.length + 1);
        warning.setVisible(false);

        Button create = new Button("Create");
        create.getStyleClass().add("submit-button");
        gPane.add(create, 0, labels.length + 3);

        create.setOnAction(value -> {
            Article article = new Article(ProblemArea.currProblemArea);
            if(validName(form) && !body.getText().isEmpty()){
                //convert user input into an article object where the data can be stored
                form.formToContent(article);
                article.setBody(body.getText());
                try {
                    //Save data to the backend
                    //Updating articles with their new text
                    DataServices.createArticle(article);
                    //Updating problemAreas to hold a new piece of content
                    DataServices.updateProblemAreas(ProblemArea.currProblemArea, article);
                } catch (IOException e) {
                    System.out.println(e);
                }
                displayContent(ProblemArea.currProblemArea);
            }
            else{
                warning.setVisible(true);
            }
        });

        HomeScreen.setCenter(gPane);
    }

    //Form for creating a resource object (type of content)
    public static void createResource(){
        GridPane gPane = new GridPane();
        GridPane resourcePane = new GridPane();
        resourcePane.setVgap(10);
        gPane.getStyleClass().add("test");

        String[] labels = {"Title"};
        String[] ids = {"name"};

        Form form = new Form(labels, ids, "New Resource");
        form.displayFormFields(gPane);

        Button addMore = new Button("Add a Resource");
        addMore.getStyleClass().add("neutral-button");
        gPane.add(addMore, 0, labels.length + 2);

        //create a gridPane where future links can be add as the user presses a button
        //this allows the number of textfields to be dynamic rather than a fixed amount while
        //having a predictable display on the screen
        gPane.add(resourcePane, 0, labels.length + 3);

        Text warning = new Text("Resource at Least Needs a Name");
        gPane.add(warning, 0, labels.length + 1);
        warning.setVisible(false);

        Button create = new Button("Create");
        create.getStyleClass().add("submit-button");
        gPane.add(create, 0, labels.length + 4);

        Text resourceCountText = new Text("Number of resources created: " + 0);
        gPane.add(resourceCountText, 0, labels.length + 5);

        //using a temp variable since we only want to create a resource after the form has been succesfully submittied
        //once the form has been submittied, we can create a single instance of resource and set the links arraylist equal to 
        //temp
        ArrayList<Link> temp = new ArrayList<>();

        create.setOnAction(value -> {
            if(validName(form)){
                //once the form is valid, it can be submitted and the resource instance can be created
                Resource resource = new Resource(ProblemArea.currProblemArea);
                //move all Links from the temp arraylist
                resource.getLinks().addAll(temp);
                form.formToContent(resource);
                try {
                    //Save data to the backend
                    //Newly created resource with user input
                    DataServices.createResource(resource);
                    //Add to the array of content in the problem area
                    DataServices.updateProblemAreas(ProblemArea.currProblemArea, resource);
                } catch (IOException e) {
                    System.out.println(e);
                }
                displayContent(ProblemArea.currProblemArea);
            }
            else{
                warning.setVisible(true);
            }
        });

        //function to dynamically add more links to a resource
        addMore.setOnAction(value -> {
            addMore.setVisible(false);
            TextField link = new TextField();
            link.setPromptText("Resource URL");
            TextArea description = new TextArea();
            description.setPromptText("Resource Description");
            Button finishResource = new Button("Finish Resource");
            finishResource.getStyleClass().add("neutral-button");
            Text emptyWarning = new Text("Both url and description need to be specified");
            emptyWarning.setVisible(false);

            resourcePane.add(link, 0, 0);
            resourcePane.add(description, 0, 1);
            resourcePane.add(finishResource, 0, 2);
            resourcePane.add(emptyWarning, 0, 3);

            //Handle submit of sub-form
            finishResource.setOnAction(e -> {
                if(link.getText().isEmpty() || description.getText().isEmpty()){
                    emptyWarning.setVisible(true);
                }
                else{
                    Link lObject = new Link(link.getText(), description.getText());
                    temp.add(lObject);
                    try {
                        DataServices.createLink(lObject);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    addMore.setVisible(true);
                    resourcePane.getChildren().removeAll(link, description, finishResource, emptyWarning);
                    int lastVal = resourceCountText.getText().charAt(resourceCountText.getText().length() - 1) - '0';
                    resourceCountText.setText("Number of resources created: " + (lastVal + 1));
                }
            });
        });

        HomeScreen.setCenter(gPane);
    }

    public static void displayArticle(Article article){
        GridPane gPane = new GridPane();
        gPane.setVgap(10);
        gPane.setPadding(new Insets(10));

        Header header = new Header(article.getTitle());
        gPane.add(header.getHeader(), 0, 0);
        String[] sections = article.getBody().split("\n");
        int indexer = 1;
        for (String string : sections) {
            if(string.length() > 3 && string.substring(0, 3).equals("***")){
                Header subHeader = new Header(string.substring(3));
                gPane.add(subHeader.getHeader(FontWeight.MEDIUM, 25), 0, indexer);
                gPane.add(subHeader.getSeparator(), 0, indexer + 1);
                indexer += 2;
            }
            else{
                Text text = new Text(string);
                text.wrappingWidthProperty().bind(App.window.widthProperty());
                gPane.add(text, 0, indexer);
                indexer++;
            }
        }
        HomeScreen.setCenter(gPane);
    }

    public static void displayResource(Resource resource){
        GridPane gPane = new GridPane();
        gPane.setVgap(10);
        gPane.setPadding(new Insets(10));

        Header header = new Header(resource.getTitle());
        gPane.add(header.getHeader(), 0, 0);
        for (int i = 0, row = 1; i < resource.getLinks().size(); i++) {
            //seperator
            Header sep = new Header("");
            gPane.add(sep.getSeparator(), 0, row);
            row++;
            //description heading
            Header descriptionHeading = new Header("Description");
            gPane.add(descriptionHeading.getHeader(FontWeight.MEDIUM, 20), 0, row);
            row++;
            //description text
            Text descriptionText = new Text(resource.getLinks().get(i).getDescription());
            gPane.add(descriptionText, 0, row);
            row++;
            //url text
            Text urlText = new Text("URL: " + resource.getLinks().get(i).getUrl());
            gPane.add(urlText, 0, row);
            row++;
        }
        HomeScreen.setCenter(gPane);
    }

    //form screen for creating a new problem area
    public static void createProblemArea(){
        GridPane gPane = new GridPane();
        gPane.getStyleClass().add("test");

        String[] labels = {"Problem Name", "Potential Impact", "Root Cause"};
        String[] ids = {"name", "impact", "cause"};
        Form form = new Form(labels, ids, "Problem Description");
        form.displayFormFields(gPane);

        Label heading2 = new Label("Problem Details");
        heading2.setFont(Font.font("Arail", FontWeight.BOLD, 20));
        TextArea problemStatement = new TextArea();
        problemStatement.setPromptText("Problem Statement");

        TextArea nextSteps = new TextArea();
        nextSteps.setPromptText("Next Steps / Most Value Thing to Work On");

        Button submit = new Button("Create Problem Area");
        submit.getStyleClass().add("submit-button");
        Text warning = new Text("Problem Area at Least Needs a Name");

        gPane.add(heading2, 0, labels.length + 1);
        gPane.add(problemStatement, 0, labels.length + 2);
        gPane.add(nextSteps, 0, labels.length + 3);
        gPane.add(submit, 0, labels.length + 4);
        gPane.add(warning, 0, labels.length + 5);
        warning.setVisible(false);


        submit.setOnAction(value -> {
            ProblemArea problem = new ProblemArea();
            form.formToProblemArea(problem);
            if (validName(form)){

                problem.setProblemStatement(problemStatement.getText());
                problem.setNextStep(nextSteps.getText());

                //the current user is the only one who can create any content
                //any methods with a user parameter can be given as the current user
                ((Individual)User.currUser).getProblemAreas().add(problem);
                try {
                    DataServices.createProblemAreas(problem);
                    DataServices.updateUser((Individual)User.currUser);
                } catch (IOException e) {
                    System.out.println(e);
                }
                //the current user is the only one who can create any content
                problemAreaGrid(User.currUser);
            }
            else{
                warning.setVisible(true);
            }
        });
        HomeScreen.setCenter(gPane);
    }
}
