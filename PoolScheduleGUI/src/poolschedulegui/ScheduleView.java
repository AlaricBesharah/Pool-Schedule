package poolschedulegui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ScheduleView extends Pane implements ModelListener{
    
    Canvas canvas;
    GraphicsContext gc;
    ScheduleModel model;
    InteractionModel iModel;
    private static Color[] colors;
    
    public ScheduleView(){
        canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();
        getChildren().add(canvas);
        
        colors = new Color[9];
        colors[0] = Color.rgb(50,161,252,0.9); // blue lane swimming rgb(50,161,252)
        colors[1] = Color.rgb(255,204,0,0.9); // orange fam swim rgb(255,204,0)
        colors[2] = Color.rgb(255,66,94,0.9); // red aqaufit rgb(255,66,94)
        colors[3] = Color.rgb(38,211,119,0.9); // green lesson  rgb(38,211,119)
        colors[4] = Color.rgb(193,61,237,0.9); // purple rental rgb(193,61,237)
    }
    
    public void setModel(ScheduleModel newModel){
        this.model = newModel;
    }
    
    public void setIModel(InteractionModel newIModel){
        this.iModel = newIModel;
    }
    
    public void draw(){
        gc.clearRect(0, 0, 800, 600);
        
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 800, 600);
        
        for(int i = 0; i < 6; i++){
            int x = i * 100 + 100;
            gc.setFill(Color.BLACK);
            gc.fillRect(x + 40, 100, 20, 300);
            gc.fillRect(x + 20, 100, 60, 20);
            gc.fillRect(x + 20, 400, 60, 20);
        }
        
        
        
        double currentX = 100;
        int lanes = 0;
        
        TimeSlot curr = model.getSchedule().get(iModel.day).timeSlots.get(iModel.time);
        
        for (Event event : curr.myEvents) {
            gc.setFill(getColorFromName(event.name));
            gc.fillRect(currentX, 50, 100*event.lanes, 400);
            
            gc.setFont(new Font("Verdana", 20));
            gc.setFill(Color.WHITE);
            gc.fillText(event.name, currentX + 50*(event.lanes-1), 250);
            currentX += 100*event.lanes;
        }
    }

    @Override
    public void update() {
        this.draw();
    }
    
    private static Color getColorFromName(String name){
        switch(name){
            case "LAPSWIM":
                return colors[0];
            case "FAMSWIM":
                return colors[1];
            case "AQUAFIT":
                return colors[2];
            case "LESSONS":
                return colors[3];
            case "RENTAL":
                return colors[4];
            default:
                return null;
        }
    }
}
