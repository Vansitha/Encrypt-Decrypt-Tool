package encryptdecrypt;

public class Main {

    private static final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public static void main(String[] args) {
        String message = "we found a treasure!";
        String newMessage = encryptMessage(message);
        System.out.println(newMessage);
    }

    /**
     * Creates a new string by replacing each letter in the provided string
     * with the letter that is in the corresponding position from the end of the
     * alphabet.
     *
     * @param message string containing a message to be encrypted
     * @return new shuffled string
     */
    public static String encryptMessage(String message)
    {
        int totalIndices = alphabet.length - 1;
        StringBuilder encryptedStr = new StringBuilder();

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
}