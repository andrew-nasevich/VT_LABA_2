package Service;

import bean.Client;
import bean.Doctor;
import dao.DaoClient;
import controller.*;

import javax.print.Doc;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ClientService implements Service<Client> {
    private static final String NOT_FOUND = "Client not found.";

    private DaoClient daoClient;

    public ClientService(DaoClient daoClient) {
        this.daoClient = daoClient;
    }

    public void create(Client item) throws ServiceException {
        List<Client> clients = daoClient.getAll();
        if (item == null) {
            throw new ServiceException(NOT_FOUND);
        }

        if (clients.contains(item)) {
            throw new ServiceException("Client already exists");
        }

        daoClient.add(item);
        //DBController dbcontroller = new DBController();
        //dbcontroller.addInDB(item.getId(),item.getName(),item.getSurname(),item.getMobilePhone(),item.getRoom().getNumberOfRoom(),item.getRoom().getPrice());
        //dbcontroller.addInDB(item);
    }

    @Override
    public Client read(int id) {
        return daoClient.get(id);
    }

    @Override
    public void update(Client item) {
        for (var x : daoClient.getAll()) {
            if (x.getId() == item.getId()) {
                daoClient.delete(x);
                daoClient.add(item);
               // DBController dbcontroller = new DBController();
                // dbcontroller.updateInDB(item.getId(),item.getSurname());
                //dbcontroller.updateInDB(item);
                return;
            }
        }

        throw new ServiceException(NOT_FOUND);
    }


    public void visitTheDoctor(Client item, Doctor doctor) throws IOException {
        for (var x : daoClient.getAll()) {
            if (x.getId() == item.getId()) {
                daoClient.delete(x);
                doctor.visitTheDoctor(item);
                daoClient.add(item);
                // DBController dbcontroller = new DBController();
                // dbcontroller.updateInDB(item.getId(),item.getSurname());
                //dbcontroller.updateInDB(item);
                return;
            }
        }

        throw new ServiceException(NOT_FOUND);
    }

    @Override
    public void delete(int id) {
        daoClient.delete(daoClient.get(id));
        //DBController dbcontroller = new DBController();
        //dbcontroller.deleteFromDB(id);
        //dbcontroller.deleteFromDB(id);
    }

    public void migrationAll() throws ServiceException {
        List<Client> clients = daoClient.getAll();
       DBController dbcontroller = new DBController();
        for(Client item :clients){
            dbcontroller.addInDB(item);

        }
    }

    public void sort(Comparator<Client> comparator) {
        if (comparator == null) {
            throw new ServiceException("Comparator is null.");
        }

        var clients = daoClient.getAll();
        clients.sort(comparator);
        daoClient.addSort(clients);
    }

    @Override
    public List<Client> getAll() {
        return daoClient.getAll();
    }

    public void findClientInBase(String name){
        daoClient.findByName(name);
    }
}
