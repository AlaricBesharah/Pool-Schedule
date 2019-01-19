/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poolschedulegui;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Alaric
 */
public class TimeSlot {
    ArrayList<Event> myEvents;
    
    public TimeSlot(){
        myEvents = new ArrayList<>();
    }
    
    public void insert(Event e){
        myEvents.add(e);
        Collections.sort(myEvents);
    }
    
    public static void main(String[] args){
        System.out.println("Boggesh");
        
        TimeSlot t = new TimeSlot();
        
        Event e1 = new Event(3,3,"Lane Swim");
        Event e2 = new Event(1,1,"Pub Swim");
        Event e3 = new Event(2,5,"Rental");
        Event e4 = new Event(7,1,"Lessons");
        
        t.insert(e1);
        t.insert(e2);
        t.insert(e3);
        t.insert(e4);
        
        t.myEvents.forEach((a)->System.out.println(a));
    }
}
