package dao;

import Serialization.Serialization;
import bean.Doctor;

import java.util.ArrayList;
import java.io.IOException;

public class DaoStuff {

    private static Serialization<Doctor> serialization = new Serialization<Doctor>();

    public static ArrayList<Doctor> ReadInitially(String pathToFile) throws IOException {
        return serialization.deserialize(pathToFile);
    }

    public static void clearStuff(String pathToFile) throws IOException {
        serialization.serialize(pathToFile, null);
    }

    public static void storeDoctors(String pathToFile, ArrayList<Doctor> doctors) throws IOException {
        serialization.serialize(pathToFile, doctors);
    }
}
