package ca.uqam.mgl7460.tp1.types.traitements.definitions;

/**
 * cette interface représente les définitions de transitions.
 * 
 * Une transition est définie par:
 * 1) une <code>tacheSource</code>
 * 2) une <code>tacheDestination</code>, et
 * 3) une <code>conditionTransition</code>, qui est une 
 * fonction booleenne que l'on exécute pour savoir si
 * on passer de <code>tacheSource</code> à 
 * <code>tacheDestination</code> ou non.
 */
public interface DefinitionTransition {

    /**
     * retourne la tache source
     * @return
     */
    public DefinitionTache getTachesource();

    /**
     * retourne la tache destination
     * @return
     */
    public DefinitionTache getTacheDestination();

    /**
     * retourne la condition de transition
     * @return
     */
    public ConditionTransition getConditionTransition();

}
