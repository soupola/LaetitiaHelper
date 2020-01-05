package be.gib.helper.core.bean;

import be.gib.helper.core.bean.TimeSlot;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<TimeSlot> timeSlots;
    private String name;

    public Scheduler(List<TimeSlot> timeSlots, String name) {
        this.timeSlots = timeSlots;
        this.name = name;
    }

    public List<TimeSlot> getTimeSlots() {
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
