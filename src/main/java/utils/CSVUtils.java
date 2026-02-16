package utils;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.List;

public class CSVUtils {

    public static Object[][] getData(String path) throws Exception {
        CSVReader reader = new CSVReader(new FileReader(path));
        List<String[]> data = reader.readAll();
        Object[][] result = new Object[data.size() - 1][1];
        for(int i = 1; i < data.size(); i++){
            result[i-1][0] = data.get(i)[0];
        }
        return result;
    }
}
