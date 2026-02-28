package bench;

import java.time.Duration;

public class BenchResult {
    private Duration duration;
    private Object source;

    public BenchResult(Duration duration, Object source) {
        this.duration = duration;
        this.source = source;
    }

    @Override
    public String toString() {
        return String.format("Bench of %s completed in %s", source.toString(), duration.toString());
    }
}
