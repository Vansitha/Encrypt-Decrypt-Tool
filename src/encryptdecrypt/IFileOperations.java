package encryptdecrypt;

import java.util.ArrayList;

public interface IFileOperations {
     ArrayList<String> readFile(String filename);
    void writeToFile(String filename, ArrayList<String> encodedMessages);
}
