interface Switchable {

    void on();
    void off();

    default void toggle() {
        System.out.println("Toggling device...");
        on();
        off();
    }
}

class Fan implements Switchable {

    public void on() {
        System.out.println("Fan ON");
    }

    public void off() {
        System.out.println("Fan OFF");
    }
}

class Light implements Switchable {

    public void on() {
        System.out.println("Light ON");
    }

    public void off() {
        System.out.println("Light OFF");
    }
}

interface SwitchPolicy {
    boolean maySwitchOn(Switchable device, int hour);
}

public class SwitchDemo {

    public static void main(String[] args) {

        Switchable[] devices = {
            new Fan(),
            new Light()
        };

        System.out.println("=== Toggle Devices ===");

        for (Switchable device : devices) {
            device.toggle();
            System.out.println();
        }

        SwitchPolicy officePolicy = new SwitchPolicy() {

            public boolean maySwitchOn(
                    Switchable device, int hour) {

                return hour >= 9 && hour <= 18;
            }
        };

        SwitchPolicy nightPolicy =
                (device, hour) -> hour >= 18;

        System.out.println("=== Policies ===");

        System.out.println("Office Policy at 10 AM: "+ officePolicy.maySwitchOn(devices[0], 10));

        System.out.println("Office Policy at 8 PM: "+ officePolicy.maySwitchOn(devices[0], 20));

        System.out.println("Night Policy at 8 PM: "+ nightPolicy.maySwitchOn(devices[1], 20));

        System.out.println("Night Policy at 9 AM: "+ nightPolicy.maySwitchOn(devices[1], 9));

        System.out.println("25CE052-Mann");
    }
}