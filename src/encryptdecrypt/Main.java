package encryptdecrypt;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        String mode = CmdModes.MODE_ENCRYPT;
        int key = 0;
        String data, inputFile, outputFile;
        data = inputFile = outputFile = "";
        String encryptionAlg = CmdModes.MODE_SHIFT;

        // get and validate command line arguments
       if (!(args.length > CmdModes.MAX_ACCEPTED_ARGS)) {
           for (int i = 0; i < args.length; i++) {
               if (CmdModes.ARG_MODE.equals(args[i]))
                   mode = args[i + 1];
               if (CmdModes.ARG_KEY.equals(args[i]))
                   key = Integer.parseInt(args[i + 1]);
               if (CmdModes.ARG_DATA.equals(args[i]))
                   data = args[i + 1];
               if (CmdModes.ARG_IN.equals(args[i]) & data.isBlank())
                   inputFile = args[i + 1];
               if (CmdModes.ARG_OUT.equals(args[i]))
                   outputFile = args[i + 1];
               if (CmdModes.ARG_ALG.equals(args[i]))
                   encryptionAlg = args[i + 1];
           }
       }

       // extract data from input file
        if (!inputFile.isBlank()) {
            data = readFile(inputFile);
        }

        // process the message string
       String outputMessage = "";
        if (encryptionAlg.equals(CmdModes.MODE_UNICODE)) {
            Encryption algo = new UnicodeEncryption(key);
            if (mode.equals(CmdModes.MODE_ENCRYPT))
                outputMessage = algo.encrypt(data);
            if (mode.equals(CmdModes.MODE_DECRYPT))
                outputMessage = algo.decrypt(data);
        }

        if (encryptionAlg.equals(CmdModes.MODE_SHIFT)) {
            Encryption algo = new ShiftEncryption(key);
            if (mode.equals(CmdModes.MODE_ENCRYPT))
                outputMessage = algo.encrypt(data);
            if (mode.equals(CmdModes.MODE_DECRYPT))
                outputMessage = algo.decrypt(data);
        }

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
}