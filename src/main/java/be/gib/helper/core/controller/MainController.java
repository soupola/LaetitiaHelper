package be.gib.helper.core.controller;

import be.gib.helper.calendar.controller.CalendarController;
import be.gib.helper.core.bean.Scheduler;
import be.gib.helper.loader.controller.LoadCsvViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainController {
    private static LoadCsvViewController loaderController;
    private static CalendarController calendarController;
    private static ArrayList<Scheduler> schedulers = new ArrayList<>();

    public void showWindows(Stage stage, String path) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(path));
        stage.setTitle("Welcome to laetitia helper app ");
        stage.setScene(new Scene(root));
        stage.show();
    }

    protected void closeAndOpen(Stage stage, String path) throws IOException {
        stage.close();
        showWindows(new Stage(), path);
    }

    protected LoadCsvViewController getLoaderController() {
        return loaderController;
    }

    protected void setLoaderController(LoadCsvViewController loaderController) {
        MainController.loaderController = loaderController;
    }

    protected CalendarController getCalendarController() {
        return calendarController;
    }

    protected void setCalendarController(CalendarController calendarController) {
        MainController.calendarController = calendarController;
    }

    protected ArrayList<Scheduler> getSchedulers() {
        return schedulers;
    }

    protected void setSchedulers(ArrayList<Scheduler> schedulers) {
        MainController.schedulers = schedulers;
    }
}
