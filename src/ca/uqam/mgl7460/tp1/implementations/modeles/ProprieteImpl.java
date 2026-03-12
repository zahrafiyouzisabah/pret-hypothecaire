package ca.uqam.mgl7460.tp1.implementations.modeles;

import ca.uqam.mgl7460.tp1.types.modeles.Adresse;
import ca.uqam.mgl7460.tp1.types.modeles.Propriete;

public class ProprieteImpl implements Propriete {

    private final Adresse adresse;
    private float valeurDeMarche;

    public ProprieteImpl(Adresse adresse) {
        this.adresse = adresse;
    }

    public ProprieteImpl(Adresse adresse, float valeurDeMarche) {
        this.adresse = adresse;
        this.valeurDeMarche = valeurDeMarche;
    }

    @Override
    public Adresse getAdresse() {
        return this.adresse;
    }

    @Override
    public float getValeurDeMarche() {
        return this.valeurDeMarche;
    }

    @Override
    public void setValeurDeMarche(float valeur) {
        this.valeurDeMarche = valeur;
    }
}
