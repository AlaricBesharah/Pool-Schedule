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
public class ScheduleModel {
    
    ArrayList<Day> schedule;
    
    // Placeholder values till I do the quick math
    private static final int weekdayLength = 50;
    private static final int weekendLength = 60;
    
    public ScheduleModel(){
        schedule = new ArrayList<>();
        for(int i=0; i<7; i++){
            int size;
            if(i == 0 || i == 6)
                size = weekendLength;
            else
                size = weekdayLength;
            Day newDay = new Day(size);
            schedule.add(newDay);
        }
    }
    
    
    // Takes an event e,
    public void insert(Event e, int day, int time){
        schedule.get(day).insert(e, time);
    }
    
    public static void main(String[] args){
        ScheduleModel boggs = new ScheduleModel();
        Event e = new Event(6,1,"Lane Swim");
        boggs.insert(e, 0, 0);
        System.out.println(boggs.schedule.get(0).timeSlots.get(0).myEvents.get(0).toString());
    }
}
