package poolschedulegui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class PoolScheduleGUI extends Application {
    
    ScheduleModel model;
    InteractionModel iModel;
    ScheduleView view;
    
    @Override
    public void start(Stage primaryStage) {
        
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
        
        ChoiceBox<Integer> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll(0,1,2,3,4,5,6);
        choiceBox.setValue(0);
        
        cs.setOnMouseDragged(event->{
            int timeValue = (int)cs.getValue();
            int day = choiceBox.getValue();
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
