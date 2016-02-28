package encryptor1;

public class DoubleEncryption<T,S> implements EncryptionAlgorithm<DoubleKey<T,S>> {

	private EncryptionAlgorithm<T> encryption1;
	private EncryptionAlgorithm<S> encryption2;

	/*
	 * Constructs a DoubleEncryption algorithm, with two given encryption algorithms.
	 */
	public DoubleEncryption(EncryptionAlgorithm<T> encryption1, EncryptionAlgorithm<S> encryption2) {
		this.encryption1= encryption1;
		this.encryption2= encryption2;
	}
	
	/*
	 * Encrypts the given character with the first algorithm,
	 * and then the second.
	 */
	public char encrypt(char character) {
		return encryption2.encrypt(encryption1.encrypt(character));
	}

	/*
	 * Decrypts the given character with the second algorithm,
	 * and then the first one.
	 */
	public char decrypt(char character) {
		return encryption1.decrypt(encryption2.decrypt(character));
	}
	/*
	 * Adds the two key strengths of the given algorithms.
	 */
	public int getKeyStrength() {
		return encryption1.getKeyStrength() + encryption2.getKeyStrength();
	}

}
