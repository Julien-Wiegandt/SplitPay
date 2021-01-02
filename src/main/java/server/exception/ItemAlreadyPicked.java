package server.exception;

/**
 * Thrown when an item is already picked in a split
 *
 */
public class ItemAlreadyPicked extends Exception{

    public ItemAlreadyPicked(String message) {
        super(message);
    }
}