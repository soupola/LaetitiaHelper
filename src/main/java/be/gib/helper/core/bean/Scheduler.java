package be.gib.helper.core.bean;

import be.gib.helper.core.enums.EnumChaine;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<TimeSlot> timeSlots;
    private EnumChaine chaine;

    public Scheduler(List<TimeSlot> timeSlots, EnumChaine chaine) {
        this.timeSlots = timeSlots;
        this.chaine = chaine;
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
                '}';
    }

    public EnumChaine getChaine() {
        return chaine;
    }

    public void setChaine(EnumChaine chaine) {
        this.chaine = chaine;
    }

    public double getTotalTime() {
        double duration = 0;
        for (TimeSlot slot : timeSlots) {
            duration += slot.getTotalTime();
        }
        return duration;
    }
}
