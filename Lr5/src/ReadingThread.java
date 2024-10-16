public class ReadingThread extends Thread{
    private IStudent student;

    public ReadingThread(IStudent student){
        this.student = student;
    }
    @Override
    public void run(){
        if(student == null){
            System.out.println("Объект не найден");
        }
        else{
            for(int i =0; i < student.GetGrades().length; i++){
                System.out.println("Read: " + student.GetGrades(i) + " Position: " + i);
            }

        }
    }
}
