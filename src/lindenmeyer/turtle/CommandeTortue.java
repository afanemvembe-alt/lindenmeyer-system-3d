package lindenmeyer.turtle;

import java.util.List;
import lindenmeyer.lsystem.Segment;

public interface CommandeTortue {
    void executer(Tortue tortue, List<Segment> segments);
}