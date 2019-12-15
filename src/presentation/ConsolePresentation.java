package presentation;

import bean.*;
import Service.*;
import comparators.ComparatorByName;
import dao.*;
import Parser.*;

import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ConsolePresentation {
    public void present() throws IOException {

        final String INPUT_PERSONAL_DATA = "Введите новое имя и дату рождения ";
        final String INPUT_QUALIFICATION = "Введите профессиональную квалификацию";

        ClientXmlParser clientParser = new ClientXmlParser("C:\\Users\\Lenovo\\IdeaProjects\\VT_JAVA_2\\src\\resources\\schemeXML.xml", "C:\\Users\\Lenovo\\IdeaProjects\\VT_JAVA_2\\src\\resources\\schemeXSD.xsd");
        DaoClient dc = new DaoClient(clientParser);
        ClientService cs = new ClientService(dc);

        var clients = cs.getAll();

        var hospital = new Hospital((ArrayList<Client>)clients);

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
        Client cl;
        int command = -1;

        while (command != 0)
        {
            System.out.println("Пациенты: ");
            clients = cs.getAll();
            clients.forEach(el -> System.out.println(el.getId() + " " + el.getName()));

            System.out.println("Доступные действия:\n" +
                    "0) Выход\n" +
                    "1) Создать пациента\n" +
                    "2) Изменить пациента\n" +
                    "3) Удалить пациента \n" +
                    "4) Проверить наличие данного пациента \n" +
                    "5) Записаться на прием к врачу\n" +
                    "6) Отсортировать пациентов по имени\n" +
                    "7) Миграция\n" +
                    "Выберите действие:");
            command = scanner.nextInt();
            scanner.nextLine();

            switch (command){
                case (0):
                    break;
                case (1):
                    System.out.println("Creating new client");
                    Client client = new Client(101, "Misha", "03.02.99");
                    cs.create(client);
                    break;
                case (2):
                    System.out.println("Updating");
                    cl = clients.get(0);
                    cl.setName("Vera");
                    cs.update(cl);
                    cs.getAll().forEach(x -> System.out.println(x.getId() + " - " + x.getName()));
                    break;
                case (3):
                    System.out.println("Deleting");
                    cs.delete(0);
                    cs.getAll().forEach(x -> System.out.println(x.getId() + " " + x.getName()));
                    break;
                case (4):
                    System.out.println("Find by name:");
                    cs.findClientInBase("Andrew");
                    break;
                case (5):
                    System.out.println("Visiting surgeon");
                    clients = cs.getAll();
                    doctor = stuff.getCertainDoctor("Surgeon");
                    cs.visitTheDoctor(clients.get(0), doctor);
                    break;
                case (6):
                    System.out.println("Sorting");
                    var comp = new ComparatorByName();
                    cs.sort(comp);
                    break;
                case (7):
                    System.out.println("Migration");
                    cs.migrationAll();
                    break;

                default:
                    System.out.println("Incorrect command");
            }
        }
    }
}
