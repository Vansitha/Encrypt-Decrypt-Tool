package encryptdecrypt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        // get the arguments
        IFileOperations fileHandler = new FIleOperations();
        Map<String, String> arguments = extractArguments(args);
        List<String> messageList = extractDataSet(arguments, fileHandler);

        if (messageList.isEmpty())
            return;

        //run the encryption algorithm
        EncryptionFactory algorithmFactory = new EncryptionFactory();
        IEncryption encryptionAlgorithm = algorithmFactory
                .getEncryptionAlgorithm(arguments.get("-alg"), Integer.parseInt(arguments.get("-key")));

        String mode = arguments.get("-mode");
        List<String> modifiedMessageList = runEncryptionAlgorithm(messageList, encryptionAlgorithm, mode);

        //output the results (file or stdout)
        if (!arguments.get("-out").isBlank()) {
            String filename = arguments.get("-out");
            fileHandler.writeToFile(filename, modifiedMessageList);
        } else {
            for (String modifiedMessage : modifiedMessageList) {
                System.out.println(modifiedMessage);
            }
        }

    }

    public static HashMap<String, String> extractArguments(String[] args) {

        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("-mode", "enc");
        arguments.put("-key", "0");
        arguments.put("-alg", "shift");
        arguments.put("-in", "");
        arguments.put("-out", "");
        arguments.put("-data", "");

        for (int i = 0; i < args.length; i++) {
            String key = args[i];
            if (arguments.containsKey(key))
                arguments.put(key, args[i + 1]);
        }

        return arguments;
    }

    public static List<String> extractDataSet(Map<String, String> arguments, IFileOperations fileHandler) {

        List<String> messageList = new ArrayList<>();
        // Extract data
        // check whether data flag or in flag is set
        // if both set use data flag -> extract the data
        // if both are null -> exit display error message
        if (arguments.get("-in").isBlank() && arguments.get("-data").isBlank()) {
            System.out.printf("No messages provided to %s", arguments.get("-mode"));

        } else if (!arguments.get("-in").isBlank() && !arguments.get("-data").isBlank()) {
            messageList.add(arguments.get("-data"));

        } else if (!arguments.get("-in").isBlank() && arguments.get("-data").isBlank()) {
            messageList = fileHandler.readFile(arguments.get("-in"));

        } else if (arguments.get("-in").isBlank() && !arguments.get("-data").isBlank()) {
            messageList.add(arguments.get("-data"));
        }
        return messageList;
    }

    public static List<String> runEncryptionAlgorithm(List<String> messageList, IEncryption encryptionAlgorithm, String mode) {

        // process each message (run encrypt or decrypt algorithm)
        List<String> modifiedMessageList = new ArrayList<>();
        for (String message : messageList) {
            if (mode.equals("enc")) {
                String encryptedMessage = encryptionAlgorithm.encrypt(message);
                System.out.println(encryptedMessage);
                modifiedMessageList.add(encryptedMessage);
            }
            if (mode.equals("dec")) {
                String decryptedMessage = encryptionAlgorithm.decrypt(message);
                System.out.println(decryptedMessage);
                modifiedMessageList.add(decryptedMessage);
            }
        }
        return modifiedMessageList;
    }

}