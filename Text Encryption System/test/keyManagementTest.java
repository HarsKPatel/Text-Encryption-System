import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class keyManagementTest {
    @Test
    void getKeyNoChars() {
        ISubstitutionKey cipher = new SubstitutionKey( );

        HashMap<Character, Character> key ;
        key = cipher.getKey();

        assertNotNull( key , "empty key on getKey returning nothing" );
        Set<Character> keyset;
        keyset = key.keySet();

        assertEquals( 0, keyset.size(), "empty key returning characters" );
    }

    @Test
    void validKeyRange() {
        ISubstitutionKey cipher = new SubstitutionKey( );
    }

    @Test
    void getValidKey() {
        HashMap<Character, Character> key = new HashMap<>();
        key.put( 'b', 'c');
        key.put( 'c', 'd');

        ISubstitutionKey cipher = new SubstitutionKey( key );
        HashMap<Character, Character> retrieved = cipher.getKey();

        assertNotNull( retrieved, "retrieve good key" );

        assertEquals( 'c', retrieved.get( 'b' ), "get character" );
        assertEquals( 'd', retrieved.get( 'c' ), "get character" );
    }


    @Test
    void getInvalidKey() {
        HashMap<Character, Character> key = new HashMap<>();
        key.put( 'b', 'c');
        key.put( 'c', 'c');

        ISubstitutionKey cipher = new SubstitutionKey( key );
        HashMap<Character, Character> retrieved = cipher.getKey();

        assertNotNull( retrieved, "retrieve good key" );

        assertEquals( 'c', retrieved.get( 'b' ), "get character" );
        assertEquals( 'c', retrieved.get( 'c' ), "get character" );
    }

    @Test
    void invertKey() {
        HashMap<Character, Character> key = new HashMap<>();
        key.put( 'b', 'c');
        key.put( 'c', 'd');

        ISubstitutionKey cipher = new SubstitutionKey( key );
        ISubstitutionKey invert = cipher.invertKey();
        HashMap<Character, Character> retrieved = invert.getKey();

        assertNotNull( retrieved, "retrieve good key" );

        assertEquals( 'b', retrieved.get( 'c' ), "get character" );
        assertEquals( 'c', retrieved.get( 'd' ), "get character" );
    }

    @Test
    void invertEmptyKey() {
        HashMap<Character, Character> key = new HashMap<>();

        ISubstitutionKey cipher = new SubstitutionKey( key );
        ISubstitutionKey invert = cipher.invertKey();
        HashMap<Character, Character> retrieved = invert.getKey();

        assertNotNull( retrieved, "retrieve good key" );
    }

    /**
     * This method test's inferShift() of SubstitutionKey when there is no variation in shifts of letters in the key
     */
    @Test
    void inferShiftWithoutVariation() {
        ISubstitutionCipher substitutionCipher = new SubstitutionCipher();
        substitutionCipher.setCaesarCipher(5);

        assertEquals(5, ((ISubstitutionKey)substitutionCipher.getEncryptionKey()).inferShift());
    }

    /**
     * This method test's inferShift() of SubstitutionKey when there is variation in shifts of letters in the key
     */
    @Test
    void inferShiftWithVariation() {
        ISubstitutionCipher substitutionCipher = new SubstitutionCipher();
        substitutionCipher.setDecodeLetter('a', 't');
        substitutionCipher.setDecodeLetter('b', 'p');
        substitutionCipher.setDecodeLetter('c', 'v');
        substitutionCipher.setDecodeLetter('d', 'd');
        substitutionCipher.setDecodeLetter('e', 'x');
        substitutionCipher.setDecodeLetter('f', 'm');

        assertEquals(19, ((ISubstitutionKey)substitutionCipher.getEncryptionKey()).inferShift());
    }
}