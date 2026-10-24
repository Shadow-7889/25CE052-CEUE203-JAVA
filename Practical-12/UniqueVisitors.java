import java.util.*;
public class UniqueVisitors {
    public static void main(String[] args) {

        List<String> logins = Arrays.asList(
            "Sairaj", "Rahul", "Amit", "Sairaj",
            "Priya", "Rahul", "Neha", "Amit"
        );

        HashSet<String> uniqueUsers = new HashSet<>(logins);

        System.out.println("Unique visitors count: " + uniqueUsers.size());

        LinkedHashSet<String> orderedUsers = new LinkedHashSet<>(logins);

        System.out.println("First three distinct names:");

        int count = 0;

        for (String name : orderedUsers) {
            if (count == 3) {
                break;
            }

            System.out.println(name);
            count++;
        }
        System.out.println("25CE052-Mann");
    }
}
