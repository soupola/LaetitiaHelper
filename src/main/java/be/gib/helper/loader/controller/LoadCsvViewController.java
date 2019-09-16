package be.gib.helper.loader.controller;

import be.gib.helper.core.beans.Show;
import be.gib.helper.loader.file.FileLoader;
import be.gib.helper.loader.file.impl.ExcelFileProcessor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
        if (!csvPathTa.getText().trim().isEmpty()) {
            try {
                ExcelFileProcessor processor = new ExcelFileProcessor();
                ArrayList<Show> shows = processor.generateShowsFromFile(csvPathTa.getText());
                assert shows != null;
                for (Show s : shows) {
                    System.out.println(s.toString());
                }
            } catch (IOException e) {
                System.out.println("Unable to load file from disk");
            }
        }
    }

    @FXML
    void initialize() {
        assert csvPathTa != null : "fx:id=\"csvPathTa\" was not injected: check your FXML file 'Untitled'.";

    }
}
