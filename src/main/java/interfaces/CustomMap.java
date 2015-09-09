package interfaces;


public interface CustomMap<K, V> {

    int size();

    boolean isEmpty();

    boolean containsKey(Object key);

    boolean containsValue(Object value);

    V get(Object key);

    V put(K key, V value);

    V remove(Object key);

    void clear();

    interface Entry<K, V> {
        K getKey();

        V getValue();
    }
}
