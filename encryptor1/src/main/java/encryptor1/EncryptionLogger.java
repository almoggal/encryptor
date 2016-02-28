package encryptor1;

public class EncryptionLogger {


	FileEncryptor<?> fileEnc;
	/*
	 * Creates an EncryptioLogger with a given FileEncryptor<?>.
	 * Adds to the registered list of the argument this EncryptionLogger.
	 * 
	 */
	public EncryptionLogger(FileEncryptor<?> fileEnc){
		this.fileEnc=fileEnc;
		fileEnc.register(this);
		EncryptionLog4JLogger.logger.debug("logger registered");
	}
	
	/*
	 * Prints a message for encryption start with the given argument.
	 */
	public void logEncryptionStart(EncryptionLogEventArgs args) {
		
		System.out.println("The encryption for file " + args.getFileName()
				+ " with algorithm " + args.getAlgorithmType()
				+ " has started.");
		
	}
	/*
	 * Prints a message for encryption end with the given argument.
	 */
	public void logEncryptionEnd(EncryptionLogEventArgs args) {
		System.out.println("The encryption for file " + args.getFileName()
				+ " with algorithm " + args.getAlgorithmType() + " took "
				+ args.getDuration()
				+ "millisecons. The encrypted file is located in file "
				+ FileOperations.addSubString(args.getFileName(), "e"));
	}
	/*
	 * 	Prints a message for decryption start with the given argument.
	 */
	public void logDecryptionStart(EncryptionLogEventArgs args) {
		System.out.println("The decryption for file " + args.getFileName()
				+ " with algorithm " + args.getAlgorithmType() + " has started.");
				
	}
	/*
	 * 	Prints a message for decryption end with the given argument.
	 */
	public void logDecryptionEnd(EncryptionLogEventArgs args) {
		System.out.println("The decryption for file " + args.getFileName()
				+ " with algorithm " + args.getAlgorithmType() + " took "
				+ args.getDuration()
				+ "millisecons. The decrypted file is located in file "
				+ FileOperations.addSubString(args.getFileName(), "d"));
	}
}
