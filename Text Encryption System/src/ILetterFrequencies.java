import java.util.ArrayList;

public interface ILetterFrequencies {
  Boolean countLetter( Character letter );
  Boolean countStringLetters( String lineOfText );
  ArrayList< LetterCount > getSortedCounts();
}
