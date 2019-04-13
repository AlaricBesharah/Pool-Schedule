/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poolschedulegui;

import java.util.ArrayList;

/**
 *
 * @author Alaric
 */
public class InteractionModel {
    int time;
    int day;
    ArrayList<ModelListener> subscribers;
    
    public InteractionModel(){
        time = 0;
        day = 0;
        subscribers = new ArrayList<>();
    }
    
    public void setTime(int t){
        this.time = t;
        notifySubscribers();
    }
    
    public void setDay(int d){
        this.day = d;
        notifySubscribers();
    }
    
    public void addSubscriber(ModelListener aSub){
        subscribers.add(aSub);
    }
    
    private void notifySubscribers() {
        subscribers.forEach((subs) -> {
            subs.update();
        });
    }
}
