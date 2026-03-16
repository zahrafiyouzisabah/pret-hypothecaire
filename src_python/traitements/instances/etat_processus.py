from dataclasses import dataclass
from traitements.instances.instance_tache import InstanceTache
from traitements.instances.etat_traitement import EtatTraitement

@dataclass
class EtatProcessus:
    tacheCourante: InstanceTache
    etatTraitement: EtatTraitement