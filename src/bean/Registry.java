package bean;

import dao.DaoRegistry;
import dao.DaoStuff;

import java.util.ArrayList;
import java.io.*;
import java.util.Collections;
import java.util.List;

public class Registry {

    private ArrayList<MedicalFile> medicalFiles = new ArrayList();
    private String pathToFile;

    public Registry(String pathToFile) throws IOException {

        int i;

        if (pathToFile == null)
        {
            throw new ExceptionInInitializerError("Incorrect path was received in bean.Stuff class constructor.");
        }
        this.pathToFile = pathToFile;

        ArrayList<String> records = DaoRegistry.readInitially(pathToFile);

        if(records == null)
        {
            return;
        }

        i = 0;
        while (i < records.size())
        {
            medicalFiles.add(new MedicalFile(new Human(records.get(i), records.get(i + 1)), records.get(i + 2), this));
            i += 3;
        }
    }


    public List getMedicalFiles() {
        return medicalFiles;
    }

    public MedicalFile getCertainMedicalFile(Human human) {
        if (human != null)
        {
            for (MedicalFile mf : medicalFiles)
            {
                if (mf.getName().equals(human.getName())  && mf.getBirthday().equals(human.getBirthday()) )
                    return mf;
            }
        }
        return null;
    }

    public MedicalFile addMedicalFile(Human human) throws IOException {
        if (human != null)
        {
            MedicalFile mf = new MedicalFile(human, "", this);
            medicalFiles.add(mf);

            DaoRegistry.addMedicalFile(pathToFile, human);

            return mf;
        }
        return null;
    }

    public void storeMedicalFiles() throws IOException {
        DaoRegistry.storeMedicalFiles(pathToFile, medicalFiles);
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public void removeMedicalFile(Human human) {
        medicalFiles.remove(getCertainMedicalFile(human));
    }

    public void sort() throws IOException {
        Collections.sort(medicalFiles, MedicalFile::compareTo);

        DaoRegistry.storeMedicalFiles(pathToFile, medicalFiles);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Registry that = (Registry) o;

        if (that.pathToFile != pathToFile || that.getMedicalFiles().size() != medicalFiles.size())
            return  false;

        List<MedicalFile> thatMedicalFiles = that.getMedicalFiles();

        for(int i = 0; i <= medicalFiles.size(); i ++)
        {
            if(!thatMedicalFiles.get(i).equals(medicalFiles.get(i)))
                return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return medicalFiles.hashCode() * pathToFile.hashCode() * 11;
    }

    @Override
    public String toString() {
        return "MedicalFile{" +
                "medicalFiles = '" + medicalFiles.toString() + ", " +
                "pathToFile = " + pathToFile + '}';
    }
}
