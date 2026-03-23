package ca.uqam.mgl7460.tp1.implementations.modeles;

import ca.uqam.mgl7460.tp1.types.modeles.Adresse;
import ca.uqam.mgl7460.tp1.types.modeles.Propriete;

public class ProprieteImpl implements Propriete{

    private Adresse adresse;

    private float valeurDeMarche;

    public ProprieteImpl(Adresse adresse){
        this.adresse = adresse;
    }

    public ProprieteImpl(Adresse adresse, float valeur){
        this(adresse);
        valeurDeMarche = valeur;
    }

    @Override
    public Adresse getAdresse() {
       return adresse;
    }

    @Override
    public float getValeurDeMarche() {
        return valeurDeMarche;
    }

    @Override
    public void setValeurDeMarche(float valeur) {
        valeurDeMarche = valeur;
    }  
    
    public String toString() {
        StringBuilder builder = new StringBuilder("PROPRIETE[Située à: " + adresse);
        builder.append(", Valeur marché : "+valeurDeMarche + "]");
        return builder.toString();
    }
}
