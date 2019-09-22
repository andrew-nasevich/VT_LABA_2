public class MedicalFile {

    private String medicalHistory;
    private String name;
    private String birthday;

    public MedicalFile(Human human)
    {
        if(human == null)
        {
            throw new ExceptionInInitializerError("Null pointer was received in MedicalFile class constructor.");
        }

        this.name = human.getName();
        this.birthday = human.getBirthday();
    }

    public String getMedicalHistory()
    {
        return  medicalHistory;
    }

    public void addRecord(String record)
    {
        medicalHistory += record;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }
}
