import java.io.IOException;

public class Doctor {
    private String speciality;

    public Doctor(){}

    public Doctor(String specialty)
    {
        if(specialty != null) {
            this.speciality = specialty;
        }
    }

    public String getSpeciality() {
        return speciality;
    }

    public void  setSpeciality(String speciality){
        this.speciality = speciality;
    }

    public void visitTheDoctor(MedicalFile mf) throws IOException {
        if(mf != null)
        {
            mf.addRecord("The patient visited the " + speciality);
        }
    }

}
