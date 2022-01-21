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

public class CreatePage {

    public static boolean validName(Form form){
        FormField field = form.getFormFieldById("name");
        return !field.getText().equals("") && !field.getText().equals(field.getPromptText());
    }

    public static Rectangle block(String text, String styles, Color color, FontWeight weight, int FontSize, Pane pane){
        StackPane stack = new StackPane();
        Rectangle rect = new Rectangle(200, 200);
        rect.setFill(color);
        Button title = new Button(text);
        title.setFont(Font.font("Arial", weight, FontSize));
        title.setTextFill(Color.WHITE);

        String r = Integer.toHexString((int) (color.getRed() * 255));
        String g = Integer.toHexString((int) (color.getGreen() * 255));
        String b = Integer.toHexString((int) (color.getBlue() * 255));
        r = r.length() == 1 ? r + "0" : r;
        g = g.length() == 1 ? g + "0" : g;
        b = b.length() == 1 ? b + "0" : b;

        title.setStyle(styles + "-fx-background-color: #" + r + g + b);
        Rectangle test = new Rectangle(200, 200);
        test.setFill(Color.TRANSPARENT);
        stack.getChildren().addAll(rect, title, test);
        pane.getChildren().add(stack);
        return test;
    }

    public static FlowPane displayContentGrid(ArrayList<Content> content, Insets padding){
        FlowPane fPane = new FlowPane(10, 10);
        fPane.setPadding(padding);
        fPane.setPrefWrapLength(800);

        Rectangle[] contentRectangles = new Rectangle[content.size()];
        for (int i = 0; i < contentRectangles.length; i++) {
            contentRectangles[i] = block(content.get(i).getTitle(), "", User.colors[i % User.colors.length], FontWeight.NORMAL, 25, fPane);
        }

        //CREATE ADD BUTTON
        Rectangle createRectangle = block("+", "", Color.BLACK, FontWeight.BOLD, 80, fPane);
        createRectangle.setId("test");

        return fPane;
    }

    public static void displayContent(){
        GridPane gPane = new GridPane();
        gPane.setPadding(new Insets(10));

        Text articleHeading = new Text("Articles");
        articleHeading.setFont(Font.font("Arail", FontWeight.MEDIUM, 25));

        Text resourceHeading = new Text("Resources");
        resourceHeading.setFont(Font.font("Arail", FontWeight.MEDIUM, 25));

        ArrayList<Content> articles = ProblemArea.currProblemArea.getArticles();

        ArrayList<Content> resources = ProblemArea.currProblemArea.getResources();
        FlowPane articlePane = displayContentGrid(articles, new Insets(15, 0, 15, 0));
        for (int i = 0; i < articlePane.getChildren().size(); i++) {
            int iterator = i;
            articlePane.getChildren().get(i).setOnMouseClicked(value -> {
                if (iterator == articlePane.getChildren().size() - 1){
                    createArticle();
                }
                else{
                    //open document
                }
            });
        }

        FlowPane resourcePane = displayContentGrid(resources, new Insets(15, 0, 15, 0));
        for (int i = 0; i < resourcePane.getChildren().size(); i++) {
            int iterator = i;
            resourcePane.getChildren().get(i).setOnMouseClicked(value -> {
                if (iterator == resourcePane.getChildren().size() - 1){
                    createResource();
                }
                else{
                    //open document
                }
            });
        }

        gPane.add(problemAreaHeader(ProblemArea.currProblemArea), 0, 0);
        gPane.add(articleHeading, 0, 1);
        gPane.add(articlePane, 0, 2);
        gPane.add(resourceHeading, 0, 3);
        gPane.add(resourcePane, 0, 4);


        // VBox pane = new VBox(10, artsicleHeaderBox, articleBox);
        HomeScreen.setCenter(gPane);
    }

    public static GridPane problemAreaHeader(ProblemArea problemArea){
        GridPane gPane = new GridPane();
        String[] text = {problemArea.getName(), problemArea.getProblemStatement(), problemArea.getImpact(), 
                        problemArea.getRootCause(), problemArea.getNextStep()};
        String[] labels = {"", "Problem Statement", "Potential Impact", "Root Cause", "Next Steps"};

        for (int i = 0; i < text.length; i++) {
            if (text[i] != null && !text[i].isEmpty()){
                if (i == 0){
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
    
    public static GridPane problemAreaGrid(){
        GridPane gPane = new GridPane();
        gPane.setPadding(new Insets(15));
        gPane.setVgap(10);
        
        FlowPane fPane = new FlowPane(10, 10);
        //top, right, bottom, left
        // fPane.setPadding(new Insets(15, 10, 15, 10));
        fPane.setPrefWrapLength(800);

        ArrayList<ProblemArea> problemAreas = ((Individual) User.currUser).getProblemAreas();
        Rectangle[] problemRectangles = new Rectangle[problemAreas.size()];
        for (int i = 0; i < problemAreas.size(); i++) {
            problemRectangles[i] = block(problemAreas.get(i).getName(), "", User.colors[i % User.colors.length], FontWeight.MEDIUM, 25, fPane);

        }

        for (int i = 0; i < problemRectangles.length; i++) {
            int iterator = i;
            problemRectangles[i].setOnMouseClicked(value -> {
                ProblemArea.currProblemArea = problemAreas.get(iterator);
                displayContent();
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

    public static void createArticle(){
        GridPane gPane = new GridPane();
        gPane.getStyleClass().add("test");

        String[] labels = {"Title"};
        String[] ids = {"name"};

        Form form = new Form(labels, ids, "New Article");
        form.displayFormFields(gPane);
        TextArea body = new TextArea();
        gPane.add(body, 0, labels.length + 2);

        Text warning = new Text("Problem Area at Least Needs a Name");
        gPane.add(warning, 0, labels.length + 1);
        warning.setVisible(false);

        Button create = new Button("Create");
        create.getStyleClass().add("submit-button");
        gPane.add(create, 0, labels.length + 3);

        create.setOnAction(value -> {
            Article article = new Article(ProblemArea.currProblemArea);
            if(validName(form)){
                form.formToArticle(article);
                article.setBody(body.getText());
                displayContent();
            }
        });

        HomeScreen.setCenter(gPane);

    }

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

        gPane.add(resourcePane, 0, labels.length + 3);

        Text warning = new Text("Resource at Least Needs a Name");
        gPane.add(warning, 0, labels.length + 1);
        warning.setVisible(false);

        Button create = new Button("Create");
        create.getStyleClass().add("submit-button");
        gPane.add(create, 0, labels.length + 4);

        create.setOnAction(value -> {
            if(validName(form)){
                displayContent();
            }
            else{
                warning.setVisible(true);
            }
        });


        addMore.setOnAction(value -> {
            Resource resource = new Resource(ProblemArea.currProblemArea);
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


            finishResource.setOnAction(e -> {
                if(link.getText().isEmpty() || description.getText().isEmpty()){
                    emptyWarning.setVisible(true);
                }
                else{
                    resource.getLinks().add(new Link(link.getText(), description.getText()));
                    addMore.setVisible(true);
                    resourcePane.getChildren().removeAll(link, description, finishResource, emptyWarning);
                }
            });
        });

        HomeScreen.setCenter(gPane);
    }

    public static void displayArticle(){
        GridPane gPane = new GridPane();
        gPane.getStyleClass().add("test");
        
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

                problem.setRootCause(problemStatement.getText());
                problem.setNextStep(nextSteps.getText());

                ((Individual)User.currUser).getProblemAreas().add(problem);
                try {
                    DataServices.updateProblemAreas((Individual)User.currUser, problem);
                } catch (IOException e) {
                    System.out.println(e);
                }
                problemAreaGrid();
            }
            else{
                warning.setVisible(true);
            }
        });
        HomeScreen.setCenter(gPane);
    }
}
