package GenericPairs;

import java.util.Comparator;
import java.util.Map;		
		
/**
 * @author Avishai
 *
 * @param <K> - key type
 * @param <V> - value type
 */
public class GenericPair<K,V> implements Map.Entry<K, V>{		
	private K key;	
	private V value;	
		
	/**
	 * private constructor to init the key and the value
	 * @param key
	 * @param value
	 */
	private GenericPair(K key,V value) {
		this.key = key;
		this.value = value;
	}	
		
	/**
	 * to swap the values between the key and the value
	 * @param pair - the GenericPair that need to be swaped
	 * @return a new GenericPair with the swaped values
	 */
	public static <K,V> GenericPair<V,K> swap(GenericPair<K,V> pair) {	
		return new GenericPair<V, K>(pair.getValue(), pair.getKey());
	}	
		
	/* 
	 * to get the key from GenericPair
	 * @see java.util.Map.Entry#getKey()
	 */
	@Override	
	public K getKey() {	
		return this.key;
	}	
		
	/* 
	 * to get the value from GenericPair
	 * @see java.util.Map.Entry#getValue()
	 */
	@Override	
	public V getValue() {	
		return this.value;
	}	
		
	/* 
	 * to set a new value in the GenericPair
	 * @see java.util.Map.Entry#setValue(java.lang.Object)
	 * @param value
	 * @return the old value that stored in the GenericPair
	 */
	@Override	
	public V setValue(V value) {
		final V oldVal = this.getValue();
		
		this.value = value;
		
		return oldVal;
	}	
	
	/**
	 * to return the min and the max in a given array with Comparable values, in O(1.5n)
	 * @param array - to search
	 * @return a GenericPair of the min and the max
	 */
	public static <T extends Comparable<? super T>> GenericPair<T,T> minMax(T[] array) {
		T min = array[0];
		T max = array[0];
		
		int i = array.length % 2;
		
		// jumping of two cells
		for ( ; i < array.length - 1; i += 2) {
			GenericPair<T,T> tempMinMax;
			
			// comparing two cells themselves
			if (array[i].compareTo(array[i + 1]) < 0) {
				tempMinMax = of(array[i], array[i + 1]);
			}
			else {
				tempMinMax = of(array[i + 1], array[i]);
			}
			
			// comparing the min value
			if (tempMinMax.getKey().compareTo(min) < 0) {
				min = tempMinMax.getKey();
			}
			
			// comparing the max value
			if (tempMinMax.getValue().compareTo(max) > 0) {
				max = tempMinMax.getValue();
			}
		}
		
		return of(min, max);
	}	
		
	/**
	 * to return the min and the max in a given array, by a given func, in O(1.5n)
	 * @param array - to search
	 * @param cmp - the check function
	 * @return a GenericPair of the min and the max
	 */
	public static <T> GenericPair<T,T> minMax(T[] array, Comparator<? super T> cmp) {
		T min = array[0];
		T max = array[0];
		
		int i = array.length % 2;
		
		// jumping of two cells
		for ( ; i < array.length - 1; i += 2) {
			GenericPair<T,T> tempMinMax;
			
			// comparing two cells themselves
			if (cmp.compare(array[i], array[i + 1]) < 0) {
				tempMinMax = of(array[i], array[i + 1]);
			}
			else {
				tempMinMax = of(array[i + 1], array[i]);
			}
			
			// comparing the min value
			if (cmp.compare(tempMinMax.getKey(), min) < 0) {
				min = tempMinMax.getKey();
			}
			
			// comparing the max value
			if (cmp.compare(tempMinMax.getValue(), max) > 0) {
				max = tempMinMax.getValue();
			}
		}
		
		return of(min, max);
	}	
		
	/**
	 * factory function to create a new GenericPair
	 * @param key
	 * @param value
	 * @return new GenericPair with the key and the value
	 */
	public static <K,V> GenericPair<K,V> of(K key, V value) {	
		return new GenericPair<K, V>(key, value);
	}	
		
	/* 
	 * to check if a given object is equals to the GenericPair
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override	
	public boolean equals(Object pair) {	
		boolean result = false;
				
		if (pair instanceof GenericPair) {
			GenericPair<?, ?> p = (GenericPair<?, ?>)pair;
			
			result = ((this.getKey().equals(p.getKey())) &&
					  (this.getValue().equals(p.getValue())));
		}
		
		return result;
	}	
		
	/* 
	 * to get the hash code of a GenericPair
	 * @see java.lang.Object#hashCode()
	 */
	@Override	
	public int hashCode() {	
		return this.getKey().hashCode() + this.getValue().hashCode();
	}	
		
	/* 
	 * to get the key and the value from the GenericPair
	 * @see java.lang.Object#toString()
	 */
	@Override	
	public String toString() {	
		return ("key: " + this.getKey() + ", and value: " + this.getValue());
	}	
}		