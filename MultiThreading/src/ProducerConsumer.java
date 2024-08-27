import java.util.*;

public class ProducerConsumer {

    public void execute () {
        PCCommon obj = new PCCommon(3);

        new Thread (() -> {
            for (int i=0; i<10; i++) {
                try {
                    obj.produce(i);
                    Thread.sleep(1000);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread (() -> {
            for (int i=0; i<10; i++) {
                try {
                    obj.consume();
                    Thread.sleep(500);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

class PCCommon {
    int size = 0;
    Queue<Integer> queue;

    public PCCommon (int size) {
        queue = new LinkedList<>();
        this.size = size;
    }

    public synchronized void produce (int item) throws InterruptedException {
        if (queue.size() == size) {
            System.out.println("full");
            wait();
        }

        queue.add(item);
        System.out.println(item + "produced");
        notifyAll();
    }

    public synchronized void consume () throws InterruptedException {
        if (queue.isEmpty()) {
            System.out.println("empty");
            wait();
        }

        System.out.println(queue.poll() + "cnsumed");
        notifyAll();
    }
}
