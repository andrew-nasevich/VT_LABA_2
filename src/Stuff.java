import java.util.List;

public class Stuff {

    private  List<Doctor> doctors;

    public Stuff() { }

    public void addDoctor(String speciality)
    {
        if(speciality == null || speciality.trim().equals(""))
            return;

        if(!this.contains(speciality))
            doctors.add(new Doctor(speciality));
    }

    public void clearStuff()
    {
        doctors.clear();
    }

    public List<Doctor> getDoctors()
    {
        return doctors;
    }

    public void removeDoctor(String speciality)
    {
        if(speciality == null || speciality.trim().equals(""))
            return;

        for (Doctor d:
                doctors) {
            if (d.getSpeciality().equals(speciality))
            {
                doctors.remove(d);
                return;
            }
        }
    }

    public boolean contains(String speciality)
    {
        if(speciality == null || speciality.trim().equals(""))
            return false;

        for (Doctor d:
                doctors) {
            if (d.getSpeciality().equals(speciality))
                return true;
        }

        return false;
    }

    public void visitDoctor(MedicalFile mf, String doctorsSpeciality)
    {

        if (mf == null || doctorsSpeciality == null || doctorsSpeciality.trim().equals(""))
            return;

        for (Doctor d :
                doctors) {
            if (d.getSpeciality().equals(doctorsSpeciality)) {
                d.visitTheDoctor(mf);
                return;
            }
        }
    }

}
