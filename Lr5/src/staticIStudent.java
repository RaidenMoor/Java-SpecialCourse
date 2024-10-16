
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class staticIStudent {
    public static void outputLengthList(List<IStudent> list, BufferedOutputStream bufferedOutputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream);
        dataOutputStream.writeInt(list.size());
        dataOutputStream.flush();
    }

    public static void outputIStudent(List<IStudent> list, OutputStream out) throws IOException {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out);
        outputLengthList(list, bufferedOutputStream);
        for (IStudent student : list) {
            if (student != null) {
                student.output(out);
            }
        }
    }

    public static void writeIStudent(List<IStudent> list, Writer out) {
        BufferedWriter bufferedWriter = new BufferedWriter(out);
        writeLengthList(list, bufferedWriter);
        for (IStudent student : list) {
            if (student != null) {
                student.write(out);
            }
        }
    }

    public static void writeLengthList(List<IStudent> list, BufferedWriter bufferedWriter) {
        PrintWriter printWriter = new PrintWriter(bufferedWriter);
        int count = 0;
        for (IStudent student : list)
            count++;
        printWriter.println(count);
        printWriter.flush();
    }

    public static List<IStudent> readIStudentList(Reader in) {
        BufferedReader bufferedReader = new BufferedReader(in);
        int length = -1;
        try {
            length = Integer.parseInt(bufferedReader.readLine());
            if (length == -1) {
                throw new IOException("Не удалось считать длину из текстового потока");
            }
        } catch (IOException | NumberFormatException exc) {
            System.out.println(exc.getMessage());
        }
        List<IStudent> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            list.add(readIStudent(bufferedReader));
        }
        return list;
    }

    public static IStudent readIStudent(BufferedReader bufferedReader) {
        IStudent student = null;
        try {
            while (bufferedReader.ready()) {
                String className = bufferedReader.readLine();
                String name = bufferedReader.readLine();
                int age = Integer.parseInt(bufferedReader.readLine());
                int course = Integer.parseInt(bufferedReader.readLine());
                int gradesLen = Integer.parseInt(bufferedReader.readLine());
                int[] grades = new int[gradesLen];
                for (int i = 0; i < gradesLen; i++) {
                    grades[i] = Integer.parseInt(bufferedReader.readLine());
                }
                if (className.equals(Student.class.getName())) {
                    student = new Student(name, age, course, grades);
                } else if (className.equals(SchoolChild.class.getName())) {
                    student = new SchoolChild(name, age, course, grades);
                }
                break;

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return student;
    }

    public static IStudent inputIStudent(InputStream in) {
        IStudent student = null;
        DataInputStream dataInputStream = new DataInputStream(in);
        try {
            String className = dataInputStream.readUTF();
            String name = dataInputStream.readUTF();
            int age = dataInputStream.readInt();
            int course = dataInputStream.readInt();
            int gradesLen = dataInputStream.readInt();
            int[] grades = new int[gradesLen];
            for (int i = 0; i < gradesLen; i++) {
                grades[i] = dataInputStream.readInt();
            }
            if (className.equals(Student.class.getName())) {
                student = new Student(name, age, course, grades);
            }
            if (className.equals(SchoolChild.class.getName())) {
                student = new SchoolChild(name, age, course, grades);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return student;
    }

    public static List<IStudent> inputIStudentList(InputStream in) {
        List<IStudent> list = new ArrayList<>();
        int length = -1;
        DataInputStream dataInputStream;
        try {
            dataInputStream = new DataInputStream(in);
            length = dataInputStream.readInt();
            if (length == -1) {
                throw new IOException("Не удалось считать длину из байтового потока");
            }
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }


        for (int i = 0; i < length; i++) {
            list.add(inputIStudent(in));
        }
        return list;
    }

    public static void serializeIStudent(IStudent student, OutputStream out) {
        ObjectOutputStream serializer;
        try {
            serializer = new ObjectOutputStream(out);
            serializer.writeObject(student);
            serializer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static IStudent deserializeIStudent(InputStream in) {
        IStudent student;
        ObjectInputStream deserialazer;
        try {
            deserialazer = new ObjectInputStream(in);
            student = (IStudent) deserialazer.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            student = null;
        }
        return student;
    }

    public static void serializeIStudentList(List<IStudent> list, OutputStream out) {
        ObjectOutputStream serializer;
        try {
            serializer = new ObjectOutputStream(out);
            serializer.writeObject(list);
            serializer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<IStudent> deserializeIStudentList(InputStream in) {

        List<IStudent> list = new ArrayList<>();
        ObjectInputStream deserializer;
        try {
            deserializer = new ObjectInputStream(in);
            list = (List<IStudent>) deserializer.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    public static void writeFormatIStudent(List<IStudent> list, Writer out) {
        PrintWriter printWriter = new PrintWriter(out);
        try {
            printWriter.println(list.size());
            for (IStudent student : list) {
                printWriter.println(student.getClass().getName());
                printWriter.println(student.GetName());
                printWriter.println(student.GetAge());
                printWriter.println(student.GetCourse());
                printWriter.println(student.GetGrades().length);
                for (int grade : student.GetGrades()) {
                    printWriter.println(grade);
                }

            }
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<IStudent> readFormatIStudent(Scanner in) {

        List<IStudent> list = new ArrayList<>();
        int size = Integer.parseInt(in.nextLine());
        IStudent object;
        object = null;
        String name;
        int age;
        int course;
        int gradeCount;
        int[] grades;
        String type;
        for (int i = 0; i < size; i++) {
            type = in.nextLine();
            name = in.nextLine();
            age = Integer.parseInt(in.nextLine());
            course = Integer.parseInt(in.nextLine());
            gradeCount = Integer.parseInt(in.nextLine());
            grades = new int[gradeCount];
            for (int j = 0; j < gradeCount; j++)
                grades[j] = Integer.parseInt(in.nextLine());
            if (type.equals(Student.class.getName()))
                object = new Student(name, age, course, grades);
            if (type.equals(SchoolChild.class.getName()))
                object = new SchoolChild(name, age, course, grades);
            list.add(object);
            object = null;
        }
        return list;

    }
    public static IStudentSynchronized IStudentSync(IStudent object){
        return new IStudentSynchronized(object);
    }
}
