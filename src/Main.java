import java.time.LocalTime;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<Activity> activities = new LinkedList<>();
        final Boolean[] lock = {false}; // Lock variable

        // Runnable for inputting activities
        Runnable inputRunnable = () -> {
            synchronized (lock) {
                Input input = new Input();
                Activity activity = input.inputActivity();
                activities.add(activity);
                System.out.println("Reminder has been placed");
                lock[0] = true; // Signal that an activity is added
                lock.notify(); // Notify the reminder thread
            }
        };

        Runnable reminder = () -> {
            synchronized (lock) {
                try {
                    while (!lock[0]) {
                        lock.wait(); // Wait until an activity is added
                    }
                    while (true) {
                        // Retrieve the first activity
                        Activity activity = activities.getFirst();

                        // Check if it's time to trigger the alarm
                        while (LocalTime.now().isBefore(activity.getPause())) {
                            System.out.println("Waiting for activity...");
                            Thread.sleep(1000);
                        }

                        // Trigger the alarm
                        System.out.println("Alarm for: " + activity.getDescription());

                        // Remove the activity from the list
                        activities.removeFirst();

                        // If there are more activities, continue the loop
                        if (activities.isEmpty()) {
                            lock[0] = false; // Reset the lock if no more activities
                            break; // Exit the loop if no more activities
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread inputThread = new Thread(inputRunnable);
        Thread reminderThread = new Thread(reminder);

        inputThread.start();
        reminderThread.start();
    }
};