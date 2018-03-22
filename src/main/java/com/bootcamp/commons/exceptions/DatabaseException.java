package com.bootcamp.commons.exceptions;

/**
 * Created by darextossa on 11/21/17.
 */
public class DatabaseException extends Exception {

    private StackTraceElement[] stackTraceElements;

    /**
     * The exception generate when a jpql query is run
     */
    public DatabaseException() {
    }

    /**
     * Initialize the exception with the wanted message
     * @param message
     */
    public DatabaseException(String message) {
        super(message);
    }

    /**
     * Initialize the exception with the wanted message and stack trace elements
     * @param message
     * @param stackTraceElements
     */
    public DatabaseException(String message, StackTraceElement[] stackTraceElements) {
        super(message);
        this.stackTraceElements = stackTraceElements;
    }

    /**
     * Initialize the exception with the wanted stack trace elements
     * @param stackTraceElements
     */
    public DatabaseException(StackTraceElement[] stackTraceElements) {
        this.stackTraceElements = stackTraceElements;
    }

    @Override
    public String getMessage() {
        String message = super.getMessage() + " ";
        if (stackTraceElements != null || stackTraceElements.length != 0) {
            for (StackTraceElement stackTraceElement : stackTraceElements) {
                message = message + stackTraceElement.toString() + " ";
            }
        }

        return message;
    }
}
