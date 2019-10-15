package dao;

import bean.Doctor;

import java.io.*;
import java.util.ArrayList;

public class DaoStuff {

    public static ArrayList<String> ReadInitially(String pathToFile) throws IOException {
        String readStr;
        ArrayList<String> records = new ArrayList();

        if (pathToFile == null)
        {
            throw new ExceptionInInitializerError("Incorrect path was received in bean.Stuff class constructor.");
        }


        File f = new File(pathToFile);
        if(!f.exists())
        {
            f.createNewFile();
            return null;
        }
        FileReader fr = new FileReader(pathToFile);
        BufferedReader br = new BufferedReader(fr);
        try {


            while ((readStr = br.readLine()) != null) {
                records.add(readStr);
            }
        }
        finally {
            fr.close();
            br.close();
        }
        return records;
    }

    public static void addDoctor(String pathToFile, String speciality) throws IOException {
        FileWriter fw = new FileWriter(pathToFile, true);
        try {
            fw.write(speciality.concat("\n"));
        }
        finally {
            fw.close();
        }
    }

    public static void clearStuff(String pathToFile) throws IOException {
        FileWriter fw = new FileWriter(pathToFile, false);
        fw.close();
    }

    public static void storeDoctors(String pathToFile, ArrayList<Doctor> doctors) throws IOException {
        FileWriter fw = new FileWriter(pathToFile, false);
        try {
            for (Doctor doctor :
                    doctors) {
                fw.write(doctor.getSpeciality().concat("\n"));
            }
        }
        finally {
            fw.close();
        }
    }
}
