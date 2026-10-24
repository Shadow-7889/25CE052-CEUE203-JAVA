import java.util.*;
public class Leaderboard {
    public static void main(String[] args) {

        Map<String, Integer> scores = new HashMap<>();

        scores.put("Rahul", 85);
        scores.put("Amit", 95);
        scores.put("Priya", 90);
        scores.put("Mann", 95);
        scores.put("Neha", 75);

        int N = 3; 

        List<String> players = new ArrayList<>(scores.keySet());

        Collections.sort(players, new Comparator<String>() {
            public int compare(String p1, String p2) {
                int result = scores.get(p2) - scores.get(p1);

                if (result == 0) {
                    return p1.compareTo(p2);
                }

                return result;
            }
        });

        System.out.println("Top " + N + " Players:");

        for (int i = 0; i < Math.min(N, players.size()); i++) {
            String name = players.get(i);
            System.out.println(name + " : " + scores.get(name));
        }

        System.out.println("25CE052-Mann");
    }
}
