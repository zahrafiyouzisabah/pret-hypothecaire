
class DemandeurPret:
    def __init__(self, prenom, nom, nas, revenuAnnuel= 0.0, obligationsAnnuelles=0.0, scoreCredit=0):
        self.prenom = prenom
        self.nom = nom
        self.nas = nas

        self.revenuAnnuel = revenuAnnuel
        self.obligationsAnnuelles = obligationsAnnuelles
        self.scoreCredit = scoreCredit
    
    @property
    def nom(self):
        return self.nom
    
    @property 
    def prenom(self):
        return self.prenom
    
    @property
    def nas(self):
        return self.nas
    
    @property
    def revenuAnnuel(self):
        return self._revenuAnnuel
    
    @revenuAnnuel.setter
    def revenuAnnuel(self, value):
        self._revenuAnnuel = value

    @property
    def obligationsAnnuelles(self):
        return self._obligationsAnnuelles
    
    @obligationsAnnuelles.setter
    def obligationsAnnuelles(self, value):
        self._obligationsAnnuelles = value
    
    @property
    def scoreCredit(self):
        return self._scoreCredit
    
    @scoreCredit.setter
    def scoreCredit(self, value):
        self._scoreCredit = value

    @property
    def tauxEndettement(self):
        if self.revenuAnnuel == 0:
            return 0.0
        return self.obligationsAnnuelles / self.revenuAnnuel


