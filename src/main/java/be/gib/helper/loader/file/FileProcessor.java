package be.gib.helper.loader.file;

import be.gib.helper.core.beans.Show;
import be.gib.helper.core.enums.EnumShowType;
import be.gib.helper.loader.constant.FileProcessorConstant;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileProcessor {

    public static ArrayList<Show> generateShowsFromFile(File file) throws IOException, ParseException {
        ArrayList<Show> shows = new ArrayList<>();
        List<String> lines = readLines(file.getAbsolutePath());
        for (String line : lines) {
            String[] split = line.split(";");
            shows.add(generateShow(split));
        }
        return shows;
    }

    private static Show generateShow(String[] split) throws ParseException {
        Long timeInMinute = (long) Integer.parseInt(split[FileProcessorConstant.DURATION]);
        String title = split[FileProcessorConstant.NAME];
        Duration duration = Duration.ofMinutes(timeInMinute);
        boolean recurent = Boolean.parseBoolean(split[FileProcessorConstant.RECUR]);
        String country = split[FileProcessorConstant.NAT];
        EnumShowType type = EnumShowType.match(split[FileProcessorConstant.TYPE]);
        Date date = new SimpleDateFormat(FileProcessorConstant.DATE_FORMAT).parse(split[FileProcessorConstant.DATE]);
        return new Show(title, duration, recurent, country, type, date);
    }

    public static ArrayList<Show> generateShowsFromFile(String path) throws IOException, ParseException {
        File file = new File(path);
        if (file.exists()) {
            return generateShowsFromFile(file);
        }
        return null;
    }

    private static List<String> readLines(String path) throws IOException {
        return Files.readAllLines(Paths.get(path));
    }
}
