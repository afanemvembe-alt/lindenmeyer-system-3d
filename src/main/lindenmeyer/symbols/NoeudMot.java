package lindenmeyer.symbols;

public class NoeudMot {

    private Mot value;
    private NoeudMot premierEnfant;
    private NoeudMot prochaineFratrie;

    public Mot getValue() {
        return value;
    }

    public NoeudMot getPremierEnfant() {
        return premierEnfant;
    }

    public NoeudMot(
        Mot value,
        NoeudMot premierEnfant,
        NoeudMot prochaineFratrie
    ) {
        this.value = value;
        this.premierEnfant = premierEnfant;
        this.prochaineFratrie = prochaineFratrie;
    }

    public NoeudMot(Mot value) {
        this(value, null, null);
    }

    /**
     * Modifie la mot contenu dans ce noeud.
     * @param value un mot
     */
    public void setValue(Mot value) {
        this.value = value;
    }

    /**
     * Remplace le premier enfant de l'arbre avec celui donnée en entrée.
     * @param premierEnfant un arbre de mots
     */
    public void setPremierEnfant(NoeudMot premierEnfant) {
        this.premierEnfant = premierEnfant;
    }

    /**
     * Remplace la prochaine fratrie avec celle fournie en entrée.
     * @param prochaineFratrie un arbre de mots
     */
    public void setProchaineFratrie(NoeudMot prochaineFratrie) {
        this.prochaineFratrie = prochaineFratrie;
    }

    /**
     * Ajoute un arbre à la fin de la fratrie actuelle.
     * @param prochaineFratrie un arbre de mots
     */
    public void addFratrie(NoeudMot prochaineFratrie) {
        NoeudMot tmp = this;

        while (tmp.getProchaineFratrie() != null) {
            tmp = tmp.getProchaineFratrie();
        }

        tmp.setProchaineFratrie(prochaineFratrie);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof NoeudMot) {
            NoeudMot a = (NoeudMot) obj;

            return (
                value.equals(a.getValue()) && premierEnfant != null
                    ? premierEnfant.equals(a.getPremierEnfant())
                    : premierEnfant == a.getPremierEnfant() &&
                      prochaineFratrie.equals(a.getProchaineFratrie())
            );
        }

        return false;
    }

    public NoeudMot getProchaineFratrie() {
        return prochaineFratrie;
    }
}
