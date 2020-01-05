package be.gib.helper.loader.file.impl;

import be.gib.helper.core.bean.Scheduler;
import be.gib.helper.core.bean.Show;
import be.gib.helper.core.bean.TimeSlot;
import be.gib.helper.core.enums.EnumChaine;
import be.gib.helper.loader.file.FileProcessor;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import static be.gib.helper.loader.constant.FileProcessorConstant.DATE_FORMAT;

public class ExcelFileProcessor extends FileProcessor {
    public ArrayList<Scheduler> extractFromFile(String path) throws IOException {
        File file = new File(path);
        if (file.exists()) {
            return extractFromFile(file);
        }
        return null;
    }

    public ArrayList<Scheduler> extractFromFile(File file) throws IOException {
        return extractAllFromFile(file);
    }

    private ArrayList<Scheduler> extractAllFromFile(File file) throws IOException {
        FileInputStream excelFile = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(excelFile);
        ArrayList<Scheduler> schedulers = new ArrayList<>();

        //boucle pour les sheets
        for (int sheetNbr = 0; sheetNbr < workbook.getNumberOfSheets(); sheetNbr++) {
            Sheet currentSheet = workbook.getSheetAt(sheetNbr);
            //transformation en csv
            ArrayList<TimeSlot> timeSlots = new ArrayList<>();
            for (Row currentRow : currentSheet) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < currentRow.getPhysicalNumberOfCells(); i++) {
                    System.out.println(currentSheet.getSheetName() + " : " + currentRow.getCell(i).toString());
                    currentRow.getCell(i).setCellType(CellType.STRING);
                    sb.append(currentRow.getCell(i).getStringCellValue());
                    sb.append(";");
                }
                try {
                    String[] split = sb.toString().split(";");
                    if (split.length > 4) {
                        String[] showInfo = Arrays.copyOfRange(split, 0, 4);
                        String[] showDate = Arrays.copyOfRange(split, 4, split.length);
                        Show show = generateShow(showInfo);
                        TimeSlot timeSlot = new TimeSlot(show);
                        for (String date : showDate) {
                            timeSlot.addStartDate(DATE_FORMAT.parse(date));
                        }
                        timeSlots.add(timeSlot);
                    }
                } catch (ParseException e) {
                    System.out.println(sb.toString());
                    e.printStackTrace();
                }
            }
            schedulers.add(new Scheduler(timeSlots, EnumChaine.match(currentSheet.getSheetName())));
        }
        return schedulers;
    }
}