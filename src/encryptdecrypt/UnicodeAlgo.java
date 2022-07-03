package encryptdecrypt;

public class UnicodeAlgo implements  EncryptionAlgo {

    private int key;

    UnicodeAlgo(int key) {
        this.key = key;
    }

    @Override
    public String encrypt(String message) {
        var encryptedStr = new StringBuilder();

        // looping through the message
        for (int i = 0; i < message.length(); i++) {
            char charInMessage = message.charAt(i);

            if (Character.isLowerCase(charInMessage)) {
                for (int j = 0; j < ALPHABET.length - 1; j++) {
                    if(charInMessage == ALPHABET[j]) {
                        if (!(j + key > ALPHABET.length - 1))
                            encryptedStr.append(ALPHABET[j + key]);
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

    @Override
    public String decrypt(String message) {
        var decryptedString = new StringBuilder();

        // looping through the message
        for (int i = 0; i < message.length(); i++) {
            char charInMessage = message.charAt(i);

            if (Character.isLowerCase(charInMessage)) {
                for (int j = 0; j < ALPHABET.length - 1; j++) {
                    if(charInMessage == ALPHABET[j]) {
                        if (!(j + key > ALPHABET.length - 1))
                            decryptedString.append(ALPHABET[j - key]);
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
