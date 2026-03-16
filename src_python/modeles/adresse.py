from dataclasses import dataclass
from province_ou_territoire import ProvinceOuTerritoire


@dataclass
class Adresse:
    numero_porte: str
    numero_rue: str
    nom_rue: str
    ville: str
    province: ProvinceOuTerritoire
    code_postal: str

    def __str__(self):
        return (
            f"Porte {self.numero_porte}, {self.numero_rue} rue {self.nom_rue}, "
            f"{self.ville}, {self.code_postal} {self.province.name}"
        )