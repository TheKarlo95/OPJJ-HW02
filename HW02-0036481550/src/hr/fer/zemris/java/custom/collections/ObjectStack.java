package hr.fer.zemris.java.custom.collections;

/**
 * The ObjectStack class represents a last-in-first-out (LIFO) stack of objects.
 * The usual push and pop operations are provided, as well as a method to peek
 * at the top item on the stack and a method to test for whether the stack is
 * empty.
 * 
 * @author TheKarlo95
 * @version 1.0
 */
public class ObjectStack {
    
    /** Array used to store elements of the stack. */
    private ArrayIndexedCollection storage;

    /**
     * Creates an empty stack with the initial capacity of 16.
     */
    public ObjectStack() {
        storage = new ArrayIndexedCollection();
    }

    /**
     * Creates an empty stack with the initial specified capacity.
     * 
     * @param initialCapacity
     *            The initial capacity of the array
     */
    public ObjectStack(int initialCapacity) {
        storage = new ArrayIndexedCollection(initialCapacity);
    }

    /**
     * Tests if this stack is empty.
     * 
     * @return True if and only if this stack contains no items; false
     *         otherwise.
     */
    public boolean isEmpty() {
        return storage.isEmpty();
    }

    /**
     * Returns the number of elements in this stack.
     * 
     * @return The number of elements in this stack
     */
    public int size() {
        return storage.size();
    }

    /**
     * Pushes an item onto the top of this stack.
     * 
     * @param value
     *            Object to be pushed onto this stack.
     */
    public void push(Object value) {
        storage.add(value);
    }

    /**
     * Looks at the object at the top of this stack without removing it from the
     * stack.
     * 
     * @return The object at the top of this stack
     */
    public Object peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return storage.get(storage.size() - 1);
    }

    /**
     * Removes the object at the top of this stack and returns that object as
     * the value of this function.
     * 
     * @return The object at the top of this stack
     */
    public Object pop() {
        Object output = peek();
        storage.remove(storage.size() - 1);

        return output;
    }

    /**
     * Removes all of the elements from this stack. The stack will be empty
     * after this call returns.
     */
    public void clear() {
        storage.clear();
    }

    /**
     * {inheritDoc]
     */
    @Override
    public String toString() {
        return storage.toString();
    }
}
