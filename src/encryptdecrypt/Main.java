package encryptdecrypt;

import java.util.Scanner;

public class Main {

    private static final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        int key = scanner.nextInt();

        String newMessage = encryptMessage(message, key);
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
    public static String replaceLetters(String message) {
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

            if (Character.isLetter(charInMessage)) {
                for (int j = 0; j < alphabet.length - 1; j++) {
                    if(charInMessage == alphabet[j]) {
                        if (!(j + key > alphabet.length - 1))
                            encryptedStr.append(alphabet[j + key]);
                        else {
                            int newIndexFromArrStart = calcNewIndex(key, j);
                            encryptedStr.append(alphabet[newIndexFromArrStart]);
                        }
                    }
                }
            }
            else {
                encryptedStr.append(charInMessage);
            }
        }
        return encryptedStr.toString();
    }

    /**
     * calculates the new index from start of the array accounting for the overflow
     *
     * @param key - value used to shift the values by
     * @param currIndex - position of the letter which is required to be shifted
     * @return new re-calculated index from the start of the array
     */
    public static int calcNewIndex(int key, int currIndex) {
        int overflow = currIndex + 1 + key;
        int calcIndex =  overflow - alphabet.length - 1; // -1 because array is 0 index
        return calcIndex;
    }
}