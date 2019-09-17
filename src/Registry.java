import java.util.List;
import java.util.Date;

public class Registry {

    private List<MedicalFile> MedicalFiles;

    public List<MedicalFile> getMedicalFiles() {
        return MedicalFiles;
    }

    public MedicalFile getCertainMedicalFile(Human human)
    {
        for (MedicalFile mf: MedicalFiles)
        {
            if (mf.getName() == human.GetName() && mf.getBirthday() == human.GetBirthday())
                return  mf;
        }

        return null;
    }

    public void AddMedicalFile(MedicalFile mf)
    {
        MedicalFiles.add(mf);
    }
}
