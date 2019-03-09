package fr.amisss.core.common.exception;

/**
 * Basic exception to return for non functional and internal errors. 
 */
public class TechnicalException extends RuntimeException {

    private static final long serialVersionUID = -3055156376286812885L;

    public TechnicalException() {
        super();
    }

    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
    }

    public TechnicalException(String message) {
        super(message);
    }

    public TechnicalException(Throwable cause) {
        super(cause);
    }
    
}
