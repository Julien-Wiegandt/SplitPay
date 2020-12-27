package server.exception;

/**
 * Thrown couldn't retrieve a matching participant
 *
 */
public class ParticipantNotFoundException extends Exception{

    public ParticipantNotFoundException(String message) {
        super(message);
    }
}
