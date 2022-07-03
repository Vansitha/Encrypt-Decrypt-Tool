package encryptdecrypt;

public class ShiftAlgo implements EncryptionAlgo {

    /**
     * Creates a new string by replacing each letter in the provided string
     * with the letter that is in the corresponding position from the end of the
     * alphabet.
     *
     * @param message string containing a message to be encrypted
     * @return new shuffled string
     */
    @Override
    public String encrypt(String message) {
        int totalIndices = ALPHABET.length - 1;
        var encryptedStr = new StringBuilder();

        // loop through the message string
        for(int i = 0; i < message.length(); i++){
            if (Character.isLetter(message.charAt(i))) {
                int indexInAlphaArray = 0;

                // loop through the alphabet array
                for (int j = 0; j < ALPHABET.length; j++) {
                    if (message.charAt(i) == ALPHABET[j])
                        indexInAlphaArray = j;
                }
                // find and replace letter from the end of the alphabet array
                encryptedStr.append(ALPHABET[totalIndices - indexInAlphaArray]);
            } else {
                encryptedStr.append(message.charAt(i));
            }
        }
        return encryptedStr.toString();
    }

    @Override
    public String decrypt(String message) {
        return null;
    }
}
