package server.exception.splitException;

/**
 * Thrown when can't find matching item
 *
 */
public class UnknownItemException extends Exception{

    public UnknownItemException(String message) {
        super(message);
    }
}