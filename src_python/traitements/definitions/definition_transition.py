from traitements.definitions.condition_transition import ConditionTransition

class DefinitionTransition:
    def __inti__(self, tache_source, tache_destination, condition_transition: ConditionTransition = None):
        self.tache_source = tache_source
        self.tache_destination = tache_destination
        self.condition_transition = condition_transition
    
    @property
    def tache_source(self):
        return self._tache_source
    
    @property
    def tache_destination(self):
        return self._tache_destination
    
    @property
    def condition_transition(self):
        return self._condition_transition