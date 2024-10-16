import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;

public interface IStudent extends Serializable {
    String GetName();

    int GetAge();

    int[] GetGrades();

    double AverageGrade();

    int GetCourse();

    void output(OutputStream out);
    void write(Writer out);
}