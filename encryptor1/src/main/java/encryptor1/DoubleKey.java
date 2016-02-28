package encryptor1;
/*
 * Class that represents a double key, two keys for one such object.
 */
public class DoubleKey<T,S> {
	
	private T key1;
	private S key2;
	/*
	 * Creates a DoubleKey ,which has two keys.
	 */
	public DoubleKey(T key1, S key2) {
		this.setKey1(key1);
		this.setKey2(key2);
	}
	/*
	 * Returns the first key.
	 */
	public T getKey1() {
		return key1;
	}
	/*
	 * Modifies the first key to the given key.
	 */
	public void setKey1(T key1) {
		this.key1 = key1;
	}
	/*
	 * Returns the second key.
	 */
	public S getKey2() {
		return key2;
	}
	/*
	 * Modifies the second key to the given key.
	 */
	public void setKey2(S key2) {
		this.key2 = key2;
	}
}
