package dao;

import java.util.List;
import bean.Client;
import Parser.*;

/**
 * The type Dao client.
 */
public class DaoClient implements Dao<Client> {
    private static final String NOT_EXIST = "This client doesn't exist";
    private static final String EXIST = "This client exists";
    private static final String IS_NULL = "Clients is null";

    private ClientXmlParser xmlParser;

    /**
     * Instantiates a new Dao client.
     */
    public DaoClient() {
    }

    /**
     * Instantiates a new Dao client.
     *
     * @param clientXmlParser the client xml parser
     */
    public DaoClient(ClientXmlParser clientXmlParser) {
        this.xmlParser = clientXmlParser;
    }

    public List<Client> getAll() throws XmlParserException {
        return xmlParser.getData();
    }

    @Override
    public void delete(Client obj) throws DaoException {
        Boolean isInList = false;
        List<Client> clients;

        try {
            clients = xmlParser.getData();
        } catch (XmlParserException e) {
            throw new DaoException(e.getMessage());
        }

        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getName().equals(obj.getName())) {
                clients.remove(i);
                isInList = true;
            }
        }

        if (isInList) {
            try {
                xmlParser.setData(clients);
            } catch (XmlParserException e) {
                throw new DaoException(e.getMessage());
            }

            return;
        }

        throw new DaoException(NOT_EXIST);
    }

    @Override
    public void add(Client obj) throws DaoException {
        if (obj == null)
        {
            throw new DaoException(IS_NULL);
        }

        var clients = xmlParser.getData();

        for (Client cl : clients) {
            if (cl.getName().equals(obj.getName()))
            {
                throw new DaoException(EXIST);
            }
        }
        clients.add(obj);
        xmlParser.setData(clients);
    }

    @Override
    public Client get(int id) throws DaoException, XmlParserException {
        return xmlParser.getData().stream().filter(x -> x.getId() == id).findFirst()
                .orElseThrow(() -> new DaoException(NOT_EXIST));
    }

    @Override
    public void addRange(List<Client> items) throws DaoException, XmlParserException {
        if (items == null) {
            throw new DaoException(IS_NULL);
        }

        var clients = xmlParser.getData();
        var stream = clients.stream();

        if (items.stream().anyMatch(x -> stream.anyMatch(z -> z.equals(x)))) {
            throw new DaoException(EXIST);
        }

        clients.addAll(items);
        xmlParser.setData(clients);
    }

    public void addSort(List<Client> clients) throws DaoException {
        xmlParser.setData(clients);
    }

    public Client findByName(String name){
        var clients = xmlParser.getData();
        for (Client cl : clients)
        {
            if (cl.getName().equals(name))
            {
                return cl;
            }
        }

        return null;
    }
}