import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class SubstitutionCipher extends Cipher implements ISubstitutionCipher {
    private ISubstitutionKey encryptKey;
    /**
     * Constructor to create a completely empty cipher
     */
    public SubstitutionCipher() {
        encryptKey = new SubstitutionKey();
    }

    /**
     * Constructor to create a cipher for which we're given an existing mapping of characters.
     * This constructor is most useful when you're debugging so that you can establish a correct
     * key to see if the rest of the decoding is working
     * @param name - a name that identifies this key set
     * @param key - the encryption key structure from plaintext to ciphertext
     */
    public SubstitutionCipher( String name, HashMap<Character, Character> key ) {

        /* Make sure that we have quality parameter values to start */
        if ((name != null) && (name.length() > 0) && (key != null)) {
            encryptKey = new SubstitutionKey( key );

            /* The name isn't being used, so I have no purpose in storing it. */
        } else {
            encryptKey = new SubstitutionKey();
        }
    }

    /**
     * Given plaintext in this object, use whatever key we have to encode the plaintext.
     * @return - return true if the plaintext was properly stored as ciphertext in this object.
     */
    public Boolean encodeText() {
        String encoded = null;
        Set<Character> encryptLetters = encryptKey.getKey().keySet();

        plainText = "";

        /* Get the letters in the plaintext. */

        Set<Character> plainLetters = null;
        if (plain != null) {
            plainLetters = plain.lettersInCorpus();
        } else {
            plainLetters = new HashSet<>();
        }

        /* The cipher Corpus object gives us an iterator that looks similar to that of a file. */

        if (encryptLetters.size() == 0) {
            return false;
        }

        if (encryptKey.keyIsValid() && (plain != null) && encryptLetters.containsAll( plainLetters ) && plain.beginCorpus()) {
            StringBuilder inProgress = new StringBuilder();

            int charRead;
            String encodedLine;

            /* Encode each line, one at a time.  The corpus stream should end with a null value as the next line. */

            String line  = plain.nextCorpusLine();
            while (line != null) {
                encodedLine = encryptKey.encryptText( line );
                if (encodedLine != null) {
                    /* Add the encoded line.  Since we're processing one line at a time, assume that all our
                       entries properly end with a carriage return.
                     */
                    inProgress.append( encodedLine );
                    inProgress.append( "\n" );
                    plainText = plainText + line + '\n';
                } else {
                    /* Something happened in the encryption of a viable line.  Return with an error condition. */
                    return null;
                }
                line = plain.nextCorpusLine();
            }
            plain.endCorpus();

            encoded = inProgress.toString();
            cipherText = encoded;
            return true;
        }
        return false;
    }

    /**
     * Given ciphertext in this object, use whatever key we have to decode the ciphertext.
     * @return - return true if the corresponding plaintext has been stored in this object as plaintext.
     */
    public Boolean decodeText() {
        String decoded = null;

        ISubstitutionKey decodeKey = encryptKey.invertKey();
        Set<Character> encryptLetters = decodeKey.getKey().keySet();

        cipherText = "";

        /* Get the letters in the ciphertext. */

        Set<Character> cipherLetters = null;
        if (cipher != null) {
            cipherLetters = cipher.lettersInCorpus();
        } else {
            cipherLetters = new HashSet<>();
        }

        /* The cipher Corpus object gives us an iterator that looks similar to that of a file. */
        if (encryptLetters.size() == 0) {
            return false;
        }

        if (encryptKey.keyIsValid() && (cipher != null) && encryptLetters.containsAll( cipherLetters ) && cipher.beginCorpus()) {
            StringBuilder inProgress = new StringBuilder();

            int charRead;
            String decodedLine;

            /* Decode each line, one at a time.  The corpus stream should end with a null value as the next line. */

            String line  = cipher.nextCorpusLine();
            while (line != null) {
                decodedLine = decodeKey.encryptText( line );
                if (decodedLine != null) {
                    /* Add the decoded line.  Since we're processing one line at a time, assume that all our
                       entries properly end with a carriage return.
                     */
                    inProgress.append( decodedLine );
                    inProgress.append( "\n" );
                    cipherText = cipherText + line + '\n';
                } else {
                    /* Something happened in the decryption of a viable line.  Return with an error condition. */
                    return null;
                }
                line = cipher.nextCorpusLine();
            }
            cipher.endCorpus();

            decoded = inProgress.toString();
            plainText = decoded;
            return true;
        }
        return false;
    }

    /**
     * Manually create or modify an encryption key.  The method associates one plaintext
     * character with one ciphertext character.  Building a full key would involve calling
     * this method once for each character to be encoded/decoded.
     * @param plaintextChar - the plaintext character to encode
     * @param ciphertextChar - the character to use as the encoding of the plaintext character
     * @return - true if the character could be added as part of the key, false otherwise
     */
    public Boolean setDecodeLetter( Character plaintextChar, Character ciphertextChar ) {
        Boolean changeAccepted = false;

        /* Make sure that we have quality parameter values to start */
        if ((plaintextChar != null) && (Character.isLetter(plaintextChar)) &&
                (ciphertextChar != null) && (Character.isLetter(ciphertextChar))) {
            changeAccepted = encryptKey.setKey( plaintextChar, ciphertextChar );
        }

        return changeAccepted;
    }

    /**
     * Obtain a copy of the encryption key currently used in this object.  The Map has
     * the plaintxt character as the key and the encoded character as the Map value.
     * @return - a Map of the encryption key.  Return null in the case of error.
     */
    public HashMap<Character, Character> getKey() {
        return encryptKey.getKey();
    }

    /**
     * This method assigns a new value to the plain letter according to the shift letter given.
     * @param shiftNumber
     * @return
     */
    public Boolean setCaesarCipher(int shiftNumber) {
        Boolean changeAccepted = false;

        /* Make sure that we have quality parameter values to start */
        if(shiftNumber <0 || shiftNumber >=26)
        {
            return false;
        }

        char cipherChar;
        for (int i = 'a'; i <= 'z'; i++) {
            cipherChar = (char) (i + shiftNumber);
            if (cipherChar > 'z') {
                cipherChar -= 26;
            }
            encryptKey.setKey((char) i, cipherChar);
        }
        return true;
    }

    @Override
    public ISubstitutionKey getEncryptionKey() {
        return encryptKey;
    }
}
