from traitements.definitions.traitement_tache import TraitementTache

class DefinitionTache:
    def __init__(self, nom: str, description: str, traitementTache: TraitementTache = None):
        self.nom = nom
        self.description = description
        self.traitementTache = traitementTache
    
    @property
    def nom(self):
        return self._nom   
    
    @property
    def description(self):
        return self._description    
    @description.setter
    def description(self, value):
        self._description = value

    @property
    def traitementTache(self):
        return self._traitementTache   
    @traitementTache.setter
    def traitementTache(self, value):
        self._traitementTache = value
        