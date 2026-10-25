
class FileResource implements AutoCloseable {

    public FileResource() {
        System.out.println("Resource opened.");
    }

    public void use() {
        System.out.println("Resource is being used.");
    }

    @Override
    public void close() {
        System.out.println("Resource closed.");
    }
}

public class AutoCloseableDemo {
    public static void main(String[] args) {
        try (FileResource resource = new FileResource()) {
            resource.use();
            throw new RuntimeException("Original error occurred!");
        } catch (Exception e) {
            System.out.println("Caught: " + e.getMessage());
        }

        System.out.println("25CE052-Mann");
    }
}
