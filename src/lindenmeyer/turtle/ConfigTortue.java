package lindenmeyer.turtle;

public class ConfigTortue {
    private double pas; // remplace le "10"(la distance pour F)
    private double angleRotation; // remplace le "60"( l'angle pour + et -)

    public ConfigTortue(double pas , double angleRotation) {
        this.pas = pas ;
        this.angleRotation = angleRotation;
    }

    // getters pour que la tortue puisse lire ces valeurs (elle appelle ces methodes pour connaitre ses ordres)
    public double getPas() {return pas ; }
    public double getAngleRotation(){ return angleRotation; }

}
