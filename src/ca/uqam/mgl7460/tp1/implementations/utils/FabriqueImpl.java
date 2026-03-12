package ca.uqam.mgl7460.tp1.implementations.utils;

import ca.uqam.mgl7460.tp1.implementations.modeles.DemandePretImpl;
import ca.uqam.mgl7460.tp1.implementations.modeles.DemandeurPretImpl;
import ca.uqam.mgl7460.tp1.implementations.modeles.ProprieteImpl;
import ca.uqam.mgl7460.tp1.implementations.modeles.ResultatTraitementImpl;
import ca.uqam.mgl7460.tp1.implementations.traitements.definitions.DefinitionTacheImpl;
import ca.uqam.mgl7460.tp1.types.modeles.*;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.*;
import ca.uqam.mgl7460.tp1.types.traitements.instances.InstanceProcessus;
import ca.uqam.mgl7460.tp1.types.traitements.instances.InstanceTache;
import ca.uqam.mgl7460.tp1.types.utils.Fabrique;

public class FabriqueImpl implements Fabrique {
    private static final Fabrique instance = new FabriqueImpl();

    // private constructor to avoid client applications using the constructor
    private FabriqueImpl(){}

    public static Fabrique getSingleton() {
        return instance;
    }

    @Override
    public Propriete creerPropriete(Adresse adresse) {
        return new ProprieteImpl(adresse);
    }

    @Override
    public Propriete creerPropriete(Adresse adresse, float valeurMarche) {
        return new ProprieteImpl(adresse, valeurMarche);
    }

    @Override
    public DemandeurPret creerDemandeurPret(String prenom, String nom, String NAS) {
        return new DemandeurPretImpl(prenom, nom, NAS);
    }

    @Override
    public DemandeurPret creerDemandeurPret(String prenom, String nom, String NAS, float revenuAnnuel, float obligationsAnnuelles, int scoreCredit) {
        return new DemandeurPretImpl(prenom, nom, NAS, revenuAnnuel, obligationsAnnuelles, scoreCredit);
    }

    @Override
    public DemandePret creerDemandePret(Propriete propriete, DemandeurPret demandeurPret) {
        return new DemandePretImpl(propriete, demandeurPret);
    }

    @Override
    public DemandePret creerDemandePret(Propriete propriete, DemandeurPret demandeurPret, float prixAchat, float miseDeFonds) {
        return new DemandePretImpl(propriete, demandeurPret, prixAchat, miseDeFonds);
    }

    @Override
    public ResultatTraitement creerResultatTraitement(Resultat resultat) {
        return new ResultatTraitementImpl(resultat);
    }

    @Override
    public DefinitionTache creerDefinitionTache(String nom, String description) {
        return new DefinitionTacheImpl(nom, description);
    }

    @Override
    public DefinitionTache creerDefinitionTache(String nom, String description, TraitementTache traitementTache) {
        return new DefinitionTacheImpl(nom, description, traitementTache);
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
