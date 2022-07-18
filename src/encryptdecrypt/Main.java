package encryptdecrypt;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        // get the arguments
        HashMap<String, String> arguments = extractArguments(args);

        // Extract data
        // now check whether data flag or in flag is set
        // if both set use data flag
        // extract the data
        // if both are null exit display error message

        //run the encryption algorithm

        //output the results

    }

    public static HashMap<String, String> extractArguments(String[] args) {

        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("-mode", "enc");
        arguments.put("-key", "0");
        arguments.put("-alg", "");
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
}