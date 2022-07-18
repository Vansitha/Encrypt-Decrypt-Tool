package encryptdecrypt;

import java.io.*;
import java.util.ArrayList;

public class FIleOperations implements IFileOperations {

    @Override
    public ArrayList<String> readFile(String filename) {
        ArrayList<String> dataList = new ArrayList<>();
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
    public void writeToFile(String filename, ArrayList<String> encodedMessages) {
        try {
            // loop through the array list and insert each encrypted string to the file
            var file = new File(filename);
            var writer = new FileWriter(file);
            String message = "";
            writer.write(message);
            writer.close();

        } catch (IOException e) {
            System.out.printf("Error: cannot write to file %s", e.getMessage());
        }
    }
}
