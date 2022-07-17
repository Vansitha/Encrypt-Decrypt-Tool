package encryptdecrypt;

public class Main {
    public static void main(String[] args) {
        String mode = CmdModes.MODE_ENCRYPT;
        int key = 0;
        String data, inputFile, outputFile;
        data = inputFile = outputFile = "";
        String encryptionType = CmdModes.MODE_SHIFT;

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
                   encryptionType = args[i + 1];
           }
       }

       // extract data from input file
        if (!inputFile.isBlank()) {
            return;
            //data = readFile(inputFile);
        }

        // process the message string
        var encryptionAlgorithm = new EncryptionFactory()
                                            .getEncryptionAlgorithm(encryptionType, key);
        String outputMessage = "";

        if (mode.equals(CmdModes.MODE_ENCRYPT))
            outputMessage = encryptionAlgorithm.encrypt(data);
        if (mode.equals(CmdModes.MODE_DECRYPT))
            outputMessage = encryptionAlgorithm.decrypt(data);

        // output processed message
        if (!outputFile.isBlank())
            return;
            //writeToFile(outputFile, outputMessage);
        else
            System.out.println(outputMessage);
    }
}
