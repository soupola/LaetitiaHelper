package be.gib.helper.calendarFx.calendarVo;

import be.gib.helper.core.enums.EnumShowType;

import java.time.Duration;
import java.time.LocalDate;

public class CalendarEntryVo {
    LocalDate startDate;
    LocalDate endDate;
    private String title;
    private Duration duration;
    private String country;
    private EnumShowType type;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public EnumShowType getType() {
        return type;
    }

    public void setType(EnumShowType type) {
        this.type = type;
    }
}
