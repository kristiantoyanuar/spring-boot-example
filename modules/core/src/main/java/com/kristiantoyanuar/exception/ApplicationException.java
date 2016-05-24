package com.kristiantoyanuar.exception;

/**
 * Created by Kristianto Yanuar on 5/23/16.
 */
public class ApplicationException extends Exception {

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String code, String message) {
        super(message);
    }

}
