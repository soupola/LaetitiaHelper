package loader.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class LoadCsvViewController {

    @FXML
    private TextArea csvPathTa;

    @FXML
    void browse(ActionEvent event) {

    }

    @FXML
    void generate(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert csvPathTa != null : "fx:id=\"csvPathTa\" was not injected: check your FXML file 'Untitled'.";

    }
}
