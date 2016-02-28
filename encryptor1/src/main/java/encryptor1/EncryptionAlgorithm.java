package encryptor1;

public interface EncryptionAlgorithm<T> {
	/*
	 * Encrypts the given character, according to the implementer algorithm.
	 * Returns the encrypted character. 
	 */
	public char encrypt(char character);
	/*
	 * Decrypts the given character, according to the implementer algorithm.
	 * Returns the decrypted character. 
	 */
	public char decrypt(char character);
	/*
	 * Returns the maximal number of digits for the key, in the implementer algorithm.
	 */
	public int getKeyStrength();
}
