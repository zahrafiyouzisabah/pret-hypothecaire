from tests.creation_definitions_processus import processus_lineaire, processus_errone, processus_complexe
from tests.creation_demandes_pret import demande_pret_oui_oui_oui
from tests.creation_definitions_taches import tache_eligibilite_pret
from traitements.instances.etat_traitement import EtatTraitement
from traitements.instances.exception_definition_processus import ExceptionDefinitionProcessus
from utils import fabrique
import logging
import traceback

def main():
    logging.basicConfig(level=logging.INFO, format="%(levelname)s: %(message)s")

    # 1 Création de définition de processus
    linear_process = processus_lineaire()
    demande_pret = demande_pret_oui_oui_oui()

    # 2 Création d'instance de processus
    linear_process_instance = fabrique.creer_instance_processus(definition_processus=linear_process, demandePret=demande_pret)

    # 3 logger
    logger = logging.getLogger("Test Logger")
    linear_process_instance.logger = logger

    try:
        # 4 Démarrage du processus
        linear_process_instance.demarrer()
        if (linear_process_instance.tache_courante.definition_tache.nom == tache_eligibilite_pret().nom):
            logger.info("Cool, je me suis rendu jusqu'à la fin")
        if (linear_process_instance.etat_processus.etatTraitement == EtatTraitement.TERMINE):
            logger.info("Cool, l'exécution a réussi")
    
    except ExceptionDefinitionProcessus as e:
        traceback.print_exc()



if __name__ == "__main__":
    main()