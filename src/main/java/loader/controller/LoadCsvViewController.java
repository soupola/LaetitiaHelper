package loader.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import loader.tool.FileLoader;

import java.io.File;

public class LoadCsvViewController {

    @FXML
    private TextArea csvPathTa;

    @FXML
    void browse(ActionEvent event) {
        File file = FileLoader.pickFile();
        if(file != null)
            csvPathTa.setText(file.getAbsolutePath());
    }

    @FXML
    void generate(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert csvPathTa != null : "fx:id=\"csvPathTa\" was not injected: check your FXML file 'Untitled'.";

    }
}
