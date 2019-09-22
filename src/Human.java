public class Human {

    private String name;
    private String birthday;

    public Human(String name, String birthday)
    {
        if ( name == null || name.trim().equals(""))
        {
            throw new ExceptionInInitializerError("Incorrect name was received in Human class constructor.");
        }

        this.name = name;
        this.birthday = birthday;
    }

    public String getName()
    {
        return name;
    }

    public String getBirthday()
    {
        return birthday;
    }

}
