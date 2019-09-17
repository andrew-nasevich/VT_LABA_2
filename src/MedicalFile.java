import java.security.PrivateKey;
import java.util.Date;

public class MedicalFile {

    private String medicalHistory;
    private String name;
    private Date birthday;

    public MedicalFile(Human human)
    {
        medicalHistory = new String();
        this.name = human.GetName();
        this.birthday = human.GetBirthday();
    }

    public String GetMedicalHistory()
    {
        return  medicalHistory;
    }

    public void AddRecord(String record)
    {
        medicalHistory += record;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }
}
