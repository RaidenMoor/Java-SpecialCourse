import java.io.OutputStream;
import java.io.Writer;

public class IStudentSynchronized implements IStudent{
    private final IStudent student;

    public IStudentSynchronized(IStudent student){
        this.student = student;
    }
    @Override
    public String GetName() {
        return student.GetName();
    }

    @Override
    public int GetAge() {
        return student.GetAge();
    }

    @Override
    public int[] GetGrades() {
        return student.GetGrades();
    }

    @Override
    public int GetGrades(int id) {
        return student.GetGrades(id);
    }

    @Override
    public double AverageGrade() {
        return student.AverageGrade();
    }

    @Override
    public int GetCourse() {
        return student.GetCourse();
    }

    @Override
    public void SetName(String name) {
        student.SetName(name);
    }

    @Override
    public void SetAge(int age) {
        student.SetAge(age);
    }

    @Override
    public void SetGrades(int[] grades) {
        student.SetGrades(grades);
    }

    @Override
    public void SetGrades(int id, int val) {
        student.SetGrades(id, val);
    }

    @Override
    public void SetCourse(int course) {
        student.SetCourse(course);
    }

    @Override
    public void output(OutputStream out) {
        student.output(out);
    }

    @Override
    public void write(Writer out) {
        student.write(out);
    }
}
