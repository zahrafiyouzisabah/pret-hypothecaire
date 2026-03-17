import uuid
from datetime import datetime


class DemandePret:

    def __init__(self, propriete, demandeur_pret, prix_achat=0.0, montant_mise_de_fonds=0.0):
        self._initialize()

        self.propriete = propriete
        self.demandeur_pret = demandeur_pret

        self.prix_achat = prix_achat
        self.montant_mise_de_fonds = montant_mise_de_fonds

        self.termes_pret = None
        self.resultat_traitement = None

    def _initialize(self):
        self.numero_demande = str(uuid.uuid4())
        self.date_demande = datetime.now()

    
    @property
    def prix_achat(self):
        return self._prix_achat

    @prix_achat.setter
    def prix_achat(self, value):
        self._prix_achat = value

    @property
    def montant_mise_de_fonds(self):
        return self._montant_mise_de_fonds

    @montant_mise_de_fonds.setter
    def montant_mise_de_fonds(self, value):
        self._montant_mise_de_fonds = value

    @property
    def resultat_traitement(self):
        return self._resultat_traitement

    @resultat_traitement.setter
    def resultat_traitement(self, value):
        self._resultat_traitement = value

    @property
    def termes_pret(self):
        return self._termes_pret

    @termes_pret.setter
    def termes_pret(self, value):
        self._termes_pret = value

    @property
    def montant_pret(self):
        return self._prix_achat - self._montant_mise_de_fonds

    @property
    def ratio_emprunt_valeur(self):
        valeur = self.propriete.valeur_de_marche
        if valeur == 0:
            return 0
        return self.montant_pret / valeur



