package id.co.faspay.snap.logging;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Factory for creating Logger instances.
 * This is a replacement for org.slf4j.LoggerFactory to remove external dependencies.
 */
public class LoggerFactory {
    private static final ConcurrentMap<String, Logger> loggers = new ConcurrentHashMap<>();
    
    /**
     * Private constructor to prevent instantiation.
     */
    private LoggerFactory() {
        // Prevent instantiation
    }
    
    /**
     * Get a logger for the given class.
     *
     * @param clazz the class to get a logger for
     * @return a logger for the class
     */
    public static Logger getLogger(Class<?> clazz) {
        return getLogger(clazz.getName());
    }
    
    /**
     * Get a logger with the given name.
     *
     * @param name the name of the logger
     * @return a logger with the given name
     */
    public static Logger getLogger(String name) {
        return loggers.computeIfAbsent(name, SimpleLogger::new);
    }
    
    /**
     * Set the global log level for all loggers.
     *
     * @param level the log level to set
     */
    public static void setLevel(SimpleLogger.Level level) {
        SimpleLogger.setLevel(level);
    }
}