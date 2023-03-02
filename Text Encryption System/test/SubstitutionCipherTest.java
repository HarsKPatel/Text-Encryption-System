import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class SubstitutionCipherTest {

  /**
   * This method test's if after calling encode method, the cipherText and plainText are holding their
   * corresponding values.
   */
  @Test
  public void encodeTest() {
    ISubstitutionCipher substitutionCipher = new SubstitutionCipher();

    substitutionCipher.setCaesarCipher(2);
    substitutionCipher.plaintext("ABC");
    substitutionCipher.encodeText();

    assertEquals("ABC\n", substitutionCipher.getPlainText());
    assertEquals("CDE\n", substitutionCipher.getCipherText());
  }

  /**
   * This method test's if after calling decode method, the cipherText and plainText are holding their
   * corresponding values.
   */
  @Test
  public void decodeTest() {
    ISubstitutionCipher substitutionCipher = new SubstitutionCipher();

    substitutionCipher.setCaesarCipher(3);
    substitutionCipher.ciphertext("XYZ");
    substitutionCipher.decodeText();

    assertEquals("UVW\n", substitutionCipher.getPlainText());
    assertEquals("XYZ\n", substitutionCipher.getCipherText());
  }
}
