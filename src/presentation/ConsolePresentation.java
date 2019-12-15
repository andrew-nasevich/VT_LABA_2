package presentation;

import bean.*;
import Service.*;
import dao.*;
import Parser.*;

import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ConsolePresentation {
    public void present() throws IOException {

        //if(hospital == null)
        //    throw new ExceptionInInitializerError("Incorrect data was received in ConsolePresentation.present.");

        final String INPUT_PERSONAL_DATA = "Введите новое имя и дату рождения ";
        final String INPUT_QUALIFICATION = "Введите профессиональную квалификацию";

        ClientXmlParser clientParser = new ClientXmlParser("C:\\Users\\Lenovo\\IdeaProjects\\VT_JAVA_2\\src\\resources\\schemeXML.xml", "C:\\Users\\Lenovo\\IdeaProjects\\VT_JAVA_2\\src\\resources\\schemeXSD.xsd");
        DaoClient dc = new DaoClient(clientParser);
        ClientService cs = new ClientService(dc);

        var clients = cs.getAll();
        clients.forEach(el -> System.out.println(el.getId() + " " + el.getName()));
        ArrayList<Doctor> doctors = new ArrayList<Doctor>();
        doctors.add(new Doctor("Surgeon"));
        doctors.add(new Doctor("Therapist"));
        doctors.add(new Doctor("Dentist"));

        var hospital = new Hospital((ArrayList<Client>)clients, doctors);

        Scanner scanner = new Scanner(System.in);
        Registry registry = hospital.getRegistry();
        Stuff stuff = hospital.getStuff();

        String NewName;
        String NewBirthday;
        String name;
        String birthday;
        String speciality;
        String newSpeciality;
        MedicalFile mf;
        Doctor doctor;
        int command = -1;

        System.out.println("Доступные действия:\n" +
                "0) Выход\n" +
                "1) Создать карточку пациента\n" +
                "2) Изменить карточку пациента\n" +
                "3) Удалить карточку пациента \n" +
                "4) Проверить наличие карточки у данного пациента \n" +
                "5) Записаться на прием к врачу\n" +
                "/*Следующие действия доступны только главврачу(кроме 8)*/\n" +
                "6) Нанять врача с данной профессиональной квалификацией\n" +
                "7) Изменить проф. квалификацию врача\n" +
                "8) Проверить наличие врача с данной проф. квалификацией\n" +
                "9) Уволить врача с данной проф. квалификацией\n"+
                "10) Отсортировать карточки в регистратуре" + "\n" +
                "11) Отсртировать список докторов в больнице" + "\n");

        while (command != 0)
        {
            System.out.println("Выберите действие:");
            command = scanner.nextInt();
            scanner.nextLine();

            switch (command){
                case (0):
                    break;
                case (1):
                    System.out.println(INPUT_PERSONAL_DATA);
                    name = scanner.nextLine();
                    birthday = scanner.nextLine();

                    //registry.addMedicalFile(new Client(name, birthday));
                    break;
                case (2):
                    System.out.println("Введите имя и дату рождения ");
                    name = scanner.nextLine();
                    birthday = scanner.nextLine();

                    System.out.println(INPUT_PERSONAL_DATA);
                    NewName = scanner.nextLine();
                    NewBirthday = scanner.nextLine();

                    /*mf = registry.getCertainMedicalFile(new Client(name, birthday));
                    if (mf != null)
                    {
                        mf.setClientName(NewName);
                        mf.setBirthday(NewBirthday);
                    }*/
                    break;
                case (3):
                    System.out.println(INPUT_PERSONAL_DATA);
                    name = scanner.nextLine();
                    birthday = scanner.nextLine();

                    //registry.removeMedicalFile(new Client(name, birthday));
                    break;
                case (4):
                    System.out.println(INPUT_PERSONAL_DATA);
                    name = scanner.nextLine();
                    birthday = scanner.nextLine();

                    /*if(registry.getCertainMedicalFile(new Client(name, birthday)) == null)
                    {
                        System.out.println("Такой карточки нет");
                    }
                    else {
                        System.out.println("Такая карточка есть");
                    }*/
                    break;
                case (5):
                    System.out.println("Введите ваше имя, дату рождения и проф. квалификацию доктора");
                    name = scanner.nextLine();
                    birthday = scanner.nextLine();
                    speciality = scanner.nextLine();

                    //hospital.visitDoctor(new Client(name, birthday), speciality);
                    break;
                case (6):
                    System.out.println(INPUT_QUALIFICATION);
                    speciality = scanner.nextLine();

                    stuff.addDoctor(speciality);
                    break;
                case (7):
                    System.out.println(INPUT_QUALIFICATION);
                    speciality = scanner.nextLine();

                    System.out.println("Введите новую профессиональную квалификацию");
                    newSpeciality = scanner.nextLine();

                    doctor = stuff.getCertainDoctor(speciality);
                    doctor.setSpeciality(newSpeciality);
                    break;
                case (8):
                    System.out.println(INPUT_QUALIFICATION);
                    speciality = scanner.nextLine();

                    if(stuff.getCertainDoctor(speciality) != null)
                    {
                        System.out.println("Врач с данной проф. квалификацией уже нанят");
                    }else {
                        System.out.println("Врача с данной проф. квалификацией нет");
                    }
                    break;
                case (9):
                    System.out.println(INPUT_QUALIFICATION);
                    speciality = scanner.nextLine();

                    //stuff.removeDoctor(speciality);
                    break;

                case (10):
                    //registry.sort();
                    System.out.println("Карточки были отсортированы");
                    break;

                case (11):
                    //stuff.sort();
                    System.out.println("Доктора были отсортированы");
                    break;

                default:
                    System.out.println("Неверная команда");
            }
        }
    }
}
