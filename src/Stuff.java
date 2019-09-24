import java.io.*;
import java.util.ArrayList;

public class Stuff {

    private ArrayList<Doctor> doctors = new ArrayList<Doctor>();
    private String pathToFile;

    public Stuff(String pathToFile) throws IOException {

        String readStr;

        if (pathToFile == null)
        {
            throw new ExceptionInInitializerError("Incorrect path was received in Stuff class constructor.");
        }

        this.pathToFile = pathToFile;

        File f = new File(pathToFile);
        if(!f.exists())
        {
            f.createNewFile();
            return;
        }
        FileReader fr = new FileReader(pathToFile);
        BufferedReader br = new BufferedReader(fr);
        while((readStr = br.readLine()) != null)
        {
            doctors.add(new Doctor(readStr));
        }

        fr.close();
        br.close();
    }

    public void addDoctor(String speciality) throws IOException {
        if(speciality == null)
            return;

        if(this.getCertainDoctor(speciality) == null) {
            doctors.add(new Doctor(speciality));

            FileWriter fw = new FileWriter(pathToFile, true);
            fw.write(speciality.concat("\n"));
            fw.close();
        }
    }

    public void clearStuff() throws IOException {
        doctors.clear();
        FileWriter fw = new FileWriter(pathToFile, false);
        fw.close();
    }

    public ArrayList<Doctor> getDoctors()
    {
        return doctors;
    }

    public void removeDoctor(String speciality) throws IOException {

        boolean docWasRemoved = false;
        if(speciality == null || speciality.trim().equals(""))
            return;

        for (Doctor d:
                doctors) {
            if (d.getSpeciality().equals(speciality))
            {
                doctors.remove(d);
                docWasRemoved = true;
                break;
            }
        }

        if(docWasRemoved)
        {
            FileWriter fw = new FileWriter(pathToFile, false);
            for (Doctor doctor:
                    doctors) {
                fw.write(doctor.getSpeciality().concat("\n"));
            }
        }
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

    public void visitDoctor(MedicalFile mf, String doctorsSpeciality) throws IOException {

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

    public String getPathToFile()
    {
        return  pathToFile;
    }
}
