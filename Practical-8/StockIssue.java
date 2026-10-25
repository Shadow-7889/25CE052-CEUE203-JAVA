
import java.util.*;

class OutOfStockException extends Exception {
    private int shortfall;

    public OutOfStockException(int shortfall) {
        super("Insufficient stock. Shortfall: " + shortfall);
        this.shortfall = shortfall;
    }

    public int getShortfall() {
        return shortfall;
    }
}

class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String message) {
        super(message);
    }
}

class Warehouse {
    private Map<String, Integer> stock = new HashMap<>();

    public Warehouse() {
        stock.put("Pen", 10);
        stock.put("Book", 5);
        stock.put("Pencil", 8);
    }

    public void issue(String item, int qty)
            throws OutOfStockException, InvalidQuantityException {

        if (qty <= 0) {
            throw new InvalidQuantityException(
                "Quantity must be greater than zero.");
        }

        int available = stock.getOrDefault(item, 0);

        if (qty > available) {
            throw new OutOfStockException(qty - available);
        }

        stock.put(item, available - qty);
        System.out.println("Issued " + qty + " " + item);
    }
}

public class StockIssue {
    public static void main(String[] args) {
        Warehouse w = new Warehouse();

        String[][] requests = {
            {"Pen", "3"},
            {"Book", "8"},
            {"Pencil", "0"},
            {"Pen", "4"},
            {"Eraser", "2"}
        };

        for (String[] request : requests) {
            try {
                String item = request[0];
                int qty = Integer.parseInt(request[1]);
                w.issue(item, qty);

            } catch (OutOfStockException e) {
                System.out.println("Out of stock: "
                    + e.getMessage());

            } catch (InvalidQuantityException e) {
                System.out.println("Invalid quantity: "
                    + e.getMessage());

            } catch (NumberFormatException e) {
                System.out.println("Invalid number format.");

            } finally {
                System.out.println("Request processed.\n");
            }
        }

        System.out.println("All requests completed.");

        System.out.println("25CE052-Mann");
    }
}
