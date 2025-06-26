package id.co.faspay.snap.logging;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple implementation of the Logger interface that logs messages to the console.
 * This is a replacement for org.slf4j.Logger implementations to remove external dependencies.
 */
public class SimpleLogger implements Logger {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    
    // Log levels
    public enum Level {
        DEBUG, INFO, WARN, ERROR
    }
    
    // Default log level
    private static Level currentLevel = Level.INFO;
    
    private final String name;
    
    /**
     * Creates a new SimpleLogger with the given name.
     *
     * @param name the name of the logger, typically the class name
     */
    public SimpleLogger(String name) {
        this.name = name;
    }
    
    /**
     * Set the global log level.
     *
     * @param level the log level to set
     */
    public static void setLevel(Level level) {
        currentLevel = level;
    }
    
    /**
     * Format a log message with the given level.
     *
     * @param level the log level
     * @param message the message to log
     * @return the formatted log message
     */
    private String formatMessage(Level level, String message) {
        return String.format("%s [%s] %s - %s", 
                DATE_FORMAT.format(new Date()), 
                level.toString(), 
                name, 
                message);
    }
    
    /**
     * Replace placeholders in the format string with the given arguments.
     *
     * @param format the format string with {} placeholders
     * @param args the arguments to replace the placeholders
     * @return the formatted string
     */
    private String formatWithArgs(String format, Object... args) {
        if (args == null || args.length == 0) {
            return format;
        }
        
        StringBuilder result = new StringBuilder();
        int argIndex = 0;
        int i = 0;
        
        while (i < format.length()) {
            int j = format.indexOf("{}", i);
            if (j == -1) {
                result.append(format.substring(i));
                break;
            }
            
            result.append(format, i, j);
            if (argIndex < args.length) {
                result.append(args[argIndex++]);
            } else {
                result.append("{}");
            }
            
            i = j + 2;
        }
        
        return result.toString();
    }
    
    /**
     * Log a message to the console.
     *
     * @param level the log level
     * @param message the message to log
     */
    private void log(Level level, String message) {
        if (level.ordinal() < currentLevel.ordinal()) {
            return;
        }
        
        PrintStream stream = (level == Level.ERROR) ? System.err : System.out;
        stream.println(formatMessage(level, message));
    }
    
    /**
     * Log a message with parameters to the console.
     *
     * @param level the log level
     * @param format the message format with {} placeholders
     * @param args the arguments to replace the placeholders
     */
    private void log(Level level, String format, Object... args) {
        if (level.ordinal() < currentLevel.ordinal()) {
            return;
        }
        
        log(level, formatWithArgs(format, args));
    }
    
    @Override
    public void debug(String message) {
        log(Level.DEBUG, message);
    }
    
    @Override
    public void debug(String format, Object... args) {
        log(Level.DEBUG, format, args);
    }
    
    @Override
    public void info(String message) {
        log(Level.INFO, message);
    }
    
    @Override
    public void info(String format, Object... args) {
        log(Level.INFO, format, args);
    }
    
    @Override
    public void warn(String message) {
        log(Level.WARN, message);
    }
    
    @Override
    public void warn(String format, Object... args) {
        log(Level.WARN, format, args);
    }
    
    @Override
    public void error(String message) {
        log(Level.ERROR, message);
    }
    
    @Override
    public void error(String format, Object... args) {
        log(Level.ERROR, format, args);
    }
    
    @Override
    public void error(String message, Throwable throwable) {
        log(Level.ERROR, message);
        throwable.printStackTrace(System.err);
    }
}