package ca.uqam.mgl7460.tp1.implementations.modeles;

import ca.uqam.mgl7460.tp1.types.modeles.Resultat;
import ca.uqam.mgl7460.tp1.types.modeles.ResultatTraitement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResultatTraitementImpl implements ResultatTraitement {
    private Resultat resultat;
    private final List<String> messages = new ArrayList<>();

    public ResultatTraitementImpl(Resultat resultat) {
        this.resultat = resultat;
    }

    @Override
    public Resultat getResultat() {
        return resultat;
    }

    @Override
    public void setResultat(Resultat resultat) {
        this.resultat = resultat;
    }

    @Override
    public Iterator<String> getMessages() {
        return this.messages.iterator();
    }

    @Override
    public void ajouteMessage(String message) {
        this.messages.add(message);
    }
}
