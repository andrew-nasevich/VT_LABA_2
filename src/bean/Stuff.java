package bean;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.IOException;

public class Stuff {

    private ArrayList<Doctor> doctors;

    public Stuff() throws IOException {

        var doctors = new ArrayList<Doctor>();
        doctors.add(new Doctor("Surgeon"));
        doctors.add(new Doctor("Therapist"));
        doctors.add(new Doctor("Dentist"));

        this.doctors = doctors;
    }

    public void addDoctor(String speciality) throws IOException {
        if(speciality == null)
            return;

        if(this.getCertainDoctor(speciality) == null) {
            doctors.add(new Doctor(speciality));
        }
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void removeDoctor(Doctor doctor) throws IOException {
        doctors.remove(doctor);
    }

    public Doctor getCertainDoctor(String speciality) {
        if(speciality == null || speciality.trim().equals(""))
            return null;

        for (Doctor d:
                doctors) {
            if (d.getSpeciality().equals(speciality))
                return d;
        }

        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Stuff that = (Stuff) o;

        if (that.getDoctors().size() != getDoctors().size())
            return  false;

        List<Doctor> thatDoctors = that.getDoctors();

        for(int i = 0; i <= doctors.size(); i ++)
        {
            if(!thatDoctors.get(i).equals(doctors.get(i)))
                return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return doctors.hashCode();
    }

    @Override
    public String toString() {
        return "Stuff{" +
                "doctors = '" + doctors.toString();
    }
}
