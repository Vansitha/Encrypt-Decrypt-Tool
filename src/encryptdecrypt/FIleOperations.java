package encryptdecrypt;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FIleOperations implements IFileOperations {

    @Override
    public List<String> readFile(String filename) {
        List<String> dataList = new ArrayList<>();
        try {
            // use a loop and insert each item into the file.
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String dataString = reader.readLine();
            while (dataString != null) {
                dataList.add(dataString);
                dataString = reader.readLine();
            }
            reader.close();

        } catch (IOException e) {
            System.out.printf("ERROR: cannot read file %s", e.getMessage());
        }
        return dataList;
    }

    @Override
    public void writeToFile(String filename, List<String> encodedMessages) {
        try {
            // loop through the array list and insert each encrypted string to the file
            var file = new File(filename);
            var writer = new FileWriter(file);
            for (String message : encodedMessages) {
                writer.write(message + "\n");
            }
            writer.close();

        } catch (IOException e) {
            System.out.printf("Error: cannot write to file %s", e.getMessage());
        }
    }
}
