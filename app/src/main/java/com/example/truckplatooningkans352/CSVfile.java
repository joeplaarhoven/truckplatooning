package com.example.truckplatooningkans352;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CSVfile {
    InputStream inputStream;

    public CSVfile(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public ArrayList<ArrayList<Double>> read(){
        ArrayList resultList1 = new ArrayList();
        ArrayList resultList2 = new ArrayList();
        ArrayList resultList = new ArrayList();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            Integer x = 0;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");

                for(String test: row){
                    if(x % 2 == 0){
                        resultList1.add(Double.parseDouble(test));
                    }
                    else{
                        resultList2.add(Double.parseDouble(test));
                    }
                    x = x + 1;
                }

            }
            resultList.add(resultList1);
            resultList.add(resultList2);
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: "+e);
            }
        }
        return resultList;
    }
}