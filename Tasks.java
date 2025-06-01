import java.time.LocalDate;

public class Tasks {

    private String task;
    private LocalDate date;

    Tasks(String newTask, LocalDate newDate) {
        task = newTask;  
        date = newDate;      
    }

    Tasks(String newTask) {
        task = newTask;
    }

    public String getTask() {
        return task;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        if (date != null) {
            return task + " - " + date;
        }
        else {
            return task + " - " + "No due date.";
        }
    }
}