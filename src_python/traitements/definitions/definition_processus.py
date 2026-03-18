
from traitements.definitions import definition_transition
from traitements.definitions.definition_tache import DefinitionTache
from traitements.definitions.definition_transition import DefinitionTransition
from utils import fabrique

class DefinitionProcessus:
    def __init__(self, nom: str, description: str):
        self.nom = nom
        self.description = description
        
        self.premiere_tache  = None
        self.taches = []
        self.transitions = []
         
    @property
    def description(self):
        return self._description
    @description.setter
    def description(self, value):   
        self._description = value   

    @property
    def premiere_tache(self):
        return self._premiere_tache
    @premiere_tache.setter
    def premiere_tache(self, value):
        self._premiere_tache = value
    
    def ajouter_premiere_tache(self, tache: DefinitionTache):
        self.premiere_tache = tache
        self.ajouter_tache(tache)
        
    def ajouter_tache(self, tache: DefinitionTache):
        self.taches.append(tache)

    def ajoute_transition(self, tache_source=None, tache_destination=None, *conditions, definition_transition=None):
        """
        As input, pass either:
            definition_transition
        or:
            tache_source, tache_destination, optional conditions
        """
        if definition_transition:
            # Case 1: add existing transition
            self.transitions.append(definition_transition)
            return definition_transition

        if tache_source is None or tache_destination is None:
            raise ValueError("Either pass a definition_transition or both tache_source and tache_destination")

        # Case 2: create a new DefinitionTransition
        condition_transition = conditions[0] if conditions else (lambda t, d: True)
        transition = fabrique.creer_definition_transition(tache_source=tache_source, tache_destination=tache_destination, condition_transition=condition_transition)

        self.transitions.append(transition)
        return transition
    
    def get_trasitions_sortant_de(self, tache: DefinitionTache):
        return [t for t in self.transitions if t.tache_source == tache]
    
    def is_tache_finale(self, tache: DefinitionTache):
        return len(self.get_trasitions_sortant_de(tache)) == 0

        