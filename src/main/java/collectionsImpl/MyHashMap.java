package collectionsImpl;

import interfaces.CustomMap;

public class MyHashMap<K, V> implements CustomMap<K, V> {

    static final int CAPACITY = 16;
    private int size;
    private int threshold;
    private float loadFactor;

    private Node<K, V>[] table;

    public MyHashMap() {
        table = new Node[16];
        loadFactor = 0.75f;
        threshold = (int) (CAPACITY * loadFactor);
    }

    @Override
    public V put(K key, V value) {
        if (key == null) { // put entry in the table[0] for null key
            return putForNullKey(value);
        } else {
            int i = indexForTable(key.hashCode(), table.length); // calculate index for not null key
            for (Node<K, V> node = table[i]; node != null; node = node.next) {
                if ((node.key == key || key.equals(node.key))) { // rewrite value if key equals to existing key in map
                    V oldValue = node.value;
                    node.value = value;
                    return oldValue;
                }
            }
            addNode(key, value, i); // add new Node to map
        }
        return null;
    }

    private V putForNullKey(V value) {
        for (Node<K, V> e = table[0]; e != null; e = e.next) {
            if (e.key == null) { // override value for null key if entry with key = null exists in map
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }
        addNode(null, value, 0); // add new Node to the first bucket
        return null;
    }


    private void addNode(K key, V value, int bucketIndex) {
        Node<K, V> e = table[bucketIndex];
        table[bucketIndex] = new Node<>(key, value, e);

        if (size++ >= threshold) {
            int increasedCapacity = 2 * table.length;
            increaseMapSize(2 * increasedCapacity);
            threshold = (int) (increasedCapacity * loadFactor);
        }
    }

    void increaseMapSize(int newCapacity) {
        Node<K, V>[] newTable = new Node[newCapacity];

        transfer(newTable); // transfer old table to new table with new capacity
        table = newTable;
    }

    void transfer(Node<K, V>[] newTable) {
        Node<K, V>[] srcTable = table; // table to copy in new one
        for (int j = 0; j < srcTable.length; j++) { // for each bucket of source table
            Node<K, V> e = srcTable[j];

            while (e != null) {
                Node<K, V> next = e.next;
                // calculate new bucket for the increased table
                int i = indexForTable(e.key == null ? 0 : e.key.hashCode(), newTable.length);
                e.next = newTable[i];
                newTable[i] = e;
                e = next;
            }
        }
    }

    static int indexForTable(int keyHash, int capacity) {
        return Math.abs(keyHash % (capacity));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return getNode(key) != null;
    }

    Node<K, V> getNode(Object key) {
        if (key == null) {
            for (Node<K, V> node = table[0]; node != null; node = node.next) {
                if (node.key == null) {
                    return node;
                }
            }
            return null;
        } else {
            int bucketIndex = indexForTable(key.hashCode(), table.length);
            for (Node<K, V> node = table[bucketIndex]; node != null; node = node.next) {
                if (key.equals(node.key)) {
                    return node;
                }
            }
            return null;
        }
    }

    @Override
    public boolean containsValue(Object value) {
        if (value == null) {
            for (int i = 0; i < table.length; i++) { // search through each bucket
                for (Node node = table[i]; node != null; node = node.next) {
                    if (null == node.value) {
                        return true;
                    }
                }
            }
            return false;
        } else {

            for (int i = 0; i < table.length; i++) { // search through each bucket
                for (Node node = table[i]; node != null; node = node.next) {
                    if (value.equals(node.value)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    @Override
    public V get(Object key) {
        int bucketIndex = 0;
        if (key == null) { // search in the first bucket
            for (Node<K, V> node = table[bucketIndex]; node != null; node = node.next) {
                if (node.key == null) {
                    return node.value;
                }
            }
            return null;
        } else {
            bucketIndex = indexForTable(key.hashCode(), table.length);
            for (Node<K, V> node = table[bucketIndex]; node != null; node = node.next) {
                if (key.equals(node.key)) {
                    return node.value;
                }
            }
            return null;
        }
    }

    @Override
    public V remove(Object key) {
        int i = key == null ? 0 : indexForTable(key.hashCode(), table.length);

        Node<K, V> first = table[i];
        Node<K, V> e = first;

        while (e != null) {
            Node<K, V> next = e.next;
            Object k;
            if (((k = e.key) == key || (key != null && key.equals(k)))) {
                size--;
                if (first == e)
                    table[i] = next;
                else
                    first.next = next;

                return e.value;
            }
            first = e;
            e = next;
        }

        return null;
    }


    @Override
    public void clear() {
        for (int i = 0; i < table.length; i++) {
            for (Node node = table[i]; node != null; node = node.next) {
                node.value = null;
            }
            table[i] = null;
        }
        size = 0;
    }

    static class Node<K, V> implements CustomMap.Entry<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

    }
}
