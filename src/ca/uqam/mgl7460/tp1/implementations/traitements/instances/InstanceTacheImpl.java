package ca.uqam.mgl7460.tp1.implementations.traitements.instances;

import ca.uqam.mgl7460.tp1.implementations.modeles.ResultatTraitementImpl;
import ca.uqam.mgl7460.tp1.types.modeles.DemandePret;
import ca.uqam.mgl7460.tp1.types.modeles.Resultat;
import ca.uqam.mgl7460.tp1.types.modeles.ResultatTraitement;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.DefinitionTache;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.TraitementTache;
import ca.uqam.mgl7460.tp1.types.traitements.instances.EtatTraitement;
import ca.uqam.mgl7460.tp1.types.traitements.instances.ExceptionDefinitionProcessus;
import ca.uqam.mgl7460.tp1.types.traitements.instances.InstanceProcessus;
import ca.uqam.mgl7460.tp1.types.traitements.instances.InstanceTache;

import java.util.Collections;

public class InstanceTacheImpl implements InstanceTache {
    private DemandePret demandePret;
    private final DefinitionTache definitionTache;
    private final InstanceProcessus processusEnglobant;
    private EtatTraitement etatInstanceTache;

    public InstanceTacheImpl(InstanceProcessus processusEnglobant, DefinitionTache definitionTache) {
        this.processusEnglobant = processusEnglobant;
        this.demandePret = processusEnglobant.getDemanderPret();
        this.definitionTache = definitionTache;
    }

    @Override
    public DefinitionTache getDefinitionTache() {
        return this.definitionTache;
    }

    @Override
    public EtatTraitement getEtatInstanceTache() {
        return this.etatInstanceTache;
    }

    @Override
    public void setEtatInstanceTache(EtatTraitement etat) {
        this.etatInstanceTache = etat;
    }

    @Override
    public InstanceProcessus getProcessusEnglobant() {
        return this.processusEnglobant;
    }

    @Override
    public DemandePret getDemandePret() {
        return this.demandePret;
    }

    @Override
    public void setDemandePret(DemandePret demande) {
        this.demandePret = demande;
    }

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
    @Override
    public void executer() throws ExceptionDefinitionProcessus {
        TraitementTache traitementTache = this.definitionTache.getTraitementTache();
        if (traitementTache == null){
            throw new ExceptionDefinitionProcessus(this.getProcessusEnglobant(), Collections.singletonList(this.getDefinitionTache()), "La tache n'a pas de traitement.");
        }
        boolean resultat = traitementTache.traiteDemandePret(this.getDemandePret(), this.getProcessusEnglobant().getLogger());

        ResultatTraitement resultatTraitement = this.demandePret.getResultatTraitement();

        if (resultatTraitement == null) {
            resultatTraitement = new ResultatTraitementImpl(resultat ? Resultat.ACCEPTEE : Resultat.REFUSEE);
            this.demandePret.setResultatTraitement(resultatTraitement);
        }

        if (!resultat) {
            resultatTraitement.ajouteMessage("La tâche " + this.getDefinitionTache().getNom() + " a échoué");
            resultatTraitement.setResultat(Resultat.REFUSEE);
        }

        this.demandePret.getResultatTraitement();
        this.setEtatInstanceTache(EtatTraitement.TERMINE);
        this.getProcessusEnglobant().signalerFinTache(this);
    }
}
