package server.exception;

/**
 * Thrown when a user is already a participant
 *
 */
public class ParticipantAlreadyInException extends Exception{

    public ParticipantAlreadyInException(String message) {
        super(message);
    }
}