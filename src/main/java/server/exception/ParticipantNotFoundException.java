package server.exception;

/**
 * The MyBusinessException wraps all checked standard Java exception and enriches them with a custom error code.
 * You can use this code to retrieve localized error messages and to link to our online documentation.
 *
 * @author TJanssen
 */
public class ParticipantNotFoundException extends Exception{

    public ParticipantNotFoundException(String message) {
        super(message);
    }
}
