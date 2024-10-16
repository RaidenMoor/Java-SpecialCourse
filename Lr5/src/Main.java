import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) { System.out.println("Лабораторная работа №5 " +
            "\nВыполнила Гусева Мария, студентка группы 6201-020302D");
        Scanner in = new Scanner(System.in);
        String choice;

        do {

            System.out.println("""
                    Выберете пункт меню:
                    0 - Выход
                    1 - Задание 1
                    2 - Задание 2
                    3 - Задание 3""");
            choice = in.next();
            switch (choice) {
                case "0":
                    return;
                case "1":
                   first();
                   // Waiting();
                    break;
                case "2":
                  second();
                 // Waiting();
                    break;
                case "3":
                    third();
                 //   Waiting();
                    break;
                default:
                    System.out.println("Вы ввели несуществующий пункт меню");
                    break;

            }
        } while (true);
    }
    public static void first(){
        IStudent object = new Student();
        WritingThread writingThread = new WritingThread(object);
        ReadingThread readingThread = new ReadingThread(object);
        writingThread.start();
        readingThread.start();

    }
    public static void second(){
        IStudent object = new Student();
        IStudentSynchronizer synchronizer = new IStudentSynchronizer(object);

        Thread writingThread = new Thread(new WritingRunnableThread(synchronizer));
        Thread readingThread = new Thread(new ReadingRunnableThread(synchronizer));

        writingThread.start();
        readingThread.start();

    }
    public static void third(){
        IStudent object = new Student();
        IStudentSynchronized synchronizer = new IStudentSynchronized(object);

        Thread readingThread = new ReadingThread(synchronizer);
        Thread writingThread = new WritingThread(synchronizer);

        writingThread.start();
        readingThread.start();
    }
    public static IStudent CreateObject(Scanner in) {

        IStudent object = null;
        int age;
        int course;
        String grades_string;
        System.out.println("Введите имя");
        String name = in.next();
        System.out.println("Введите возраст " +
                "(допустимый возраст для школьника от 6-и до 18-и лет; допустимый возраст студента от 15-и лет)");
        try {
            age = in.nextInt();
            System.out.println("Введите курс/класс (допустимы курсы с первого по пятый; допустимы классы с 1-го по 11-й)");
            course = in.nextInt();

            System.out.println("Введите оценки через пробел(по пятибалльной шкале)");
            in.nextLine();
            grades_string = in.nextLine();
            int[] grades;
            System.out.println("Выберете класс объекта:\n1 - SchoolChild\n2 - Student");
            String answer = in.next();
            switch (answer) {
                case "1":
                    try {
                        if (age < 6 || age > 18)
                            throw new Exception("Допустимый возраст школьника с 6-и до 18-и лет включительно");
                        if ((course < 1) || (course > 11))
                            throw new Exception("Школьник может обучаться в классе с первого по одиннадцатый включительно");
                        else {
                            String[] transfer = grades_string.split(" ");
                            grades = new int[transfer.length];
                            for (int i = 0; i < transfer.length; i++) {
                                grades[i] = Integer.parseInt(transfer[i]);
                            }
                            for (int grade : grades) {
                                if ((grade < 2 || grade > 5))
                                    throw new Exception("Оценки ставятся по пятибалльной шкале");
                            }

                            object = new SchoolChild(name, age, course, grades);

                            System.out.println("Объект успешно добавлен");
                        }

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "2":
                    try {
                        if (age < 15)
                            throw new Exception("Допустимый возраст студента начинается c 15-и лет");
                        if ((course < 1) || (course > 5))
                            throw new Exception("Студент может обучаться на курсе с первого по пятый включительно");

                        String[] transfer = grades_string.split(" ");
                        grades = new int[transfer.length];
                        for (int i = 0; i < transfer.length; i++) {
                            grades[i] = Integer.parseInt(transfer[i]);
                        }
                        for (int grade : grades) {
                            if ((grade < 2 || grade > 5))
                                throw new Exception("Оценки ставятся по пятибалльной шкале");

                        }
                        object = new Student(name, age, course, grades);
                        System.out.println("Объект успешно добавлен");
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Введен несуществующий пункт");
                    Waiting();
            }
            //throw new InputMismatchException();
            in.nextLine();
        }
        catch (InputMismatchException e)
        {
            System.out.println("Данные некорректны");
        }




        return object;
    }
    public static void AddElement(List<IStudent> list) {
        Scanner in = new Scanner(System.in);
        String choice;
        boolean flag = true;
        while (flag)
        {
            System.out.println("Хотите добавить объект в список?\n1 - Да\n2 - Нет");
            choice = in.next();
            switch (choice) {
                case "1":
                    list.add(CreateObject(in));
                    Waiting();
                    break;
                case "2":
                    flag = false;
                    Waiting();
                    break;
                default:
                    System.out.println("Введен несуществующий пункт.");
                    break;
            }
        }
    }
    public static void Waiting()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Нажмите Enter, чтобы продолжить");
        in.nextLine();
    }
}
