package com.self.utilities;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.self.payloads.AddressPayload;
import com.self.payloads.UserPayload;

public class CsvIO {

    private final String path;
    private List<String[]> allRows = null;
    Map<String, Integer> headers = new HashMap<>();

    public CsvIO(String path) {
        this.path = CsvIO.class.getClassLoader().getResource(path).getFile();
        try {
            loadData();
            getHeaders();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private Map<String, Integer> getHeaders() throws FileNotFoundException, IOException {

        if (allRows != null && headers.isEmpty()) {
            String[] header = allRows.get(1);
            for (int i = 0; i < header.length; i++) {
                headers.put(header[i].trim(), i + 1);
            }
        }
        return headers;

    }

    public String getCellData(int rownum, int colnum) throws IOException {
        colnum = colnum - 1;
        loadData();
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
    }

    private void loadData() throws IOException, FileNotFoundException {

        if (allRows == null) {
            try (CSVReader reader = new CSVReader(new java.io.FileReader(path))) {
                allRows = reader.readAll();
            } catch (CsvException e) {
                System.out.println("Could not read all rows " + e.getMessage());
            }
        }
    }

    public void setCellData(int rownum, int colnum, String data) throws IOException {

        if (rownum >= 0 && rownum < allRows.size()) {
            String[] row = allRows.get(rownum);
            if (colnum >= 0 && colnum < row.length) {
                row[colnum] = data;
                allRows.set(rownum, row);

                try (CSVWriter writer = new CSVWriter(
                        new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8))) {
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

    private UserPayload createUserBody(int rowNo) throws IOException {
        UserPayload objUserPayload = new UserPayload();
        AddressPayload objAddressPayload = new AddressPayload();
        objUserPayload.setUser_first_name(getCellData(rowNo, getHeaders().get("firstName")));
        objUserPayload.setUser_last_name(getCellData(rowNo, getHeaders().get("lastName")));
        objUserPayload.setUser_contact_number(getCellData(rowNo, getHeaders().get("phone")));
        objUserPayload.setUser_email_id(getCellData(rowNo, getHeaders().get("emailId")));
        objAddressPayload.setPlotNumber(getCellData(rowNo, getHeaders().get("plotNo")));
        objAddressPayload.setStreet(getCellData(rowNo, getHeaders().get("street")));
        objAddressPayload.setState(getCellData(rowNo, getHeaders().get("state")));
        objAddressPayload.setCountry(getCellData(rowNo, getHeaders().get("country")));
        objAddressPayload.setZipCode(getCellData(rowNo, getHeaders().get("zipCode")));
        objUserPayload.setExpectedStatusCode(Integer.parseInt(getCellData(rowNo, getHeaders().get("StatusCode"))));
        objUserPayload.setUserAddress(objAddressPayload);
        return objUserPayload;
    }

    public List<UserPayload> createUserPayloads() {
        List<UserPayload> userPayloads = new ArrayList<>();
        for (int i = 2; i < allRows.size(); i++) {
            try {
                userPayloads.add(createUserBody(i));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return userPayloads;
    }
}