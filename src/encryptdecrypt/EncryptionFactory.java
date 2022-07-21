package encryptdecrypt;

public class EncryptionFactory {

    public IEncryption getEncryptionAlgorithm(String algoType, int key) {

        if (algoType.equals(EncryptionType.SHIFT.toString().toLowerCase()))
            return new ShiftEncryption(key);
        else if (algoType.equals(EncryptionType.UNICODE.toString().toLowerCase()))
            return new UnicodeEncryption(key);

        return null;
    }
}
