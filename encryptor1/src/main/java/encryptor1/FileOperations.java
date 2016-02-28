package encryptor1;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class FileOperations {

	
	public static void main(String[] args) throws IOException, InvalidEncryptionKeyException {

		String command, keyPath=null, sourceFile, encryptedFilePath=null, decryptedFilePath;
		int key, key2;
		Scanner scanner = new Scanner(System.in);

		System.out.println("For encryption enter e, for decryption enter d:");
		command = scanner.nextLine();
		
		if (command.equals("e")) {
			// encryption
			System.out.println("Enter the path to the source file:");
			sourceFile = scanner.nextLine();
			validateNotEmpty(sourceFile, "source");
			
			Random rand = new Random();
			key = rand.nextInt(256);
			key2 = rand.nextInt(256);
			keyPath = createKeyFile(sourceFile, key, key2);

			encryptedFilePath = addSubString(sourceFile, command);

			EncryptionAlgorithm<DoubleKey<Integer, Integer>> encryption = null;
			
			encryption = new DoubleEncryption<Integer, Integer>(
						new ShiftUpEncryption(key), new ShiftUpEncryption(key2));
			

			FileEncryptor<DoubleKey<Integer, Integer>> encryptor = new FileEncryptor<DoubleKey<Integer, Integer>>(
					encryption);

			EncryptionLogger logger=new EncryptionLogger(encryptor);
			
			encryptor.encryptFile(sourceFile, encryptedFilePath);

			encryptor.unRegister(logger);
			
			System.out.println("The location of the encrypted file is: "
					+ encryptedFilePath
					+ "  and the location of the key file is: " + keyPath);

		} else if (command.equals("d")) {
			// decryption
			
			System.out.println("Enter the path to the encrypted source file: ");
			encryptedFilePath = scanner.nextLine();
			System.out.println("Enter the path to the key file:");
			keyPath = scanner.nextLine();

			pathValidation(encryptedFilePath,keyPath);
			
			File keyFile = new File(keyPath);
			FileReader fReader = new FileReader(keyFile);
			key = fReader.read();
			key2 = fReader.read();
			fReader.close();

			decryptedFilePath = addSubString(encryptedFilePath, command);

			EncryptionAlgorithm<DoubleKey<Integer, Integer>> encryption = null;
		
			encryption = new DoubleEncryption<Integer, Integer>(
						new ShiftUpEncryption(key), new ShiftUpEncryption(key2));
		
			FileEncryptor<DoubleKey<Integer, Integer>> encryptor = new FileEncryptor<DoubleKey<Integer, Integer>>(
					encryption);
			EncryptionLogger logger=new EncryptionLogger(encryptor);
			
			encryptor.decryptFile(encryptedFilePath, decryptedFilePath);
			
			encryptor.unRegister(logger);

			System.out.println("The location of the decrypted file is: "
					+ decryptedFilePath);

		} else {
			EncryptionLog4JLogger.logger.fatal("Invalid command, enter e for encryption or d for decryption");
			
		}
		
		scanner.close();
	}

	/*
	 * Creating a file containing the specified key number.
	 */
	private static String createKeyFile(String sourceFile, int key, int key2)
			throws IOException {
		EncryptionLog4JLogger.logger.debug("Creating the key file");
		String keyPath = sourceFile.substring(0,
				sourceFile.lastIndexOf(File.separator) + 1)
				+ "key.txt";

		File keyFile = new File(keyPath);
	
		FileWriter fWriter = new FileWriter(keyFile);
		fWriter.write(key);
		fWriter.write(key2);
		fWriter.close();
		
		EncryptionLog4JLogger.logger.debug("Finished creating key file");
		return keyPath;

	}

	/*
	 * Adding to the file path the substring "_decrypt" when decrypted, and
	 * "_encrypt" when encrypted.
	 */
	public static String addSubString(String filePath, String command) {

		EncryptionLog4JLogger.logger.debug("Entered addSubString method");
		int indexSplit = filePath.lastIndexOf(".");
		String encryptionString;

		if (command.equals("e")) {
			encryptionString = "_encrypted";
		} else { // command equals "d"
			encryptionString = "_decrypted";
		}
		
		EncryptionLog4JLogger.logger.debug("Exiting addSubString method");
		
		return filePath.substring(0, indexSplit) + encryptionString
				+ filePath.substring(indexSplit);

	}
	
	/*
	 * Validates that the specified encrypted file path and key file path are 
	 * in the right format.
	 */
	private static void pathValidation(String encryptedPath, String keyPath){
		
		EncryptionLog4JLogger.logger.debug("Entered pathValidation method");
		Path encryptedFilePath= Paths.get(encryptedPath);
		Path keyFilePath = Paths.get(keyPath);
		
		validateNotEmpty(encryptedPath,"encrypted");
		validateNotEmpty(keyPath,"key");
		
		
		if(!(keyFilePath.endsWith("key.txt"))){
			invalidPathExceptionHandler("key file name should be key.txt");
		}
		
		
		if(keyFilePath.getParent()==null || encryptedFilePath.getParent()==null){
			if(!(encryptedFilePath.getParent()==null && keyFilePath.getParent()==null)){
				invalidPathExceptionHandler("the location of the key file and the encrypted file does not match");
			}
		}
	
		if(!(encryptedFilePath.getParent().equals(keyFilePath.getParent()))){
			
			invalidPathExceptionHandler("the location of the key file and the encrypted file does not match");
		}
		
		
		EncryptionLog4JLogger.logger.info("Key file path and encrypted file path were entered correctly.");
		
		
	}

	
	
	/*
	 * Validates that the path to the file, which is a key file
	 *  ,a decrypted file or an encrypted file, is not empty.
	 */
	private static void validateNotEmpty(String path,String typeOfFile){
		if(path.isEmpty()){
			invalidPathExceptionHandler("must enter "+typeOfFile+" file path");
			
		}
	}
	/*
	 * Throws an exception with the specified message.
	 */
	private static void invalidPathExceptionHandler(String msg) {
		try {
			throw new InvalidEncryptionPathException(msg);
		} catch (InvalidEncryptionPathException e) {
			EncryptionLog4JLogger.logger.fatal(e.getMessage());
			System.exit(1);
		}
	}
	

}
