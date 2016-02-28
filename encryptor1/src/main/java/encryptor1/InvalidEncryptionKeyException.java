package encryptor1;


public class InvalidEncryptionKeyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * Returns a message when this exception is thrown.
	 */
	public String getMessage(){
		return "invalid key format, key should be between 0 to 256";
	}
	
	
	
}
