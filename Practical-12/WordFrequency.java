import java.util.*;

public class WordFrequency {
    public static void main(String[] args) {

        String paragraph = "Java is easy. Java is powerful. Java is popular.";

        String[] words = paragraph.toLowerCase().replaceAll("[^a-z ]", "").split("\\s+");

        HashMap<String, Integer> map = new HashMap<>();

        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        List<String> list = new ArrayList<>(map.keySet());

        Collections.sort(list, new Comparator<String>() {
            public int compare(String w1, String w2) {
                return map.get(w2) - map.get(w1);
            }
        });

        System.out.println("Word Frequencies:");

        for (String word : list) {
            System.out.println(word + " : " + map.get(word));
        }

        System.out.println("25CE052-Mann");
    }
}
