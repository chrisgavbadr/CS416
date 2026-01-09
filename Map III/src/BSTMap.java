
/**
 * @author Christian Baduria
 * @version 1.0
 * @param <K> Key data type
 * @param <V> Value data type
 */
public class BSTMap<K extends Comparable, V> implements Map<K, V> {

    private BST<Entry<K, V>> entries;

    /**
     * Constructor for creating BSTMap object.
     */
    public BSTMap() {
        entries = new BST<>();
    }

    /**
     * Returns entries binary search tree.
     *
     * @return [BST[Entry[K, V]] Entries binary search tree
     */
    public BST<Entry<K, V>> getTree() {
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
        return entries.contains(key);
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
        if (entries.contains(key)) {
            return entries.get(key).getValue();
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
        if (entries.contains(key)) {
            V oldValue = entries.get(key).getValue();
            entries.remove(key);
            entries.add(new Entry<>(key, value));
            return oldValue;
        } else {
            entries.add(new Entry<>(key, value));
            return null;
        }
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
        if (!entries.contains(key) || entries.get(key).getValue() == null) {
            entries.add(new Entry<>(key, value));
            return null;
        }
        return entries.get(key).getValue();
    }

    /**
     * Returns true if this map contains no key-value mappings.
     *
     * @return true if this map contains no key-value mappings
     */
    @Override
    public boolean isEmpty() {
        return entries.isEmpty();
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
        if (entries.contains(key)) {
            V previousValue = entries.get(key).getValue();
            entries.remove(key);
            return previousValue;
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

    /**
     * Returns entries as string.
     *
     * @return [String] Entries as string
     */
    @Override
    public String toString() {
        return entries.toString();
    }

    /**
     * @param <K> Key data type
     * @param <V> Value data type
     */
    public class Entry<K extends Comparable, V> implements Map.Entry, Comparable {

        K key;
        V value;


        /**
         * Constructor for creating Entry object.
         *
         * @param key [K] Key
         * @param value [V] Value
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
        public void setValue(Object value) {
            this.value = (V) value;
        }

        /**
         * Determines whether this object and other object are equal.
         *
         * @param o [Object] Object to compare with
         * @return [boolean] Whether this object and other object are equal
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

        /**
         * Compares this object with the specified object for order.  Returns a
         * negative integer, zero, or a positive integer as this object is less
         * than, equal to, or greater than the specified object.
         *
         * <p>The implementor must ensure
         * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
         * for all {@code x} and {@code y}.  (This
         * implies that {@code x.compareTo(y)} must throw an exception iff
         * {@code y.compareTo(x)} throws an exception.)
         *
         * <p>The implementor must also ensure that the relation is transitive:
         * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
         * {@code x.compareTo(z) > 0}.
         *
         * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
         * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
         * all {@code z}.
         *
         * <p>It is strongly recommended, but <i>not</i> strictly required that
         * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
         * class that implements the {@code Comparable} interface and violates
         * this condition should clearly indicate this fact.  The recommended
         * language is "Note: this class has a natural ordering that is
         * inconsistent with equals."
         *
         * <p>In the foregoing description, the notation
         * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
         * <i>signum</i> function, which is defined to return one of {@code -1},
         * {@code 0}, or {@code 1} according to whether the value of
         * <i>expression</i> is negative, zero, or positive, respectively.
         *
         * @param o the object to be compared.
         * @return a negative integer, zero, or a positive integer as this object
         * is less than, equal to, or greater than the specified object.
         * @throws NullPointerException if the specified object is null
         * @throws ClassCastException   if the specified object's type prevents it
         *                              from being compared to this object.
         */
        @Override
        @SuppressWarnings("unchecked")
        public int compareTo(Object o) {
            if (o.getClass() == this.getClass()) {
                Entry<K, V> other = (Entry<K, V>) o;
                return this.key.compareTo(other.key);
            }
            return this.key.compareTo(o);
        }
    }
}
