package encryptdecrypt;

public class EncryptionFactory {

    public IEncryption getEncryptionAlgorithm(String algoType, int key) {
        if (algoType.equals("shift"))
            return new ShiftEncryption(key);
        else if (algoType.equals("unicode"))
            return new UnicodeEncryption(key);

        return null;
    }
}
