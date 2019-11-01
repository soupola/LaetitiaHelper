package be.gib.helper.core.bean;

import be.gib.helper.core.enums.EnumShowType;

import java.time.Duration;

public class Show {
    private String title;
    private Duration duration;
    private String country;
    private EnumShowType type;

    public Show(String title, Duration duration, String country, EnumShowType type) {
        this.title = title;
        this.duration = duration;
        this.country = country;
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
                ", country='" + country + '\'' +
                ", type=" + type +
                '}';
    }
}
