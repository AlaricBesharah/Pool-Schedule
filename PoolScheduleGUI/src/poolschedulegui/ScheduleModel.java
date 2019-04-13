/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poolschedulegui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 *
 * @author Alaric
 */
public class ScheduleModel {
    
    private ArrayList<Day> schedule;
    
    // Placeholder values till I do the quick math
    private static final int weekdayLength = 60;
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
    
    public ArrayList<Day> getSchedule(){
        return schedule;
    }
    
    public void insert(Event e, int day, int time){
        schedule.get(day).insert(e, time);
    }
    
    // Takes the information from a properly formatted txt file
    // and adds it to the right day.
    public void populate(String fileName) throws IOException{
        
        int day = stringToDay(fileName);
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(
                ScheduleModel.class.getResourceAsStream(fileName),
                StandardCharsets.UTF_8))) {}
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splited = line.split("\\s+");
                int start = Integer.parseInt(splited[3]);
                int range = Integer.parseInt(splited[4]) - start;
                int priority = Integer.parseInt(splited[1]);
                int lanes = Integer.parseInt(splited[2]);
                String name = splited[0];
                for(int i = start; i < start + range + 1; i++){
                    Event tempEvent = new Event(lanes, priority, name);
                    insert(tempEvent, day, i);
                }
            }
        
        } catch (Exception e) {
            System.out.println("file not found: " + e.toString());
        }
    }
    
    // Takes all acceptable file names and 
    // returns the corresponding integer [0-6]
    static public int stringToDay(String name){
        try{
            switch (name) {
                case "sunday.txt":
                    return 0;
                case "monday.txt":
                    return 1;
                case "tuesday.txt":
                    return 2;
                case "wednesday.txt":
                    return 3;
                case "thursday.txt":
                    return 4;
                case "friday.txt":
                    return 5;
                case "saturday.txt":
                    return 6;
            }
        }catch(Exception e){
            System.out.println(name + " is not a valid file name.");
        }
        return 0;
    }
    
    public static void main(String[] args){
        ScheduleModel boggs = new ScheduleModel();
        boggs.populate("sunday.txt");
        boggs.populate("monday.txt");
        boggs.getSchedule().get(0).timeSlots.get(28).printTimeSlot();
    }
}
