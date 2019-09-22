import java.util.List;

public class Registry {

    private List<MedicalFile> MedicalFiles;

    public Registry(){}

    public List<MedicalFile> getMedicalFiles() {
        return MedicalFiles;
    }

    public MedicalFile getCertainMedicalFile(Human human)
    {
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

    public MedicalFile addMedicalFile(Human human)
    {
        if (human != null)
        {
            MedicalFile mf = new MedicalFile(human);
            MedicalFiles.add(mf);
            return mf;
        }
        return null;
    }
}
