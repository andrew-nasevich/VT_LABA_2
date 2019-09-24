import java.util.ArrayList;
import java.io.*;

public class Registry {

    private ArrayList<MedicalFile> MedicalFiles = new ArrayList<MedicalFile>();
    private String pathToFile;

    public Registry(String pathToFile) throws IOException {

        String strName, strBirthday, strMedicalHistory;
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

        do
        {
            strName = br.readLine();
            strBirthday = br.readLine();
            strMedicalHistory = br.readLine();
            if (strName != null && strBirthday != null && strMedicalHistory != null)
                MedicalFiles.add(new MedicalFile(new Human(strName, strBirthday), strMedicalHistory, this));
        }while (strName != null && strBirthday != null && strMedicalHistory != null);

        fr.close();
        br.close();
    }

    public ArrayList<MedicalFile> getMedicalFiles() {
        return MedicalFiles;
    }

    public MedicalFile getCertainMedicalFile(Human human) {
        if (human != null)
        {
            for (MedicalFile mf : MedicalFiles)
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
            MedicalFiles.add(mf);

            FileWriter fw = new FileWriter(pathToFile, true);
            fw.write(human.getName()+"\n"+human.getBirthday()+"\n"+"\n");
            fw.close();

            return mf;
        }
        return null;
    }

    public void storeMedicalFiles() throws IOException {
        FileWriter fw = new FileWriter(pathToFile, false);
        for (MedicalFile mf:
                MedicalFiles) {
            fw.write(mf.getName() + "\n" +
                    mf.getBirthday() + "\n" +
                    mf.getMedicalHistory() + "\n");
        }
        fw.close();

    }

    public String getPathToFile() {
        return pathToFile;
    }

    public void removeMedicalFile(Human human) {
        MedicalFiles.remove(getCertainMedicalFile(human));
    }
}
