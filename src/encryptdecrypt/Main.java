package encryptdecrypt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static final String ARGUMENT_ALG = "-alg";
    private static final  String ARGUMENT_KEY = "-key";
    private static  final String ARGUMENT_MODE = "-mode";
    private  static final String ARGUMENT_OUT = "-out";
    private static  final String ARGUMENT_IN = "-in";
    private static  final String ARGUMENT_DATA = "-data";
    private static  final String ARGUMENT_ENCRYPT = "enc";
    private static  final String ARGUMENT_DECRYPT = "dec";

    public static void main(String[] args) {

        // get the arguments
        IFileOperations fileHandler = new FIleOperations();
        EncryptionFactory algorithmFactory = new EncryptionFactory();
        Map<String, String> arguments = extractMessages(args);
        List<String> messageList = extractMessages(arguments, fileHandler);

        if (messageList.isEmpty())
            return;

        //run the encryption algorithm
        IEncryption encryptionAlgorithm = algorithmFactory
                .getEncryptionAlgorithm(arguments.get(ARGUMENT_ALG), Integer.parseInt(arguments.get(ARGUMENT_KEY)));

        String mode = arguments.get(ARGUMENT_MODE);
        List<String> modifiedMessageList = runEncryptionAlgorithm(messageList, encryptionAlgorithm, mode);

        //output the results (file or stdout)
        String filename = arguments.get(ARGUMENT_OUT);
        String outputMode = arguments.get(ARGUMENT_IN);
        outputData(modifiedMessageList, outputMode, filename, fileHandler);

    }

    public static void outputData(List<String> dataList, String outputMode, String filename,
                                  IFileOperations fileHandler) {
        if (!outputMode.isBlank()) {
            fileHandler.writeToFile(filename, dataList);
        } else {
            for (String modifiedMessage : dataList) {
                System.out.println(modifiedMessage);
            }
        }
    }

    public static Map<String, String> extractMessages(String[] args) {

        Map<String, String> arguments = initializeDefaultArguments();

        for (int i = 0; i < args.length; i++) {
            String key = args[i];
            if (arguments.containsKey(key))
                arguments.put(key, args[i + 1]);
        }

        return arguments;
    }

    public static Map<String, String> initializeDefaultArguments() {

        HashMap<String, String> defaultArgumentData = new HashMap<>();

        String defaultKeyStr = "0";
        String defaultModeStr = "enc";
        String defaultEmptyStr = "";

        defaultArgumentData.put(ARGUMENT_MODE, defaultModeStr);
        defaultArgumentData.put(ARGUMENT_KEY, defaultKeyStr);
        defaultArgumentData.put(ARGUMENT_ALG, EncryptionType.SHIFT.toString().toLowerCase());
        defaultArgumentData.put(ARGUMENT_IN, defaultEmptyStr);
        defaultArgumentData.put(ARGUMENT_OUT, defaultEmptyStr);
        defaultArgumentData.put(ARGUMENT_DATA, defaultEmptyStr);

        return defaultArgumentData;
    }

    public static List<String> extractMessages(Map<String, String> arguments, IFileOperations fileHandler) {

        List<String> messageList = new ArrayList<>();
        // Extract data
        // check whether data flag or in flag is set
        // if both set use data flag -> extract the data
        // if both are null -> exit display error message
        if (arguments.get(ARGUMENT_IN).isBlank() && arguments.get(ARGUMENT_DATA).isBlank()) {
            System.out.printf("No data provided to %s", arguments.get(ARGUMENT_MODE));

        } else if (!arguments.get(ARGUMENT_IN).isBlank() && !arguments.get(ARGUMENT_DATA).isBlank()) {
            messageList.add(arguments.get(ARGUMENT_DATA));

        } else if (!arguments.get(ARGUMENT_IN).isBlank() && arguments.get(ARGUMENT_DATA).isBlank()) {
            messageList = fileHandler.readFile(arguments.get(ARGUMENT_IN));

        } else if (arguments.get(ARGUMENT_IN).isBlank() && !arguments.get(ARGUMENT_DATA).isBlank()) {
            messageList.add(arguments.get(ARGUMENT_DATA));
        }
        return messageList;
    }

    public static List<String> runEncryptionAlgorithm(List<String> messageList, IEncryption encryptionAlgorithm,
                                                      String mode) {

        // process each message (run encrypt or decrypt algorithm)
        List<String> modifiedMessageList = new ArrayList<>();
        for (String message : messageList) {
            if (mode.equals(ARGUMENT_ENCRYPT)) {
                String encryptedMessage = encryptionAlgorithm.encrypt(message);
                modifiedMessageList.add(encryptedMessage);
            }
            if (mode.equals(ARGUMENT_DECRYPT)) {
                String decryptedMessage = encryptionAlgorithm.decrypt(message);
                modifiedMessageList.add(decryptedMessage);
            }
        }
        return modifiedMessageList;
    }

}