package bean;

import java.util.ArrayList;
import java.io.*;
import java.util.Comparator;
import java.util.List;

public class Registry {

    private ArrayList<Client> clients;

    public Registry(ArrayList<Client> clients) throws IOException {

        if (clients == null)
        {
            this.clients = new ArrayList<Client>();
            return;
        }

        this.clients = clients;
    }


    public List getClients() {
        return clients;
    }

    public Client getClientByName(String name) {
        for (Client c : clients)
        {
            if (c.getName() == name)
                return c;
        }
        return null;
    }

    public void addClient(Client client) throws IOException {
        if (client != null)
        {
            clients.add(client);
        }
        return;
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }

    public void sort(Comparator<Client> comparator) throws IOException {
        if(comparator != null) {
            clients.sort(comparator);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Registry that = (Registry) o;

        if (that.getClients().size() != clients.size())
            return  false;

        List<Client> thatClients = that.getClients();

        for(int i = 0; i <= clients.size(); i ++)
        {
            if(!clients.get(i).equals(clients.get(i)))
                return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return clients.hashCode();
    }

    @Override
    public String toString() {
        return "Registry{" +
                "Clients = '" + clients.toString();
    }
}
