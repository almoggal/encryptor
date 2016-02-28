package encryptor1;

public class ShiftMultiplyEncryption extends EncryptionAlgorithmWithKey {

	/*
	 * Constructs a multiplication by key algorithm
	 * with the specified key. If key is even, makes it odd.
	 */
	public ShiftMultiplyEncryption(int key) {
		super(key);
		//key must be odd.
		if(key%2==0){
			this.key = (key+1)%256;
		}
	
	}
	
	
	public char encrypt(char character) {
		return (char) ((character*key)%256);
	}

	
	public char decrypt(char character) {
		
		int x;
		
		for(x=0; x < key; x++){
			if((character+256*x) % key ==0)
				break;
			
		}
		
		return (char) (((character+256*x)/key)%256);
	}
	
	
}
