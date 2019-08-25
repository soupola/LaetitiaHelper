package loader.tool;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileLoader {
    public static File pickFile() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            return file;
        }
        return null;
    }
}
