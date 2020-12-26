package server.exception;

/**
 * Thrown when can't find matching split
 *
 * @author TJanssen
 */
public class SplitNotFoundException extends Exception{

    public SplitNotFoundException(String message) {
        super(message);
    }
}