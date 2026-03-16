from resultat import Resultat

class ResultatTraitement:
    def __init__(self, resultat: Resultat):
        self.resultat = resultat
        self.messages = []

@property
def resultat(self):
    return self._resultat
@resultat.setter
def resultat(self, value: Resultat):
    self._resultat = value

@property
def messages(self):
    return self._messages

def ajouter_message(self, message: str):
    self._messages.append(message)