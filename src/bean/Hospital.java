package bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Hospital  {

    private Registry registry;
    private Stuff stuff;

    public Hospital (ArrayList<Client> clients) throws IOException {
        registry = new Registry(clients);
        stuff = new Stuff();
    }

    public Stuff getStuff()
    {
        return stuff;
    }

    public Registry getRegistry()
    {
        return registry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Hospital that = (Hospital) o;

        if (that.stuff == stuff && that.registry == registry)
            return true;

        return registry.equals(that.registry) && stuff.equals(that.stuff);
    }

    @Override
    public int hashCode() {
        return stuff.hashCode() * registry.hashCode() * 11;
    }

    //Переопределенный метод toString()
    @Override
    public String toString() {
        return "Hospital{" +
                "Registry = '" + registry.toString() + ", " +
                "Stuff = " + stuff.toString() + '}';
    }
}