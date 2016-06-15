package hr.fer.zemris.java.custom.collections;

/**
 * Doubly-linked list implementation of the Collection class. Implements all
 * optional list operations, and permits all elements, excluding null.
 * 
 * @author TheKarlo95
 * @version 1.0
 */
public class LinkedListIndexedCollection extends Collection {

    /**
     * Class used as node in LinkedListIndexedCollection.
     * 
     * @author TheKarlo95
     * @version 1.0
     */
    private static class ListNode {
        /** Previous node of a list. */
        ListNode previous;
        /** Next node of a list. */
        ListNode next;
        /** Value contained in this node. */
        Object value;

        /**
         * Constructs a node with specified data and references to previous and
         * next node.
         * 
         * @param value
         *            Data to be written in node
         * @param previous
         *            Reference to previous node
         * @param next
         *            Reference to next node
         */
        public ListNode(Object value, ListNode previous, ListNode next) {
            this.previous = previous;
            this.next = next;
            this.value = value;
        }
    }

    /** Current size of this list. */
    private int size;
    /** First node of this list. */
    private ListNode first;
    /** Last node of this list. */
    private ListNode last;

    /**
     * Constructs an empty list.
     */
    public LinkedListIndexedCollection() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }

    /**
     * Constructs a list containing the elements of the specified collection, in
     * the original order.
     * 
     * @param other
     *            The collection whose elements are to be placed into this list
     */
    public LinkedListIndexedCollection(Collection other) {
        this();

        this.addAll(other);
    }

    /**
     * Returns the number of elements in this list.
     * 
     * @return The number of elements in this list
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns true if this list contains the specified element.
     * 
     * @param value
     *            Element whose presence in this list is to be tested
     * @return True if this list contains the specified element
     */
    @Override
    public boolean contains(Object value) {
        return indexOf(value) != -1 ? true : false;
    }

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element).
     * 
     * @return An array containing all of the elements in this list in proper
     *         sequence
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int currentIndex = 0;
        for (ListNode node = first; node != null; node = node.next) {
            array[currentIndex] = node.value;
            currentIndex++;
        }

        return array;
    }

    /**
     * Appends the specified element to the end of this list.
     * 
     * @param value
     *            Element to be appended to this list.
     */
    @Override
    public void add(Object value) {
        if (value == null) {
            throw new NullPointerException();
        }

        if (first == null) {
            first = new ListNode(value, null, null);
            last = first;
            size++;
        } else {
            ListNode newNode = new ListNode(value, last, null);

            last.next = newNode;
            last = newNode;
            size++;
        }
    }

    /**
     * Gets the object at specified index.
     * 
     * @param index
     *            Index of the object you want to get.
     * @return Object at the specified index.
     */
    public Object get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (index <= (size - 1) / 2) {
            int currentIndex = 0;
            for (ListNode node = first; node != null; node = node.next) {
                if (index == currentIndex) {
                    return node.value;
                }
                currentIndex++;
            }
        } else {
            int currentIndex = size - 1;

            for (ListNode node = last; node != null; node = node.previous) {
                if (index == currentIndex) {
                    return node.value;
                }
                currentIndex--;
            }
        }

        return null;
    }

    /**
     * Removes all of the elements from this list. The list will be empty after
     * this call returns.
     */
    @Override
    public void clear() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any subsequent
     * elements to the right (adds one to their indices).
     * 
     * @param value
     *            Element to be inserted
     * @param position
     *            Index at which the specified element is to be inserted
     */
    public void insert(Object value, int position) {
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException();
        } else if (value == null) {
            throw new NullPointerException();
        }

        if (position == 0) {
            ListNode newNode = new ListNode(value, null, first);

            first.previous = newNode;
            first = newNode;
            size++;
        } else if (position == size) {
            ListNode newNode = new ListNode(value, last, null);

            last.next = newNode;
            last = newNode;
            size++;
        } else if (position <= (size - 1) / 2) {
            int currentIndex = 0;

            for (ListNode node = first; node != null; node = node.next) {
                if (position == currentIndex) {
                    ListNode newNode = new ListNode(value, node.previous, node);

                    node.previous.next = newNode;
                    node.previous = newNode;
                    size++;
                    return;
                }
                currentIndex++;
            }
        } else {
            int currentIndex = size - 1;

            for (ListNode node = last; node != null; node = node.previous) {
                if (position == currentIndex) {
                    ListNode newNode = new ListNode(value, node.previous, node);

                    node.previous.next = newNode;
                    node.previous = newNode;
                    size++;
                    return;
                }
                currentIndex--;
            }
        }
    }

    /**
     * Returns the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element.
     * 
     * @param value
     *            Element to search for
     * @return The index of the first occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
     */
    public int indexOf(Object value) {
        if (value == null) {
            throw new NullPointerException();
        }

        int currentIndex = 0;
        for (ListNode node = first; node != null; node = node.next) {
            if (value.equals(node.value)) {
                return currentIndex;
            }

            currentIndex++;
        }

        return -1;
    }

    /**
     * Removes the element at the specified position in this list. Shifts any
     * subsequent elements to the left (subtracts one from their indices).
     * 
     * @param index
     *            The index of the element to be removed
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            first = first.next;
            first.previous = null;

            size--;
        } else if (index == size - 1) {
            last = last.previous;
            last.next = null;

            size--;
        } else if (index <= (size - 1) / 2) {
            int currentIndex = 0;

            for (ListNode node = first; node != null; node = node.next) {
                if (index == currentIndex) {
                    node.previous.next = node.next;
                    node.next.previous = node.previous;

                    size--;
                    return;
                }
                currentIndex++;
            }
        } else {
            int currentIndex = size - 1;

            for (ListNode node = last; node != null; node = node.previous) {
                if (index == currentIndex) {
                    node.previous.next = node.next;
                    node.next.previous = node.previous;

                    size--;
                    return;
                }
                currentIndex--;
            }
        }
    }
}
