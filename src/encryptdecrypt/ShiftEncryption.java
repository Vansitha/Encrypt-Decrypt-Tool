package encryptdecrypt;

public class ShiftEncryption implements Encryption {

    private int key;

    ShiftEncryption(int key) {
        this.key = key;
    }

    @Override
    public String encrypt(String message) {
        var encryptedStr = new StringBuilder();

        // looping through the message
        for (int i = 0; i < message.length(); i++) {
            char charInMessage = message.charAt(i);

            if (Character.isLetter(charInMessage)) {
                for (int j = 0; j < ALPHABET.length - 1; j++) {
                    if(charInMessage == ALPHABET[j]) {
                        if (!(j + key > ALPHABET.length - 1))
                            encryptedStr.append(ALPHABET[j + key]);
                        else {
                            int newIndex = calcNewIndex(key, j);
                            encryptedStr.append(ALPHABET[newIndex]);
                        }
                    }
                    else if(Character.toLowerCase(charInMessage )== ALPHABET[j]) {
                        if (!(j + key > ALPHABET.length - 1))
                            encryptedStr.append(Character.toUpperCase(ALPHABET[j + key]));
                        else {
                            int newIndex = calcNewIndex(key, j);
                            encryptedStr.append(ALPHABET[newIndex]);
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

    @Override
    public String decrypt(String message) {
        var encryptedStr = new StringBuilder();

        // looping through the message
        for (int i = 0; i < message.length(); i++) {
            char charInMessage = message.charAt(i);

            if (Character.isLetter(charInMessage)) {
                for (int j = 0; j < ALPHABET.length - 1; j++) {
                    if(charInMessage == ALPHABET[j]) {
                        if (!(j - key < 0))
                            encryptedStr.append(ALPHABET[Math.abs(j - key)]);
                        else {
                            int newIndex = calcOriginalIndex(key, j);
                            encryptedStr.append(ALPHABET[newIndex]);
                        }
                    }
                    else if(Character.toLowerCase(charInMessage )== ALPHABET[j]) {
                        if (!(j - key < 0))
                            encryptedStr.append(Character.toUpperCase(ALPHABET[Math.abs(j - key)]));
                        else {
                            int newIndex = calcOriginalIndex(key, j);
                            encryptedStr.append(ALPHABET[newIndex]);
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

    public int calcNewIndex(int key, int currIndex) {
        int overflow = currIndex + 1 + key;
        int newIndex =  overflow - ALPHABET.length - 1; // -1 because array is 0 index
        return newIndex;
    }

    public int calcOriginalIndex(int key, int currIndex) {
        int previousOverFlow = currIndex + ALPHABET.length;
        int originalIndex = Math.abs(previousOverFlow - key);
        return originalIndex;
    }
}
