package server.exception.splitException;

/**
 * Thrown if the goal amount of the split is about to be exceeded
 *
 */
public class GoalAmountExceededException extends Exception{

    public GoalAmountExceededException(String message) {
        super(message);
    }

}
