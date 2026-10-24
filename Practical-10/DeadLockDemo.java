class BankAccount {
    int id;

    BankAccount(int id) {
        this.id = id;
    }
}

class BankTransfer {

    static void transferDeadlock(BankAccount a, BankAccount b) {
        synchronized (a) {
            System.out.println(Thread.currentThread().getName()
                    + " locked Account " + a.id);

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            synchronized (b) {
                System.out.println("Transfer completed.");
            }
        }
    }

    static void transferSafe(BankAccount a, BankAccount b) {
        BankAccount first = a.id < b.id ? a : b;
        BankAccount second = a.id < b.id ? b : a;

        synchronized (first) {
            synchronized (second) {
                System.out.println(Thread.currentThread().getName()
                        + " transferred safely between Accounts "
                        + a.id + " and " + b.id);
            }
        }
    }
}

public class DeadLockDemo {
    public static void main(String[] args)
            throws InterruptedException {

        System.out.println("=== Deadlock demonstration ===");

        BankAccount a1 = new BankAccount(1);
        BankAccount a2 = new BankAccount(2);

        Thread t1 = new Thread(
                () -> BankTransfer.transferDeadlock(a1, a2),
                "Thread-1");

        Thread t2 = new Thread(
                () -> BankTransfer.transferDeadlock(a2, a1),
                "Thread-2");

        t1.setDaemon(true);
        t2.setDaemon(true);

        t1.start();
        t2.start();

        Thread.sleep(500);

        System.out.println("Deadlock demonstration finished.");

        System.out.println("\n=== Safe locking demonstration ===");

        BankAccount b1 = new BankAccount(1);
        BankAccount b2 = new BankAccount(2);

        Thread t3 = new Thread(
                () -> BankTransfer.transferSafe(b1, b2),
                "Thread-3");

        Thread t4 = new Thread(
                () -> BankTransfer.transferSafe(b2, b1),
                "Thread-4");

        t3.start();
        t4.start();

        t3.join();
        t4.join();

        System.out.println("Safe transfers completed.");

        System.out.println("25CE052-Mann");
    }
}
