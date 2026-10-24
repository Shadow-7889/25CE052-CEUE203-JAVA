import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class LogAnalyzer {
    public static void main(String[] args) throws IOException {

        String[] names = { "log1.txt", "log2.txt", "log3.txt" };

        String[][] content = {
                { "INFO: Started", "ERROR: Failed", "INFO: Running" },
                { "WARNING: Low memory", "ERROR: Timeout" },
                { "INFO: Completed", "ERROR: Disk full" }
        };

        for (int i = 0; i < names.length; i++) {
            Files.write(Path.of(names[i]), List.of(content[i]));
        }

        String keyword = "ERROR";
        int totalLines = 0;
        int matchingLines = 0;

        for (String name : names) {
            Path path = Path.of(name);

            try (var reader = Files.newBufferedReader(path)) {
                String line;

                while ((line = reader.readLine()) != null) {
                    totalLines++;

                    if (line.contains(keyword)) {
                        matchingLines++;
                    }
                }
            }

            BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);

            System.out.println(name + " size: "+ attr.size() + " bytes");
        }

        System.out.println("Total lines: " + totalLines);
        System.out.println("Lines containing " + keyword + ": "+ matchingLines);

        System.out.println("25CE052-Mann");
    }
}
