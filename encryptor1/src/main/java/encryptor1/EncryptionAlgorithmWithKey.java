package encryptor1;

public abstract class EncryptionAlgorithmWithKey implements EncryptionAlgorithm<Integer>{
	
	protected int key;
	
	/*
	 * Creates an EncryptionAlgorithmWithKey that is an encryption algorithm,
	 * but with a given key.
	 * Checks if the key is out of bounds, if it is throws a corresponding exception.
	 */
	public EncryptionAlgorithmWithKey(int key) {
		if(!(key >=0 && key<256)){
			try {
				throw new InvalidEncryptionKeyException();
			} catch (InvalidEncryptionKeyException e) {
				
				e.printStackTrace();
			}
		}else this.key = key;
	}
	
	/*
	 * Returns the maximal number of digits for the key.
	 * In the default case, key is between 0-256.
	 * 
	 */
	public int getKeyStrength() {
		return 3;
	}


}
