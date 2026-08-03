import java.util.Scanner;

enum Vehicle {
    CAR,
    BIKE,
    TRUCK
}

public class TollBooth {
    public static void main(String[] args) {

        int tollPrice = 100;
        int total = 0;

        int carCount = 0;
        int bikeCount = 0;
        int truckCount = 0;

        Scanner sc = new Scanner(System.in);

       while (total < tollPrice) {

    System.out.print("Enter Vehicle Type (CAR, BIKE, TRUCK): ");
    String input = sc.next().toUpperCase();

    if (!input.equals("CAR") && !input.equals("BIKE") && !input.equals("TRUCK")) {
        System.out.println("Invalid vehicle type!");
        continue;
    }

    Vehicle vehicle = Vehicle.valueOf(input);

    int value = switch (vehicle) {
        case CAR -> {
            carCount++;
            yield 50;
        }
        case BIKE -> {
            bikeCount++;
            yield 20;
        }
        case TRUCK -> {
            truckCount++;
            yield 150;
        }
    };

    total += value;

    System.out.println("Added Amount: " + value);
    System.out.println("Total Amount: " + total);

    if (total < tollPrice) {
        System.out.println("Remaining Amount: " + (tollPrice - total));
    }
}
        

        if (total > tollPrice) {
            System.out.println("Return Change: " + (total - tollPrice));
        } else {
            System.out.println("No Change.");
        }

        
        System.out.println("Total toll: " + total);

        String mostFrequent;

        if (carCount >= bikeCount && carCount >= truckCount) {
            mostFrequent = "CAR";
        } else if (bikeCount >= carCount && bikeCount >= truckCount) {
            mostFrequent = "BIKE";
        } else {
            mostFrequent = "TRUCK";
        }

        System.out.println("Most frequent: " + mostFrequent);

        System.out.println("Thank you! Gate Opened.");

        sc.close();
    }
}