import java.time.LocalTime;
import java.util.Scanner;

public class Input {

    Scanner in = new Scanner(System.in);
    public Activity inputActivity() {
        System.out.println("Enter the activity descrition");
        String description = in.nextLine();
        Integer secs = null;

        try {
            System.out.println("Enter the delay (seconds) in the reminder :");
            secs = Integer.parseInt(in.next());

        } catch(NumberFormatException e) {
            System.out.println("Invalid delay");
        }

        LocalTime pause = LocalTime.now().plusSeconds(secs);

        return new Activity(description,pause);


    }
}
