
from modeles.adresse import Adresse
from modeles.demande_pret import DemandePret
from modeles.demandeur_pret import DemandeurPret
from modeles.propriete import Propriete
from modeles.resultat import Resultat
from modeles.resultat_traitement import ResultatTraitement
from traitements.definitions.condition_transition import ConditionTransition
from traitements.definitions.definition_processus import DefinitionProcessus
from traitements.definitions.definition_tache import DefinitionTache
from traitements.definitions.definition_transition import DefinitionTransition
from traitements.definitions.traitement_tache import TraitementTache
from traitements.instances.instance_processus import InstanceProcessus
from traitements.instances.instance_tache import InstanceTache


def creer_propriete(adresse: Adresse, valeur_marche: float = 0.0) -> Propriete:
    return Propriete(adresse, valeur_marche)

def creer_demandeur_pret(prenom: str, nom: str, nas: str,
                         revenu_annuel: float | None = None,
                         obligations_annuelles: float | None = None,
                         score_credit: int | None = None) -> DemandeurPret:
    return DemandeurPret(
        prenom,
        nom,
        nas,
        revenu_annuel or 0.0,
        obligations_annuelles or 0.0,
        score_credit or 0
    )

def creer_demande_pret(propriete: Propriete, demandeur_pret: DemandeurPret, prix_achat=0.0, montant_mise_de_fonds=0.0) -> DemandePret:
    return DemandePret(propriete=propriete, demandeur_pret=demandeur_pret, prix_achat=prix_achat, montant_mise_de_fonds=montant_mise_de_fonds)

def creer_resultat_traitement(resultat: Resultat) -> ResultatTraitement:
    return ResultatTraitement(resultat)

def creer_definition_tache(nom: str, description: str, traitementTache: TraitementTache = None) -> DefinitionTache:
    return DefinitionTache(nom=nom, description=description, traitementTache=traitementTache)


def creer_definition_transition(tache_source, tache_destination, condition_transition: ConditionTransition = None) -> DefinitionTransition:
    return DefinitionTransition(tache_source = tache_source, tache_destination = tache_destination, condition_transition = condition_transition)

def creer_definition_processus(nom: str, description: str) -> DefinitionProcessus:
    return DefinitionProcessus(nom=nom, description=description)

def creer_instance_processus(definition_processus: DefinitionProcessus, demandePret: DemandePret) -> InstanceProcessus:
    return InstanceProcessus(definition_processus = definition_processus, demandePret = demandePret)

def creer_instance_tache(processus_englobant: InstanceProcessus, definition_tache: DefinitionTache) -> InstanceTache:
    return InstanceTache(definition_tache=definition_tache, processus_englobant=processus_englobant)