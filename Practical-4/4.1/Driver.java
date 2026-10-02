class PasswordChecker {

    public String strength(String pw) {

        int count = 0;

        boolean length = pw.length() >= 8;
        boolean upper = pw.matches(".*[A-Z].*");
        boolean digit = pw.matches(".*[0-9].*");
        boolean special = pw.matches(".*[^a-zA-Z0-9].*");

        if (length) count++;
        if (upper) count++;
        if (digit) count++;
        if (special) count++;

        if (count <= 1)
            return "Weak";
        else if (count <= 3)
            return "Medium";
        else
            return "Strong";
    }

    public void printRules(String pw) {

        System.out.println("Password: " + pw);

        if (pw.length() >= 8)
            System.out.println("Length >= 8");

        if (pw.matches(".*[A-Z].*"))
            System.out.println("Contains Uppercase");

        if (pw.matches(".*[0-9].*"))
            System.out.println("Contains Digit");

        if (pw.matches(".*[^a-zA-Z0-9].*"))
            System.out.println("Contains Special Character");

        System.out.println("Strength: " + strength(pw));
        System.out.println();
    }
}

public class Driver {
    public static void main(String[] args) {

        PasswordChecker pc = new PasswordChecker();

        String[] passwords = {
            "abc",
            "abcd1234",
            "Abcd1234",
            "Abcd1234!"
        };

        for (String pw : passwords) {
            pc.printRules(pw);
        }
        System.out.println("25CE052-Mann");
    }
}