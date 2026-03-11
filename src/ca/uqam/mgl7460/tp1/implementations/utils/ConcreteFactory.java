package ca.uqam.mgl7460.tp1.implementations.utils;

import ca.uqam.mgl7460.tp1.types.modeles.*;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.*;
import ca.uqam.mgl7460.tp1.types.traitements.instances.InstanceProcessus;
import ca.uqam.mgl7460.tp1.types.traitements.instances.InstanceTache;
import ca.uqam.mgl7460.tp1.types.utils.Fabrique;

public class ConcreteFactory implements Fabrique {
    private static final Fabrique instance = new ConcreteFactory();

    // private constructor to avoid client applications using the constructor
    private ConcreteFactory(){}

    public static Fabrique getSingleton() {
        return instance;
    }

    @Override
    public Propriete creerPropriete(Adresse adresse) {
        return null;
    }

    @Override
    public Propriete creerPropriete(Adresse adresse, float valeurMarche) {
        return null;
    }

    @Override
    public DemandeurPret creerDemandeurPret(String prenom, String nom, String NAS) {
        return null;
    }

    @Override
    public DemandeurPret creerDemandeurPret(String prenom, String nom, String NAS, float revenuAnnuel, float obligationsAnnuelles, int scoreCredit) {
        return null;
    }

    @Override
    public DemandePret creerDemandePret(Propriete propriete, DemandeurPret demandeurPret) {
        return null;
    }

    @Override
    public DemandePret creerDemandePret(Propriete propriete, DemandeurPret demandeurPret, float prixAchat, float miseDeFonds) {
        return null;
    }

    @Override
    public ResultatTraitement creerResultatTraitement(Resultat resultat) {
        return null;
    }

    @Override
    public DefinitionTache creerDefinitionTache(String nom, String description) {
        return null;
    }

    @Override
    public DefinitionTache creerDefinitionTache(String nom, String description, TraitementTache traitementTache) {
        return null;
    }

    @Override
    public DefinitionTransition creerDefinitionTransition(DefinitionTache tacheSource, DefinitionTache tacheDestination, ConditionTransition conditionTransition) {
        return null;
    }

    @Override
    public DefinitionTransition creerDefinitionTransition(DefinitionTache tacheSource, DefinitionTache tacheDestination) {
        return null;
    }

    @Override
    public DefinitionProcessus creerDefinitionProcessus(String nomProcessus, String descriptionProcessus) {
        return null;
    }

    @Override
    public InstanceProcessus creerInstanceProcessus(DefinitionProcessus definitionProcessus, DemandePret demandePret) {
        return null;
    }

    @Override
    public InstanceTache creerInstanceTache(InstanceProcessus instanceProcessus, DefinitionTache definitionTache) {
        return null;
    }
}
