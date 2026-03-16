from typing import Callable
import logging
from modeles.demande_pret import DemandePret

TraitementTache = Callable[[DemandePret, logging.Logger], bool]