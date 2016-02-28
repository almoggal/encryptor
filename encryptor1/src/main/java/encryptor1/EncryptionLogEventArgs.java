package encryptor1;

public class EncryptionLogEventArgs {
	
	private String fileName;
	private String algorithmType;
	private long duration;
	
	/*
	 * Creates an EncryptionLogEventArgs with the relevant information
	 * for each message of EncryptionLogger.
	 */
	public EncryptionLogEventArgs(String fileName, String algorithmType, long duration) {
		this.setFileName(fileName);
		this.setAlgorithmType(algorithmType);
		this.setDuration(duration);
	}
	/*
	 * Returns the name of the file.
	 */
	public String getFileName() {
		return fileName;
	}
	/*
	 * Changes fileName to the name of the file specified.
	 */
	private void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/*
	 * Returns the name of the algorithm type.
	 */
	public String getAlgorithmType() {
		return algorithmType;
	}
	/*
	 * Modifies the name of the algorithm type, to the given one.
	 */
	private void setAlgorithmType(String algorithmType) {
		this.algorithmType = algorithmType;
	}
	/*
	 * Returns the duration, which is in milliseconds.
	 */
	public long getDuration() {
		return duration;
	}
	/*
	 * Modifies the duration to the given one.
	 */
	private void setDuration(long duration) {
		this.duration = duration;
	}
	
	
	@Override
	public int hashCode() {
		int result = 7;
		result = 37*result + fileName.hashCode();
		result = 37*result + algorithmType.hashCode();
		result = 37*result + (int)(duration ^ (duration >>> 32));
		return result;
	}
	
	/*
	 * Implementing equals in a manner that it behaves
	 * the same as '==' operation, as requested.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		return false;
	}
	

}
