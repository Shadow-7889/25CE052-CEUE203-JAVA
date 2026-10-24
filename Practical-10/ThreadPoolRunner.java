import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolRunner {
    public static void main(String[] args)
            throws InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 10; i++) {
            final int id = i;

            Runnable task = () -> {
                System.out.println("Task " + id + " running on "
                        + Thread.currentThread().getName());

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            };

            pool.submit(task);
        }

        pool.shutdown();

        if (pool.awaitTermination(1, TimeUnit.MINUTES)) {
            System.out.println("All tasks completed.");
        } else {
            System.out.println("Tasks did not finish in time.");
            pool.shutdownNow();
        }
        System.out.println("25CE052-Mann");
    }
}
