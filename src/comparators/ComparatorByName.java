package comparators;

import bean.Client;
import java.util.Comparator;

public class ComparatorByName implements Comparator<Client> {
    public int compare(Client a, Client b){
        return a.getName().compareTo(b.getName());
    }
}