
class ExceptionDefinitionProcessus(Exception):
    def __init__(self, instance_processus, prochaines_taches_candidates, message: str):
        super().__init__(message)
        self.instance_processus = instance_processus
        self.prochaines_taches_candidates = prochaines_taches_candidates 