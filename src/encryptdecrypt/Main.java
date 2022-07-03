package encryptdecrypt;

import java.io.*;

public class Main {

    private static final int MAX_ACCEPTED_ARGS = 10;
    private static final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String ARG_MODE = "-mode";
    private static final String ARG_KEY = "-key";
    private static  final String ARG_DATA = "-data";
    private static final String MODE_ENCRYPT = "enc";
    private static final String MODE_DECRYPT = "dec";
    private static  final String ARG_IN = "-in";
    private static final String ARG_OUT = "-out";

    public static void main(String[] args) {

        String mode = "enc";
        int key = 0;
        String data, inputFile, outputFile;
        data = inputFile = outputFile = "";

        // get and validate command line arguments
       if (!(args.length > MAX_ACCEPTED_ARGS)) {
           for (int i = 0; i < args.length; i++) {
               if (ARG_MODE.equals(args[i]))
                   mode = args[i + 1];
               if (ARG_KEY.equals(args[i]))
                   key = Integer.parseInt(args[i + 1]);
               if (ARG_DATA.equals(args[i]))
                   data = args[i + 1];
               if (ARG_IN.equals(args[i]) & data.isBlank())
                   inputFile = args[i + 1];
               if (ARG_OUT.equals(args[i]))
                   outputFile = args[i + 1];
           }
       }

       // extract data from input file
        if (!inputFile.isBlank()) {
            data = readFile(inputFile);
        }


        // process the message string
       String outputMessage = "";
        if (mode.equals(MODE_ENCRYPT))
            outputMessage = encryptMessage(data, key);
        if (mode.equals(MODE_DECRYPT))
            outputMessage = decryptMessage(data, key);

        // output processed message
        if (!outputFile.isBlank())
            writeToFile(outputFile, outputMessage);
        else
            System.out.println(outputMessage);
    }

    public static String readFile(String inputFileName) {
        String dataString = "";
        try {
            File file = new File(inputFileName);
            FileReader reader = new FileReader(file);
            BufferedReader buffReader = new BufferedReader(reader);
            dataString = buffReader.readLine();
            reader.close();

        } catch (IOException e) {
            System.out.printf("ERROR: cannot read file %s", e.getMessage());
        }
        return dataString;
    }

    public static void writeToFile(String filename, String message) {
        try {
            var file = new File(filename);
            var writer = new FileWriter(file);
            writer.write(message);
            writer.close();

        } catch (IOException e) {
            System.out.printf("Error: cannot write to file %s", e.getMessage());
        }
    }

    /**
     * Creates a new string by replacing each letter in the provided string
     * with the letter that is in the corresponding position from the end of the
     * alphabet.
     *
     * @param message string containing a message to be encrypted
     * @return new shuffled string
     */
    public static String shiftLetters(String message) {
        int totalIndices = alphabet.length - 1;
        var encryptedStr = new StringBuilder();

        // loop through the message string
        for(int i = 0; i < message.length(); i++){
            if (Character.isLetter(message.charAt(i))) {
                int indexInAlphaArray = 0;

                // loop through the alphabet array
                for (int j = 0; j < alphabet.length; j++) {
                    if (message.charAt(i) == alphabet[j])
                        indexInAlphaArray = j;
                }
                // find and replace letter from the end of the alphabet array
                encryptedStr.append(alphabet[totalIndices - indexInAlphaArray]);
            } else {
                encryptedStr.append(message.charAt(i));
            }
        }
        return encryptedStr.toString();
    }

    /**
     * Shifts the letters in a given string by the supplied key
     *
     * @param message - message to be encrypted
     * @param key - the number that each letter needs to shifted and replaced
     * @return new encrypted string where the letters have been shifted
     * according the given key
     */
    public static String encryptMessage(String message, int key) {
        var encryptedStr = new StringBuilder();

        // looping through the message
        for (int i = 0; i < message.length(); i++) {
            char charInMessage = message.charAt(i);

            if (Character.isLowerCase(charInMessage)) {
                for (int j = 0; j < alphabet.length - 1; j++) {
                    if(charInMessage == alphabet[j]) {
                        if (!(j + key > alphabet.length - 1))
                            encryptedStr.append(alphabet[j + key]);
                        else {
                            int newCharVal = charInMessage + key;
                            encryptedStr.append((char) newCharVal);
                        }
                    }
                }
            }
            else {
                int newCharVal = charInMessage + key;
                encryptedStr.append((char) newCharVal);
            }
        }
        return encryptedStr.toString();
    }

    public static String decryptMessage(String message, int key){
        var decryptedString = new StringBuilder();

        // looping through the message
        for (int i = 0; i < message.length(); i++) {
            char charInMessage = message.charAt(i);

            if (Character.isLowerCase(charInMessage)) {
                for (int j = 0; j < alphabet.length - 1; j++) {
                    if(charInMessage == alphabet[j]) {
                        if (!(j + key > alphabet.length - 1))
                            decryptedString.append(alphabet[j - key]);
                        else {
                            int newCharVal = charInMessage - key;
                            decryptedString.append((char) newCharVal);
                        }
                    }
                }
            }
            else {
                int newCharVal = charInMessage - key;
                decryptedString.append((char) newCharVal);
            }
        }
        return decryptedString.toString();
    }
}