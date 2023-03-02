import java.util.HashMap;

public interface ISubstitutionCipher extends ICipher {
  Boolean setDecodeLetter( Character plaintextChar, Character ciphertextChar );
  HashMap<Character, Character> getKey();
  Boolean setCaesarCipher(int shiftNumber);
}
