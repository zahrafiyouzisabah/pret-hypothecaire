package ca.uqam.mgl7460.tp1.types.traitements.instances;

import ca.uqam.mgl7460.tp1.types.modeles.DemandePret;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.DefinitionTache;

/**
 * le type InstanceTache représente l'exécution d'une <code>DefinitionTache</code> faisant partie
 * d'une modèle de processus (<code>DefinitionProcessus</code>), sur une <code>DemandePret</code>,
 * dans le cadre d'un processus englobant (<code>InstanceProcessus</code>)
 */
public interface InstanceTache {

    /**
     * la définition de tâche dont cette instance est une exécution particulière.
     * 
     * C'est la définition de tâche qui me permet de "aller chercher la fonction
     * à exécuter" (voir méthode <code>executer</code> plus bas)
     * @return
     */
    public DefinitionTache getDefinitionTache();

    /**
     * retourne l'état d'exécution de la tache. C'est l'un de PRET, ENCOURS et TERMINE
     * @return
     */
    public EtatTraitement getEtatInstanceTache();

    /**
     * Modifie l'état de traitement de la tâche
     * @param etat
     */
    public void setEtatInstanceTache(EtatTraitement etat);

    /**
     * retourne le processus englobant, i.e. la <code>InstanceProcessus</code>
     * dont je fais partie (c-a-d dont je suis l'une des étapes)
     * @return
     */
    public InstanceProcessus getProcessusEnglobant();

    /**
     * retourne la demande de prêt sur laquelle je vais exécuter le traitement
     * @return
     */
    public DemandePret getDemandePret();

    /**
     * modifie la demande de pret sur laquelle je (vais) opérer
     * @param demande
     */
    public void setDemandePret(DemandePret demande);

    /**
     * Cette méthode exécute l'instance de tâche.
     * 
     * Elle va chercher le <code>TraitementTache</code> disponible dans sa
     * <code>definbitionTache</code> et l'exécuter.
     * 
     * En fonction du résultat (true ou false), elle modifie:
     * 1)   le <code>ResultatTraitement</code> de la demande de prêt
     * 2)   l'état de traitement de l'instance de tache elle même
     * 
     * Et elle va signaler au processus englobant qu'elle a fini, en appelant la méthode 
     * <code>void signalerFinTache(InstanceTache instanceTache) throws ExceptionDefinitionProcessus</code>
     * 
     * @throws ExceptionDefinitionProcessus
     */
    public void executer() throws ExceptionDefinitionProcessus;

}
