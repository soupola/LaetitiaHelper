package be.gib.helper.core.bean;

import be.gib.helper.core.bean.TimeSlot;

import java.util.ArrayList;

public class Scheduler {
    private ArrayList<TimeSlot> timeSlots;
    private String name;

    public Scheduler(ArrayList<TimeSlot> timeSlots, String name) {
        this.timeSlots = timeSlots;
        this.name = name;
    }

    public ArrayList<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(ArrayList<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    @Override
    public String toString() {
        return "Scheduler{" +
                "timeSlots=" + timeSlots +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalTime() {
        double duration = 0;
        for (TimeSlot slot : timeSlots) {
            duration += slot.getTotalTime();
        }
        return duration;
    }
}
