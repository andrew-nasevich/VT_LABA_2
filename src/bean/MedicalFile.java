package bean;

public class MedicalFile implements java.io.Serializable, Comparable<MedicalFile>{

    private int clientId;
    private String medicalHistory;

    public MedicalFile(){}

    public MedicalFile(String medicalHistory, int clientId)
    {
        this.medicalHistory = medicalHistory;
        this.clientId = clientId;
    }

    public String getMedicalHistory()
    {
        return  medicalHistory;
    }

    public void addRecord(String record) {
        medicalHistory += record;
    }


    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String toFormattedString()
    {
        return clientId + "\n" + medicalHistory;
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

        return that.clientId == clientId && that.medicalHistory == medicalHistory;
    }

    @Override
    public int hashCode() {
        return clientId *  medicalHistory.hashCode() * 11;
    }

    @Override
    public String toString() {
        return "MedicalFile{" +
                "medicalHistory" + medicalHistory +
                "id = " + clientId +'}';
    }

    @Override
    public int compareTo(MedicalFile o) {
        if(o.clientId != clientId)
            return clientId - o.clientId;

        if(o.medicalHistory.compareTo(medicalHistory) != 0)
            return o.medicalHistory.compareTo(medicalHistory);

        return 0;
    }
}
