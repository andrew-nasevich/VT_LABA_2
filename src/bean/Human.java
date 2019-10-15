package bean;

public class Human {

    private String name;
    private String birthday;

    public  Human(){}

    public Human(String name, String birthday)
    {
        if ( name == null || name.trim().equals(""))
        {
            throw new ExceptionInInitializerError("Incorrect name was received in bean.Human class constructor.");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Human that = (Human) o;

        return that.birthday == birthday && that.name == name;
    }

    @Override
    public int hashCode() {
        return name.hashCode() * birthday.hashCode() * 11;
    }

    //Переопределенный метод toString()
    @Override
    public String toString() {
        return "Human{" +
                "name = '" + name + ", " +
                "birthday = " + birthday + '}';
    }
}
