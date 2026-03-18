import unittest
from modeles.adresse import Adresse
from traitements.definitions.definition_tache import DefinitionTache
from utils import fabrique
import logging
from modeles.resultat import Resultat


class TestCreationDefinitionProcessus(unittest.TestCase):

    nom_processus = "Vérification Éligibilité"
    description_processus = (
        "Ce processus vérifie l'éligibilité du demandeur, de la propriété, et des termes du prêt"
    )

    nom_tache_1 = "Éligibilité demandeur-se"
    description_tache_1 = "Cette tâche vérifie l'éligibilité de la personne demandeuse"

    nom_tache_2 = "Éligibilité propriété"
    description_tache_2 = "Cette tâche vérifie l'éligibilité de la propriété"

    nom_tache_3 = "Éligibilité prêt"
    description_tache_3 = "Cette tâche vérifie l'éligibilité de l'emprunt"

    logger = logging.getLogger("Logger for test creation definition processus")

    def setUp(self):
        self.coquille = fabrique.creer_definition_processus(nom=self.nom_processus, description=self.description_processus)
        logging.basicConfig(level=logging.INFO, format="%(levelname)s: %(message)s")
     
    def test_definition_processus_not_null(self):
        self.assertIsNotNone(self.coquille)
    
    def test_attributs_definition_processus(self):
        self.assertEqual(
            self.nom_processus,
            self.coquille.nom,
            "Nom du processus mal initialisé"
        )
        self.assertEqual(
            self.description_processus,
            self.coquille.description,
            "Description du processus mal initialisée"
        )
    
    def test_creation_definition_tache(self):
        definition_tache = fabrique.creer_definition_tache(
            nom=self.nom_tache_1,
            description=self.description_tache_1
        )
        self.assertIsNotNone(definition_tache)
        self.assertEqual(
            self.nom_tache_1,
            definition_tache.nom,
            "Nom définition tâche mal initialisé"
        )
        self.assertEqual(
            self.description_tache_1,
            definition_tache.description,
            "Description tache mal initialisée"
        )
    
    def test_ajout_premiere_definition_tache(self):
        definition_tache = fabrique.creer_definition_tache(
            nom=self.nom_tache_1,
            description=self.description_tache_1
        )

        self.coquille.ajouter_tache(definition_tache)

        # vérifier qu'elle a été ajoutée
        self.assertEqual(
            self.coquille.taches[0],
            definition_tache,
            "La tache n'a pas été ajoutée"
        )
        # vérifier qu'elle est terminale
        self.assertTrue(
            self.coquille.is_tache_finale(definition_tache),
            "Tache est supposée être finale"
        )

    def test_ajout_transition(self):
        dt1 = fabrique.creer_definition_tache(nom=self.nom_tache_1, description=self.description_tache_1)
        dt2 = fabrique.creer_definition_tache(nom=self.nom_tache_2, description=self.description_tache_2)
        dt3 = fabrique.creer_definition_tache(nom=self.nom_tache_3, description=self.description_tache_3)

        self.coquille.ajouter_tache(dt1)
        self.coquille.ajouter_tache(dt2)
        self.coquille.ajouter_tache(dt3)

        # transition sans condition
        transition = fabrique.creer_definition_transition(dt1, dt2, condition_transition=lambda t, d: True)
        self.coquille.ajoute_transition(definition_transition=transition)

        # transition avec condition
        condition = lambda tache, demande: demande.ratio_emprunt_valeur() > 0.95
        self.coquille.ajoute_transition(dt1, dt3, condition)

        transitions = self.coquille.get_trasitions_sortant_de(dt1)
        self.assertEqual(
            2,
            len(transitions),
            f"Je m'attendais à 2 transitions sortantes de {dt1.nom} et j'en trouve {len(transitions)}"
        )

        self.assertTrue(self.coquille.is_tache_finale(dt2), f"oops! tache {dt2.nom} est supposée être finale.")
        self.assertTrue(self.coquille.is_tache_finale(dt3), f"oops! tache {dt3.nom} est supposée être finale.")
        self.assertFalse(self.coquille.is_tache_finale(dt1), f"oops! tache {dt1.nom} n'est pas supposée être finale.")
    
    def creer_demande_pret(self):
        demandeur = fabrique.creer_demandeur_pret(
            prenom="Jeanne",
            nom="Bergeron",
            nas="123-456-789",
            revenu_annuel=120000.0,
            obligations_annuelles=40000.0,
            score_credit=765
        )
        propriete = fabrique.creer_propriete(
            adresse=Adresse(
                numero_porte="201A",
                numero_rue="2100",
                nom_rue="Saint-Urbain",
                ville="Montréal",
                province="QUEBEC",
                code_postal="H2X2XH"
            ),
            valeur_marche=400000.0
        )
        return fabrique.creer_demande_pret(
            propriete=propriete,
            demandeur_pret=demandeur,
            prix_achat=405000.0,
            montant_mise_de_fonds=30000.0
        )

    def test_executer_definition_tache(self):
        dt: DefinitionTache = fabrique.creer_definition_tache(nom=self.nom_tache_1, description=self.description_tache_1, traitementTache=lambda demande, log: demande.demandeur_pret.tauxEndettement <= 0.37)
        traitement_tache  = dt.traitementTache
        demande_pret = self.creer_demande_pret()
        resultat = traitement_tache(demande_pret, self.logger)
        self.assertTrue(resultat,f"Coudonc! l'emprunteur-se " + demande_pret.demandeur_pret.nom + " est supposé-e être éligible")


    def test_executer_definition_transition(self):
        dt1 = fabrique.creer_definition_tache(nom=self.nom_tache_1, description=self.description_tache_1)
        self.coquille.ajouter_tache(dt1)

        dt2 = fabrique.creer_definition_tache(nom=self.nom_tache_2, description=self.description_tache_2)
        self.coquille.ajouter_tache(dt2)

        condition = lambda instanceTache, demande: demande.resultat_traitement.resultat == Resultat.ACCEPTEE
        definition_transition = fabrique.creer_definition_transition(tache_source=dt1, tache_destination=dt2, condition_transition=condition)

        self.assertEqual(condition, definition_transition.condition_transition, "La condition de la transition n'est pas celle qui a été passée en paramètre")

        demande_pret = self.creer_demande_pret()
        demande_pret.resultat_traitement = fabrique.creer_resultat_traitement(Resultat.ACCEPTEE)

        condition = definition_transition.condition_transition
        resultat = condition(None, demande_pret)

        self.assertTrue(resultat, "Transition de " + dt1.nom + " à " + dt2.nom + " est supposée être activée")



if __name__ == '__main__':
    unittest.main()
