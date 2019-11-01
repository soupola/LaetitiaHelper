package be.gib.helper.loader.file;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileLoader {
    public static File pickFile() {
        FileChooser fileChooser = new FileChooser();
        return fileChooser.showOpenDialog(new Stage());
    }
}
