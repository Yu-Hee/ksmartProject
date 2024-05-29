package ksmart.tourproject.exception;

public class EDException extends Exception {
    private static final long serialVersionUID = 1L;

    public EDException(String message) {
        super(message);
    }

    public EDException(String message, Throwable cause) {
        super(message, cause);
    }
}
