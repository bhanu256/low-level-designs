import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EvenOdd {

    public void execute () {
        Common obj = new Common(10);
        new Thread(() -> {
            try {
                obj.printEven();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                obj.printOdd();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}

//class Common {
//    private int counter = 1;
//    private int upperLimit;
//    private Object object = new Object();
//
//    public Common (int upperLimit) {
//        this.upperLimit = upperLimit;
//    }
//
//    public void printEven () throws InterruptedException {
//        synchronized (object) {
//            while (counter <= upperLimit) {
//                if (counter % 2 == 1) {
//                    object.wait();
//                }
//
//                System.out.println(counter++);
//                object.notifyAll();
//            }
//        }
//    }
//
//    public synchronized void printOdd () throws InterruptedException {
//        synchronized (object) {
//            while (counter <= upperLimit) {
//                if (counter % 2 == 0) {
//                    object.wait();
//                }
//
//                System.out.println(counter++);
//                object.notifyAll();
//            }
//        }
//    }
//}

class Common {
    private int counter = 0;
    private int upperLimit;
    Lock lock = new ReentrantLock();
    Condition cond = lock.newCondition();

    public Common (int upperLimit) {
        this.upperLimit = upperLimit;
    }

    public void printEven () throws InterruptedException {
        while (counter <= upperLimit) {
            lock.lock();
            if (counter % 2 == 1) cond.await();

            System.out.println(counter++);
            cond.signalAll();
            lock.unlock();
        }
    }

    public void printOdd () throws InterruptedException {
        while (counter <= upperLimit) {
            lock.lock();
            if (counter % 2 == 0) cond.await();

            System.out.println(counter++);
            cond.signalAll();
            lock.unlock();
        }
    }

}