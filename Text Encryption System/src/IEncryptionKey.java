public interface IEncryptionKey {
  String encryptText( String text );
  Boolean keyIsValid();
}
