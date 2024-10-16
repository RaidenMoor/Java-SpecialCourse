import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) { System.out.println("Лабораторная работа №4 " +
            "\nВыполнила Гусева Мария, студентка группы 6201-020302D");
        Scanner in = new Scanner(System.in);
        String choice;
        List<IStudent> list = new ArrayList<>();
        AddElement(list);

        String byteFile = "IStudent.byte";
        String charFile = "IStudent.txt";
        String serializeFile = "IStudentSerialize.bin";
        String formatFile = "IStudentFormat.txt";
        do {

            System.out.println("""
                    Выберете пункт меню:0 - Выход
                    1 - Вывод информации об объектах массива
                    2 - Отделить объекты с одинаковым значением бизнес-метода
                    3 - Разбиение на два массива по типу
                    4 - Добавить элементы в список
                    5 - Запись списка в байтовый поток
                    6 - Запись списка в символьный поток
                    7 - Чтение из символьного потока
                    8 - Чтение из байтового потока
                    9 - Сериализация списка
                    10 - Десериализация списка
                    11 - Форматная запись
                    12 - Форматное чтение""");
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
                case "5":
                    if (list.isEmpty()){
                        System.out.println("Список пуст!");
                    } else {
                        FileOutputStream fileOutputStream;
                        try{
                            fileOutputStream = new FileOutputStream(byteFile);
                            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                           // outputInputIStudent.outputLengthList(list, bufferedOutputStream);
                            outputInputIStudent.outputIStudent(list, bufferedOutputStream);
                            fileOutputStream.flush();
                            fileOutputStream.close();
                            System.out.println("Список успешно записан в байтовый поток");


                        }
                        catch (IOException e)
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                    Waiting();
                    break;
                case "6":
                    if(list.isEmpty())
                    {
                        System.out.println("Список пуст");
                    }
                    else {
                        FileWriter fileWriter;
                        try
                        {
                            fileWriter = new FileWriter(charFile);
                            outputInputIStudent.writeIStudent(list, fileWriter);
                            fileWriter.flush();
                            fileWriter.close();
                            System.out.println("Список успешно записан в символьный поток");

                        }
                        catch (IOException e)
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                    Waiting();
                    break;
                case "7":
                    List<IStudent> newList = new ArrayList<>();
                    FileReader fileReader;
                    try{
                        fileReader = new FileReader(charFile);
                        newList = outputInputIStudent.readIStudentList(fileReader);
                        fileReader.close();
                    } catch(IOException e){
                        System.out.println(e.getMessage());
                }
                    if(newList.isEmpty()){
                        System.out.println("Список отсутсвует");
                    }
                    else{
                        System.out.println("Считанный из потока список:");
                        for(IStudent student: newList)
                        {
                            if(student!= null)
                            {
                                System.out.println(student);

                            }
                        }
                    }
                    Waiting();
                    break;
                case "8":
                    List<IStudent> byteList;
                    FileInputStream fileInputStream;
                    try{
                        fileInputStream = new FileInputStream(byteFile);
                        byteList = outputInputIStudent.inputIStudentList(fileInputStream);
                        fileInputStream.close();
                        System.out.println("Считанный из потока список:");
                        for (IStudent student: byteList)
                        {
                            if(student != null)
                                System.out.println(student);
                        }
                    } catch(IOException e){
                        System.out.println(e.getMessage());
                    }
                    Waiting();
                    break;
                case "9":
                    if (list.isEmpty()){
                        System.out.println("Список пуст");
                    }
                    else{
                        FileOutputStream fileOutputStream;
                        try{
                            fileOutputStream = new FileOutputStream(serializeFile);
                            outputInputIStudent.serializeIStudentList(list, fileOutputStream);
                            fileOutputStream.flush();
                            fileOutputStream.close();
                            System.out.println("Объект успешно сериализован");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    Waiting();
                    break;
                case "10":

                        FileInputStream inputStream;
                        List<IStudent> deList;
                        try {
                            inputStream = new FileInputStream(serializeFile);
                            deList = outputInputIStudent.deserializeIStudentList(inputStream);

                            inputStream.close();
                            System.out.println("Объект успешно десериализован\nРезультат:");
                            if(deList.isEmpty()){
                                System.out.println("Список пуст");
                            } else{
                                for(IStudent student: deList)
                                    if (student != null)
                                     System.out.println(student);
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Waiting();
                    break;
                case "11":
                    FileWriter fileWriter;
                    try{
                        fileWriter = new FileWriter(formatFile);
                        outputInputIStudent.writeFormatIStudent(list,fileWriter);
                        fileWriter.flush();
                        fileWriter.close();
                        System.out.println("Объект успешно записан");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    Waiting();
                    break;

                case "12":
                    File read;
                    try{
                        read = new File(formatFile);
                        Scanner reader = new Scanner(read);
                        List<IStudent> formatList = outputInputIStudent.readFormatIStudent(reader);
                        System.out.println("Объект успешно считан\nРезультат:");
                        if(formatList.isEmpty()){
                            System.out.println("Список пуст");
                        } else{
                            for(IStudent student: formatList)
                                if (student != null)
                                    System.out.println(student);
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("Файл не найден");
                    }
                    Waiting();
                    break;
                default:
                    System.out.println("Вы ввели несуществующий пункт меню");
                    break;


            }
        } while (true);
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
