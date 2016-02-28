package encryptor1;

public class ShiftUpEncryption extends EncryptionAlgorithmWithKey {

		private final static int mod=256;
		/*
		 * Constructs an encryption algorithm that is
		 * adding the given key.
		 */
		public ShiftUpEncryption(int key) throws InvalidEncryptionKeyException{
			super(key);
		}
		
		public char encrypt(char character){
			return (char) (( mod +(character +(char)(key % mod)))% mod);
		}

		public char decrypt(char character){
			return (char) ((mod+(character-(char)(key % mod)))% mod);
		}

}
