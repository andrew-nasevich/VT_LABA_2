package dao;

import bean.MedicalFile;
import bean.Human;
import java.io.*;
import java.util.ArrayList;

public class DaoRegistry {
    public static ArrayList<String> readInitially(String pathToFile) throws IOException {

        ArrayList<String> records = new ArrayList();
        String strName;
        String strBirthday;
        String strMedicalHistory;

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
            do {
                strName = br.readLine();
                strBirthday = br.readLine();
                strMedicalHistory = br.readLine();
                if (strName != null && strBirthday != null && strMedicalHistory != null)
                {
                    records.add(strName);
                    records.add(strBirthday);
                    records.add(strMedicalHistory);
                }
            } while (strName != null && strBirthday != null && strMedicalHistory != null);
        }
        finally {
            fr.close();
            br.close();
        }

        return records;
    }

    public static void addMedicalFile(String pathToFile, Human human) throws IOException {
        FileWriter fw = new FileWriter(pathToFile, true);
        try {
            fw.write(human.getName()+"\n"+human.getBirthday()+"\n"+"\n");
        }
        finally {
            fw.close();
        }
    }

    public static void storeMedicalFiles(String pathToFile, ArrayList<MedicalFile> medicalFiles) throws IOException {
        FileWriter fw = new FileWriter(pathToFile, false);
        try {
            for (MedicalFile mf :
                    medicalFiles) {
                fw.write(mf.getName() + "\n" +
                        mf.getBirthday() + "\n" +
                        mf.getMedicalHistory() + "\n");
            }
        }
        finally {
            fw.close();
        }
    }
}
