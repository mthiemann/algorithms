package DataStructures.Map;

import java.util.*;

@SuppressWarnings("unchecked")
public class HashMap<K,V> implements Map<K,V>{

  private int size;
  private double loadFactor;
  private LinkedList<MapEntry<K,V>>[] arr;
  private Set<K> keySet;
  private Set<MapEntry<K,V>> mapEntrySet;

  HashMap() {
    this.initHashMap();
  }

  private void initHashMap() {
    this.size = 0;
    this.loadFactor = 0.75;
    this.arr = new LinkedList[10];
    this.keySet = new HashSet<>();
    this.mapEntrySet = new HashSet<>();
  }

  private int getIdx(K key) {
    if (key == null) return this.arr.length - 1;
    return key.hashCode() % this.arr.length;
  }

  private boolean maximumCapacityReached() {
    return this.size >= (int)(this.arr.length * this.loadFactor);
  }

  private void resize() {
    this.arr = new LinkedList[this.arr.length << 1];
  }

  private void rehash() {
    for (MapEntry<K,V> mapEntry: this.mapEntrySet) {

      int idx = this.getIdx(mapEntry.getKey());
      LinkedList<MapEntry<K,V>> mapEntries = this.arr[idx];
      boolean bucketIsNull = mapEntries == null;

      if (bucketIsNull) mapEntries = new LinkedList<>();
      mapEntries.add(mapEntry);
      if (bucketIsNull) this.arr[idx] = mapEntries;
    }
  }

  private MapEntry<K,V> getMapEntry(K key) {
    LinkedList<MapEntry<K,V>> mapEntries = this.arr[this.getIdx(key)];

    if (mapEntries != null) {
      for (MapEntry<K, V> entry : mapEntries) {
        K entryKey = entry.getKey();
        if (entryKey == null) {
          if (key == null) return entry;
        } else {
          if (entryKey.equals(key)) return entry;
        }
      }
    }
    return null;
  }

  @Override
  public V get(K key) {
    MapEntry<K,V> mapEntry = this.getMapEntry(key);
    return (mapEntry != null) ? mapEntry.getValue() : null;
  }

  @Override
  public V put(K key, V value) {

    // resize and rehash if the maximum capacity was reached
    if (this.maximumCapacityReached()) {
      this.resize();
      this.rehash();
    }

    boolean exists = false;
    int idx = this.getIdx(key);
    LinkedList<MapEntry<K,V>> mapEntries = this.arr[idx];

    // create new linked list if there is no linked list in this bucket yet
    if (mapEntries == null) {
      mapEntries = new LinkedList<>();
      this.arr[idx] = mapEntries;
    } else {
      // update value if key exists
      MapEntry<K,V> mapEntry = this.getMapEntry(key);
      if (mapEntry != null) {
        mapEntry.setValue(value);
        exists = true;
      }
    }

    // add new map entry if the key does not exist
    if (!exists) {
      MapEntry<K,V> newMapEntry = new MapEntry<>(key, value);
      mapEntries.add(newMapEntry);

      this.keySet.add(key);
      this.mapEntrySet.add(newMapEntry);
      this.size++;
    }

    return value;
  }

  @Override
  public V remove(K key) {
    LinkedList<MapEntry<K,V>> mapEntries = this.arr[this.getIdx(key)];

    if (mapEntries != null) {
      Iterator<MapEntry<K,V>> iterator = mapEntries.iterator();
      boolean found = false;
      while (iterator.hasNext()) {
        MapEntry<K,V> entry = iterator.next();
        K entryKey = entry.getKey();

        if (entryKey == null) {
          if (key == null) {
            found = true;
          }
        } else {
          if(entryKey.equals(key)) {
            found = true;
          }
        }

        if (found) {
          iterator.remove();
          this.mapEntrySet.remove(entry);
          this.keySet.remove(key);
          this.size--;
          return entry.getValue();
        }
      }
    }

    return null;
  }

  @Override
  public Set<MapEntry<K, V>> entrySet() {
    return this.mapEntrySet;
  }

  @Override
  public Set<K> keySet() {
    return this.keySet;
  }

  @Override
  public ArrayList<V> values() {
    ArrayList<V> values = new ArrayList<>(this.size);
    int counter = 0;

    for (int i = 0; i < this.arr.length; i++) {
      if (counter == this.size) break;

      LinkedList<MapEntry<K,V>> mapEntries = this.arr[i];
      if (mapEntries == null) continue;

      for (MapEntry<K,V> entry: mapEntries) {
        values.add(entry.getValue());
        counter++;
      }
    }
    return values;
  }

  @Override
  public boolean containsKey(K key) {
    MapEntry<K,V> mapEntry = this.getMapEntry(key);
    return mapEntry != null;
  }

  @Override
  public boolean containsValue(V value) {
    int counter = 0;

    for (int i = 0; i < this.arr.length; i++) {
      if (counter == this.size) break;

      LinkedList<MapEntry<K,V>> mapEntries = this.arr[i];

      if (mapEntries != null) {
        for (MapEntry<K,V> entry: this.arr[i]) {
          V entryValue = entry.getValue();
          if (entryValue == null) {
            if (value == null) return true;
          } else {
            if(entryValue.equals(value)) return true;
          }
          counter++;
        }
      }
    }
    return false;
  }

  @Override
  public void clear() {
    this.initHashMap();
  }

  @Override
  public boolean isEmpty() {
    return this.size == 0;
  }

  @Override
  public int size() {
    return this.size;
  }
}
