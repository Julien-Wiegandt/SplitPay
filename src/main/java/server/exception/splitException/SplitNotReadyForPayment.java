package server.exception.splitException;

/**
 * Thrown when split not ready for payment
 *
 */
public class SplitNotReadyForPayment extends Exception{

    public SplitNotReadyForPayment(String message) {
        super(message);
    }
}