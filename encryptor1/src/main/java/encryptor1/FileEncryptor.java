package encryptor1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


public class FileEncryptor<T> {

	
	
	private EncryptionAlgorithm<T> encryption = null;
	private List<EncryptionLogger> registered;

	public FileEncryptor(EncryptionAlgorithm<T> encryption) {
		this.encryption = encryption;
		registered = new ArrayList<EncryptionLogger>();
	}

	/*
	 * Adds the given logger to registered list.
	 */
	public void register(EncryptionLogger logger) {
		
		registered.add(logger);
	}
	/*
	 * Removes the given logger from the registered list.
	 */
	public void unRegister(EncryptionLogger logger) {
		int loggerIndex= registered.indexOf(logger);
		registered.remove(loggerIndex);
		EncryptionLog4JLogger.logger.debug("unregistered logger");
	}
	/*
	 * Activates the corresponding method in EncryptionLogger, 
	 * for all loggers registered.
	 */
	public void onEncryptionStart(String fileName, String algorithmType,
			long duration) {
		EncryptionLogEventArgs args = new EncryptionLogEventArgs(fileName,
				algorithmType, duration);
		for (EncryptionLogger logger : registered) {
			logger.logEncryptionStart(args);
		}
	}
	/*
	 * Activates the corresponding method in EncryptionLogger, 
	 * for all loggers registered.
	 */
	public void onEncryptionEnd(String fileName, String algorithmType,
			long duration) {
		EncryptionLogEventArgs args = new EncryptionLogEventArgs(fileName,
				algorithmType, duration);
		for (EncryptionLogger logger : registered) {
			logger.logEncryptionEnd(args);
		}
	}
	/*
	 * Activates the corresponding method in EncryptionLogger, 
	 * for all loggers registered.
	 */
	public void onDecryptionStart(String fileName, String algorithmType,
			long duration) {
		EncryptionLogEventArgs args = new EncryptionLogEventArgs(fileName,
				algorithmType, duration);
		for (EncryptionLogger logger : registered) {
			logger.logDecryptionStart(args);
		}
	}
	/*
	 * Activates the corresponding method in EncryptionLogger, 
	 * for all loggers registered.
	 */
	public void onDecryptionEnd(String fileName, String algorithmType,
			long duration) {
		EncryptionLogEventArgs args = new EncryptionLogEventArgs(fileName,
				algorithmType, duration);
		for (EncryptionLogger logger : registered) {
			logger.logDecryptionEnd(args);
		}
	}

	/*
	 * Returns the name of the algorithm used.
	 */
	private String getAlgoName() {
		return encryption.getClass().getName();
	}
	
	/*
	 * Encrypts the file corresponding to the first argument,
	 * and outputs the encrypted file to the path in the second argument.
	 */
	public void encryptFile(String originalFilePath, String outputFilePath)
			throws IOException, FileNotFoundException {
		onEncryptionStart(originalFilePath, getAlgoName(), 0);
		long startTime = System.currentTimeMillis();

		execute(originalFilePath, outputFilePath, "e");

		long endTime = System.currentTimeMillis();
		onEncryptionEnd(originalFilePath, getAlgoName(), endTime - startTime);
		
	}

	/*
	 * Decrypts the encrypted file corresponding to originalFilePath,
	 * and outputs the decrypted file to outputFilePath. 
	 */
	public void decryptFile(String originalFilePath, String outputFilePath) {
		onDecryptionStart(originalFilePath, getAlgoName(), 0);
	
		long startTime = System.currentTimeMillis();

		execute(originalFilePath, outputFilePath, "d");

		long endTime = System.currentTimeMillis();
		onDecryptionEnd(originalFilePath, getAlgoName(), endTime - startTime);
		
	}

	/*
	 * Executes the encryption algorithm with the specified arguments.
	 */
	private void execute(String fromFilePath, String toFilePath, String command) {

		EncryptionLog4JLogger.logger.debug("Entering the execute method");
		
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;

		try {
			File fromFile = new File(fromFilePath);
			bufferedReader = new BufferedReader(new InputStreamReader(
					new FileInputStream(fromFile), "UTF-8"));

			File toFile = new File(toFilePath);
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(toFile), "UTF-8"));

			int character;
			while ((character = bufferedReader.read()) != -1) {
				if (command.equals("e"))
					bufferedWriter.write(encryption.encrypt((char) character));
				else
					bufferedWriter.write(encryption.decrypt((char) character));
			}
			
		} catch (IOException e) {
			EncryptionLog4JLogger.logger.fatal("ERROR: in execute method " + e.getMessage());
			
		} finally {
			try {
				bufferedReader.close();
				bufferedWriter.close();
			} catch (IOException e) {
				EncryptionLog4JLogger.logger.fatal("ERROR: while closing buffers in execute method " + e.getMessage());
				
			}
		}

	}
}
