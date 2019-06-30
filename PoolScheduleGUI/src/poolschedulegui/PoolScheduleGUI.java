package poolschedulegui;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class PoolScheduleGUI extends Application {
    
    ScheduleModel model;
    InteractionModel iModel;
    ScheduleView view;
    
    private final String[] dayss = {"Sunday","Monday","Tuesday","Wednesday",
                              "Thursday","Friday","Saturday"};
    List<String> days = Arrays.asList(dayss);
    
    ImageView iv = new ImageView(new Image(getClass().getResourceAsStream("/FakePool.png")));
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        
        model = new ScheduleModel();
        model.populate("sunday.txt");
        model.populate("monday.txt");
        model.populate("tuesday.txt");
        model.populate("wednesday.txt");
        model.populate("thursday.txt");
        
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
        label1.setText("Choose day:");
        
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        
        days.forEach(d ->{choiceBox.getItems().add(d);});
        choiceBox.setValue(dayss[0]);
        
        cs.setOnMouseDragged(event->{
            int timeValue = (int)cs.getValue();
            int day = ScheduleModel.stringToDay(choiceBox.getValue());
            iModel.setDay(day);
            iModel.setTime(timeValue);
        });
        
        Pane pane = new Pane();
        pane.getChildren().addAll(iv);
        
        AnchorPane top = new AnchorPane();
        AnchorPane.setTopAnchor(pane, -55.0);
        AnchorPane.setLeftAnchor(pane, 20.0);
        AnchorPane.setRightAnchor(choiceBox, 20.0);
        AnchorPane.setTopAnchor(choiceBox, -25.0);
        AnchorPane.setRightAnchor(label1, 130.0);
        AnchorPane.setTopAnchor(label1, -20.0);
        
        top.getChildren().addAll( pane,label1, choiceBox);
        
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        
        root.getChildren().addAll(top, view, container);
        
        Scene scene = new Scene(root, 800, 750);
        
        primaryStage.setTitle("Interactive Pool Schedule");
        primaryStage.getIcons().add(new Image("icon.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
        
        view.draw();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
