package DataStructures.Map;

import java.util.ArrayList;
import java.util.Set;

public interface Map<K, V> {

  V get(K key);

  V put(K key, V value);

  V remove(K key);

  Set<MapEntry<K,V>> entrySet();

  Set<K> keySet();

  ArrayList<V> values();

  boolean containsKey(K key);

  boolean containsValue(V value);

  void clear();

  boolean isEmpty();

  int size();
}
