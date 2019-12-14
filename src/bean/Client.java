package bean;

public class Client {

    private int id;
    private String name;
    private String birthday;
    private MedicalFile medicalFile;

    public Client(){}

    public Client(int id, String name, String birthday)
    {
        if ( name == null || name.trim().equals(""))
        {
            throw new ExceptionInInitializerError("Incorrect name was received in bean.Human class constructor.");
        }

        this.name = name;
        this.birthday = birthday;
        this.id = id;

        medicalFile = new MedicalFile("", id);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getBirthday()
    {
        return birthday;
    }

    public void setBirthday(String birthday){
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Client that = (Client) o;

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

    public MedicalFile getMedicalFile() {
        return medicalFile;
    }

    public void setMedicalFile(MedicalFile medicalFile) {
        this.medicalFile = medicalFile;
    }
}
