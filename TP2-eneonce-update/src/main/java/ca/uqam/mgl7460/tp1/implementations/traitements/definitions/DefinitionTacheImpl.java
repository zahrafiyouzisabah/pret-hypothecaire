package ca.uqam.mgl7460.tp1.implementations.traitements.definitions;

import ca.uqam.mgl7460.tp1.types.traitements.definitions.DefinitionTache;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.TraitementTache;

public class DefinitionTacheImpl implements DefinitionTache {

    private String nom;

    private String description;

    private TraitementTache traitementTache;

    private String nomFichierRegles;

    public DefinitionTacheImpl(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public DefinitionTacheImpl(String nom, String description, TraitementTache traitementTache){
        this(nom, description);
        this.traitementTache = traitementTache;
    }

    public DefinitionTacheImpl(String nom, String description, String nomFichier){
        this(nom, description);
        this.nomFichierRegles = nomFichier;
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
    public TraitementTache getTraitementTache() {
        return traitementTache;
    }

    @Override
    public void setTraitementTache(TraitementTache traitementTache) {
        this.traitementTache = traitementTache;
    }

    @Override
    public String getNomFichierRegles() {
        return nomFichierRegles;
    }

    @Override
    public void setNomFichierRegles(String nomFichierDrools) {
        this.nomFichierRegles = nomFichierDrools;
    }
    
}
