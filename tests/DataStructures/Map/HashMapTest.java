package DataStructures.Map;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HashMapTest {

  private HashMap<Integer, Integer> hm;

  private static Integer[] smallArrayWithKeys = new Integer[]{100, 2, 7, 6, null};
  private static Integer[] smallArrayWithValues = new Integer[]{7, null, 2, 1, 14};
  private static Integer nonExistingKeyInSmallArray = 8;

  private static Integer[] bigArrayWithKeys = new Integer[]{200, 11, 23, null, 9, 600, 101, 2, 3};
  private static Integer[] bigArrayWithValues = new Integer[]{1, 20, 8, null, 900, 1100, null, 32, 6};

  private static Integer[] twoDistinctKeysForDuplicateValues = new Integer[]{234, 767};
  private static Integer[] twoDuplicateValues = new Integer[]{90, 90};
  private static Integer[] fiveDistinctKeysForDuplicateValues = new Integer[]{211, 321, 73, 109, 42};
  private static Integer[] fiveDuplicateValues = new Integer[]{102, 102, 102, 102, 102};

  @Before
  public void setUp() {
    this.hm = new HashMap<>();
  }

  private void checkSameLength(Integer[] keys, Integer[] values) {
    if (keys.length != values.length) {
      throw new IllegalArgumentException("Arrays must be of same length!");
    }
  }

  /**
   * Fills the HashMap with the elements of the passed key and value arrays and checks the size
   * while filling
   * @param keys
   * @param values
   */
  private void fillHashMap(Integer[] keys, Integer[] values) {

    this.checkSameLength(keys, values);
    int initialSize = this.hm.size();
    int uniqueElementsAdded = 0;

    for (int i = 0; i < keys.length; i++) {
      if (!this.hm.containsKey(keys[i])) uniqueElementsAdded++;
      this.hm.put(keys[i], values[i]);
      assertEquals(this.hm.size(), initialSize + uniqueElementsAdded);
    }
  }

  /**
   * Returns the frequency of each value for the passed ArrayList
   * @param arrayList
   * @return A HashMap that maps the value to its frequency
   */
  private HashMap<Integer, Integer> getFrequencyOfValues(ArrayList<Integer> arrayList) {
    HashMap<Integer, Integer> valueToFrequency = new HashMap<>();
    for (Integer value: arrayList) {
      Integer frequency = valueToFrequency.get(value);
      if (frequency != null) {
        valueToFrequency.put(value, frequency + 1);
      } else {
        valueToFrequency.put(value, 1);
      }
    }
    return valueToFrequency;
  }

  /**
   * Removes the passed keys from the HashMap and compares the returned value with the corresponding
   * value of the passed values array
   * Checks the size, contains key and contains value after every removal of an element
   * @param keys
   * @param values
   */
  private void removeFromHashMap(Integer[] keys, Integer[] values) {

    this.checkSameLength(keys, values);

    int initialSize = this.hm.size();

    // check if array is bigger than the HashMap
    if (keys.length > initialSize) {
      throw new IllegalArgumentException("The number of passed keys exceeds " +
        "the number of elements in the HashMap! (Max: " + initialSize +
        ", Received: " + keys.length + ")");
    }

    // check for duplicate keys
    HashSet<Integer> existingKeys = new HashSet<>();
    for (Integer key : keys) {
      if (!existingKeys.add(key)) {
        throw new IllegalArgumentException("No duplicate keys allowed!");
      }
    }

    HashMap<Integer, Integer> valueToFrequency = this.getFrequencyOfValues(this.hm.values());

    for (int i = 0; i < keys.length; i++) {
      Integer value = this.hm.remove(keys[i]);

      assertEquals(value, values[i]);
      assertEquals(this.hm.size(), initialSize - 1 - i); // check size
      assertFalse(this.hm.containsKey(keys[i])); // check contains key

      // check contains value
      Integer newFrequency = valueToFrequency.get(value) - 1;
      valueToFrequency.put(value, newFrequency);
      if (newFrequency == 0) {
        assertFalse(this.hm.containsValue(value));
      } else {
        assertTrue(this.hm.containsValue(value));
      }
    }
  }

  /**
   * Checks the mapping for the passed integer key and value arrays
   * by calling the get, containsKey and containsValue method
   * @param keys
   * @param values
   * @param exists Determines if the mapping is expected to exist in the HashMap
   */
  private void checkMapping(Integer[] keys, Integer[] values, boolean exists) {

    this.checkSameLength(keys, values);

    for (int i = 0; i < keys.length; i++) {

      boolean containsKey = this.hm.containsKey(keys[i]);
      boolean containsValue = this.hm.containsValue(values[i]);

      if (!exists) {
        containsKey = !containsKey;
        containsValue = !containsValue;
      }
      Integer expectedValue = (exists) ? values[i] : null;

      assertEquals(this.hm.get(keys[i]), expectedValue);
      assertTrue(containsKey);
      assertTrue(containsValue);
    }
  }

  /**
   * Checks that the set of keys in the HashMap equals the passed array of input keys
   * (expects no other keys in the map than the passed input keys)
   * @param inputKeys
   */
  private void checkKeys(Integer[] inputKeys) {
    HashSet<Integer> inputKeySet = new HashSet<>(Arrays.asList(inputKeys));
    assertTrue(this.hm.keySet().equals(inputKeySet));
  }

  /**
   * Checks that the frequency of the passed values equals the frequency of the values in the HM
   * @param inputValues
   */
  private void checkFrequencyOfValues(Integer[] inputValues) {

    ArrayList<Integer> inputValuesArrayList = new ArrayList<>(Arrays.asList(inputValues));
    ArrayList<Integer> hmValuesArrayList = this.hm.values();

    HashMap<Integer, Integer> inputValueToFrequency = this.getFrequencyOfValues(inputValuesArrayList);
    HashMap<Integer, Integer> hmValueToFrequency = this.getFrequencyOfValues(hmValuesArrayList);

    // the occurrences for all values match
    for (MapEntry<Integer, Integer> entry: inputValueToFrequency.entrySet()) {
      assertEquals(entry.getValue(), hmValueToFrequency.get(entry.getKey()));
    }
  }

  /**
   * Checks that the map entries in the HashMap equal the passed array mappings
   * (expects no other values in the map than the passed key and value arrays
   * @param inputKeys
   * @param inputValues
   */
  private void checkMapEntries(Integer[] inputKeys, Integer[] inputValues) {
    Set<MapEntry<Integer, Integer>> hmSet = this.hm.entrySet();
    HashSet<MapEntry<Integer, Integer>> inputSet = new HashSet<>(inputKeys.length);

    for (int i = 0; i < inputKeys.length; i++) {
      inputSet.add(new MapEntry<>(inputKeys[i], inputValues[i]));
    }

    assertTrue(hmSet.equals(inputSet));
  }

  @Test
  public void testGetOnEmptyHashMap() {
    assertEquals(this.hm.get(10), null);
  }

  @Test
  public void testGetForNonExistingElement() {
    this.fillHashMap(HashMapTest.smallArrayWithKeys, HashMapTest.smallArrayWithValues);
    assertEquals(this.hm.get(HashMapTest.nonExistingKeyInSmallArray), null);
  }

  @Test
  public void testMappingWithoutResize() {
    this.fillHashMap(HashMapTest.smallArrayWithKeys, HashMapTest.smallArrayWithValues);
    this.checkMapping(HashMapTest.smallArrayWithKeys, HashMapTest.smallArrayWithValues, true);
  }

  @Test
  public void testMappingAfterResize() {
    this.fillHashMap(HashMapTest.bigArrayWithKeys, HashMapTest.bigArrayWithValues);
    this.checkMapping(HashMapTest.bigArrayWithKeys, HashMapTest.bigArrayWithValues, true);
  }

  @Test
  public void testKeysWithoutResize() {
    this.fillHashMap(HashMapTest.smallArrayWithKeys, HashMapTest.smallArrayWithValues);
    this.checkKeys(HashMapTest.smallArrayWithKeys);
  }

  @Test
  public void testKeysAfterResize() {
    this.fillHashMap(HashMapTest.bigArrayWithKeys, HashMapTest.bigArrayWithValues);
    this.checkKeys(HashMapTest.bigArrayWithKeys);
  }

  @Test
  public void testValuesWithoutResize() {
    this.fillHashMap(HashMapTest.smallArrayWithKeys, HashMapTest.smallArrayWithValues);
    this.checkFrequencyOfValues(HashMapTest.smallArrayWithValues);
  }

  @Test
  public void testValuesAfterResize() {
    this.fillHashMap(HashMapTest.bigArrayWithKeys, HashMapTest.bigArrayWithValues);
    this.checkFrequencyOfValues(HashMapTest.bigArrayWithValues);
  }

  @Test
  public void testMapEntriesWithoutResize() {
    this.fillHashMap(HashMapTest.smallArrayWithKeys, HashMapTest.smallArrayWithValues);
    this.checkMapEntries(HashMapTest.smallArrayWithKeys, HashMapTest.smallArrayWithValues);
  }

  @Test
  public void testMapEntriesAfterResize() {
    this.fillHashMap(HashMapTest.bigArrayWithKeys, HashMapTest.bigArrayWithValues);
    this.checkMapEntries(HashMapTest.bigArrayWithKeys, HashMapTest.bigArrayWithValues);
  }

  @Test
  public void testRemoveForExistingElements() {
    this.fillHashMap(HashMapTest.smallArrayWithKeys, HashMapTest.smallArrayWithValues);
    this.removeFromHashMap(HashMapTest.smallArrayWithKeys, HashMapTest.smallArrayWithValues);
  }

  @Test
  public void testRemoveForNonExistingElement() {
    this.fillHashMap(HashMapTest.smallArrayWithKeys, HashMapTest.smallArrayWithValues);
    assertEquals(this.hm.remove(HashMapTest.nonExistingKeyInSmallArray), null);
  }

  @Test
  public void testRemoveOnEmptyHashMap() {
    assertEquals(this.hm.remove(10), null);
  }

  @Test
  public void testClear() {
    this.fillHashMap(HashMapTest.smallArrayWithKeys, HashMapTest.smallArrayWithValues);
    this.hm.clear();

    this.checkMapping(HashMapTest.smallArrayWithKeys, HashMapTest.smallArrayWithValues, false);
    assertTrue(this.hm.isEmpty());
  }

  @Test
  public void testIsEmpty() {
    assertTrue(this.hm.isEmpty()); // initial state: no values

    this.hm.put(HashMapTest.nonExistingKeyInSmallArray, 7);
    assertFalse(this.hm.isEmpty()); // edge case: one value

    this.fillHashMap(HashMapTest.smallArrayWithKeys, HashMapTest.smallArrayWithValues);
    assertFalse(this.hm.isEmpty()); // after inserting multiple values

    this.hm.remove(HashMapTest.nonExistingKeyInSmallArray);
    assertFalse(this.hm.isEmpty()); // after one removal operation

    this.removeFromHashMap(HashMapTest.smallArrayWithKeys, HashMapTest.smallArrayWithValues);
    assertTrue(this.hm.isEmpty()); // after removing all values
  }

  @Test
  public void testNoDuplicateKeys() {
    this.hm.put(2, 5);
    this.hm.put(2, 7);

    // check for map with one element
    assertEquals(this.hm.size(), 1);
    assertEquals((int)this.hm.get(2), 7);

    // add other elements
    this.fillHashMap(HashMapTest.smallArrayWithKeys, HashMapTest.smallArrayWithValues);

    // check for map with multiple elements
    int cachedSize = this.hm.size();
    this.hm.put(2, 10);
    assertEquals(this.hm.size(), cachedSize);
    assertEquals((int)this.hm.get(2), 10);
  }

  @Test
  public void testDuplicateValuesForDifferentKeys() {
    // fill with two duplicate values
    Integer valueWithTwoOccurrences = HashMapTest.twoDuplicateValues[0];
    this.fillHashMap(HashMapTest.twoDistinctKeysForDuplicateValues, HashMapTest.twoDuplicateValues);

    // fill with another five duplicate values
    Integer valueWithFiveOccurrences = HashMapTest.fiveDuplicateValues[0];
    this.fillHashMap(HashMapTest.fiveDistinctKeysForDuplicateValues, HashMapTest.fiveDuplicateValues);

    this.fillHashMap(HashMapTest.bigArrayWithKeys, HashMapTest.bigArrayWithValues);

    this.checkFrequencyOfValues(HashMapTest.fiveDuplicateValues);
    this.checkFrequencyOfValues(HashMapTest.twoDuplicateValues);
    this.checkFrequencyOfValues(HashMapTest.bigArrayWithValues);

    HashMap<Integer, Integer> valueToFrequency = this.getFrequencyOfValues(this.hm.values());
    assertEquals((int)valueToFrequency.get(valueWithTwoOccurrences), 2);
    assertEquals((int)valueToFrequency.get(valueWithFiveOccurrences), 5);
  }

}