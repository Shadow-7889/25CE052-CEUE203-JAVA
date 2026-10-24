import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;

public class DirectoryReport {
    public static void main(String[] args) {
        Path root = Paths.get("testfolder");
        Path report = Paths.get("report.txt");

        int[] count = { 0 };
        long[] totalSize = { 0 };

        try {
            Files.walkFileTree(root, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(
                        Path file, BasicFileAttributes attrs)
                        throws IOException {

                    String name = file.getFileName().toString();
                    long size = attrs.size();
                    Instant modified = attrs.lastModifiedTime().toInstant();

                    String line = name + " | " + size + " bytes | " + modified;

                    Files.writeString(report, line + System.lineSeparator(), StandardOpenOption.CREATE,
                            StandardOpenOption.APPEND);

                    count[0]++;
                    totalSize[0] += size;

                    System.out.println(line);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(
                        Path file, IOException exc) {
                    System.out.println("Cannot access: " + file);
                    return FileVisitResult.CONTINUE;
                }
            });

            Files.writeString(report, "\nTotal files: " + count[0] + "\nTotal size: " + totalSize[0] + " bytes\n",
                    StandardOpenOption.APPEND);

            System.out.println("\nTotal files: " + count[0]);
            System.out.println("Total size: " + totalSize[0] + " bytes");
            System.out.println("Report saved to: " + report.toAbsolutePath());

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("25CE052-Mann");
    }
}
