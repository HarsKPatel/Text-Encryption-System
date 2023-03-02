import java.util.HashSet;

public interface ICorpus {
  Boolean replaceContentWithString( String newText );
  Boolean corpusIsReady();
  Boolean beginCorpus();
  String nextCorpusLine();
  void endCorpus();
  HashSet<Character> lettersInCorpus();
}
