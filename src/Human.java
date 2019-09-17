import java.util.Date;

public class Human {

    private String name;
    private Date birthday;

    public Human(String name, Date birthday)
    {
        this.name = name;
        this.birthday = birthday;
    }

    public String GetName()
    {
        return name;
    }

    public Date GetBirthday()
    {
        return birthday;
    }

}
