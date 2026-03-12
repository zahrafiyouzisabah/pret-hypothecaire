package ca.uqam.mgl7460.tp1.implementations.traitements.definitions;

import ca.uqam.mgl7460.tp1.types.traitements.definitions.ConditionTransition;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.DefinitionProcessus;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.DefinitionTache;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.DefinitionTransition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefinitionProcessusImpl implements DefinitionProcessus {
    private final String nom;
    private String description;

    private DefinitionTache premiereTache;
    private final List<DefinitionTache> taches = new ArrayList<>();
    private final List<DefinitionTransition> transitions = new ArrayList<>();

    public DefinitionProcessusImpl(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    @Override
    public String getNom() {
        return this.nom;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Iterator<DefinitionTache> getTaches() {
        return this.taches.iterator();
    }

    @Override
    public void ajouteTache(DefinitionTache definitionTache) {
        this.taches.add(definitionTache);
    }

    @Override
    public void ajouteTransition(DefinitionTransition definitionTransition) {
        this.transitions.add(definitionTransition);
    }

    @Override
    public DefinitionTransition ajouteTransition(DefinitionTache tacheSource, DefinitionTache tacheDestination, ConditionTransition... conditions) {
        ConditionTransition conditionTransition = (conditions != null && conditions.length > 0)
                ? conditions[0]
                : (tache, demandePret) -> true;
        return new DefinitionTransitionImpl(tacheSource, tacheDestination, conditionTransition);
    }

    @Override
    public Iterator<DefinitionTransition> getTransitionsSortantesDe(DefinitionTache tache) {
        List<DefinitionTransition> transitionsSortantes = new ArrayList<>();
        for (DefinitionTransition transition : this.transitions) {
            if (transition.getTachesource().equals(tache)) {
                transitionsSortantes.add(transition);
            }
        }
        return transitionsSortantes.iterator();
    }

    @Override
    public DefinitionTache getPremiereTache() {
        return this.premiereTache;
    }

    @Override
    public void setPremiereTache(DefinitionTache tache) {
        this.premiereTache = tache;
    }

    @Override
    public void ajoutePremiereTache(DefinitionTache tache) {
        this.ajouteTache(tache);
        this.setPremiereTache(tache);
    }

    @Override
    public boolean isTacheFinale(DefinitionTache tache) {
        return !this.getTransitionsSortantesDe(tache).hasNext();
    }
}
