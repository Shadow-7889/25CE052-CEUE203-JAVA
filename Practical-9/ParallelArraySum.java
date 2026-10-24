import java.util.concurrent.atomic.AtomicLong;

class SharedTotal {
    long total = 0;

    synchronized void addSynchronized(long value) {
        total += value;
    }

    AtomicLong atomicTotal = new AtomicLong(0);

    void addAtomic(long value) {
        atomicTotal.addAndGet(value);
    }
}

class ParallelArraySum {
    static final int SIZE = 1_000_000;
    static final int THREADS = 4;

    static long[] numbers = new long[SIZE];

    static {
        for (int i = 0; i < SIZE; i++) {
            numbers[i] = 1;
        }
    }

    static void runTest(String mode) throws InterruptedException {
        SharedTotal shared = new SharedTotal();
        Thread[] threads = new Thread[THREADS];

        long start = System.nanoTime();

        for (int t = 0; t < THREADS; t++) {
            final int begin = t * SIZE / THREADS;
            final int end = (t + 1) * SIZE / THREADS;

            threads[t] = new Thread(() -> {
                long localSum = 0;

                for (int i = begin; i < end; i++) {
                    localSum += numbers[i];
                }

                if (mode.equals("unsafe")) {
                    shared.total += localSum;
                } else if (mode.equals("sync")) {
                    shared.addSynchronized(localSum);
                } else {
                    shared.addAtomic(localSum);
                }
            });

            threads[t].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long end = System.nanoTime();

        long result = mode.equals("atomic")
                ? shared.atomicTotal.get()
                : shared.total;

        System.out.println(mode + " total: " + result);
        System.out.println(mode + " time: "
                + (end - start) / 1_000_000.0 + " ms");
    }

    public static void main(String[] args)
            throws InterruptedException {
        System.out.println("Expected total: " + SIZE);

        runTest("unsafe");
        runTest("sync");
        runTest("atomic");

        System.out.println("25CE052-Mann");
    }
}
