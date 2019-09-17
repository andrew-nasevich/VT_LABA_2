import java.util.Date;

public class Hospital {

    private Registry registry;

    public Hospital ()
    {
        registry = new Registry();
    }

    public void MakeAppointmentWithDentist(Human human)
    {
        MedicalFile mf = registry.getCertainMedicalFile(human);
    }

    public static void main(String[] args)
    {
        System.out.println("1");
    }

}
