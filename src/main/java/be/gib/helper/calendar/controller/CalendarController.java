package be.gib.helper.calendar.controller;

import be.gib.helper.calendar.builder.CalendarFactory;
import be.gib.helper.calendar.builder.calendarfx.CalendarFactoryImpl;
import be.gib.helper.core.controller.MainController;
import be.gib.helper.stat.builder.ChartFactory;
import be.gib.helper.stat.builder.pie.NbePieBuilder;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.ArrayList;

public class CalendarController extends MainController {

    @FXML
    private TabPane mainCalendar;

    @FXML
    void initialize() {
        assert mainCalendar != null : "fx:id=\"mainCalendar\" was not injected: check your FXML file 'calendarView.fxml'.";
        setCalendarController(this);
        if (!getSchedulers().isEmpty()) {
           // CalendarFactory factory = new CalendarFactoryImpl();
          //  Node calendar = factory.getLoadedCalendar(getSchedulers());

            ArrayList<Node> graphs = new ArrayList();
            graphs.add(ChartFactory.generateNbeChart(getSchedulers().get(0)));

            for (Node node : graphs) {
                Tab tab = new Tab();
                tab.setClosable(false);
                tab.setContent(node);
                mainCalendar.getTabs().add(tab);
            }
        } else {
            System.err.println("No scheduler to display");
        }
    }
}
