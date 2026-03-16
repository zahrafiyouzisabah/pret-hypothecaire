from modeles.demande_pret import DemandePret
from traitements.instances.instance_tache import InstanceTache

from typing import Callable

ConditionTransition = Callable[[InstanceTache, DemandePret], bool]