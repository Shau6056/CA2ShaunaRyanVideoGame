package controllers;

// Creating the class for the HashTables
public class MyHashTable<K, V> {
    private MyLinkedList<Entry<K, V>>[] table; // Here we are using the user defined Linked list, storing the K for key and V for value into
    // entry meaning the key pairs are stored that. Then we are calling it table and it is an array.
    private int size;

    public MyHashTable(int cap) { // Constructor giving a capacity
        // The objective of this is to instantiate the hashtable and the linked lists will store at indexes to handle collisions
        table = new MyLinkedList[cap]; // Table to hold an array to hold a max no of linked lists
        for (int i = 0; i < cap; i++) { // Loop through until the cap is met - 1
            table[i] = new MyLinkedList<>(); // And make a new linked list of this until you have reached the cap
        }
        size = 0; // And the size is 0 still.
    }

    public int getCapacity() {
        return table.length; // Return the length of the table
    }

    public static class Entry<K, V> { // Class in a class stored in entry K, V
        private K key; // Storing the key for the value/object
        private V value;

        public Entry(K key, V value) { // What is sent in is now stored in the above
            this.key = key;
            this.value = value;
        }

        public K getKey() { // Returning the key
            return key;
        }

        public V getValue() { // Returning the value
            return value;
        }

        @Override
        public String toString() { // Just simply toString of the key and value
            return key + "=" + value;
        }
    }

    private int hashFunction(int key) { // THIS WAS CAUSING A HUGE ISSUE, IT WOULDNT WORK AS KEPT GETTING OUT OF BOUNDS
        return Math.abs(key) % table.length; // Anyway it was solved by using this function gives the ABSOLUTE value fo the key
        // Meaning it will always return a positive number. Then the % modulo with calculate the remainder of the division key by the table length
        // This will give us the operation that will always in the proper range we need maintaining the functionality of the code
    }

    public void put(K key, V value) {
        int index = hashFunction(key.hashCode()); // Using the above hashFunction to get the index
       // System.out.println("Adding to hash table at index: " + index + " with key: " + key); // Debug statement
        Entry<K, V> newEntry = new Entry<>(key, value); // Then making a new pair
        table[index].add(newEntry); // And adding it at the index with the data
        size++; // And making the size bigger
      //  System.out.println("Entry added to hash table: " + key); // Debug statement
    }

    public V get(K key) {
        int index = hashFunction(key.hashCode());
        MyLinkedList<Entry<K, V>> bucket = table[index];
        if (bucket != null) {
            Node<Entry<K, V>> current = bucket.getHead();
            while (current != null) {
                if (current.getData().getKey().equals(key)) {
                    System.out.println("Entry found for key: " + key); // Debug statement
                    return current.getData().getValue();
                }
                current = current.getNext();
            }
        }
        return null;
    }

    public MyLinkedList<Entry<K, V>> getBucket(int index) { // This will get the area where all the data is stored
        if (index >= 0 && index < table.length) { // Return the list at the index sent in
            return table[index];
        }
        return null;
    }

    public boolean remove(K key) {
        int index = hashFunction(key.hashCode()); // We have our index and its set to the key.hashcode method
        MyLinkedList<Entry<K, V>> bucket = table[index]; // We find it
        boolean removed = bucket.remove(new Entry<>(key, null)); // Returned true if removed
        if (removed) size--; // Decrease size if removed
        return removed;
    }

    public int size() {
        return size;
    }
}
