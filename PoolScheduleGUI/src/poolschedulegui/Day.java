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
public class Day {
    ArrayList<TimeSlot> timeSlots;
    int size;
    
    public Day(int s){
        size = s;
        timeSlots = new ArrayList<>();
        for(int i=0; i<s; i++){
            TimeSlot n = new TimeSlot();
            timeSlots.add(n);
        }
    }
    
    public void insert(Event e, int time){
        timeSlots.get(time).insert(e);
    }
    
    
}
