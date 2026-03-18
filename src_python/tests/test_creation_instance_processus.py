import unittest
from utils import fabrique
from tests.creation_definitions_processus import processus_lineaire, processus_errone, processus_complexe
from tests.creation_demandes_pret import demande_pret_oui_oui_oui, demande_pret_oui_oui_non, demande_pret_oui_non_non, demande_pret_non_non_non, demande_pret_oui_non_oui
from tests.creation_definitions_taches import tache_eligibilite_pret, tache_eligibilite_propriete, tache_eligibilite_emprunteur
from modeles.resultat import Resultat
from modeles.demande_pret import DemandePret
from traitements.instances.exception_definition_processus import ExceptionDefinitionProcessus
import traceback

class TestCreationInstanceProcessus(unittest.TestCase):

    def setUp(self):
        self.processus_lineaire = processus_lineaire()
        self.processus_errone = processus_errone()
        self.processus_complexe = processus_complexe()
    
    def test_creation_instance_processus(self):
        demande_pret = demande_pret_oui_oui_oui()
        instance_processus = fabrique.creer_instance_processus(definition_processus=self.processus_lineaire, demandePret=demande_pret)
        self.assertEqual(self.processus_lineaire, instance_processus.definition_processus, "Le nouveau processus n'a pas la bonne definition")

    def test_execution_processus_simple_sur_demande_oui_oui_oui(self):
        demande_pret = demande_pret_oui_oui_oui()
        instance_processus = fabrique.creer_instance_processus(definition_processus=self.processus_lineaire, demandePret=demande_pret)
        try:
            instance_processus.demarrer()
            self.assertEqual(Resultat.ACCEPTEE, demande_pret.resultat_traitement.resultat, "Demande " + str(demande_pret) + " était supposée être acceptée")
        except ExceptionDefinitionProcessus as e:
            traceback.print_exc()
    
    def test_execution_processus_simple_sur_demande_oui_oui_non(self):
        demande_pret = demande_pret_oui_oui_non()
        instance_processus = fabrique.creer_instance_processus(definition_processus=self.processus_lineaire, demandePret=demande_pret)
        try:
            instance_processus.demarrer()
            self.assertEqual(Resultat.REFUSEE, demande_pret.resultat_traitement.resultat, "Demande " + str(demande_pret) + " était supposée être refusée")
            self.assertTrue(self.aEchoueLesTaches(demande_pret, tache_eligibilite_pret() ), "La tache EligibilitePret était supposée échouer (aEchoueLesTaches failed)")
        except ExceptionDefinitionProcessus as e:
            traceback.print_exc()
    
    def aEchoueLesTaches(self, demande_pret: DemandePret, *definitions_tache):
        errorMessages = demande_pret.resultat_traitement.messages

        if (len(errorMessages) != len(definitions_tache)): 
            return False
        
        isMatche = True
        for dt in definitions_tache:
            if not any(dt.nom in message for message in errorMessages):
                isMatche = False
                break
        return isMatche
    
    def test_execution_processus_simple_sur_demande_oui_non_non(self):
        demande_pret = demande_pret_oui_non_non()
        instance_processus = fabrique.creer_instance_processus(definition_processus=self.processus_lineaire, demandePret=demande_pret)
        try:
            instance_processus.demarrer()
            self.assertEqual(Resultat.REFUSEE, demande_pret.resultat_traitement.resultat, "Demande " + str(demande_pret) + " était supposée être refusée")
            self.assertTrue(self.aEchoueLesTaches(demande_pret, tache_eligibilite_propriete(), tache_eligibilite_pret() ), "La tache EligibilitePropriete était supposée échouer (aEchoueLesTaches failed)")
        except ExceptionDefinitionProcessus as e:
            traceback.print_exc()
    
    def test_execution_processus_simple_sur_demande_non_non_non(self):
        demande_pret = demande_pret_non_non_non()
        instance_processus = fabrique.creer_instance_processus(definition_processus=self.processus_lineaire, demandePret=demande_pret)
        try:
            instance_processus.demarrer()
            self.assertEqual(Resultat.REFUSEE, demande_pret.resultat_traitement.resultat, "Demande " + str(demande_pret) + " était supposée être refusée")
            self.assertTrue(self.aEchoueLesTaches(demande_pret, tache_eligibilite_propriete(), tache_eligibilite_pret(), tache_eligibilite_emprunteur() ), "Les 3 taches étaient supposées échouer (aEchoueLesTaches failed)")
        except ExceptionDefinitionProcessus as e:
            traceback.print_exc()
    
    def test_execution_processus_simple_sur_demande_oui_non_oui(self):
        demande_pret = demande_pret_oui_non_oui()
        instance_processus = fabrique.creer_instance_processus(definition_processus=self.processus_lineaire, demandePret=demande_pret)
        try:
            instance_processus.demarrer()
            self.assertEqual(Resultat.REFUSEE, demande_pret.resultat_traitement.resultat, "Demande " + str(demande_pret) + " était supposée être refusée")
            self.assertTrue(self.aEchoueLesTaches(demande_pret, tache_eligibilite_propriete()), "La tache EligibilitePropriete était supposée échouer (aEchoueLesTaches failed)")
        except ExceptionDefinitionProcessus as e:
            traceback.print_exc()
    
    def test_execution_processus_mal_forme(self):
        demande_pret = demande_pret_oui_oui_oui()
        instance_processus = fabrique.creer_instance_processus(definition_processus=self.processus_errone, demandePret=demande_pret)
        with self.assertRaises(ExceptionDefinitionProcessus):
            instance_processus.demarrer()
    
    def test_execution_processus_complexe_sur_demande_oui_oui_oui(self):
        demande_pret = demande_pret_oui_oui_oui()
        instance_processus = fabrique.creer_instance_processus(definition_processus=self.processus_complexe, demandePret=demande_pret)
        try:
            instance_processus.demarrer()
            self.assertEqual(Resultat.ACCEPTEE, demande_pret.resultat_traitement.resultat, "Demande " + str(demande_pret) + " était supposée être acceptée")
        except ExceptionDefinitionProcessus as e:
            traceback.print_exc()
    
    def test_execution_processus_complexe_sur_demande_oui_oui_non(self):
        demande_pret = demande_pret_oui_oui_non()
        instance_processus = fabrique.creer_instance_processus(definition_processus=self.processus_complexe, demandePret=demande_pret)
        try:
            instance_processus.demarrer()
            self.assertEqual(Resultat.REFUSEE, demande_pret.resultat_traitement.resultat, "Demande " + str(demande_pret) + " était supposée être refusée")
            self.assertTrue(self.aEchoueLesTaches(demande_pret, tache_eligibilite_pret()), "La tache EligibilitePret était supposée échouer (aEchoueLesTaches failed)")
        except ExceptionDefinitionProcessus as e:
            traceback.print_exc()

    def test_execution_processus_complexe_sur_demande_oui_non_non(self):
        demande_pret = demande_pret_oui_non_non()
        instance_processus = fabrique.creer_instance_processus(definition_processus=self.processus_complexe, demandePret=demande_pret)
        try:
            instance_processus.demarrer()
            self.assertEqual(Resultat.REFUSEE, demande_pret.resultat_traitement.resultat, "Demande " + str(demande_pret) + " était supposée être refusée")
            self.assertTrue(self.aEchoueLesTaches(demande_pret, tache_eligibilite_propriete()), "La tache EligibilitePropriete était supposée échouer (aEchoueLesTaches failed)")
        except ExceptionDefinitionProcessus as e:
            traceback.print_exc()
    
    def test_execution_processus_complexe_sur_demande_non_non_non(self):
        demande_pret = demande_pret_non_non_non()
        instance_processus = fabrique.creer_instance_processus(definition_processus=self.processus_complexe, demandePret=demande_pret)
        try:
            instance_processus.demarrer()
            self.assertEqual(Resultat.REFUSEE, demande_pret.resultat_traitement.resultat, "Demande " + str(demande_pret) + " était supposée être refusée")
            self.assertTrue(self.aEchoueLesTaches(demande_pret, tache_eligibilite_emprunteur() ), "La tache EligibiliteEmprunteur était supposée échouer (aEchoueLesTaches failed)")
        except ExceptionDefinitionProcessus as e:
            traceback.print_exc()

if __name__ == '__main__':
    unittest.main()