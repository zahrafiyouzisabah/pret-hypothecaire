package ca.uqam.mgl7460.tp1.types.traitements.definitions;

/**
 * ce type représente la définition de tâche. Seul le nom ne doit pas 
 * être modifié après création. La description et le traitement à réaliser
 * par la tâche sont modifiables
 */
public interface DefinitionTache {

    /**
     * le nom de la tache
     * @return
     */
    public String getNom();

    /**
     * sa description par une phrase (ou deux) qui explique(nt) ce qu'elle fait
     * @return
     */
    public String getDescription();

    /**
     * modifie la description
     * @param description
     */
    public void setDescription(String description);

    /**
     * retourne le traitement réalisé par la tâche. <code>TraitementTache</code> est
     * une interface fonctionnelle avec comme seule fonction
     * <code>boolean traiteDemandePret(DemandePret demande, Logger logger)</code>, qui est
     * une fonction booleenne qui prend comme argument une demande de prêt, evalue des  
     * conditions (booléennes) dessus, et retourne vrai ou faux.
     * 
     * @return
     */
    public TraitementTache getTraitementTache();
    public void setTraitementTache(TraitementTache traitementTache);
    
}
