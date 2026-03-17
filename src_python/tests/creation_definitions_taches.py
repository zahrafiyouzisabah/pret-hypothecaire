from utils import fabrique
from traitements.definitions.traitement_tache import TraitementTache
from modeles.province_ou_territoire import ProvinceOuTerritoire
from modeles.resultat import Resultat

def tache_eligibilite_emprunteur():
    tache = fabrique.creer_definition_tache(nom= "Vérifier éligibilité emprunteur", description="Cette tâche vérifie que la personne qui emprunte est éligible pour un prêt")
    traitementTache: TraitementTache =  lambda demandePret, log : (demandePret.demandeur_pret.revenuAnnuel >= 35000) and (demandePret.demandeur_pret.tauxEndettement <= 0.37) and (demandePret.demandeur_pret.scoreCredit >= 700)
    tache.traitementTache = traitementTache
    return tache

def tache_eligibilite_propriete():
    tache = fabrique.creer_definition_tache(nom="Vérifier éligibilité propriété", description="Cette tâche vérifie l'éligibilité de la propriété")
    traitementTache: traitementTache = lambda demandePret, log : demandePret.propriete.adresse.province == ProvinceOuTerritoire.QUEBEC
    tache.traitementTache = traitementTache
    return tache

def tache_eligibilite_pret():
    tache = fabrique.creer_definition_tache(nom="Éligibilité prêt", description="Cette tâche vérifie l'éligibilité du prêt en terms de mise de fonds et LTV")
    traitementTache: traitementTache = lambda demandePret, log : (demandePret.ratio_emprunt_valeur <= 0.95) and (demandePret.montant_mise_de_fonds >= 0.05* demandePret.montant_pret)
    tache.traitementTache = traitementTache
    return tache

def tache_affichage_erreur():
    tache = fabrique.creer_definition_tache(nom="Affichage messages d'erreur", description="Cette tâche affiche les messages d'erreur des différents traitements")
    def traitement_tache(demande_pret, log) -> bool:
        resultat_traitement = demande_pret.resultat_traitement

        log.info("Nous regrettons de vous informer que votre demande a été refusée pour les raisons suivantes")

        for message in resultat_traitement.messages:
            log.info(message)

        return True
    
    tache.traitementTache = traitement_tache
    return tache

def tache_acceptation():
    tache = fabrique.creer_definition_tache(nom="Acceptation", description="Cette tâche affiche le message d'acceptation")
    def traitement_tache(demande_pret, log) -> bool:
        resultat_traitement_resultat = demande_pret.resultat_traitement.resultat

        if (resultat_traitement_resultat == Resultat.ACCEPTEE):
            log.info("Félicitations! votre demande de prêt a été approuvée. Veuillez prendre contact avec votre agent pour finaliser les termes du prêt")
            return True
        else:
            log.info("Je comprends pas! votre demande a été refusée. Je devrais pas être là!")
            return False  
    
    tache.traitementTache = traitement_tache
    return tache