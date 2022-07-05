package encryptdecrypt;

public interface Encryption {

    char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    String encrypt(String message);
    String decrypt(String message);
}
