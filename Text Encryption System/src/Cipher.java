import java.io.PrintWriter;

public abstract class Cipher implements ICipher {
  protected ICorpus plain = null;
  protected ICorpus cipher = null;
  protected String plainText;
  protected String cipherText;
  /**
   * Provide the plaintext to encrypt to the object.  The plaintext is the full content
   * of the given string.
   * @param theText - the string to use as plaintext
   * @return - true if the text can be used as plaintext, false otherwise.
   */
  public Boolean plaintext( String theText ) {
    Boolean plaintextAccepted = false;

    /* Make sure that we have quality parameter values to start */
    if ((theText != null) && (theText.length() > 0)) {
      /* If we already have a plaintext in the object, replace it. */

      plain = new Corpus( "" );
      plaintextAccepted = plain.replaceContentWithString( theText );
    }

    return plaintextAccepted;
  }

  /**
   * Provide the ciphertext to decrypt to the object.  The ciphertext is the full content
   * of the given string.
   * @param theText - text to use as ciphertext
   * @return - true if the text can be used as ciphertext, false otherwise.
   */
  public Boolean ciphertext( String theText ) {
    Boolean ciphertextAccepted = false;

    /* Make sure that we have quality parameter values to start */
    if ((theText != null) && (theText.length() > 0)) {
      /* If we already have a ciphertext in the object, replace it. */

      cipher = new Corpus( "" );
      ciphertextAccepted = cipher.replaceContentWithString( theText );
    }

    return ciphertextAccepted;
  }

  /**
   * Send the stored plaintext to some output device.  That output device can be a PrintWriter
   * wrapping around a file, the screen, a string, or whatever else you want.
   * @param output -- the output device.
   */
  public void extractPlaintext( PrintWriter output ) {
    output.print(plainText);
  }

  /**
   * Send the stored ciphertext to some output device.  That output device can be a PrintWriter
   * wrapping around a file, the screen, a string, or whatever else you want.
   * @param output -- the output device.
   */
  public void extractCiphertext( PrintWriter output ) {
    output.print(cipherText);
  }

  public String getCipherText() {
    return cipherText;
  }
  public String getPlainText() {
    return plainText;
  }

  public abstract Boolean encodeText();
  public abstract Boolean decodeText();
}
