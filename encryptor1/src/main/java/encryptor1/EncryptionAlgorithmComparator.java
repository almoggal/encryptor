package encryptor1;

import java.util.Comparator;

public class EncryptionAlgorithmComparator implements Comparator<EncryptionAlgorithm<?>>{
	/*
	 * Compares between two given encryption algorithms, by their key strength.
	 */
	public int compare(EncryptionAlgorithm<?> o1, EncryptionAlgorithm<?> o2) {
		return o1.getKeyStrength() - o2.getKeyStrength();
	}

}
