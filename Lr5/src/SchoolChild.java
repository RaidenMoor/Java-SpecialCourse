import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.PrimitiveIterator;

public class SchoolChild implements IStudent, Serializable {
    private java.lang.String name;
    private int age;
    private int[] grades;
    private int stage;
    public SchoolChild(){
        age = 0;
        stage = 0;
        grades = new int[0];
        name = "Unknown";
    }
    public SchoolChild(String name, int age, int stage, int[] grades) {

        this.name = name;
        this.stage = stage;
        this.grades = grades;
        this.age = age;
    }
    public String GetName()
    {
        return name;
    }
    public int GetAge()
    {
        return age;
    }
    public int GetCourse()
    {
        return stage;
    }

    @Override
    public void SetName(String name) {
        this.name = name;
    }

    @Override
    public void SetAge(int age) {
        this.age = age;
    }

    @Override
    public void SetGrades(int[] grades) {
        this.grades = grades;
    }

    @Override
    public void SetGrades(int id, int val) {
        grades[id] = val;
    }

    @Override
    public void SetCourse(int course) {
        this.stage = course;
    }

    public int[] GetGrades()
    {
        return grades;
    }

    @Override
    public int GetGrades(int id) {
        return grades[id];
    }


    public double AverageGrade()
    {
        double average;
        double sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        average = sum/ grades.length;
        return average;

    }
    @Override
    public String toString()
    {
        StringBuilder res = new StringBuilder();
        res.append("Имя ученика: ").append(name).append(" Возраст: ")
                .append(age).append(" Класс: ").append(stage).append(" Оценки: ");

        for(int grade: grades){
            res.append(grade).append(" ");
        }
        res.append("Средний балл:").append(this.AverageGrade());
        return res.toString();
    }

    @Override
    public boolean equals(Object obj)
    {
        boolean key = true;
        if (obj == null || getClass()  != obj.getClass())
        {
            return false;
        }
        if (Objects.equals(this.GetName(),  ((IStudent) obj).GetName()) && this.GetAge() == ((IStudent) obj).GetAge()
                && this.GetCourse() == ((IStudent) obj).GetCourse() && this.GetGrades().length == ((IStudent) obj).GetGrades().length)
        {

            for (int i = 0; i < grades.length; i++)
            {
                if (grades[i] != ((IStudent) obj).GetGrades()[i]) {
                    key = false;
                    break;
                }

            }

        }
        else key = false;
        return key;
    }
    @Override
    public int hashCode()
    {

        int total = 31;
        total = total * 31 + name.hashCode();
        total = total * 31 + age;
        total = total * 31 + stage;
        total = total * 31 + Arrays.hashCode(grades);
        return total;
    }

    @Override
    public void output(OutputStream out) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(out);
            dataOutputStream.writeUTF(getClass().getName());
            dataOutputStream.writeUTF(name);
            dataOutputStream.writeInt(age);
            dataOutputStream.writeInt(stage);
            dataOutputStream.writeInt(grades.length);
            for (int grade : grades) {
                dataOutputStream.writeInt(grade);
            }
            dataOutputStream.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void write(Writer out) {
        PrintWriter printWriter = new PrintWriter(out);
        printWriter.println(getClass().getName());
        printWriter.println(name);
        printWriter.println(age);
        printWriter.println(stage);
        printWriter.println(grades.length);
        for(int grade: grades){
            printWriter.println(grade);
        }
        printWriter.flush();
    }
}
