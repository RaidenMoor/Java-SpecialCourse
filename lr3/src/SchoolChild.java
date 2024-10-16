import java.util.Arrays;
import java.util.Objects;

public class SchoolChild implements IStudent {
    private final java.lang.String name;
    private final int age;
    private final int[] grades;
    private final int stage;
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
    public int[] GetGrades()
    {
        return grades;
    }

    /*public double CorrelationCoefficient()
     коэффициент, который позволяет установить линейную зависимость между двумя величинами
     * В данном случае мы устанавливаем связь между возрастом студента и его успеваемостью
      Зачем? Не знаю. В тз сказано, в программе сделано
    {
        double coef = 0;
        return coef;
    }*/

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
}
