package encryptdecrypt;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        HashMap<String, String> arguments = new HashMap<>();
        String data, inputFile, outputFile;
        data = inputFile = outputFile = "";

        for(int i = 0; i < args.length; i++) {
            String key = args[i];
            String value = args[i + 1];
            arguments.put(key, value);
        }

        String mode = arguments.getOrDefault("-mode", "enc");
        int key = Integer.parseInt(arguments.getOrDefault("-key", "0"));
        String encryptionType = arguments.getOrDefault("-alg", "shift");

       // extract data from input file
        if (!inputFile.isBlank()) {
            IFileOperations fIleOperationsObj = new FIleOperations();
            ArrayList<String> messageListToEncrypt = fIleOperationsObj.readFile(inputFile);
        }

        String outputMessage = "";
        // process the message string
        IEncryption encryptionAlgorithm = new EncryptionFactory()
                                            .getEncryptionAlgorithm(encryptionType, key);
        if (mode.equals("enc"))
            outputMessage = encryptionAlgorithm.encrypt(data);
        if (mode.equals("dec"))
            outputMessage = encryptionAlgorithm.decrypt(data);

        // output processed message
        if (!outputFile.isBlank())
            return;
            //writeToFile(outputFile, outputMessage);
        else
            System.out.println(outputMessage);
    }
}