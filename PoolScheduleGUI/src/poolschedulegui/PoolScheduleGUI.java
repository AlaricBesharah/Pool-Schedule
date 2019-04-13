package poolschedulegui;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class PoolScheduleGUI extends Application {
    
    ScheduleModel model;
    InteractionModel iModel;
    ScheduleView view;
    
    private String[] dayss = {"Sunday","Monday","Tuesday","Wednesday",
                                "Thursday","Friday","Saturday",};
    List<String> days = Arrays.asList(dayss);
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        
        
        
        model = new ScheduleModel();
        model.populate("sunday.txt");
        model.populate("monday.txt");
        model.populate("tuesday.txt");
        
        iModel = new InteractionModel();
        view = new ScheduleView();
        
        view.setModel(model);
        view.setIModel(iModel);
        
        iModel.addSubscriber(view);
        
        CustomSlider cs = new CustomSlider(0, 59, 0);
        HBox container = new HBox();
        container.setAlignment(Pos.CENTER);
        container.getChildren().add(cs);
        
        Label label1 = new Label();
        label1.setText("Choose day");
        
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        
        
        days.forEach(d ->{choiceBox.getItems().add(d);});
        choiceBox.setValue("Sunday");
        
        cs.setOnMouseDragged(event->{
            int timeValue = (int)cs.getValue();
            int day = ScheduleModel.stringToDay(choiceBox.getValue());
            iModel.setDay(day);
            iModel.setTime(timeValue);
        });

        Button btn = new Button();
        btn.setText("Update Display");
        
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        
        root.getChildren().addAll(view, container, label1, choiceBox);
        
        Scene scene = new Scene(root, 800, 700);
        
        primaryStage.setTitle("Pool Schedule");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        view.draw();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
