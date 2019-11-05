package GenericPairs;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GenericPairTest {

	GenericPair<String, Double> gp;
	final Double val = 1.5;
	
	@BeforeEach
	void init() {
		gp = GenericPair.of("evyatar", val);
	}
	
	
	@Test
	void testGetKey() {
		assertSame(gp.getKey(), "evyatar");
	}
	
	@Test
	void testGetValue() {
		assertSame(gp.getValue(), val);
	}
	
	
	@Test
	void testSetValue() {
		Double newVal = 3.75;
		
		Double prevVal = gp.setValue(newVal);
		
		assertSame(gp.getValue(), newVal);
		assertSame(val, prevVal);
	}
	
	@Test
	void testSwap() {
		GenericPair<Double, String> swappedGp = GenericPair.swap(gp);
		
		assertSame(gp.getValue(), val);
		assertSame(swappedGp.getKey(), val);
		assertSame(swappedGp.getValue(), "evyatar");
	}
	
	@Test
	void testEquals() {
		
		Integer val = 2;
		Integer val2 = 2;
		
		GenericPair<String, Integer> gp = GenericPair.of("evyatar", val);
		GenericPair<String, Integer> equalToGp = GenericPair.of("evyatar", val2);
		
		GenericPair<Integer, String> swappedGp = GenericPair.swap(gp);
		
		String str = "evyatar";
		
		assertTrue(gp.equals(equalToGp));
		assertFalse(gp.equals(swappedGp));
		assertFalse(gp.equals(str));
	}
	
	@Test
	void testHashCode() {
		GenericPair<String, Double> equalToGp = GenericPair.of("evyatar", val);
		
		assertEquals(gp.hashCode(), equalToGp.hashCode());
	}
	
	@Test
	void testToString() {
		GenericPair<String, Double> equalToGp = GenericPair.of("evyatar", val);
		
		assertEquals(equalToGp.toString(), gp.toString());
	}
	
	@Test
	void testTMinMaxComperable() {
		
		Integer arr[] = {3,5,6,4,1,0};
		Integer oddarr[] = {6,3,5,2,4,1,0};
		
		GenericPair<?, ?> gp = GenericPair.minMax(arr);
		GenericPair<?, ?> oddGp = GenericPair.minMax(oddarr);
		GenericPair<Integer, Integer> equalToGp = GenericPair.of(new Integer(0), new Integer(6));
		
		assertEquals(gp, equalToGp);
		assertEquals(oddGp, equalToGp);
	}
	
	class IntegerComperator<T extends Comparable<T>> implements Comparator<T> {

		IntegerComperator() {};
		
		@Override
	    public int compare(T a, T b) {
	        int num1 = ((Integer)a).intValue();
	        int num2 = ((Integer)b).intValue();
			
	        num1 /= 10;
	        num1 %= 10;
	        num2 /= 10;
	        num2 %= 10;
			
			return num1 - num2;
	    }
	}
	
	@Test
	void testTMinMaxComperator() {
		Integer arr[] = {30,50,60,40,10,0};
		Integer oddarr[] = {60,30,50,20,40,10,0};
		
		//compare by the digit of tens
		IntegerComperator<Integer> comp = new IntegerComperator<>();
		
		GenericPair<?, ?> gp = GenericPair.minMax(arr, comp);
		GenericPair<?, ?> oddGp = GenericPair.minMax(oddarr, comp);
		GenericPair<Integer, Integer> equalToGp = GenericPair.of(new Integer(0), new Integer(60));
		GenericPair<Integer, Integer> equalToOddGp = GenericPair.of(new Integer(0), new Integer(60));
		
		assertEquals(gp, equalToGp);
		assertEquals(oddGp, equalToOddGp);
	}
}