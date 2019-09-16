package be.gib.helper.loader.file.impl;

import be.gib.helper.core.beans.Show;
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

public class ExcelFileProcessor extends FileProcessor {
    public ArrayList<Show> generateShowsFromFile(String path) throws IOException {
        File file = new File(path);
        if (file.exists()) {
            return generateShowsFromFile(file);
        }
        return null;
    }

    public ArrayList<Show> generateShowsFromFile(File file) throws IOException {
        return extractShowsFromFile(file);
    }

    private ArrayList<Show> extractShowsFromFile(File file) throws IOException {
        FileInputStream excelFile = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(excelFile);
        ArrayList<Show> shows = new ArrayList<>();

        for (int sheetNbr = 0; sheetNbr < workbook.getNumberOfSheets(); sheetNbr++) {
            Sheet currentSheet = workbook.getSheetAt(sheetNbr);
            for (Row currentRow : currentSheet) {
                StringBuilder sb = new StringBuilder();
                int physicalNumberOfCells = currentRow.getPhysicalNumberOfCells();
                for (int i = 0; i < currentRow.getPhysicalNumberOfCells(); i++) {
                    currentRow.getCell(i).setCellType(CellType.STRING);
                    sb.append(currentRow.getCell(i).getStringCellValue());
                    sb.append(";");
                }
                try {
                    shows.add(generateShow(sb.toString()));

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return shows;
    }
}