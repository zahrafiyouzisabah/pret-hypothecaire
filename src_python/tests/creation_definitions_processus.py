from utils import fabrique
from traitements.definitions.condition_transition import ConditionTransition
from modeles.resultat import Resultat
from tests.creation_definitions_taches import tache_eligibilite_emprunteur, tache_eligibilite_propriete, tache_eligibilite_pret, tache_affichage_erreur, tache_acceptation

def processus_lineaire():
    # 1.   Création
    processus = fabrique.creer_definition_processus(nom="Processus éligibilité simple", description="Processus d'éeligibilité qui évalue les trois critères l'un à la suite de l'autre même si le premier échoue")

    # 2.   Ajoute de première tache qui est la vérification de l'éligibilité de l'emprunteur
    eligibilite_emprunteur_tache = tache_eligibilite_emprunteur()
    processus.ajouter_premiere_tache(eligibilite_emprunteur_tache)

    #.   Ajouter vérification de propriété
    #3.a.     Ajouter la tache
    eligibilite_propriete_tache = tache_eligibilite_propriete()
    processus.ajouter_tache(eligibilite_propriete_tache)

     #3.b.     Ajouter la transition entre eligibiliteEmprunteur et eligibilitePropriete (Sans condtion)
    def condition_transition(tache_source, demande_pret):
        return True
    processus.ajoute_transition(eligibilite_emprunteur_tache, eligibilite_propriete_tache, condition_transition)
    
    #.4.   Ajouter vérification de prêt
    #4.a.     Ajouter la tâche
    eligibilite_pret_tache = tache_eligibilite_pret()
    processus.ajouter_tache(eligibilite_pret_tache)

    #4.b.     Ajouter la transition entre eligibilitePropriete et eligibilitePret
    #on va utiliser la même condition, qui n'en est pas une
    processus.ajoute_transition(eligibilite_propriete_tache, eligibilite_pret_tache, condition_transition)

    return processus

def processus_errone():
    # 1.   Création
    processus = fabrique.creer_definition_processus(
        nom="Processus erroné",
        description="Processus d'éligibilité contenant deux transitions sortantes sans condition de la même tâche"
    )

    # 2.   Première tâche (éligibilité emprunteur)
    eligibilite_emprunteur_tache = tache_eligibilite_emprunteur()
    processus.ajouter_premiere_tache(eligibilite_emprunteur_tache)

    # 3.   Vérification de propriété
    # 3.a Ajouter la tâche
    eligibilite_propriete_tache = tache_eligibilite_propriete()
    processus.ajouter_tache(eligibilite_propriete_tache)

    # 3.b Transition (sans condition)
    def condition_transition(tache_source, demande_pret):
        return True

    processus.ajoute_transition(
        eligibilite_emprunteur_tache,
        eligibilite_propriete_tache,
        condition_transition
    )

    # 4.   Vérification de prêt
    # 4.a Ajouter la tâche
    eligibilite_pret_tache = tache_eligibilite_pret()
    processus.ajouter_tache(eligibilite_pret_tache)

    # 4.b Transition (depuis la même tâche source → cas erroné)
    processus.ajoute_transition(
        eligibilite_emprunteur_tache,
        eligibilite_pret_tache,
        condition_transition
    )

    return processus

def processus_complexe():
    # 1.   Création
    processus = fabrique.creer_definition_processus(nom="Processus éligibilité complexe", description="Processus d'éeligibilité qui sort dès que la demande échoue un critère")

    #2.   Ajoute de première tache qui est la vérification de l'éligibilité de l'emprunteur
    eligibilite_emprunteur_tache = tache_eligibilite_emprunteur()
    processus.ajouter_premiere_tache(eligibilite_emprunteur_tache)

    # 3.   Ajouter vérification de propriété
    eligibilite_propriete_tache = tache_eligibilite_propriete()
    processus.ajouter_tache(eligibilite_propriete_tache)

    # 4.   Ajouter vérification de prêt
    eligibilite_pret_tache = tache_eligibilite_pret()
    processus.ajouter_tache(eligibilite_pret_tache)

    #5.   Ajouter affichage de messages d'erreur
    affichage_erreur_tache = tache_affichage_erreur()
    processus.ajouter_tache(affichage_erreur_tache)

    #6.  Ajouter tache acceptation
    acceptation_tache = tache_acceptation()
    processus.ajouter_tache(acceptation_tache)

    # 7.   Ajouter les transitions

    #7.a  Les transitions du happy path
    happyCondition = lambda tacheSource, demandePret : demandePret.resultat_traitement.resultat == Resultat.ACCEPTEE
       
    # 7.a.1    Transition eligibiliteEmprunteur -> eligibilitePropriete
    processus.ajoute_transition(eligibilite_emprunteur_tache, eligibilite_propriete_tache, happyCondition)

    #7.a.2    Transition eligibilitePropriete -> eligibilitePret
    processus.ajoute_transition(eligibilite_propriete_tache, eligibilite_pret_tache, happyCondition)

    #7.a.3    Transition eligibilitePret -> tacheAcceptation
    processus.ajoute_transition(eligibilite_pret_tache, acceptation_tache, happyCondition)

    # 7.b  Les transitions du unhappy path
    unhappyCondition = lambda tacheSource, demandePret : demandePret.resultat_traitement.resultat != Resultat.ACCEPTEE

    #7.b.1    Transition eligibiliteEmprunteur -> affichageErreurs
    processus.ajoute_transition(eligibilite_emprunteur_tache, affichage_erreur_tache, unhappyCondition)

    #7.b.2    Transition eligibilitePropriété -> affichageErreurs
    processus.ajoute_transition(eligibilite_propriete_tache, affichage_erreur_tache, unhappyCondition)
    
    #7.b.3    Transition eligibilitePret -> affichageErreurs
    processus.ajoute_transition(eligibilite_pret_tache, affichage_erreur_tache, unhappyCondition)

    return processus




