import java.io.IOException;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class CreatePage {
    static Pane pane;

    public static boolean validName(Form form){
        FormField field = form.getFormFieldById("name");
        return !field.getText().equals("") && !field.getText().equals(field.getPromptText());
    }

    public static void problemAreaGrid(){
        pane = new FlowPane(10, 10);
        //top, right, bottom, left
        pane.setPadding(new Insets(15, 10, 15, 10));
        ((FlowPane)pane).setPrefWrapLength(800);

        
        ArrayList<ProblemArea> problemAreas = ((Individual) User.currUser).getProblemAreas();
        for (int i = 0; i < problemAreas.size(); i++) {
            StackPane stack = new StackPane();
            Rectangle rect = new Rectangle(200, 200);
            rect.setFill(User.colors[i % User.colors.length]);
            Text title = new Text(problemAreas.get(i).getName());
            title.setFont(Font.font("Arial", FontWeight.BOLD, 25));
            title.setFill(Color.WHITE);
            stack.getChildren().addAll(rect, title);
            pane.getChildren().add(stack);
        }

        StackPane stack = new StackPane();
        Rectangle rect = new Rectangle(200, 200);
        rect.setFill(Color.BLACK);
        Button title = new Button("+");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 80));
        title.setTextFill(Color.WHITE);
        title.setStyle("-fx-background-color: #000");
        stack.getChildren().addAll(rect, title);
        pane.getChildren().add(stack);

        title.setOnAction(value -> {
            createProblemArea();
        });
        //rect.setonmouseclicked

        HomeScreen.setCenter(pane);
    }

    public static void createProblemArea(){
        pane = new GridPane();
        pane.getStyleClass().add("test");

        String[] labels = {"Problem Name", "Potential Impact", "Root Cause"};
        String[] ids = {"name", "impact", "cause"};
        Form form = new Form(labels, ids, "Problem Description");
        form.displayFormFields((GridPane) pane);

        Label heading2 = new Label("Problem Details");
        heading2.setFont(Font.font("Arail", FontWeight.BOLD, 20));
        TextArea problemStatement = new TextArea("Problem Statement");
        TextArea nextSteps = new TextArea("Next Steps / Most Value Thing to Work On");
        Button submit = new Button("Create Problem Area");
        submit.getStyleClass().add("submit-button");
        Text warning = new Text("Problem Area at Least Needs a Name");

        ((GridPane)pane).add(heading2, 0, labels.length + 1);
        ((GridPane)pane).add(problemStatement, 0, labels.length + 2);
        ((GridPane)pane).add(nextSteps, 0, labels.length + 3);
        ((GridPane)pane).add(submit, 0, labels.length + 4);
        ((GridPane)pane).add(warning, 0, labels.length + 5);
        warning.setVisible(false);


        ProblemArea problem = new ProblemArea();
        submit.setOnAction(value -> {
            form.formToProblemArea(problem);
            if (validName(form)){
                problem.setRootCause(problemStatement.getText());
                problem.setNextStep(nextSteps.getText());
                ((Individual)User.currUser).getProblemAreas().add(problem);
                // try {
                //     DataServices.updateProblemAreas((Individual)User.currUser, problem);
                // } catch (IOException e) {
                //     System.out.println(e);
                // }
                problemAreaGrid();
            }
            else{
                warning.setVisible(true);
            }
        });
        HomeScreen.setCenter(pane);
    }
}
