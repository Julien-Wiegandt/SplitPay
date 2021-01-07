package server.exception.splitException;

/**
 * Thrown when an item is already picked in a split
 *
 */
public class ItemAlreadyPickedException extends Exception{

    public ItemAlreadyPickedException(String message) {
        super(message);
    }
}