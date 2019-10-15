package bean;

import dao.DaoStuff;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stuff {

    private ArrayList<Doctor> doctors = new ArrayList();
    private String pathToFile;

    public Stuff(String pathToFile) throws IOException {

        ArrayList<String> records;

        if (pathToFile == null)
        {
            throw new ExceptionInInitializerError("Incorrect path was received in bean.Stuff class constructor.");
        }

        this.pathToFile = pathToFile;

        records = DaoStuff.ReadInitially(pathToFile);
        for (String record:
             records) {
            doctors.add(new Doctor(record));
        }
    }

    public void addDoctor(String speciality) throws IOException {
        if(speciality == null)
            return;

        if(this.getCertainDoctor(speciality) == null) {
            doctors.add(new Doctor(speciality));
            DaoStuff.addDoctor(pathToFile,speciality);
        }
    }

    public void clearStuff() throws IOException {
        doctors.clear();
        DaoStuff.clearStuff(pathToFile);
    }

    public List getDoctors() {
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
            DaoStuff.storeDoctors(pathToFile, doctors);
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

    public void sort() throws IOException {
        Collections.sort(doctors, Doctor::compareTo);

        DaoStuff.storeDoctors(pathToFile, doctors);
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

        if (that.pathToFile != pathToFile || that.getDoctors().size() != getDoctors().size())
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
        return doctors.hashCode() * pathToFile.hashCode() * 11;
    }

    @Override
    public String toString() {
        return "Stuff{" +
                "doctors = '" + doctors.toString() + ", " +
                "pathToFile = " + pathToFile + '}';
    }
}
