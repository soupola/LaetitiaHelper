package be.gib.helper;

import be.gib.helper.core.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        MainController mainController = new MainController();
        mainController.showWindows(primaryStage, "/csvLoader.fxml");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
