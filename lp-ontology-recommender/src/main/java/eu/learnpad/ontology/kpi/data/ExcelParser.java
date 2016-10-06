/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.kpi.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author andreas.martin
 */
public class ExcelParser {

    private static final Logger LOGGER = Logger.getLogger(ExcelParser.class.getName());

    private final File excelFile;
    public final static String SHEETNAME = "SOME";
    public final static String QUERYCELLNAME = "SPARQL";
    public final static String DATACELLNAME = "DATA";

    public ExcelParser(File excelFile) {
        this.excelFile = excelFile;
    }

    public String getSPARQLQuery() throws IOException, InvalidFormatException {
        Boolean foundSparqlQuery = false;

        Workbook wb = WorkbookFactory.create(excelFile);

        for (Sheet sheet : wb) {
            if (sheet.getSheetName().equals(SHEETNAME)) {
                for (Row row : sheet) {
                    for (Cell cell : row) {
                        if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                            continue;
                        }
                        if (!foundSparqlQuery && cell.getCellType() == Cell.CELL_TYPE_STRING) {
                            if (cell.getRichStringCellValue().getString().equals(QUERYCELLNAME)) {
                                foundSparqlQuery = true;
                                continue;
                            }
                        }
                        if (foundSparqlQuery && cell.getCellType() == Cell.CELL_TYPE_STRING) {
                            return cell.getRichStringCellValue().getString();
                        }
                    }
                }
            }
        }
        return null;
    }

    public List<List<String>> getDataTable() throws IOException, InvalidFormatException {
        List<List<String>> dataTable = new ArrayList<>();
        Integer rowNumber = -2;

        Workbook wb = WorkbookFactory.create(excelFile);

        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
        for (Sheet sheet : wb) {
            if (sheet.getSheetName().equals(SHEETNAME)) {
                for (Row row : sheet) {
                    //stop with the first empty row
                    if(row.getCell(0) == null){
                        break;
                    }
                    if (rowNumber >= -1) {
                        rowNumber++;
                        dataTable.add(new ArrayList<String>());
                    }
                    for (Cell cell : row) {
                        String sheetName = sheet.getSheetName();
                        String cellRow = "Row:" + cell.getRowIndex();
                        String cellColumn = "Column:" + cell.getColumnIndex();
                        Object[] o = new Object[]{sheetName, cellRow, cellColumn};
                        LOGGER.log(Level.INFO, "Processing: Sheet={0} celladress={1}", o);
                        if (rowNumber <= -1 && cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                            continue;
                        }
                        if (rowNumber == -2 && cell.getCellType() == Cell.CELL_TYPE_STRING) {
                            if (cell.getRichStringCellValue().getString().equals(DATACELLNAME)) {
                                rowNumber = -1;
                                continue;
                            }
                        }
                        //Attributes (column headers)
                        if (rowNumber == 0) {
                            dataTable.get(rowNumber).add(cell.getRichStringCellValue().getString());
                        }
                        
                        if (rowNumber >= 1) {

                            switch (cell.getCellType()) {
                                case Cell.CELL_TYPE_STRING:
                                    dataTable.get(rowNumber).add(cell.getRichStringCellValue().getString());
                                    break;
                                case Cell.CELL_TYPE_NUMERIC:
                                    if (DateUtil.isCellDateFormatted(cell)) {
                                        dataTable.get(rowNumber).add(cell.getDateCellValue().toString());
                                    } else {
                                        dataTable.get(rowNumber).add(Double.toString(cell.getNumericCellValue()));
                                    }
                                    break;
                                case Cell.CELL_TYPE_BOOLEAN:
                                    dataTable.get(rowNumber).add(Boolean.toString(cell.getBooleanCellValue()));
                                    break;
                                case Cell.CELL_TYPE_FORMULA:
                                    switch (cell.getCachedFormulaResultType()) {
                                        case Cell.CELL_TYPE_STRING:
                                            dataTable.get(rowNumber).add(cell.getRichStringCellValue().getString());
                                            break;
                                        case Cell.CELL_TYPE_NUMERIC:
                                            if (DateUtil.isCellDateFormatted(cell)) {
                                                dataTable.get(rowNumber).add(cell.getDateCellValue().toString());
                                            } else {
                                                dataTable.get(rowNumber).add(Double.toString(cell.getNumericCellValue()));
                                            }
                                            break;
                                        case Cell.CELL_TYPE_BOOLEAN:
                                            dataTable.get(rowNumber).add(Boolean.toString(cell.getBooleanCellValue()));
                                            break;
                                        default:
                                            dataTable.get(rowNumber).add("");
                                    }
                                    break;
                                default:
                                    dataTable.get(rowNumber).add("");
                            }
                        }
                    }
                }
            }
        }

        return dataTable;
    }

    public static String getCSVFromDataTable(List<List<String>> dataTable) {

        String csvData = "";

        for (List<String> rowData : dataTable) {
            for (String data : rowData) {
                if (csvData.isEmpty()) {
                    csvData = data + ",";
                } else {
                    csvData = csvData + data + ",";
                }
            }
            if (csvData.endsWith(",")) {
                csvData = csvData.substring(0, csvData.length() - 1) + "\n";
            } else {
                csvData = csvData + "\n";
            }
        }
        return csvData;
    }
}
