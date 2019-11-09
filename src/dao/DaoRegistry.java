package dao;

import Serialization.Serialization;
import bean.MedicalFile;
import java.util.ArrayList;
import java.io.IOException;

public class DaoRegistry {

    private static Serialization<MedicalFile> serialization = new Serialization<MedicalFile>();

    public static ArrayList<MedicalFile> readInitially(String pathToFile) throws IOException {
        return serialization.deserialize(pathToFile);
    }

    public static void storeMedicalFiles(String pathToFile, ArrayList<MedicalFile> medicalFiles) throws IOException {
       serialization.serialize(pathToFile, medicalFiles);
    }
}
