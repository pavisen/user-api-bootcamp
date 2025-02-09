package com.self.utilities;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

public class CsvIO {

    private String path; 

    public CsvIO(String path) {
        this.path = path;
        
    }

    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        try (CSVReader reader = new CSVReader(new java.io.FileReader(path))) {
            List<String[]> allRows = reader.readAll();
            if (rownum >= 0 && rownum < allRows.size()) {
                String[] row = allRows.get(rownum);
                if (colnum >= 0 && colnum < row.length) {
                    return row[colnum];
                } else {
                    System.out.println("Column index out of bounds");
                    return null;
                }
            } else {
                System.out.println("Row index out of bounds");
                return null;
            }
        } catch (CsvException e) {
            System.out.println("Could not read all rows " + e.getMessage());
            return null;
        }
    }

    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
        List<String[]> allRows;
        try (CSVReader reader = new CSVReader(new java.io.FileReader(path))) {
            allRows = reader.readAll();
        } catch (CsvException e) {
            System.out.println("Could not read all rows " + e.getMessage());
            return;
        }

        if (rownum >= 0 && rownum < allRows.size()) {
            String[] row = allRows.get(rownum);
            if (colnum >= 0 && colnum < row.length) {
                row[colnum] = data;
                allRows.set(rownum, row);

                try (CSVWriter writer = new CSVWriter(new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8))) {
                    writer.writeAll(allRows);
                } catch (IOException e) {
                    System.out.println("Error writing to CSV file: " + e.getMessage());
                }

            } else {
                System.out.println("Column index out of bounds");
            }
        } else {
            System.out.println("Row index out of bounds");
        }
    }
}