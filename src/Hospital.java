import java.util.List;

public class Hospital {

    private Registry registry;
    private Stuff stuff;
    private List<Doctor> doctors;

    public Hospital ()
    {
        registry = new Registry();
        stuff = new Stuff();

        stuff.addDoctor("surgeon");
        stuff.addDoctor("therapist");
        stuff.addDoctor("dermatologist");
    }

    public void visitDoctor(Human human, String doctorsSpeciality)
    {
        MedicalFile mf;

        if (human == null || doctorsSpeciality == null || doctorsSpeciality.trim().equals(""))
            return;

        if((mf = registry.getCertainMedicalFile(human)) == null)
        {
            mf = registry.addMedicalFile(human);
        }

        stuff.visitDoctor(mf, doctorsSpeciality);
    }

    public boolean addDoctor(String speciality)
    {
        if(speciality == null || speciality.trim().equals(""))
            return false;

        for (Doctor d:
             doctors) {
            if (d.getSpeciality().equals(speciality))
                return false;
        }

        doctors.add(new Doctor(speciality));
        return true;

    }

    public static void main(String[] args)
    {
        System.out.println("1");
    }

}
