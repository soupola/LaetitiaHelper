package be.gib.helper.loader.constant;

import java.text.SimpleDateFormat;

public class FileProcessorConstant {
    public static final int NAME = 0;
    public static final int NAT = 1;
    public static final int DURATION = 3;
    public static final int TYPE = 2;
    private static final String DATE_FORMAT_STRING = "dd.MM.yyyy.HH.mm";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_STRING);
}
