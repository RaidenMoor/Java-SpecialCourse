
import java.lang.System;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Лабораторная работа №3 " +
                "\nВыполнила Гусева Мария, студентка группы 6201-020302D");
        Scanner in = new Scanner(System.in);
        String choice;
        List<IStudent> list = new ArrayList<>();
        AddElement(list);

        do {

            System.out.println("""
                    Выберете пункт меню:0 - Выход
                    1 - Вывод информации об объектах массива
                    2 - Отделить объекты с одинаковым значением бизнес-метода
                    3 - Разбиение на два массива по типу
                    4 - Добавить элементы в список""");
            choice = in.next();
            switch (choice) {
                case "0":
                    return;
                case "1":
                    try {
                        if (list.isEmpty())
                            throw new Exception("Список пуст!");
                        else {
                        System.out.println("Ваш список");
                            for (IStudent iStudent : list) {
                                if(iStudent != null)
                                {
                                System.out.println(iStudent);
                                }
                            }
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                    Waiting();
                    break;
                case "2":
                    try {
                        if (list.isEmpty())
                            throw new Exception("Список пуст!");
                        else {
                            Map<Double, List<IStudent>> groupedStudents = new HashMap<>();
                            for (IStudent student : list) {
                                if (student != null) {
                                    if (!groupedStudents.containsKey(student.AverageGrade())) {
                                        groupedStudents.put(student.AverageGrade(), new ArrayList<>());
                                    }
                                    groupedStudents.get(student.AverageGrade()).add(student);
                                }
                            }

                            for (Map.Entry<Double, List<IStudent>> entry : groupedStudents.entrySet()) {
                                System.out.println("Студенты со средним баллом " + entry.getKey() + ":");
                                for (IStudent student : entry.getValue()) {
                                    System.out.println(student.toString());
                                }
                                System.out.println();
                            }
                        }
                    }
                    catch(Exception e)
                        {
                            System.out.println(e.getMessage());
                        }
                    Waiting();
                    break;
                case "3":
                    try {
                        if (list.isEmpty())
                            throw new Exception("Список пуст!");
                        else {
                            List<Student> students = new ArrayList<>();
                            List<SchoolChild> schoolChildren = new ArrayList<>();
                            for (IStudent object : list) {
                                if (object instanceof Student) {
                                    students.add((Student) object);
                                } else if (object instanceof SchoolChild) {
                                    schoolChildren.add((SchoolChild) object);
                                }
                            }
                            System.out.println("Список студентов");
                            for (Student student : students) {
                                System.out.println(student.toString());
                            }
                            System.out.println("Список школьников");
                            for (SchoolChild schoolChild : schoolChildren) {
                                System.out.println(schoolChild.toString());
                            }
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                    Waiting();
                    break;
                case "4":
                    AddElement(list);
                    break;
                default:
                    System.out.println("Вы ввели несуществующий пункт меню");
                    break;
            }
        } while (true);

    }
    public static IStudent CreateObject() {
        Scanner in = new Scanner(System.in);
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
                    list.add(CreateObject());
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