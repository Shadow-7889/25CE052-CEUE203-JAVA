class SharedBuffer {
    private int item;
    private boolean available = false;

    public synchronized void produce(int value)
            throws InterruptedException {

        while (available) {
            wait();
        }

        item = value;
        available = true;
        System.out.println("Produced: " + value);

        notifyAll();
    }

    public synchronized int consume()
            throws InterruptedException {

        while (!available) {
            wait();
        }

        int value = item;
        available = false;
        System.out.println("Consumed: " + value);

        notifyAll();
        return value;
    }
}

public class ProducerConsumer {
    public static void main(String[] args)
            throws InterruptedException {

        SharedBuffer buffer = new SharedBuffer();
        int totalItems = 10;

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= totalItems; i++) {
                    buffer.produce(i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= totalItems; i++) {
                    buffer.consume();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("All items processed.");

        System.out.println("25CE052-Mann");
    }
}
