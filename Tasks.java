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

    Tasks(LocalDate newDate) {
        date = newDate;
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

    public static Tasks fromString(String str) {
        String[] parts = str.split(" - ", 2);  // splits into [task, date or 'No due date.']
        
        if (parts.length < 2) {
            // The string doesn't have the expected format,
            // maybe just return a Task with task only (no date)
            return new Tasks(parts[0]);
        }
        
        String taskPart = parts[0];
        String datePart = parts[1];
        
        if (datePart.equals("No due date.")) {
            return new Tasks(taskPart);  // Use constructor without date
        } else {
            return new Tasks(taskPart, LocalDate.parse(datePart));  // Constructor with date
        }
    }
}