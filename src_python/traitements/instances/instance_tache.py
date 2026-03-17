from modeles.demande_pret import DemandePret
from modeles.resultat import Resultat
from traitements.instances.etat_traitement import EtatTraitement
from traitements.instances.exception_definition_processus import ExceptionDefinitionProcessus

class InstanceTache:
    def __init__(self, processus_englobant, definition_tache):
        self.processus_englobant = processus_englobant
        self.definition_tache = definition_tache
        self.demande_pret = processus_englobant.demande_pret
        self.etat_instance_tache = None

    
    @property
    def demande_pret(self):
        return self._demande_pret
    @demande_pret.setter
    def demande_pret(self, value: DemandePret):
        self._demande_pret = value
    
    @property
    def etat_instance_tache(self):
        return self._etat_instance_tache
    @etat_instance_tache.setter
    def etat_instance_tache(self, value: EtatTraitement):
        self._etat_instance_tache = value

    def executer(self):
        traitement_tache = self.definition_tache.traitementTache
        if traitement_tache is None:
            raise ExceptionDefinitionProcessus(self.processus_englobant, [self.definition_tache], "La tache n'a pas de traitement.")
        else:
            resultat = traitement_tache(self.demande_pret, self.processus_englobant.logger)
            resultat_traitement = self.demande_pret.resultat_traitement

            if (resultat_traitement is None):
                self.demande_pret.resultat_traitement = Resultat.ACCEPTEE if resultat else Resultat.REFUSEE

            if resultat == False:
                resultat_traitement.ajouter_message(f"La tache {self.definition_tache.nom} a échoué.")
                resultat_traitement.resultat = Resultat.REFUSEE
            
            self.etat_instance_tache = EtatTraitement.TERMINE
            self.processus_englobant.signaler_fin_tache(self)
            