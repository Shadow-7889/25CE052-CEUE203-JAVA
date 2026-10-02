interface Notifier 
{
    void send(String message);
}

interface Urgent {}

class EmailSender implements Notifier {

    public void send(String message) {
        System.out.println("Email: " + message);
    }
}

class SmsSender implements Notifier, Urgent {

    public void send(String message) {
        System.out.println("SMS: " + message);
    }
}

public class NotifierDemo {

    public static void main(String[] args) {

        Notifier emailLambda = msg -> System.out.println("Email Lambda: " + msg);

        Notifier smsLambda = msg -> System.out.println("SMS Lambda: " + msg);

        Notifier[] senders = {
            new EmailSender(),
            new SmsSender(),
            emailLambda,
            smsLambda
};

        String message = "Server maintenance at 10 PM";

        System.out.println("Broadcasting Message:\n");

        for (Notifier sender : senders) {

            sender.send(message);

            if (sender instanceof Urgent) {
                sender.send(message);
            }
        }

        System.out.println("25CE052-Mann");
    }
}