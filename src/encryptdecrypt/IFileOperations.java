package encryptdecrypt;

import java.util.List;

public interface IFileOperations {

    List<String> readFile(String filename);
    void writeToFile(String filename, List<String> encodedMessages);
}
