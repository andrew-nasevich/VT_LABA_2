package Serialization;

import java.io.*;
import java.util.ArrayList;

public class Serialization<T>{

    @SuppressWarnings("unchecked")
    public ArrayList<T> deserialize(String filePath) throws IOException {
        ArrayList<T> list = null;

        // Reading the object from a file
        File f = new File(filePath);
        FileInputStream file = new FileInputStream(f.getAbsolutePath());
        ObjectInputStream in = new ObjectInputStream(file);

        try
        {
            // Method for deserialization of object
            list = (ArrayList<T>)in.readObject();
        }
        catch(IOException ex)
        {
            System.out.println("IOException" +ex.getMessage()+" is caught");
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }
        finally {
            in.close();
            file.close();
        }
        return list;
    }

    public void serialize(String filePath, ArrayList<T> list){
        try
        {
            //Saving of object in a file
            File f = new File(filePath);
            FileOutputStream file = new FileOutputStream(f.getAbsoluteFile());
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(list);

            out.close();
            file.close();
        }
        catch(IOException ex)
        {
            System.out.println("IOException is caught "+ex.getMessage());
        }
    }
}
