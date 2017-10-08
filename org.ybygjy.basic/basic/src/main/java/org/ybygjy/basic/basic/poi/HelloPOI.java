package org.ybygjy.basic.basic.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;

/**
 * Created by leye on 2017/9/23.
 */
public class HelloPOI {
    public static void main(String[] args) {
        File file = new File("/Users/leye/tmp/cainiao_yz_201705.xlsx");
        Path outputFilePath = FileSystems.getDefault().getPath("/Users/leye/tmp/", "cainiao_yz_201705_utf8.txt");
        BufferedWriter bufferedWriter = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
            int numOfSheets = xssfWorkbook.getNumberOfSheets();
            System.out.println("number of sheet=>" + numOfSheets + ":" + xssfWorkbook.getSheetName(0));
            bufferedWriter = Files.newBufferedWriter(outputFilePath, StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
            final BufferedWriter tmpBuffWriter = bufferedWriter;
            xssfWorkbook.forEach(sheetObj ->{
                System.out.println("getPhysicalNumberOfRows:" + sheetObj.getPhysicalNumberOfRows());
                Iterator<Row> rowIterator = sheetObj.rowIterator();
                Row row = rowIterator.next();
                int cellNums = row.getPhysicalNumberOfCells();
                while(rowIterator.hasNext()) {
                    row = rowIterator.next();
                    StringBuilder sbud = new StringBuilder();
                    for (int i = 0; i < cellNums; i++) {
                        Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        String cellVal = cell.getStringCellValue();
                        cellVal = (null == cellVal || "".equals(cellVal)) ? "1970-01-01 00:00:00" : cellVal;
                        sbud.append(cellVal).append(",");
                    }
                    sbud.setLength(sbud.length() - 1);
                    try {
                        tmpBuffWriter.write(sbud.toString());
                        tmpBuffWriter.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}