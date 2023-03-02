import java.util.HashMap;
public interface ISubstitutionKey extends IEncryptionKey {
  HashMap<Character, Character> getKey();
  Boolean setKey( Character plainChar, Character cipherChar );
  ISubstitutionKey invertKey();
  int inferShift();
}
