package Parser;

import bean.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.ls.DOMImplementationLS;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Client xml parser.
 */
public class ClientXmlParser implements XmlParser<Client> {
    private static final String CLIENT = "client";
    private static final String CLIENTS = "clients";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String BIRTHDAY = "birthday";
    private static final String MEDICAL_FILE = "MedicalFile";
    private static final String CLIENT_ID = "clientID";
    private static final String MEDICAL_HISTORY = "medicalHistory";

    private DocumentBuilder documentBuilder;
    private String sourceFilePath;
    private String xsdFilePath;

    public String getSourceFilePath() {
        return sourceFilePath;
    }

    public String getXsdFilePath() {
        return xsdFilePath;
    }

    public ClientXmlParser() {
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
    }

    public ClientXmlParser(String sourceFilePath, String xsdFilePath) {
        this();
        this.sourceFilePath = sourceFilePath;
        this.xsdFilePath = xsdFilePath;
    }

    @Override
    public List<Client> getData() throws XmlParserException {
        var sourceFile = new File(sourceFilePath);
        var xsdFile = new File(xsdFilePath);

        if (!sourceFile.exists()) {
            throw new XmlParserException(sourceFilePath + ": file not exists.");
        }

        if (!xsdFile.exists()) {
            throw new XmlParserException(sourceFilePath + ": file not exists.");
        }

        validateXMLByXSD(sourceFile, xsdFile);

        List<Client> clients = new ArrayList<>();
        Document document;

        try {
            document = documentBuilder.parse(sourceFile);
        } catch (SAXException | IOException e) {
            throw new XmlParserException(e.getMessage());
        }

        var element = document.getDocumentElement();
        var nodeClients = element.getElementsByTagName(CLIENT);

        for (var i = 0; i < nodeClients.getLength(); i++) {
            if (nodeClients.item(i).getNodeType() == Node.ELEMENT_NODE) {
                clients.add(getClientElement((Element) nodeClients.item(i)));
            }
        }

        return clients;
    }

    @Override
    public void setData(List<Client> clients) throws XmlParserException {
        var document = documentBuilder.newDocument();
        var root = document.createElement(CLIENTS);
        document.appendChild(root);

        for (var client : clients) {
            root.appendChild(getClientElement(document, client));
        }

        var impl = document.getImplementation();
        var implLs = (DOMImplementationLS) impl.getFeature("LS", "3.0");
        var lsSerializer = implLs.createLSSerializer();
        lsSerializer.getDomConfig().setParameter("format-pretty-print", true);
        var output = implLs.createLSOutput();
        output.setEncoding("UTF-8");

        try {
            output.setByteStream(Files.newOutputStream(Paths.get(sourceFilePath)));
        } catch (IOException e) {
            throw new XmlParserException(e.getMessage());
        }

        lsSerializer.write(document, output);
    }

    private Client getClientElement(Element element) {
        Client client = new Client();

        try {
            client.setId(Integer.parseInt(getElementTextContent(element, ID)));
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("'" + ID + "'" + "is incorrect");
        }

        client.setName(getElementTextContent(element, NAME));
        client.setBirthday(getElementTextContent(element, BIRTHDAY));

        var medicalFileElement = (Element) element.getElementsByTagName(MEDICAL_FILE).item(0);
        if (medicalFileElement != null) {
            client.setMedicalFile(getMedicalFileElement(medicalFileElement));
        }

        return client;
    }

    private static String getElementTextContent(Element element, String elementName) {
        var nList = element.getElementsByTagName(elementName);
        var node = nList.item(0);
        return node.getTextContent();
    }

    private MedicalFile getMedicalFileElement(Element element) {
        var mf = new MedicalFile();
        int clientId;
        String medicalHistory;
        try {
            clientId = Integer.parseInt(getElementTextContent(element, CLIENT_ID));
        } catch (Exception ex) {
            throw new IllegalArgumentException("'" + CLIENT_ID + "'" + " item is incorrect.");
        }

        try {
            medicalHistory = getElementTextContent(element, MEDICAL_HISTORY);
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("'" + MEDICAL_HISTORY + "'" + " is incorrect");
        }

        return new MedicalFile(medicalHistory, clientId);
    }

    private Element getClientElement(Document document, Client client) {
        var clientElement = document.createElement(CLIENT);

        var idElement = document.createElement(ID);
        idElement.appendChild(document.createTextNode(Integer.toString(client.getId())));

        var nameElement = document.createElement(NAME);
        nameElement.appendChild(document.createTextNode(client.getName()));

        var birthdayElement = document.createElement(BIRTHDAY);
        birthdayElement.appendChild(document.createTextNode(client.getName()));


        clientElement.appendChild(idElement);
        clientElement.appendChild(nameElement);
        clientElement.appendChild(birthdayElement);

        if (client.getMedicalFile() != null) {
            clientElement.appendChild(getMedicalFileElement(document, client.getMedicalFile()));
        }

        return clientElement;
    }

    private Element getMedicalFileElement(Document document, MedicalFile mf) {
        var roomElement = document.createElement(MEDICAL_FILE);

        var idEl = document.createElement(CLIENT_ID);
        idEl.appendChild(document.createTextNode(Integer.toString(mf.getClientId())));

        var mhEl = document.createElement(MEDICAL_HISTORY);
        mhEl.appendChild(document.createTextNode(mf.getMedicalHistory()));

        roomElement.appendChild(idEl);
        roomElement.appendChild(mhEl);

        return roomElement;
    }

    private void validateXMLByXSD(File xml, File xsd) throws XmlParserException {
        try {
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    .newSchema(xsd)
                    .newValidator()
                    .validate(new StreamSource(xml));
        } catch (Exception e) {
            throw new XmlParserException("Invalid xml format");
        }
    }
}