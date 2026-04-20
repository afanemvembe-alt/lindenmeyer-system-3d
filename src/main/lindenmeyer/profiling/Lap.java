package lindenmeyer.profiling;

import java.time.Duration;
import java.time.Instant;

public class Lap {

    private Instant start;
    private Instant end;
    private String name;

    public Lap(Instant start, Instant end, String name) {
        this.start = start;
        this.end = end;
        this.name = name;
    }

    public Duration getDuration() {
        return Duration.between(start, end);
    }

    public String getName() {
        return name;
    }
    
    public Instant getStart() {
        return start;
    }
    
    public Instant getEnd() {
        return end;
    }
}
