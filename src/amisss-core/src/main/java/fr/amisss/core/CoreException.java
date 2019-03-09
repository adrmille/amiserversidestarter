package fr.amisss.core;

import fr.amisss.core.common.exception.TechnicalException;

/**
 * For Arbitrage Technical Exceptions.
 */
public class CoreException extends TechnicalException {

    private static final long serialVersionUID = 3417991913430449172L;

    public CoreException() {
        super();
    }

    public CoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public CoreException(String message) {
        super(message);
    }

    public CoreException(Throwable cause) {
        super(cause);
    }

}
