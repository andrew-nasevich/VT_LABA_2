package bean;

import java.io.IOException;

public class MedicalFile implements Comparable<MedicalFile>{

    private String medicalHistory;
    private String name;
    private String birthday;
    private Registry medicalFileHolder ;

    public MedicalFile(){}

    public MedicalFile(Human human, String medicalHistory, Registry medicalFileHolder)
    {
        if(human == null)
        {
            throw new ExceptionInInitializerError("Null pointer was received in bean.MedicalFile class constructor.");
        }

        this.name = human.getName();
        this.birthday = human.getBirthday();
        this.medicalHistory = medicalHistory;
        this.medicalFileHolder = medicalFileHolder;
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

    public String toFormatedString()
    {
        return name + " " + birthday + "\n" + medicalHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MedicalFile that = (MedicalFile) o;

        return medicalFileHolder.equals(that.medicalFileHolder) && name == that.name &&
                that.birthday == birthday && that.medicalHistory == medicalHistory;
    }

    @Override
    public int hashCode() {
        return medicalFileHolder.hashCode() * name.hashCode() *
                birthday.hashCode() * medicalHistory.hashCode() * 11;
    }

    @Override
    public String toString() {
        return "MedicalFile{" +
                "name = '" + name + ", " +
                "birthday = " + birthday + ", " +
                "medicalHistory" + medicalHistory +'}';
    }

    @Override
    public int compareTo(MedicalFile o) {
        if(o.name.compareTo(name) != 0)
            return o.name.compareTo(name);

        if(o.birthday.compareTo(birthday) != 0)
            return o.birthday.compareTo(birthday);

        if(o.medicalHistory.compareTo(medicalHistory) != 0)
            return o.medicalHistory.compareTo(medicalHistory);

        if(o.medicalFileHolder.equals(medicalFileHolder))
            return 0;
        else
            return 1;

    }
}
