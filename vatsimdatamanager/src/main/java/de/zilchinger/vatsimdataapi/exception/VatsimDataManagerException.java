package de.zilchinger.vatsimdataapi.exception;

/**
 * 
 * 
 * @author Stefan Zilch - (06.08.2011)
 */
public class VatsimDataManagerException extends Exception {

    /**  */
    private static final long  serialVersionUID        = -8805316279266926840L;

    /** Exception Message */
    public static final String NOT_INITIALIZED_MESSAGE = "The VatsimDataManager is not initialized. Please call init()!";

    /**
     * Constructor.
     */
    public VatsimDataManagerException() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param message
     * @param cause
     */
    public VatsimDataManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor.
     * 
     * @param message
     */
    public VatsimDataManagerException(String message) {
        super(message);
    }

    /**
     * Constructor.
     * 
     * @param cause
     */
    public VatsimDataManagerException(Throwable cause) {
        super(cause);
    }

}
