package encryptdecrypt;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EncryptionFactoryTest {

    @Test
    void encryptionFactoryShouldReturnUnicodeObject() {
        var factory = new EncryptionFactory();
        IEncryption encryptionObject = factory.getEncryptionAlgorithm("unicode", 5);
        assertTrue(encryptionObject instanceof UnicodeEncryption);
    }

    @Test
    void encryptionFactoryShouldReturnShiftEncryption() {
        var factory = new EncryptionFactory();
        IEncryption encryptionObject = factory.getEncryptionAlgorithm("shift", 5);
        assertTrue(encryptionObject instanceof ShiftEncryption);
    }

}