package ca.uqam.mgl7460.tp1.implementations.traitements.definitions;

import ca.uqam.mgl7460.tp1.types.traitements.definitions.DefinitionTache;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.TraitementTache;

public class DefinitionTacheImpl implements DefinitionTache {
    private final String nom;
    private String description;
    private TraitementTache traitementTache;

    public DefinitionTacheImpl(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public DefinitionTacheImpl(String nom, String description, TraitementTache traitementTache) {
        this.nom = nom;
        this.description = description;
        this.traitementTache = traitementTache;
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
    public TraitementTache getTraitementTache() {
        return this.traitementTache;
    }

    @Override
    public void setTraitementTache(TraitementTache traitementTache) {
        this.traitementTache = traitementTache;
    }
}
