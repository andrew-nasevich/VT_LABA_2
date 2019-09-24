import java.io.IOException;

public class MedicalFile {

    private String medicalHistory;
    private String name;
    private String birthday;
    private Registry medicalFileHolder ;

    public MedicalFile(Human human, String medicalHistory, Registry medicalFileHolder)
    {
        if(human == null)
        {
            throw new ExceptionInInitializerError("Null pointer was received in MedicalFile class constructor.");
        }

        this.name = human.getName();
        this.birthday = human.getBirthday();
        this.medicalHistory = medicalHistory;
        this.medicalFileHolder = medicalFileHolder;
    }

    public String toString()
    {
        return name + " " + birthday + "\n" + medicalHistory;
    }
    public String getMedicalHistory()
    {
        return  medicalHistory;
    }

    public void addRecord(String record) throws IOException {
        medicalHistory += record;

        medicalFileHolder.storeMedicalFiles();
    }

    public String getName() {
        return name;
    }

    public void setName(String name)
    {
        this.name  = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday)
    {
        this.birthday  = birthday;
    }
}
