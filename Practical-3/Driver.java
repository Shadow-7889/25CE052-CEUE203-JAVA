import java.util.Objects;

class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Point p = (Point) obj;
        return x == p.x && y == p.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

public class Driver {
    public static void main(String[] args) {
        Point[] points = {
            new Point(1, 2),
            new Point(3, 4),
            new Point(1, 2),
            new Point(5, 6),
            new Point(3, 4),
            new Point(7, 8)
        };

        int distinctCount = 0;

        for (int i = 0; i < points.length; i++) {
            boolean alreadyExists = false;

            for (int j = 0; j < i; j++) {
                if (points[i].equals(points[j])) {
                    alreadyExists = true;
                    break;
                }
            }

            if (!alreadyExists) {
                distinctCount++;
            }
        }

        System.out.println("Distinct: " + distinctCount);
        System.out.println("25CE052-Mann");
    }
}