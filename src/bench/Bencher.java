package bench;

import java.time.*;

public class Bencher {
    public static BenchResult bench(Runnable toRun) {
        Instant now = Instant.now();
        toRun.run();
        return new BenchResult(now.until(Instant.now()), toRun);
    }
}
