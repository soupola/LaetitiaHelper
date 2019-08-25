package core.beans;

import core.enums.EnumShowType;

import java.time.Duration;
import java.util.Date;

public class Show {
    private String title;
    private Duration duration;
    private boolean recurent;
    private String country;
    private EnumShowType type;
    private Date date;

    public Show(String title, Duration duration, boolean recurent, String country, EnumShowType type, Date date) {
        this.title = title;
        this.duration = duration;
        this.recurent = recurent;
        this.country = country;
        this.type = type;
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public boolean isRecurent() {
        return recurent;
    }

    public void setRecurent(boolean recurent) {
        this.recurent = recurent;
    }

    public EnumShowType getType() {
        return type;
    }

    public void setType(EnumShowType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Show{" +
                "title='" + title + '\'' +
                ", duration=" + duration +
                ", recurent=" + recurent +
                ", country='" + country + '\'' +
                ", type=" + type +
                ", date=" + date +
                '}';
    }
}
