package jp.dip.oyasirazu.xmlviewer;

import java.lang.Throwable;

/**
 * XmlViewBuildException
 */
public class XmlViewBuildException extends Exception {
    /**
     * Constructor
     */
    public XmlViewBuildException() {
        super();
    }

    /**
     * Constructor
     */
    public XmlViewBuildException(String message) {
        super(message);
    }

    /**
     * Constructor
     */
    public XmlViewBuildException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor
     */
    public XmlViewBuildException(Throwable cause) {
        super(cause);
    }
}
