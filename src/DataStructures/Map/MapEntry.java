package DataStructures.Map;

public class MapEntry<K, V> {

  private K key;
  private V value;

  MapEntry(K key, V value) {
    this.key = key;
    this.value = value;
  }

  K getKey() {
    return this.key;
  }

  V getValue() {
    return this.value;
  }

  void setValue(V value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    MapEntry<?, ?> mapEntry = (MapEntry<?, ?>) o;

    if (key != null ? !key.equals(mapEntry.key) : mapEntry.key != null) return false;
    return value != null ? value.equals(mapEntry.value) : mapEntry.value == null;
  }

  @Override
  public int hashCode() {
    int result = key != null ? key.hashCode() : 0;
    result = 31 * result + (value != null ? value.hashCode() : 0);
    return result;
  }
}
