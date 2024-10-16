import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;

public interface IStudent extends Serializable {
    String GetName();

    int GetAge();

    int[] GetGrades();
    int GetGrades(int id);

    double AverageGrade();

    int GetCourse();

    void SetName(String name);
    void SetAge(int age);
    void SetGrades(int[] grades);
    void SetGrades(int id, int val);
    void SetCourse(int course);

    void output(OutputStream out);
    void write(Writer out);
}