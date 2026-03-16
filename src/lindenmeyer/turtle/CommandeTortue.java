package lindenmeyer.turtle;

import java.util.List;
import lindenmeyer.turtle.Segment;

public interface CommandeTortue {
    void executer(Tortue tortue, List<Segment> segments);
}