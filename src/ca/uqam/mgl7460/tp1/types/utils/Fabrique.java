package ca.uqam.mgl7460.tp1.types.utils;

import ca.uqam.mgl7460.tp1.implementations.utils.ConcreteFactory;
import ca.uqam.mgl7460.tp1.types.modeles.Adresse;
import ca.uqam.mgl7460.tp1.types.modeles.DemandePret;
import ca.uqam.mgl7460.tp1.types.modeles.DemandeurPret;
import ca.uqam.mgl7460.tp1.types.modeles.Propriete;
import ca.uqam.mgl7460.tp1.types.modeles.Resultat;
import ca.uqam.mgl7460.tp1.types.modeles.ResultatTraitement;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.ConditionTransition;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.DefinitionProcessus;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.DefinitionTache;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.DefinitionTransition;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.TraitementTache;
import ca.uqam.mgl7460.tp1.types.traitements.instances.InstanceProcessus;
import ca.uqam.mgl7460.tp1.types.traitements.instances.InstanceTache;

public interface Fabrique {

    /**
     * Créer une propriété, en fournissant son adresse comme argument
     * @param adresse
     * @return
     */
    public Propriete creerPropriete(Adresse adresse);

    /*
     * creer une propriété en fournissant son adresse et sa valeur de marché
     */
    public Propriete creerPropriete(Adresse adresse, float valeurMarche);

    /**
     * Créer un-e demandeur-se de prêt en fournissant prenom, nom, et numéro d'assurance sociale (NAS)
     * @param prenom
     * @param nom
     * @param NAS
     * @return
     */
    public DemandeurPret creerDemandeurPret(String prenom, String nom, String NAS);

    /**
     * Créer un-e demandeur-se de prêt en founrissant prenom, nom, NAS, revenu annuel, obligations annuelles, et score de crédit
     * @param prenom
     * @param nom
     * @param NAS
     * @param revenuAnnuel
     * @param obligationsAnnuelles
     * @param scoreCredit
     * @return
     */
    public DemandeurPret creerDemandeurPret(String prenom, String nom, String NAS, float revenuAnnuel, float obligationsAnnuelles, int scoreCredit);

    /**
     * Créer une demande de prêt en fournissant, en argument, la propriété et le-la demandeur-se
     * @param propriete
     * @param demandeurPret
     * @return
     */
    public DemandePret creerDemandePret(Propriete propriete, DemandeurPret demandeurPret);

    /**
     * Créer une demande de prêt en fournissant la propriété, le-la demandeur-se de prêt, le prix d'achat, et 
     * la mise de fonds initiale de l'emprunteur-se.
     * @param propriete
     * @param demandeurPret
     * @param prixAchat
     * @param miseDeFonds
     * @return
     */
    public DemandePret creerDemandePret(Propriete propriete, DemandeurPret demandeurPret, float prixAchat, float miseDeFonds);

    /**
     * Créer un resultat de traitement en fournissant le resultat (une valeur
     * parmi NONDETERMINE, ACCEPTEE, REFUSEE)
     * @param resultat
     * @return
     */
    public ResultatTraitement creerResultatTraitement(Resultat resultat);

    /**
     * Créer une définition de tache, en fournissant le nom et la description de la tache
     * @param nom
     * @param description
     * @return
     */
    public DefinitionTache creerDefinitionTache(String nom, String description);

    /**
     * Créer une définition de tache, en fournissant le nom, la description de la tache, ET
     * la fonction à exécuter par la tâche (qui est représentée par une variable du type
     * TraitementTache qui est une 'functional interface')
     * @param nom
     * @param description
     * @param traitementTache
     * @return
     */
    public DefinitionTache creerDefinitionTache(String nom, String description, TraitementTache traitementTache);

    /**
     * Créer une définition (spécification) de transition entre deux (définitions de) tâche, en
     * fournissant: 1) la tacheSource, 2) la tacheDestination, et 3) la conditionTransition, qui est
     * une 'interface fonctionnelle'
     * @param tacheSource
     * @param tacheDestination
     * @param conditionTransition
     * @return
     */
    public DefinitionTransition creerDefinitionTransition(DefinitionTache tacheSource, DefinitionTache tacheDestination,
            ConditionTransition conditionTransition);

    /**
     * Créer une définition (spécification) de transition en entre deux définitions de tâches, en fournissant la tacheSource
     * et la sourceDestination. Ici, cela suppose que la condition de transition est toujours vraie
     * @param tacheSource
     * @param tacheDestination
     * @return
     */
    public DefinitionTransition creerDefinitionTransition(DefinitionTache tacheSource, DefinitionTache tacheDestination);

    /**
     * Crèer une coquille de définition de processus, en fournissant le nom et la description du processus
     * @param nomProcessus
     * @param descriptionProcessus
     * @return
     */
    public DefinitionProcessus creerDefinitionProcessus(String nomProcessus, String descriptionProcessus);

    /**
     * Créer une instance de processus (que l'on peut donc exécuter par la suite) en fournissant, a) la
     * definitionProcess à exécuter, et b) la demandePret sur laquelle exécuter le processus.
     * @param definitionProcessus
     * @param demandePret
     * @return
     */
    public InstanceProcessus creerInstanceProcessus(DefinitionProcessus definitionProcessus, DemandePret demandePret);

    /**
     * Créer une instance de tâche en fournissant l'instanceProcessus dont elle va faire partie, et la 
     * definitionTache dont elle sera une copie/instance
     * @param instanceProcessus
     * @param definitionTache
     * @return
     */
    public InstanceTache creerInstanceTache(InstanceProcessus instanceProcessus, DefinitionTache definitionTache);

    /**
     * Cette méthode retourne un singleton de la Fabrique. C'est un "trick" pas trop catholique (méthode statique concrête
     * dans un interface), mais ça permet aux classes d'implantation de faire appel au singleton de la factory sans
     * "connaitre" la classe d'implementation de la fabrique.
     * 
     * Quand vous aurez implémenté la classe d'implémentation de la Fabrique, vous mettrez son nom à la place de 
     * <code>FabriqueImpl</code>, qui est le nom que j'ai choisi.
     * @return
     */
    public static Fabrique getSingletonFabrique() {
        Fabrique singleton = null;
        singleton = ConcreteFactory.getSingleton();
        return singleton;
    }
}
