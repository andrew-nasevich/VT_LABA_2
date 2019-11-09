package presentation;

import bean.*;

import java.io.IOException;
import java.util.Scanner;

public class ConsolePresentation {
    public static void present(Hospital hospital) throws IOException {

        if(hospital == null)
            throw new ExceptionInInitializerError("Incorrect data was received in ConsolePresentation.present.");

        final String INPUT_PERSONAL_DATA = "Введите новое имя и дату рождения ";
        final String INPUT_QUALIFICATION = "Введите профессиональную квалификацию";

        Scanner scanner = new Scanner(System.in);
        Registry registry = hospital.getRegistry();
        Stuff stuff = hospital.getStuff();

        String name;
        String birthday;
        String speciality;
        MedicalFile mf;
        Doctor doctor;
        int command = -1;
        String current = new java.io.File( "." ).getCanonicalPath();
        System.out.println("Current dir:"+current);
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

                    registry.addMedicalFile(new Human(name, birthday));
                    break;
                case (2):
                    System.out.println(INPUT_PERSONAL_DATA);
                    name = scanner.nextLine();
                    birthday = scanner.nextLine();

                    mf = registry.getCertainMedicalFile(new Human(name, birthday));
                    if (mf != null)
                    {
                        mf.setName(name);
                        mf.setBirthday(birthday);
                    }
                    break;
                case (3):
                    System.out.println(INPUT_PERSONAL_DATA);
                    name = scanner.nextLine();
                    birthday = scanner.nextLine();

                    registry.removeMedicalFile(new Human(name, birthday));
                    break;
                case (4):
                    System.out.println(INPUT_PERSONAL_DATA);
                    name = scanner.nextLine();
                    birthday = scanner.nextLine();

                    if(registry.getCertainMedicalFile(new Human(name, birthday)) == null)
                    {
                        System.out.println("Такой карточки нет");
                    }
                    else {
                        System.out.println("Такая карточка есть");
                    }
                    break;
                case (5):
                    System.out.println("Введите ваше имя, дату рождения и проф. квалификацию доктора");
                    name = scanner.nextLine();
                    birthday = scanner.nextLine();
                    speciality = scanner.nextLine();

                    hospital.visitDoctor(new Human(name, birthday), speciality);
                    break;
                case (6):
                    System.out.println(INPUT_QUALIFICATION);
                    speciality = scanner.nextLine();

                    stuff.addDoctor(speciality);
                    break;
                case (7):
                    System.out.println(INPUT_QUALIFICATION);
                    speciality = scanner.nextLine();

                    doctor = stuff.getCertainDoctor(speciality);
                    doctor.setSpeciality(speciality);
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

                    stuff.removeDoctor(speciality);
                    break;

                case (10):
                    registry.sort();
                    System.out.println("Карточки были отсортированы");
                    break;

                case (11):
                    stuff.sort();
                    System.out.println("Доктора были отсортированы");
                    break;

                default:
                    System.out.println("Неверная команда");
            }
        }
    }
}
