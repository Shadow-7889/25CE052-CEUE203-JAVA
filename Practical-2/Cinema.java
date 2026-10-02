class CinemaShow
{
    private String title;
    private int seatsAvailable;
    private final int capacity;
    private static int totalBooked = 0;

    CinemaShow(String title, int capacity)
    {
        this.title = title;
        this.capacity = capacity;
        seatsAvailable = capacity;
    }

    CinemaShow(String title)
    {
        this(title, 100);
    }

    boolean book(int n)
    {
        if(n <= seatsAvailable)
        {
            seatsAvailable = seatsAvailable - n;
            totalBooked = totalBooked + n;
            return true;
        }
        else
        {
            return false;
        }
    }

    void cancel(int n)
    {
        seatsAvailable = seatsAvailable + n;

        if(seatsAvailable > capacity)
        {
            seatsAvailable = capacity;
        }
    }

    int getSeatsAvailable()
    {
        return seatsAvailable;
    }

    static int getTotalBooked()
    {
        return totalBooked;
    }
}

public class Cinema
{
    public static void main(String[] args)
    {
        CinemaShow c1 = new CinemaShow("Avengers", 50);
        CinemaShow c2 = new CinemaShow("Inception");

        System.out.println(c1.book(20));
        System.out.println(c1.getSeatsAvailable());

        System.out.println(c1.book(25));
        System.out.println(c1.getSeatsAvailable());

        System.out.println(c1.book(10));
        System.out.println(c1.getSeatsAvailable());

        c1.cancel(10);
        System.out.println(c1.getSeatsAvailable());

        System.out.println(c1.book(15));
        System.out.println(c1.getSeatsAvailable());

        System.out.println(c1.book(20));
        System.out.println(c1.getSeatsAvailable());

        System.out.println(CinemaShow.getTotalBooked());
        System.out.println("25CE052-Mann");
    }
}
