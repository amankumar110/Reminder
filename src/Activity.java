import java.time.LocalTime;

public class Activity {
    private String description;
    private LocalTime pause;

    Activity(String description, LocalTime pause) {

        this.description = description;
        this.pause = pause;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getPause() {
        return pause;
    }

    public void setPause(LocalTime pause) {
        this.pause = pause;
    }



}
