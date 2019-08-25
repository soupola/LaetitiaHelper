package loader.controller;

import core.beans.Show;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import loader.file.FileLoader;
import loader.file.FileProcessor;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
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
                ArrayList<Show> shows = FileProcessor.generateShowsFromFile(csvPathTa.getText());
                for (Show s : shows) {
                    System.out.println(s.toString());
                }

            } catch (IOException e) {
                System.out.println("Unable to load file from disk");
            } catch (ParseException e) {
                    System.out.println("unable to parse data");
            }
        }
    }

    @FXML
    void initialize() {
        assert csvPathTa != null : "fx:id=\"csvPathTa\" was not injected: check your FXML file 'Untitled'.";

    }
}
