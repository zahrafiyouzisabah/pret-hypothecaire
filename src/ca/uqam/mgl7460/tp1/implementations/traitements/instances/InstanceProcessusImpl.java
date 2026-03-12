package ca.uqam.mgl7460.tp1.implementations.traitements.instances;

import ca.uqam.mgl7460.tp1.types.modeles.DemandePret;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.DefinitionProcessus;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.DefinitionTache;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.DefinitionTransition;
import ca.uqam.mgl7460.tp1.types.traitements.instances.*;
import ca.uqam.mgl7460.tp1.types.utils.Fabrique;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class InstanceProcessusImpl implements InstanceProcessus {
    private final DefinitionProcessus definitionProcessus;
    private final DemandePret demandePret;

    private final List<InstanceTache> taches = new ArrayList<>();
    private InstanceTache tacheCourant;
    private EtatProcessus etatProcessus;

    private Instant tempsDemarrage;
    private Instant tempsArret;

    private Logger logger;

    public InstanceProcessusImpl(DefinitionProcessus definitionProcessus, DemandePret demandePret) {
        this.definitionProcessus = definitionProcessus;
        this.demandePret = demandePret;
    }

    @Override
    public DemandePret getDemanderPret() {
        return this.demandePret;
    }

    @Override
    public DefinitionProcessus getDefinitionProcessus() {
        return this.definitionProcessus;
    }

    @Override
    public EtatProcessus getEtatProcessus() {
        return this.etatProcessus;
    }

    @Override
    public void setEtatProcessus(EtatProcessus etatProcessus) {
        this.etatProcessus = etatProcessus;
    }

    @Override
    public InstanceTache getTacheCourante() {
        return this.tacheCourant;
    }

    @Override
    public void setTacheCourante(InstanceTache tacheCourante) {
        this.tacheCourant = tacheCourante;
    }

    /**
     * cette méthode va instancier la première tâche de la <code>definitionProcessus</code>
     * et la lancer (l'exécuter). Quand la première instance de tache aura fini, elle appelera
     * la méthode <code>void signalerFinTache(InstanceTache instanceTache) throws ExceptionDefinitionProcessus</code>
     * sur le processus. Voir suite de l'explication dans la documentation de l'entête de l'interface
     * @throws ExceptionDefinitionProcessus
     */
    @Override
    public void demarrer() throws ExceptionDefinitionProcessus {
        DefinitionTache premiereTache = this.getDefinitionProcessus().getPremiereTache();
        if (premiereTache == null){
            throw new ExceptionDefinitionProcessus(this, Collections.emptyList(), "Processus sans tâche initiale");
        }

        InstanceTache premiereInstanceTache = Fabrique.getSingletonFabrique().creerInstanceTache(this,premiereTache);
        this.setTempsDemarrage(Instant.now());
        ajouterEtLancerTache(premiereInstanceTache);
    }

    private void ajouterEtLancerTache(InstanceTache tache) throws ExceptionDefinitionProcessus {
        this.setEtatProcessus(new EtatProcessus(tache, EtatTraitement.PRET));
        this.taches.add(tache);
        this.setTacheCourante(tache);
        tache.executer();
        this.setEtatProcessus(new EtatProcessus(tache, EtatTraitement.ENCOURS));
    }

    @Override
    public Instant getTempsDemarrage() {
        return this.tempsDemarrage;
    }

    @Override
    public void setTempsDemarrage(Instant temps) {
        this.tempsDemarrage = temps;
    }

    @Override
    public Instant getTempsArret() {
        return this.tempsArret;
    }

    @Override
    public void setTempsArret(Instant temps) {
        this.tempsArret = temps;
    }

    /**
     * une méthode appelée par les <code>InstanceTache</code> quand elles font
     * fini d'exécuter. Le récepteur (instance de processus) va alors consulter la
     * définition (le modèle) de processus qu'il est entrain d'exécuter pour identifier
     * la prochaine tâche à exécuter. Si la méthode n'en trouve pas, cela marquera la
     * fin de l'exécution du processus.
     *
     * Si on en trouve plusieurs, problème! le processus est mal formé, ce qui va enclencher
     * l'exception <code>ExceptionDefinitionProcessus</code>
     * @param instanceTache
     * @throws ExceptionDefinitionProcessus
     */
    @Override
    public void signalerFinTache(InstanceTache instanceTache) throws ExceptionDefinitionProcessus {
        // If task is the same as the current task
        if (!instanceTache.equals(this.getTacheCourante())){
            throw new ExceptionDefinitionProcessus(this, List.of(this.getTacheCourante().getDefinitionTache(), instanceTache.getDefinitionTache()), "La tâche signalant la fin n'est pas la tâche courante");
        }
        // If task is finished
        if (instanceTache.getEtatInstanceTache() != EtatTraitement.TERMINE){
            throw new ExceptionDefinitionProcessus(this, Collections.singletonList(instanceTache.getDefinitionTache()), "La tâche signalant la fin n'est pas terminée");
        }
        // Processing the next tasks
        Iterator<DefinitionTransition> outgoingTransitions = this.getDefinitionProcessus().getTransitionsSortantesDe(instanceTache.getDefinitionTache());

        if (!outgoingTransitions.hasNext()){
            this.setTempsArret(Instant.now());
            this.setTacheCourante(null);
            this.setEtatProcessus(new EtatProcessus(null, EtatTraitement.TERMINE));
        }else {
            DefinitionTransition nextTransition = outgoingTransitions.next();
            if (outgoingTransitions.hasNext()) throw new ExceptionDefinitionProcessus(this, Collections.singletonList(instanceTache.getDefinitionTache()), "La tâche signalant la fin a plus d'une transition sortante");
            InstanceTache prochaineInstanceTache = Fabrique.getSingletonFabrique().creerInstanceTache(this, nextTransition.getTacheDestination());
            this.ajouterEtLancerTache(prochaineInstanceTache);
        }
    }

    @Override
    public Logger getLogger() {
        return this.logger;
    }

    @Override
    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
