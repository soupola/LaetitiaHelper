package be.gib.helper.loader.file;

import be.gib.helper.core.beans.Show;
import be.gib.helper.core.enums.EnumShowType;
import be.gib.helper.loader.constant.FileProcessorConstant;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

public abstract class FileProcessor {
    public abstract ArrayList<Show> generateShowsFromFile(String path) throws IOException;

    public abstract ArrayList<Show> generateShowsFromFile(File file) throws IOException;

    protected Show generateShow(String extract) throws ParseException {
        String[] split = extract.split(";");
        if (split.length > 0) {
            Long timeInMinute = (long) Integer.parseInt(split[FileProcessorConstant.DURATION]);
            String title = split[FileProcessorConstant.NAME];
            Duration duration = Duration.ofMinutes(timeInMinute);
            boolean recurent = Boolean.parseBoolean(split[FileProcessorConstant.RECUR]);
            String country = split[FileProcessorConstant.NAT];
            EnumShowType type = EnumShowType.valueOf(split[FileProcessorConstant.TYPE]);
            return new Show(title, duration, recurent, country, type);
        }
        return null;
    }

}
