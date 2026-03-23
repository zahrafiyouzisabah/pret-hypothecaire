package ca.uqam.mgl7460.tp1.implementations.modeles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import ca.uqam.mgl7460.tp1.types.modeles.Resultat;
import ca.uqam.mgl7460.tp1.types.modeles.ResultatTraitement;

public class ResultatTraitementImpl implements ResultatTraitement{


    private Resultat resultat;

    private Collection<String> messages;

    public ResultatTraitementImpl(Resultat resultat) {
        this.resultat = resultat; 
        this.messages = new ArrayList<>();       
    }


    @Override
    public Resultat getResultat() {
        return this.resultat;
    }

    @Override
    public void setResultat(Resultat resultat) {
        this.resultat = resultat;
    }

    @Override
    public Iterator<String> getMessages() {
        return messages.iterator();
    }

    @Override
    public void ajouteMessage(String message) {
        System.out.println(">>> MESSAGE ADDED: " + message);
        messages.add(message);
    }
    
}
