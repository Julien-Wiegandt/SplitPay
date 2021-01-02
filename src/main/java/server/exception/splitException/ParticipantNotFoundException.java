package server.exception.splitException;

/**
 * Thrown couldn't retrieve a matching participant
 *
 */
public class ParticipantNotFoundException extends Exception{

    public ParticipantNotFoundException(String message) {
        super(message);
    }
}
