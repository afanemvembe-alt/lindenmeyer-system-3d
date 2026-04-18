package lindenmeyer.turtle;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class ConfigTortue {
    private double pas; // remplace le "10"(la distance pour F)
    private double angleRotation; // remplace le "60"( l'angle pour + et -)
	private ColorFactory colorFactory;

    // Dictionnaire qui associe un caractère à une action
    private Map<Character, CommandeTortue> commandes = new HashMap<>();

    public ConfigTortue(double pas, double angleRotation) {
        this.pas = pas;
        this.angleRotation = angleRotation;
        this.colorFactory = new ColorFactory(Arrays.asList(ColorFactory.BASE_COLORS));
    }

    public ConfigTortue() {
        this(10, 60);
    }

    public void ajouterCommande(char symbole, CommandeTortue commande) {
        commandes.put(symbole, commande);
    }

    public CommandeTortue getCommande(char symbole) {
        return commandes.get(symbole);
    }

    // getters pour que la tortue puisse lire ces valeurs (elle appelle ces methodes
    // pour connaitre ses ordres)
    public double getPas() {
        return pas;
    }

    public double getAngleRotation() {
        return angleRotation;
    }
    
    public ColorFactory getColorFactory() {
		return colorFactory;
	}

	public void setColorFactory(ColorFactory colorFactory) {
		this.colorFactory = colorFactory;
	}


}
