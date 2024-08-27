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

class Common {
    private int counter = 1;
    private int upperLimit;
    private Object object = new Object();

    public Common (int upperLimit) {
        this.upperLimit = upperLimit;
    }

    public void printEven () throws InterruptedException {
        synchronized (object) {
            while (counter <= upperLimit) {
                if (counter % 2 == 1) {
                    object.wait();
                }

                System.out.println(counter++);
                object.notifyAll();
            }
        }
    }

    public synchronized void printOdd () throws InterruptedException {
        synchronized (object) {
            while (counter <= upperLimit) {
                if (counter % 2 == 0) {
                    object.wait();
                }

                System.out.println(counter++);
                object.notifyAll();
            }
        }
    }
}
