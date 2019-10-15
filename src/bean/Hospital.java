package bean;

import java.io.IOException;

public class Hospital {

    private Registry registry;
    private Stuff stuff;

    public Hospital () throws IOException {
        registry = new Registry("src/resources/medical files.txt");
        stuff = new Stuff("src/resources/doctors.txt");
    }

    public void visitDoctor(Human human, String doctorsSpeciality) throws IOException {
        MedicalFile mf;

        if (human == null || doctorsSpeciality == null || doctorsSpeciality.trim().equals(""))
            return;

        if((mf = registry.getCertainMedicalFile(human)) == null)
        {
            mf = registry.addMedicalFile(human);
        }

        stuff.visitDoctor(mf, doctorsSpeciality);
    }

    public void addDoctor(String speciality) throws IOException {
        if(speciality == null || speciality.trim().equals(""))
            return;

        stuff.addDoctor(speciality);
    }

    public Stuff getStuff()
    {
        return stuff;
    }

    public Registry getRegistry()
    {
        return registry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Hospital that = (Hospital) o;

        if (that.stuff == stuff && that.registry == registry)
            return true;

        return registry.equals(that.registry) && stuff.equals(that.stuff);
    }

    @Override
    public int hashCode() {
        return stuff.hashCode() * registry.hashCode() * 11;
    }

    //Переопределенный метод toString()
    @Override
    public String toString() {
        return "Hospital{" +
                "Registry = '" + registry.toString() + ", " +
                "Stuff = " + stuff.toString() + '}';
    }
}
