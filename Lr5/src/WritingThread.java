import java.util.Random;

public class WritingThread extends Thread {
    private final IStudent student;

    WritingThread(IStudent student){
        this.student = student;
    }
    @Override
    public void run(){
        if(student == null){
            System.out.println("Объект не обнаружен");
            return;
        }
        else{
            Random rand = new Random();
            int val = 0;
            for(int i = 0; i < student.GetGrades().length; i++){
                val = rand.nextInt(2,6);
                student.SetGrades(i,val);
                System.out.println("Write: " + val + " Position: " + i);
            }

        }
    }
}
