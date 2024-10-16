import java.util.Random;

public class WritingRunnableThread implements Runnable {
    private final IStudentSynchronizer synchronizer;
    Random rand = new Random();
    public WritingRunnableThread(IStudentSynchronizer studentSynchronizer){
        this.synchronizer = studentSynchronizer;

    }

    @Override
    public void run() {
        while(synchronizer.canWrite())
            try {
                synchronizer.write(rand.nextInt(2, 6));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
