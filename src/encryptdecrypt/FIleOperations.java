package encryptdecrypt;

import java.io.*;
import java.util.ArrayList;

public class FIleOperations implements IFileOperations {

    @Override
    public ArrayList<String> readFile(String filename) {
        String dataString = "";
        try {
            File file = new File(filename);
            FileReader reader = new FileReader(file);
            BufferedReader buffReader = new BufferedReader(reader);
            dataString = buffReader.readLine();
            reader.close();

        } catch (IOException e) {
            System.out.printf("ERROR: cannot read file %s", e.getMessage());
        }
        return null;
    }

    @Override
    public void writeToFile(String filename, ArrayList<String> encodedMessages) {
        try {
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
