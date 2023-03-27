package utils.exceptions;

public class ActionsException extends Exception {
    public ActionsException(String message) {
        super(message);
    }
    public ActionsException(String message, Throwable e) {
        super(message, e);
    }
}
