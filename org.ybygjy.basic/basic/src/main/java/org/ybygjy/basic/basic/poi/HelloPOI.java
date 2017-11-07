package org.ybygjy.basic.basic.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.util.Iterator;

/**
 * Created by leye on 2017/9/23.
 */
public class HelloPOI {
    public static void main(String[] args) {
        File file = new File("/Users/leye/1002_project/1015_双11/101_业务数据/20171107_社区二维码站点明细.xlsx");
        File outputFile = FileSystems.getDefault().getPath("/Users/leye/tmp/", "cainiao_yz_201705_utf8.txt").toFile();
        BufferedWriter bufferedWriter = null;
        FileInputStream fileInputStream = null;
        try {
            if (outputFile.canWrite()) {
                System.out.println("删除输出文件：" + outputFile.delete() + "，path:" + outputFile);
            }
            fileInputStream = new FileInputStream(file);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
            bufferedWriter = Files.newBufferedWriter(outputFile.toPath(), StandardCharsets.UTF_8, StandardOpenOption.CREATE);
            final BufferedWriter tmpBuffWriter = bufferedWriter;
            xssfWorkbook.forEach(sheetObj ->{
                String sheetName = sheetObj.getSheetName();
                System.out.println("getPhysicalNumberOfRows:" + sheetObj.getPhysicalNumberOfRows());
                Iterator<Row> rowIterator = sheetObj.rowIterator();
                Row row = rowIterator.next();
                int cellNums = row.getPhysicalNumberOfCells();
                while(rowIterator.hasNext()) {
                    row = rowIterator.next();
                    StringBuilder sbud = new StringBuilder(sheetName);
                    for (int j = 0; j < cellNums; j++) {
                        Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        CellType cellType = cell.getCellTypeEnum();
                        String cellVal = "";
                        if (CellType.NUMERIC == cellType) {
                            cellVal = new DecimalFormat("0").format(cell.getNumericCellValue());
                        } else {
                            cellVal = String.valueOf(cell.getStringCellValue());
                        }
                        sbud.append(",").append(cellVal.trim());
                    }
                    //sbud.setLength(sbud.length() - 1);
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