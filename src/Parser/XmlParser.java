package Parser;


import java.util.List;

/**
 * The interface Xml parser.
 *
 * @param <T> the type parameter
 */
public interface XmlParser<T> {

    /**
     * Gets data.
     *
     * @return the data
     * @throws XmlParserException the xml parser exception
     */
    List<T> getData() throws XmlParserException;

    /**
     * Sets data.
     *
     * @param items the items
     * @throws XmlParserException the xml parser exception
     */
    void setData(List<T> items) throws XmlParserException;
}