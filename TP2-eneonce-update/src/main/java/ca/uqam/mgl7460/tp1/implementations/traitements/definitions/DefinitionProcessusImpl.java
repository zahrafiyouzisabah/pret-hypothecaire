package ca.uqam.mgl7460.tp1.implementations.traitements.definitions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import ca.uqam.mgl7460.tp1.types.modeles.DemandePret;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.ConditionTransition;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.DefinitionProcessus;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.DefinitionTache;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.DefinitionTransition;
import ca.uqam.mgl7460.tp1.types.traitements.instances.InstanceTache;
import ca.uqam.mgl7460.tp1.types.utils.Fabrique;

public class DefinitionProcessusImpl implements DefinitionProcessus {

    private Collection<DefinitionTache> taches;

    private DefinitionTache premiereTache;

    private HashMap<DefinitionTache,Collection<DefinitionTransition>> transitions;

    private String nom;

    private String description;

    public DefinitionProcessusImpl(String nom, String description) {
        this.nom = nom;
        this.description = description;
        this.taches = new ArrayList<>();
        this.transitions = new HashMap<>();
    }
    

    @Override
    public Iterator<DefinitionTache> getTaches() {
        return taches.iterator();
    }

    @Override
    public void ajouteTache(DefinitionTache definitionTache) {
        taches.add(definitionTache);
    }

    @Override
    public void ajouteTransition(DefinitionTransition definitionTransition) {
        // 1. D'abord, vérifier si on a déjà des transitions sortantes de la 
        //    meme source que l'argument. 
        
        Collection<DefinitionTransition> transitionsPourSource = transitions.get(definitionTransition.getTachesource());
        if (transitionsPourSource == null) {
            // Sinon, créer une collection vide
            transitionsPourSource = new ArrayList<>();

            // et l'insérer
            transitions.put(definitionTransition.getTachesource(),transitionsPourSource);
        }

        // 2. Ajouter la transition a la liste des transitions sortante
        transitionsPourSource.add(definitionTransition);
    }

    @Override
    public DefinitionTransition ajouteTransition(DefinitionTache tacheSource, DefinitionTache tacheDestination,
            ConditionTransition... conditions) {
        DefinitionTransition transition = null;

        // 1.   D'abord, si on ne spécifie pas de condition par défaut, on en définit une
        //      qui retourne "true" tout le temps
        ConditionTransition conditionTransition = null;
        if (conditions != null) {
            conditionTransition = conditions[0];
        } else {
            conditionTransition = (InstanceTache source, DemandePret demande) ->  true;
        }

        // 2.   Créer la transition et l'ajouter
        transition = Fabrique.getSingletonFabrique().creerDefinitionTransition(tacheSource,tacheDestination,conditionTransition);
        this.ajouteTransition(transition);
        
        // 3.   La retourner
        return transition;
    }

    @Override
    public Iterator<DefinitionTransition> getTransitionsSortantesDe(DefinitionTache tache) {
        Collection<DefinitionTransition> transitionsSortantes = transitions.get(tache);
        if (transitionsSortantes != null) return transitionsSortantes.iterator();
        return new ArrayList<DefinitionTransition>().iterator();
    }

    @Override
    public DefinitionTache getPremiereTache() {
        return premiereTache;
    }

    @Override
    public void setPremiereTache(DefinitionTache tache) {
        if (!taches.contains(tache)) ajoutePremiereTache(tache);
        else this.premiereTache = tache;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean isTacheFinale(DefinitionTache tache) {
        // je vérifie tout simplement que je n'ai pas de transition
        // qui sort de tache. Ça va règler le cas où tache ne fait pas
        // partie du processus.
        return ((transitions.get(tache) == null) || (transitions.get(tache).isEmpty()));
    }


    @Override
    public void ajoutePremiereTache(DefinitionTache tache) {
        if (!taches.contains(tache)) taches.add(tache);
        setPremiereTache(tache);
    }
    
}
