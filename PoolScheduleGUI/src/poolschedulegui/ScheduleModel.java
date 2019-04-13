/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poolschedulegui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
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
    public void populate(String fileName) throws FileNotFoundException {
        
        int day = stringToDay(fileName);
        InputStream in = getClass().getResourceAsStream(fileName); 
        try (BufferedReader br = new BufferedReader(new FileReader("textfiles/" + fileName))) {
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
    
    static public int stringToDay(String name){
        
        switch (name) {
            case "sunday.txt":    case "Sunday":
                return 0;
            case "monday.txt":    case "Monday":
                return 1;
            case "tuesday.txt":   case "Tuesday":
                return 2;
            case "wednesday.txt": case "Wednesday":
                return 3;
            case "thursday.txt":  case "Thursday":
                return 4;
            case "friday.txt":    case "Friday":
                return 5;
            case "saturday.txt":  case "Saturday":
                return 6;
            default:
                return 0;
        }
    }
    
    public static void main(String[] args){
    }
}
