
import datetime
import logging
from traitements.instances.etat_processus import EtatProcessus
from traitements.instances.etat_traitement import EtatTraitement
from traitements.instances.exception_definition_processus import ExceptionDefinitionProcessus
from traitements.instances.instance_tache import InstanceTache


class InstanceProcessus:
    def __init__(self, definition_processus, demande_pret):
        self.definition_processus = definition_processus
        self.demande_pret = demande_pret

        self.taches = []
        self.etat_processus: EtatProcessus = None
        self.tache_courante: InstanceTache = None

        self.temps_demarrage = None
        self.temps_arret = None
        self.logger = None
        
    
    @property
    def etat_processus(self):
        return self._etat_processus
    @etat_processus.setter
    def etat_processus(self, value):
        self._etat_processus = value

    @property
    def tache_courante(self):
        return self._tache_courante
    @tache_courante.setter
    def tache_courante(self, value):
        self._tache_courante = value
    
    @property
    def temps_demarrage(self):
        return self._temps_demarrage
    @temps_demarrage.setter
    def temps_demarrage(self, value: datetime):
        self._temps_demarrage = value
    
    @property
    def temps_arret(self):
        return self._temps_arret    
    @temps_arret.setter
    def temps_arret(self, value: datetime):
        self._temps_arret = value

    @property
    def logger(self):
        return self._logger
    @logger.setter
    def logger(self, value: logging.Logger):
        self._logger = value

    def demarrer(self):
        premiere_tache = self.definition_processus.premiere_tache
        if (premiere_tache is None):
            raise ExceptionDefinitionProcessus(self, [], "Le processus n'a pas de première tâche définie.")
        premiere_instance_tache = InstanceTache(self, premiere_tache)
        self.temps_demarrage = datetime.datetime.now()
        self.ajouter_et_lancer_tache(premiere_instance_tache)

    
    def signaler_fin_tache(self, instance_tache: InstanceTache):
        if (instance_tache != self.tache_courante):
            raise ExceptionDefinitionProcessus(self, [], "La tâche signalée comme terminée n'est pas la tâche courante.")
        
        if (instance_tache.etat_instance_tache != EtatTraitement.TERMINE):
            raise ExceptionDefinitionProcessus(self, [], "La tâche signalée comme terminée n'est pas dans un état terminé.")
        
        transitions_sortantes = self.definition_processus.get_trasitions_sortant_de(instance_tache.definition_tache)

        if (len(transitions_sortantes) == 0):
            self.temps_arret = datetime.datetime.now()
            self.etat_processus = EtatProcessus(instance_tache, EtatTraitement.TERMINE)
            return
        elif (len(transitions_sortantes) > 1):
            raise ExceptionDefinitionProcessus(self, [], f"La tâche {instance_tache.definition_tache.nom} signalant la fin a plus d'une transition sortante.")
        else:
            prochaineTransition = transitions_sortantes[0]
            prochaineInstanceTache = InstanceTache(self, prochaineTransition.tache_destination)
            self.ajouter_et_lancer_tache(prochaineInstanceTache)

    def ajouter_et_lancer_tache(self, instanceTache: InstanceTache):
        self.etat_processus = EtatProcessus(instanceTache, EtatTraitement.PRET)
        self.taches.append(instanceTache)
        self.tache_courante = instanceTache
        instanceTache.executer()
        self.etat_processus = EtatProcessus(instanceTache, EtatTraitement.ENCOURS)
        


    