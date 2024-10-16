public class ReadingRunnableThread implements Runnable{
    IStudentSynchronizer synchronizer;
    ReadingRunnableThread(IStudentSynchronizer synchronizer){
        this.synchronizer = synchronizer;

    }
    @Override
    public void run() {
        while(synchronizer.canRead())
            try {
                synchronizer.read();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
