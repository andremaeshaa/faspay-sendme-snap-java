package id.co.faspay.snap.exception;

/**
 * Exception thrown when an error occurs while interacting with the Faspay SendMe Snap API.
 */
public class FaspaySnapApiException extends Exception {
    
    /**
     * Creates a new exception with the specified message.
     *
     * @param message The error message
     */
    public FaspaySnapApiException(String message) {
        super(message);
    }
    
    /**
     * Creates a new exception with the specified message and cause.
     *
     * @param message The error message
     * @param cause The cause of the exception
     */
    public FaspaySnapApiException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Creates a new exception with the specified cause.
     *
     * @param cause The cause of the exception
     */
    public FaspaySnapApiException(Throwable cause) {
        super(cause);
    }
}