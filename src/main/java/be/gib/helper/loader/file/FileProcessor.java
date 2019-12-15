package be.gib.helper.loader.file;

import be.gib.helper.core.Scheduler;
import be.gib.helper.core.bean.Show;
import be.gib.helper.core.enums.EnumShowType;
import be.gib.helper.loader.constant.FileProcessorConstant;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

public abstract class FileProcessor {
    public abstract ArrayList<Scheduler> extractFromFile(String path) throws IOException;

    public abstract ArrayList<Scheduler> extractFromFile(File file) throws IOException;

    protected Show generateShow(String[] extract) {
        if (extract.length == 4) {
            long timeInMinute = (long) Double.parseDouble(extract[FileProcessorConstant.DURATION]);
            String title = extract[FileProcessorConstant.NAME];
            Duration duration = Duration.ofMinutes(timeInMinute);
            String country = extract[FileProcessorConstant.NAT];
            EnumShowType type = EnumShowType.valueOf(extract[FileProcessorConstant.TYPE]);
            return new Show(title, duration, country, type);
        }
        return null;
    }
}
