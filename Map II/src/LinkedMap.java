import java.util.Scanner;

/**
 * @author Christian Baduria
 * @version 1.0
 * @param <K> Key data type
 * @param <V> Value data type
 */
public class LinkedMap<K, V> implements Map<K, V> {

    private LinkedList<Entry<K, V>> entries;

    /**
     * Constructor for LinkedMap object.
     */
    public LinkedMap() {
        entries = new LinkedList<>();
    }

    /**
     * Returns map, the list of key-value pairs.
     *
     * @return [LinkedList[Entry[K, V]]] List of key-value pairs
     */
    public LinkedList<Entry<K, V>> getList() {
        return entries;
    }

    /**
     * Removes all of the mappings from this map.
     * The map will be empty after this call returns.
     */
    @Override
    public void clear() {
        entries.clear();
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     *
     * @param key key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key
     */
    @Override
    public boolean containsKey(K key) {
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or null if this map contains no mapping for the key.
     * If this map permits null values, then a return value of null does not necessarily indicate
     * that the map contains no mapping for the key;
     * it's also possible that the map explicitly maps the key to null.
     * The Map#containsKey operation may be used to distinguish these two cases.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped,
     * or null if this map contains no mapping for the key
     */
    @Override
    public V get(K key) {
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getKey().equals(key)) {
                return entries.get(i).getValue();
            }
        }
        return null;
    }



    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced by the specified value.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with key, or null if there was
     * no mapping for key.
     * (A null return can also indicate that the map previously associated
     * null with key, if the implementation supports null values.)
     */
    @Override
    public V put(K key, V value) {
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getKey().equals(key)) {
                V previousValue = entries.get(i).getValue();
                entries.get(i).setValue(value);
                return previousValue;
            }
        }
        entries.add(new Entry<>(key, value));
        return null;
    }

    /**
     * If the specified key is not already associated with a value
     * (or is mapped to null) associates it with the given value and
     * returns null, else returns the current value.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with key, or null if there
     * was no mapping for key.
     * (A null return can also indicate that the map previously associated
     * null with key,
     * if the implementation supports null values.)
     */
    @Override
    public V putIfAbsent(K key, V value) {
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getKey().equals(key)) {
                if (entries.get(i).getValue() == null) {
                    entries.get(i).setValue(value);
                    return null;
                } else {
                    return entries.get(i).getValue();
                }
            }
        }
        entries.add(new Entry<>(key, value));
        return null;
    }

    /**
     * Returns true if this map contains no key-value mappings.
     *
     * @return true if this map contains no key-value mappings
     */
    @Override
    public boolean isEmpty() {
        return entries.size() == 0;
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     * Returns the value to which this map previously associated the key,
     * or null if the map contained no mapping for the key.
     * If this map permits null values, then a return value of null does not necessarily indicate
     * that the map contained no mapping for the key;
     * it's also possible that the map explicitly mapped the key to null.
     * The map will not contain a mapping for the specified key once the call returns.
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with key, or null if there was no mapping for key.
     */
    @Override
    public V remove(K key) {
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getKey().equals(key)) {
                return entries.remove(i).getValue();
            }
        }
        return null;
    }

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map
     */
    @Override
    public int size() {
        return entries.size();
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("[");
        if (entries.size() != 0) {
            string.append(entries.get(0).toString());
        }
        for (int i = 1; i < entries.size(); i++) {
            string.append(", ").append(entries.get(i).toString());
        }
        string.append("]");
        return string.toString();
    }

    /**
     * @param <K> Key data type
     * @param <V> Value data type
     */
    public class Entry<K, V> implements Map.Entry<K, V> {

        private K key;
        private V value;

        /**
         * Constructor for Entry object.
         *
         * @param key   [K] Unique key
         * @param value [V] Value associated with key
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Returns the key corresponding to this entry.
         *
         * @return the key corresponding to this entry
         */
        @Override
        public K getKey() {
            return key;
        }

        /**
         * Returns the value corresponding to this entry.
         *
         * @return the value corresponding to this entry
         */
        @Override
        public V getValue() {
            return value;
        }

        /**
         * Sets the value corresponding to this entry.
         *
         * @param value [V] New value
         */
        @Override
        public void setValue(V value) {
            this.value = value;
        }

        /**
         * Returns entry containing key-value pair.
         *
         * @return [String] String representing key-value pair
         */
        @Override
        public String toString() {
            return "(" + key + ", " + value + ")";
        }

        /**
         * Determines equality between this object and another object.
         *
         * @param o [Object] Object to be compared
         * @return [boolean] Whether or not the object is equal to this object
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (getClass() == o.getClass()) {
                Entry<?, ?> entry = (Entry<?, ?>) o;
                return key.equals(entry.getKey()) && value.equals(entry.getValue());
            } else {
                return o.equals(key);
            }
        }
    }

    /**
     * Main function that controls/tests LinkedMap class.
     *
     * @param args [String[]] args parameter of main function
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LinkedMap<String, Integer> entries = new LinkedMap<>();
        boolean exit = false;
        do {
            String key;
            int value;
            String action = scan.next();
            switch (action) {
                case "p":
                    key = scan.next();
                    value = scan.nextInt();
                    entries.put(key, value);
                    System.out.println(entries.toString());
                    break;
                case "P":
                    key = scan.next();
                    value = scan.nextInt();
                    entries.putIfAbsent(key, value);
                    System.out.println(entries.toString());
                    break;
                case "c":
                    key = scan.next();
                    System.out.println(entries.containsKey(key));
                    break;
                case "C":
                    entries.clear();
                    System.out.println(entries.toString());
                    break;
                case "g":
                    key = scan.next();
                    System.out.println(entries.get(key));
                    break;
                case "s":
                    System.out.println(entries.size());
                    break;
                case "e":
                    System.out.println(entries.isEmpty());
                    break;
                case "r":
                    key = scan.next();
                    entries.remove(key);
                    System.out.println(entries.toString());
                    break;
                case "x":
                    exit = true;
                    break;
            }
            if (!exit) {
                System.out.println("------------------");
            }
        } while (!exit);
    }
}
