package encryptdecrypt;

public interface IEncryption {

    char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    String encrypt(String message);
    String decrypt(String message);
}
