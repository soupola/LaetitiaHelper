package be.gib.helper.core.bean;

import org.apache.commons.lang3.time.DateUtils;

import java.util.ArrayList;
import java.util.Date;

public class TimeSlot {
    private ArrayList<Date> startDates = new ArrayList<>();
    private Show show;

    public TimeSlot(Show show) {
        this.show = show;
    }

    public Date getEndDate(Date startDate) {
        if (startDate == null || this.show == null) {
            return null;
        }
        return DateUtils.addMinutes(startDate, (int) show.getDuration().toMinutes());
    }

    public void addStartDate(Date startDate) {
        startDates.add(startDate);
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "startDate=" + startDates +
                ", show=" + show +
                '}';
    }

    public double getTotalTime() {
        return (show.getDuration().getSeconds() / 60) * startDates.size();
    }
    public ArrayList<Date> getStartDates() {
        return startDates;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }
}
