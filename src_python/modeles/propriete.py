
class Propriete:
    def __init__(self, adresse, valeur_de_marche = 0.0):
        self._adresse = adresse
        self._valeur_de_marche = valeur_de_marche

    @property
    def adresse(self):
        return self._adresse
    
    @property
    def valeur_de_marche(self):
        return self._valeur_de_marche
    
    @valeur_de_marche.setter
    def valeur_de_marche(self, value):
        self._valeur_de_marche = value