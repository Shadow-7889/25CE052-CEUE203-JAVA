import java.util.Scanner;

class ChatFilter {

    public void filterLogs(String[] logs, String keyword) {

        int count = 0;
        StringBuilder report = new StringBuilder();

        for (String line : logs) {

            String[] parts = line.split(" ", 3);

            if (parts.length < 3) {
                continue;
            }

            String time = parts[0];
            String user = parts[1];
            String message = parts[2];

            if (message.toLowerCase().contains(keyword.toLowerCase())) {
                count++;
                report.append(time)
                      .append(" ")
                      .append(user)
                      .append(": ")
                      .append(message)
                      .append("\n");
            }
        }
        System.out.println("Matches: " + count);
        System.out.println(report);
    }
}

public class Driver {
    public static void main(String[] args) {

        String[] logs = {
            "10:05 alice Hello there",
            "10:10 bob Good morning",
            "InvalidLine",                  
            "10:15 charlie How are you"
        };

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter keyword: ");
        String keyword = sc.nextLine();

        ChatFilter cf = new ChatFilter();
        cf.filterLogs(logs, keyword);

        sc.close();
        System.out.println("25CE052-Mann");
    }
}