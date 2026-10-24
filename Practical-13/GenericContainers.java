class Box<T> {
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}

class Pair<A, B> {
    private A first;
    private B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }
}

public class GenericContainers {

    public static <T> T firstElement(T[] array) {
        return array[0];
    }

    public static void main(String[] args) {

        Box<String> b1 = new Box<>();
        b1.set("Hello Java");
        System.out.println("String Box: " + b1.get());

        Box<Integer> b2 = new Box<>();
        b2.set(100);
        System.out.println("Integer Box: " + b2.get());

        Pair<String, Integer> p1 =
                new Pair<>("Sairaj", 95);

        System.out.println("Pair: " + p1.getFirst() + ", " + p1.getSecond());

        Pair<Integer, Double> p2 = new Pair<>(10, 25.5);

        System.out.println("Pair: " + p2.getFirst() + ", " + p2.getSecond());

        String[] names = {"Amit", "Rahul", "Priya"};
        Integer[] numbers = {10, 20, 30};

        System.out.println("First name: " + firstElement(names));

        System.out.println("First number: " + firstElement(numbers));

        System.out.println("25CE052-Mann");
    }
}
