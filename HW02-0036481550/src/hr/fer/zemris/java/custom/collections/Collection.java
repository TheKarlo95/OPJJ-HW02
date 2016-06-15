package hr.fer.zemris.java.custom.collections;

/**
 * Collection class is a custom implementation of default Java Collections.
 * 
 * @author TheKarlo95
 * @version 1.0
 */
public class Collection {

    /**
     * Default constructor. Does nothing.
     */
    Collection() {

    }

    /**
     * Checks if collection is empty.
     * 
     * @return True if empty and false otherwise.
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Checks the size of a collection(needs implementation).
     * 
     * @return 0 for every case.
     */
    public int size() {
        return 0;
    }

    /**
     * Adds an object to collection(needs implementation).
     * 
     * @param value
     *            Object you want to add to collection.
     */
    public void add(Object value) {

    }

    /**
     * Checks if collection contains specified object(needs implementation).
     * 
     * @param value
     *            Object which you want to find in collection
     * @return False for every input.
     */
    public boolean contains(Object value) {
        return false;
    }

    /**
     * Removes an object from collection(needs implementation).
     * 
     * @param value
     *            Object you want to remove from collection.
     * @return False for every input.
     */
    public boolean remove(Object value) {
        return false;
    }

    /**
     * Converts collection to array(needs implementation).
     * 
     * @return Nothing
     */
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    /**
     * Iterates the collection and process every object.
     * 
     * @param processor
     *            Operation which you want to do on objects in collection.
     */
    public void forEach(Processor processor) {
        for (Object obj : this.toArray())
            processor.process(obj);
    }

    /**
     * Adds all elements from different collection to this collection.
     * 
     * @param other
     *            Source collection.
     */
    public void addAll(Collection other) {
        for (Object obj : other.toArray())
            this.add(obj);
    }

    /**
     * Removes all of the elements from this list. The list will be empty after
     * this call returns(needs implementation).
     */
    public void clear() {

    }
}
