package lindenmeyer.turtle;

import static javafx.scene.paint.Color.*;

// import static java.awt.Color.*;
// import java.awt.Color;
// import java.awt.color.ColorSpace;
import java.util.*;
import javafx.scene.paint.Color;

/**
 * Représente une source configurable de {@link Color}, associant des objects
 * quelconques à des couleurs. Il est possible de
 * fournir une liste de couleurs souhaitées. Lorsque ces couleurs seront
 * épuisées, des couleurs aléatoires seront
 * fournies.
 */
public class ColorFactory {

    /**
     * Liste suggérée de couleurs de base.
     */
    public static final Color[] BASE_COLORS = {
        BLACK,
        RED,
        GREEN,
        BLUE,
        YELLOW,
        MAGENTA,
        CYAN,
        ORANGE,
        PINK,
        GRAY,
    };

    // list of desired colors given by user
    private ArrayList<Color> colors;
    private HashMap<Object, Color> colorMap;
    private Random rng;
    private int i;

    /**
     * Construit une nouvelle instance avec les couleurs désirées.
     *
     * @param colors une liste de couleurs
     */
    public ColorFactory(List<Color> colors) {
        this.colors = new ArrayList<>(colors);
        this.rng = new Random();
        this.colorMap = new HashMap<>();
        this.i = 0;
    }

    /**
     * Construit une instance sans aucune couleur fournie, ne retournant donc que
     * des couleurs aléatoires.
     */
    public ColorFactory() {
        this(new ArrayList<>());
    }

    private Color randomColor() {
        float f = rng.nextFloat();

        // return new Color(ColorSpace.getInstance(ColorSpace.TYPE_HSV), new float[] {
        // f, 0.5f, 0.5f }, 1.0f);
        return hsb(f, 0.5, 0.5, 1);
    }

    private Color getNextColor() {
        if (colors != null && i < colors.size()) {
            Color res = colors.get(i);
            i = (i + 1) % colors.size();
            return res;
        } else {
            // return randomColor();
            return Color.PURPLE;
        }
    }

    /**
     * Retourne la couleur associée à l'object donné, et y associe une couleur si
     * cet objet n'en a pas déjà.
     *
     * @param o un objet
     * @return la couleur associée à l'objet
     */
    public Color getColor(Object o) {
        Color c = colorMap.get(o);

        if (c == null) {
            c = getNextColor();
            colorMap.put(o, c);
        }

        return c;
    }

    public void setColorOf(Object o, Color c) {
        colorMap.put(o, c);
    }
}
