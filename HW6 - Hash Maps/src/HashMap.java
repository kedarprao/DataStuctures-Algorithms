import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

/**
 * Your implementation of HashMap.
 * 
 * @author Kedar Rao
 * @userid krao9
 * @GTID 902842074
 * @version 1.0
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */
    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        table = new MapEntry[initialCapacity];
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Neither the key nor the value "
                    + "can be null.");
        }

        double loadFactor = (size + 1.0) / table.length;

        if (loadFactor > MAX_LOAD_FACTOR) {
            resizeBackingTable(2 * table.length + 1);
        }
        MapEntry<K, V> entry = new MapEntry<>(key, value);
        int index = Math.abs(key.hashCode() % table.length);
        MapEntry<K, V> currentEntry = table[index];
        V oldValue;
        while (currentEntry != null) {
            if (currentEntry.getKey().equals(key)) {
                oldValue = currentEntry.getValue();
                currentEntry.setValue(value);
                return oldValue;
            }
            currentEntry = currentEntry.getNext();
        }
        entry.setNext(table[index]);
        table[index] = entry;
        size++;
        return null;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The key cannot be null!");
        }
        int index = Math.abs(key.hashCode() % table.length);
        MapEntry<K, V> currentEntry = table[index];
        V oldValue;
        if (currentEntry != null && currentEntry.getKey().equals(key)) {
            oldValue = currentEntry.getValue();
            table[index] = currentEntry.getNext();
            size--;
            return oldValue;
        } else if (currentEntry != null) {
            while (currentEntry.getNext() != null) {
                if (currentEntry.getNext().getKey().equals(key)) {
                    oldValue = currentEntry.getNext().getValue();
                    currentEntry.setNext(currentEntry.getNext().getNext());
                    size--;
                    return oldValue;
                }
                currentEntry = currentEntry.getNext();
            }
        }
        throw new java.util.NoSuchElementException("This key does "
                + "not exist in the hashmap");
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The key cannot be null!");
        }
        int index = Math.abs(key.hashCode() % table.length);
        MapEntry<K, V> currentEntry = table[index];
        while (currentEntry != null) {
            if (currentEntry.getKey().equals(key)) {
                return currentEntry.getValue();
            }
            currentEntry = currentEntry.getNext();
        }
        throw new java.util.NoSuchElementException("This key does "
                + "not exist in the hashmap");
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The key cannot be null!");
        }
        int index = Math.abs(key.hashCode() % table.length);
        MapEntry<K, V> currentEntry = table[index];
        while (currentEntry != null) {
            if (currentEntry.getKey().equals(key)) {
                return true;
            }
            currentEntry = currentEntry.getNext();
        }
        return false;
    }

    @Override
    public void clear() {
        table = new MapEntry[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>(size);
        for (int i = 0; i < table.length; i++) {
            MapEntry<K, V> currentEntry = table[i];
            while (currentEntry != null) {
                keys.add(currentEntry.getKey());
                currentEntry = currentEntry.getNext();
            }
        }
        return keys;
     }

    @Override
    public List<V> values() {
        List<V> values = new ArrayList<>(size);
        for (int i = 0; i < table.length; i++) {
            MapEntry<K, V> currentEntry = table[i];
            while (currentEntry != null) {
                values.add(currentEntry.getValue());
                currentEntry = currentEntry.getNext();
            }
        }
        return values;
    }

    @Override
    public void resizeBackingTable(int length) {
        if (length <= 0 || length < size) {
            throw new IllegalArgumentException(
                    "Resize length must be positive number, greater than the "
                        + "number of existing elements");
        }
        MapEntry<K, V>[] tempTable = new MapEntry[length];
        for (MapEntry<K, V> entry : table) {
            MapEntry<K, V> currentEntry = entry;
            while (currentEntry != null) {
                K key = currentEntry.getKey();
                V value = currentEntry.getValue();
                MapEntry<K, V> collisionEntry = new MapEntry<>(key, value);
                collisionEntry.setNext(tempTable[Math.abs(key.hashCode()
                        % tempTable.length)]);
                tempTable[Math.abs(key.hashCode() % tempTable.length)]
                        = collisionEntry;
                currentEntry = currentEntry.getNext();
            }
        }
        table = tempTable;
    }

    @Override
    public MapEntry<K, V>[] getTable() {
        // DO NOT EDIT THIS METHOD!
        return table;
    }

}
