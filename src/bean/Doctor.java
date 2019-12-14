package bean;

import java.io.IOException;

public class Doctor implements java.io.Serializable, Comparable<Doctor>{
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

    public void setSpeciality(String speciality){
        this.speciality = speciality;
    }

    public void  visitTheDoctor(Registry registry, MedicalFile mf) throws IOException {
        if(mf != null)
        {
            registry.addRecord(mf, "The patient visited the " + speciality);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Doctor that = (Doctor) o;

        return that.speciality == speciality;

    }

    @Override
    public int hashCode() {
        return speciality.hashCode() * 11;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "speciality='" + speciality + '}';
    }

    @Override
    public int compareTo(Doctor o) {
        return speciality.compareTo(o.speciality);
    }
}
