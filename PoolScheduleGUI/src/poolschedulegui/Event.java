/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poolschedulegui;

/**
 *
 * @author Alaric
 */
public class Event implements Comparable<Event>{
    int lanes;
    int priority;
    String name;
    
    public Event(int l, int p, String n){
        this.lanes = l;
        this.priority = p;
        this.name = n;
    }

    @Override
    public int compareTo(Event other) {
        return priority - other.priority;
    }
    
    @Override
    public String toString(){
        return "Lanes: " + lanes + ", Priority: " + priority + ", Name: " + name;
    }
}
