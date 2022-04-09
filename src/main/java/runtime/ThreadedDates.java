package runtime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ThreadedDates implements Runnable{
    private DateTimeFormatter format;
    private String hash;
    private LocalDate startPoint;

    public ThreadedDates(DateTimeFormatter format, String hash, LocalDate startPoint) {
        if (format == null || hash == null || startPoint == null) {
            throw new IllegalArgumentException();
        }
        this.format = format;
        this.hash = hash;
        this.startPoint = startPoint;
    }

    @Override
    public void run() {

    }
}
