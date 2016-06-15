package hr.fer.zemris.java.custom.collections;

/**
 * Thrown by methods in the ObjectStack class to indicate that the stack is
 * empty.
 * 
 * @author TheKarlo95
 * @version 1.0
 */
@SuppressWarnings("serial")
public class EmptyStackException extends RuntimeException {

    /**
     * Constructs a new EmptyStackException with null as its error message
     * string.
     */
    public EmptyStackException() {
        super();
    }
}
