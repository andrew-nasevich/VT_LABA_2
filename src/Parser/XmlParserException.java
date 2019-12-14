package Parser;

/**
 * The type Xml parser exception.
 */
public class XmlParserException extends RuntimeException {
    /**
     * Instantiates a new Xml parser exception.
     */
    public XmlParserException() {
    }

    /**
     * Instantiates a new Xml parser exception.
     *
     * @param message the message
     */
    XmlParserException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Xml parser exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public XmlParserException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Xml parser exception.
     *
     * @param cause the cause
     */
    public XmlParserException(Throwable cause) {
        super(cause);
    }
}