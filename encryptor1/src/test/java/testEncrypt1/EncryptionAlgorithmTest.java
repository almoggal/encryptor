package testEncrypt1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.*;
import encryptor1.*;

public class EncryptionAlgorithmTest {

	private static char testChar; 
	private static int key,n;
	private char actual;
	private static ShiftUpEncryption shiftUpAlgo;
	private static ShiftMultiplyEncryption shiftMultiplyAlgo;
	private static XorEncryption xorAlgo;
	private static RepeatEncryption<?> repeatAlgo;
	private static DoubleEncryption<?, ?> doubleAlgo;
	 
	private static EncryptionAlgorithmComparator comparator;
	@SuppressWarnings("unchecked")
	private static EncryptionAlgorithm<Integer> encAlgorithm= (EncryptionAlgorithm<Integer>) mock(EncryptionAlgorithm.class);
	private static EncryptionAlgorithm<?> encAlgorithm2=mock(EncryptionAlgorithm.class);
	
	
	
	@BeforeClass
	public static void oneTimeSetUp() throws InvalidEncryptionKeyException {
		//initialization
	    testChar='a';
	    key=1;
	    n=2;
		
		//using ShiftUpEncryption as EncryptionAlgorithm implementor for example
		char testChar2=(char)(testChar+key);
		
		when(encAlgorithm.encrypt(testChar)).thenReturn(testChar2);
		when(encAlgorithm.decrypt(testChar2)).thenReturn(testChar);
		when(encAlgorithm.encrypt(testChar2)).thenReturn((char)(testChar2 +key));
		when(encAlgorithm.decrypt((char)(testChar2+key))).thenReturn(testChar2);
		when(encAlgorithm.getKeyStrength()).thenReturn(3);
		
		
		shiftUpAlgo = new ShiftUpEncryption(key);
		shiftMultiplyAlgo = new ShiftMultiplyEncryption(key);
		xorAlgo = new XorEncryption(key);
		repeatAlgo = new RepeatEncryption<Integer>(encAlgorithm, n);
		doubleAlgo=new DoubleEncryption<Integer, Integer>(encAlgorithm, encAlgorithm);
		
		
		when(encAlgorithm.getKeyStrength()).thenReturn(3);
		when(encAlgorithm2.getKeyStrength()).thenReturn(6);
		comparator = new EncryptionAlgorithmComparator();
		
		
	}
	

	@After
	public void afterTest(){
		assertEquals(testChar, actual);
	}
	
	@Test
	public void testShiftUpEncryption() {
		
		actual = shiftUpAlgo.decrypt(shiftUpAlgo.encrypt(testChar));
		
	}
	
	@Test
	public void testShiftMultiplyEncryption() {
		
		actual = shiftMultiplyAlgo.decrypt(shiftMultiplyAlgo.encrypt(testChar));
		
		
	}
	
	@Test
	public void testXorEncryption(){
	
		actual = xorAlgo.decrypt(xorAlgo.encrypt(testChar));
		
	}
	
	@Test
	public void testRepeatEncryption(){
	
		actual = repeatAlgo.decrypt(repeatAlgo.encrypt(testChar));
		
	}
	
	@Test
	public void testDoubleEncryption(){
	
		actual = doubleAlgo.decrypt(doubleAlgo.encrypt(testChar));
		
	}
	
	@Test
	public void testInvalidEncryptionKeyException(){
	
		boolean thrown=false;
		
		try {
			shiftUpAlgo = new ShiftUpEncryption(-10);
		} catch (InvalidEncryptionKeyException encryptionKeyException) {
			thrown = true;
		} finally {
			assertEquals(true, thrown);
			testChar = actual; // for the method @after to pass
		}
	
	}
	
	@Test
	public void testEncryptionComparatorLessThan() {
	
		assertTrue(comparator.compare(encAlgorithm, encAlgorithm2) < 0);
		actual=testChar; // so the after method should pass
	}
	
	@Test
	public void testEncryptionComparatorGreaterThan() {
		
		assertTrue(comparator.compare(encAlgorithm2, encAlgorithm) > 0);
		actual=testChar;

	}
	
	@Test
	public void testEncryptionComparatorEqual() {

		assertTrue(comparator.compare(encAlgorithm, encAlgorithm) == 0);
		actual = testChar;

	}
}
