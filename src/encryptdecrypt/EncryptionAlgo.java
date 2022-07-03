package encryptdecrypt;

public interface EncryptionAlgo {

    char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    String encrypt(String message);
    String decrypt(String message);
}
