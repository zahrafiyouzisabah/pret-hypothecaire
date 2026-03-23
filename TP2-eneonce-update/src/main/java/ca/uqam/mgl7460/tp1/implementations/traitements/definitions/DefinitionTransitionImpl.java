package ca.uqam.mgl7460.tp1.implementations.traitements.definitions;

import ca.uqam.mgl7460.tp1.types.traitements.definitions.ConditionTransition;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.DefinitionTache;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.DefinitionTransition;

public class DefinitionTransitionImpl implements DefinitionTransition{

    private DefinitionTache tacheSource;

    private DefinitionTache tacheDestination;

    private ConditionTransition conditionTransition;

    public DefinitionTransitionImpl(DefinitionTache source, DefinitionTache destination, ConditionTransition conditionTransition){
        this.tacheSource = source;
        this.tacheDestination = destination;
        this.conditionTransition = conditionTransition;
    }
    
    @Override
    public DefinitionTache getTachesource() {
        return tacheSource;
    }

    @Override
    public DefinitionTache getTacheDestination() {
        return tacheDestination;
    }

    @Override
    public ConditionTransition getConditionTransition() {
        return conditionTransition;
    }
    
}
