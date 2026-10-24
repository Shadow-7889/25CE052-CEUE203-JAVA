import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

class SeatBooking {
    int seatsLeft = 5;
    AtomicInteger successfulBookings = new AtomicInteger(0);

    public synchronized void book() {
        if (seatsLeft > 0) {
            seatsLeft--;
            successfulBookings.incrementAndGet();
            System.out.println(Thread.currentThread().getName()
                    + " booked a seat.");
        } else {
            System.out.println(Thread.currentThread().getName()
                    + " failed: No seats left.");
        }
    }
}

public class SeatBookingRace {
    public static void main(String[] args)
            throws InterruptedException {

        SeatBooking booking = new SeatBooking();
        int threads = 10;

        Thread[] t = new Thread[threads];
        CountDownLatch start = new CountDownLatch(1);

        for (int i = 0; i < threads; i++) {
            t[i] = new Thread(() -> {
                try {
                    start.await();
                    booking.book();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }, "Thread-" + (i + 1));

            t[i].start();
        }

        start.countDown();

        for (Thread thread : t) {
            thread.join();
        }

        System.out.println("\nSuccessful bookings: "
                + booking.successfulBookings.get());
        System.out.println("Seats left: " + booking.seatsLeft);

        System.out.println("25CE052-Mann");
    }
}
