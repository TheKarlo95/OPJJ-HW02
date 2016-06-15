package hr.fer.zemris.java.custom.collections;

/**
 * Resizable-array implementation of the Collection class. Implements all
 * optional array operations, and permits all elements, excluding null.
 * 
 * @author TheKarlo95
 * @version 1.0
 */
public class ArrayIndexedCollection extends Collection {

    /** Number of elements contained in this {@code ArrayIndexedCollection}. */
    private int size;
    /**
     * Maximum number of elements that can be contained in this
     * {@code ArrayIndexedCollection}.
     */
    private int capacity;
    /**
     * Array of all elements contained in this {@code ArrayIndexedCollection}.
     */
    private Object[] elements;

    /**
     * Constructs an empty array with the specified initial capacity.
     * 
     * @param initialCapacity
     *            The initial capacity of the array
     */
    public ArrayIndexedCollection(int initialCapacity) {
        if (initialCapacity < 1)
            throw new IllegalArgumentException();

        this.size = 0;
        this.capacity = initialCapacity;
        this.elements = new Object[this.capacity];
    }

    /**
     * Constructs an empty array with the initial capacity of 16.
     */
    public ArrayIndexedCollection() {
        this(16);
    }

    /**
     * Constructs a list containing the elements of the specified collection and
     * the specified initial capacity.
     * 
     * @param other
     *            The collection whose elements are to be placed into this list
     * @param initialCapacity
     *            The initial capacity of the array
     */
    public ArrayIndexedCollection(Collection other, int initialCapacity) {
        this(initialCapacity);

        if (other == null) {
            throw new NullPointerException();
        }

        this.addAll(other);
    }

    /**
     * Constructs a list containing the elements of the specified collection and
     * the initial capacity of 16 or bigger if size of specified collection is
     * bigger.
     * 
     * @param other
     *            The collection whose elements are to be placed into this list
     */
    public ArrayIndexedCollection(Collection other) {
        this(other, 16);
    }

    /**
     * Returns the number of elements in this array.
     * 
     * @return Current size of the array
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns true if this array contains the specified element.
     * 
     * @param value
     *            Object which you want to find in collection
     * @return True if the object exists in array and false otherwise
     */
    @Override
    public boolean contains(Object value) {
        return indexOf(value) != -1;
    }

    /**
     * Returns an array containing all of the elements in this array in proper
     * sequence (from first to last element).
     * 
     * @return An array containing all of the elements in this array in proper
     *         sequence
     */
    @Override
    public Object[] toArray() {
        Object[] output = new Object[size];
        System.arraycopy(elements, 0, output, 0, size);

        return output;
    }

    /**
     * Appends the specified element to the end of this array.
     * 
     * @param value
     *            Element to be appended to this array.
     */
    @Override
    public void add(Object value) {
        if (value == null) {
            throw new NullPointerException();
        } else if (size == capacity) {
            capacity *= 2;
            Object[] newElements = new Object[capacity];

            System.arraycopy(elements, 0, newElements, 0, size);

            elements = newElements;
        }

        elements[size] = value;
        size++;
    }

    /**
     * Gets the object at specified index.
     * 
     * @param index
     *            Index of the object you want to get.
     * @return Object at the specified index.
     */
    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        return elements[index];
    }

    /**
     * Removes all of the elements from this list. The array will be empty after
     * this call returns.
     */
    @Override
    public void clear() {
        size = 0;
        elements = new Object[capacity];
    }

    /**
     * Inserts the specified element at the specified position in this array.
     * Shifts the element currently at that position (if any) and any subsequent
     * elements to the right (adds one to their indices).
     * 
     * @param value
     *            Element to be inserted
     * @param position
     *            Index at which the specified element is to be inserted
     */
    public void insert(Object value, int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException();
        } else if (size == capacity) {
            capacity *= 2;
            Object[] newElements = new Object[capacity];

            System.arraycopy(elements, 0, newElements, 0, size);

            elements = newElements;
        }

        if (elements[position] != null) {
            System.arraycopy(elements, position, elements, position + 1, size - position);
        }

        elements[position] = value;
        size++;
    }

    /**
     * Returns the index of the first occurrence of the specified element in
     * this array, or -1 if this array does not contain the element.
     * 
     * @param value
     *            Element to search for
     * @return The index of the first occurrence of the specified element in
     *         this array, or -1 if this array does not contain the element
     */
    public int indexOf(Object value) {
        if (value == null) {
            throw new NullPointerException();
        } else if (size == 0) {
            return -1;
        }

        for (int i = 0; i < size; i++) {
            if (elements[i].equals(value)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Removes the element at the specified position in this array. Shifts any
     * subsequent elements to the left (subtracts one from their indices).
     * 
     * @param index
     *            The index of the element to be removed
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        System.arraycopy(elements, index + 1, elements, index, size - index);
        size--;
    }
}
