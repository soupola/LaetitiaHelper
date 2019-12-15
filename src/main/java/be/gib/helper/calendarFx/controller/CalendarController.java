package be.gib.helper.calendarFx.controller;

import be.gib.helper.core.MainController;
import com.calendarfx.view.CalendarView;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class CalendarController extends MainController {

    @FXML
    private TabPane mainCalendar;

    @FXML
    void initialize() {
        assert mainCalendar != null : "fx:id=\"mainCalendar\" was not injected: check your FXML file 'calendarView.fxml'.";
        setCalendarController(this);
        if (!getSchedulers().isEmpty()) {
            CalendarView calendar = CalendarFactory.getLoadedCalendar(getSchedulers());
            Tab tab = new Tab();
            tab.setClosable(false);
            tab.setContent(calendar);
            tab.setText("Calendar");
            mainCalendar.getTabs().add(tab);
        } else {
            System.err.println("No scheduler to display");
        }
    }
}
