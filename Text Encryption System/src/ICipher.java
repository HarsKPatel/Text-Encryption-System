import java.io.PrintWriter;

public interface ICipher {
  Boolean plaintext(String theText);
  Boolean ciphertext(String theText);
  Boolean encodeText();
  Boolean decodeText();
  void extractPlaintext(PrintWriter output);
  void extractCiphertext(PrintWriter output);
  IEncryptionKey getEncryptionKey();
  public String getCipherText();
  public String getPlainText();
}
