package id.co.faspay.snap.logging;

/**
 * Simple logger interface that provides basic logging functionality.
 * This is a replacement for org.slf4j.Logger to remove external dependencies.
 */
public interface Logger {
    /**
     * Log a message at the DEBUG level.
     *
     * @param message the message to log
     */
    void debug(String message);

    /**
     * Log a message with parameters at the DEBUG level.
     *
     * @param format the message format with {} placeholders
     * @param args the arguments to replace the placeholders
     */
    void debug(String format, Object... args);

    /**
     * Log a message at the INFO level.
     *
     * @param message the message to log
     */
    void info(String message);

    /**
     * Log a message with parameters at the INFO level.
     *
     * @param format the message format with {} placeholders
     * @param args the arguments to replace the placeholders
     */
    void info(String format, Object... args);

    /**
     * Log a message at the WARN level.
     *
     * @param message the message to log
     */
    void warn(String message);

    /**
     * Log a message with parameters at the WARN level.
     *
     * @param format the message format with {} placeholders
     * @param args the arguments to replace the placeholders
     */
    void warn(String format, Object... args);

    /**
     * Log a message at the ERROR level.
     *
     * @param message the message to log
     */
    void error(String message);

    /**
     * Log a message with parameters at the ERROR level.
     *
     * @param format the message format with {} placeholders
     * @param args the arguments to replace the placeholders
     */
    void error(String format, Object... args);

    /**
     * Log a message with an exception at the ERROR level.
     *
     * @param message the message to log
     * @param throwable the exception to log
     */
    void error(String message, Throwable throwable);
}