from traitements.definitions.condition_transition import ConditionTransition

class DefinitionTransition:
    def __init__(self, tache_source, tache_destination, condition_transition: ConditionTransition = None):
        self.tache_source = tache_source
        self.tache_destination = tache_destination
        self.condition_transition = condition_transition
    