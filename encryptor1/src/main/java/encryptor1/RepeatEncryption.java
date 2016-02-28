package encryptor1;

public class RepeatEncryption<T> implements EncryptionAlgorithm<T> {

	
	private EncryptionAlgorithm<T> encAlgorithm;
	private int n;
	
	/*
	 * Constructs a repeat algorithm with the given 
	 * encryption algorithm and number of times to activate the 
	 * algorithm given. Number of times must be greater or equal to 1.
	 */
	public RepeatEncryption(EncryptionAlgorithm<T> encAlgorithm, int n) {
		this.encAlgorithm = encAlgorithm;
		if (!(1 <= n)){
		      throw new IllegalArgumentException("n= " +n +" must be greater or equal to 1.");
		}
		this.n = n;
		
	}

	public char encrypt(char character) {
		char encrypted = character;
		for(int i=0; i<n; i++)
			encrypted = encAlgorithm.encrypt(encrypted);
		return encrypted;
	}

	
	public char decrypt(char character) {
		char decrypted = character;
		for(int i=0; i<n; i++)
			decrypted = encAlgorithm.decrypt(decrypted);
		return decrypted;
	}


	public int getKeyStrength() {
		return n*encAlgorithm.getKeyStrength();
	}


	
}
