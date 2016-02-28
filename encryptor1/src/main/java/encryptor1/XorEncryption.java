package encryptor1;

public class XorEncryption extends EncryptionAlgorithmWithKey {
	
	/*
	 * Constructs an encryption algorithm that uses
	 * xor operation.
	 */
	public XorEncryption(int key){
		super(key);
	}

	
	public char encrypt(char character) {
		return (char) (character^(key%256));
	}


	public char decrypt(char character) {
		return (char) (character^(key%256));
	}

}
