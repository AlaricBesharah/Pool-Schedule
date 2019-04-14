/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poolschedulegui;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author Alaric
 */
public class CustomSlider extends Pane{
    private final double minimum;
    private final double maximum;
    private double canvasWidth = 650;
    private double canvasHeight = 60;
    private double nubSize = 18;
    private double barWidth = canvasWidth - (nubSize/2) - 100;
    
    // Defining a variable for the property 
    private final DoubleProperty myValue = new SimpleDoubleProperty();
    
    // Getter for the value
    public final double getValue(){return myValue.get();}
    
    // Setting for the value
    public final void setValue(double value){myValue.set(value);}
    
    // Getter of the property
    public DoubleProperty valueProperty(){return myValue;}
    
    public CustomSlider(double min, double max, double init) {
        this.maximum = max;
        this.minimum = min;
        
        if(init > max){
            init = max;
            this.setValue(init);
        }else if(init < min){
            init = min;
            this.setValue(init);
        }else{
            this.setValue(init);
        }
        
        StackPane widget = new StackPane();
        Canvas widgetCanvas  = new Canvas (canvasWidth, canvasHeight);
        GraphicsContext gc  = widgetCanvas.getGraphicsContext2D();

        // Grey bar
        gc.setFill(Color.rgb(140, 140, 140));
        gc.fillRoundRect(6, 6, canvasWidth - (nubSize/3)*2, 6, 6, 6);
        
        // Shadow on nub
        gc.setFill(Color.rgb(0,0,0,0.3));
        gc.fillOval(hamburgerHelper(maximum, init, canvasWidth), 2, 18, 18);
        
        // color nub
        gc.setFill(Color.DODGERBLUE);
        gc.fillOval(hamburgerHelper(maximum, init, canvasWidth), 0, 18, 18);
        
        // Current time of slider
        gc.setFont(new Font("Verdana", 20));
        gc.setFill(Color.BLACK);
        gc.fillText(returnTimeValue(), 10, 40);
        
        widget.getChildren().add(widgetCanvas);
        
        this.getChildren().add(widget);
        
        // Redrawing on 
        widget.setOnMouseDragged(e -> {
            double newX; // Value used when redrawing the canvas
            
            // Ensuring X coordinate that is recorded in newX is not
            // out of bounds.
            if (e.getX() < 9){
                newX = 9;
            }else if (e.getX() > canvasWidth - (nubSize/3)*2){
                newX = canvasWidth - (nubSize/3)*2;
            }else{
                newX = e.getX();
            }
            
            // Redrawing bar, shadow, and nub
            gc.clearRect(0,0,canvasWidth,canvasHeight);
            
            gc.setFill(Color.rgb(140, 140, 140));
            gc.fillRoundRect(6, 6, canvasWidth - (nubSize/3)*2, 6, 6, 6);
            
            gc.setFill(Color.rgb(0,0,0,0.3));
            gc.fillOval(newX - 9, 2, 18, 18);
            
            gc.setFill(Color.DODGERBLUE);
            gc.fillOval(newX - 9, 0, 18, 18);
            
            double timeX;
            if(newX < 40){
                timeX = 40;
            }else if(newX > canvasWidth - 60){
                timeX = canvasWidth - 60;            }
            else{
                timeX = newX;
            }
            gc.setFont(new Font("Verdana", 20));
            gc.setFill(Color.BLACK);
            gc.fillText(returnTimeValue(), timeX - 30, 40);
            
            // Setting value of slider without bounds
            this.setValue(minimum + ((e.getX() / canvasWidth)*maximum));
            if(this.getValue() < minimum){
                setValue(minimum);
            }else if(this.getValue() > maximum){
                setValue(maximum);
            }
            System.out.println(newX);
        });
    }
    
    private static double hamburgerHelper(double max, double init, double width){
        double result = (init/max)*(width - 12); // width - ()
        return result;
    }
    
    private String returnTimeValue(){
        
        int time;
        time = (int)Math.round(getValue());
        int initialCalc = 600 + 25 * time;

        double lastDouble = initialCalc % 100;

        int lastInt = (int)(lastDouble/100 * 60);
        int firstInt;

        if(Integer.toString(initialCalc).length() >= 4)
            firstInt = Integer.parseInt(Integer.toString(initialCalc).substring(0, 2));
        else
            firstInt = Integer.parseInt(Integer.toString(initialCalc).substring(0, 1));
        
        String morningAfternoon = "am";
        if(firstInt > 12){
            morningAfternoon = "pm";
            firstInt -= 12;
        }
        
        if(firstInt > 11){
            morningAfternoon = "pm";
        }
        
        String result;
        if(lastInt == 0){
            result = (firstInt + ":" + lastInt + "0");
        }else{
            result = (firstInt + ":" + lastInt);
        }
        
        
        return result + morningAfternoon;
    }
}
