import java.io.OutputStream;
import java.io.Writer;

public class IStudentSynchronizer {
    private final IStudent student;
    private volatile int currIndex = 0;
    private Object lock = new Object();
    private boolean isSet = false;
    IStudentSynchronizer(IStudent student){
        this.student = student;
    }
    public int read() throws InterruptedException {
        int val;
        synchronized(lock) {
            if (!canRead()) throw new InterruptedException();
            while (!isSet)
                lock.wait();
            val = student.GetGrades(currIndex);
            currIndex++;
            System.out.println("Read: " + val + " from position: " + (currIndex - 1));
            isSet = false;
            lock.notifyAll();
        }
        return val;
    }

    public void write(int val) throws InterruptedException {
        synchronized(lock) {
            if (!canWrite()) throw new InterruptedException();
            while (isSet)
                lock.wait();
            student.SetGrades(currIndex, val);
            System.out.println("Write: " + val + " to position: " + currIndex);
            isSet = true;
            lock.notifyAll();
        }
    }

    public boolean canRead() {
        return currIndex < student.GetGrades().length;
    }
    public boolean canWrite() {
        return (!isSet && currIndex < student.GetGrades().length) || (isSet && currIndex < student.GetGrades().length - 1);
    }

}
