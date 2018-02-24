package jp.dip.oyasirazu.xmlviewer;

import java.lang.Throwable;

/**
 * XmlViewerException
 */
public class XmlViewerException extends Exception {
    /**
     * Constructor
     */
    public XmlViewerException() {
        super();
    }

    /**
     * Constructor
     */
    public XmlViewerException(String message) {
        super(message);
    }

    /**
     * Constructor
     */
    public XmlViewerException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor
     */
    public XmlViewerException(Throwable cause) {
        super(cause);
    }
}
