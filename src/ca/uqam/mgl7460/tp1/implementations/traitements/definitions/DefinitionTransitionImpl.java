package ca.uqam.mgl7460.tp1.implementations.traitements.definitions;

import ca.uqam.mgl7460.tp1.types.traitements.definitions.ConditionTransition;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.DefinitionTache;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.DefinitionTransition;

public class DefinitionTransitionImpl implements DefinitionTransition {
    private final DefinitionTache tacheSource;
    private final DefinitionTache tacheDestination;
    private ConditionTransition conditionTransition;

    public DefinitionTransitionImpl(DefinitionTache tacheSource, DefinitionTache tacheDestination) {
        this.tacheSource = tacheSource;
        this.tacheDestination = tacheDestination;
    }

    public DefinitionTransitionImpl(DefinitionTache tacheSource, DefinitionTache tacheDestination, ConditionTransition conditionTransition) {
        this.tacheSource = tacheSource;
        this.tacheDestination = tacheDestination;
        this.conditionTransition = conditionTransition;
    }

    @Override
    public DefinitionTache getTachesource() {
        return this.tacheSource;
    }

    @Override
    public DefinitionTache getTacheDestination() {
        return this.tacheDestination;
    }

    @Override
    public ConditionTransition getConditionTransition() {
        return this.conditionTransition;
    }
}
