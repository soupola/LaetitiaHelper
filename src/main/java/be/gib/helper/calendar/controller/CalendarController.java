package be.gib.helper.calendar.controller;

import be.gib.helper.core.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

public class CalendarController extends MainController {

    @FXML
    private TabPane mainCalendar;

    @FXML
    void initialize() {
        assert mainCalendar != null : "fx:id=\"mainCalendar\" was not injected: check your FXML file 'calendarView.fxml'.";
        setCalendarController(this);
        if (!getSchedulers().isEmpty()) {
            System.out.println("NOT EMPTY");
        }
    }
}
