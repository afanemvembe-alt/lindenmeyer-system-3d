package lindenmeyer.symbols;

public class NoeudMot {

    private NoeudMot remplacement;
    private NoeudMot prochain;
    private Mot valeur;

    public NoeudMot getRemplacement() {
        return remplacement;
    }

    public void setRemplacement(NoeudMot remplacement) {
        this.remplacement = remplacement;
    }

    public NoeudMot getProchain() {
        return prochain;
    }

    public void setProchain(NoeudMot prochain) {
        this.prochain = prochain;
    }

    public Mot getValeur() {
        return valeur;
    }

    public void setValeur(Mot valeur) {
        this.valeur = valeur;
    }

    public NoeudMot(Mot valeur, NoeudMot remplacement, NoeudMot prochain) {
        this.valeur = valeur;
        this.remplacement = remplacement;
        this.prochain = prochain;
    }

    public NoeudMot(Mot valeur) {
        this(valeur, null, null);
    }
}
