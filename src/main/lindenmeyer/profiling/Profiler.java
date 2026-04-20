package lindenmeyer.profiling;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SequencedMap;
import java.util.TreeMap;

public class Profiler {

    private Instant start;
    private SequencedMap<Instant, String> laps;

    public Profiler() {
        start = Instant.now();
        laps = new TreeMap<>((a, b) -> {
            return a.compareTo(b);
        });
    }

    public void start() {
        start = Instant.now();
        laps.clear();
    }

    public void lap(String name) {
        laps.put(Instant.now(), name);
    }

    public List<Lap> getLaps() {
        ArrayList<Lap> res = new ArrayList<>();
        Instant tmp = start;

        for (Map.Entry<Instant, String> e : laps.sequencedEntrySet()) {
            res.add(new Lap(tmp, e.getKey(), e.getValue()));
            tmp = e.getKey();
        }

        return res;
    }
}
