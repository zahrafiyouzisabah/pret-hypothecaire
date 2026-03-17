from utils import fabrique
from modeles.adresse import Adresse
from modeles.province_ou_territoire import ProvinceOuTerritoire

def demande_pret_oui_oui_oui():
     demandeur = fabrique.creer_demandeur_pret(prenom="Jeanne", nom="Bergeron", nas="123-456-789", revenu_annuel=120000.0, obligations_annuelles=40000.0, score_credit=765)
     propriete = fabrique.creer_propriete(adresse=Adresse(numero_porte="201A", numero_rue="2100", nom_rue="Saint-Urbain", ville="Montréal", province=ProvinceOuTerritoire.QUEBEC, code_postal="H2X2XH"), valeur_marche=400000.0)
     return fabrique.creer_demande_pret(propriete=propriete, demandeur_pret=demandeur,prix_achat=405000.0, montant_mise_de_fonds=30000.0)

def demande_pret_oui_oui_non():
    demandeur = fabrique.creer_demandeur_pret(
        prenom="Mamadou",
        nom="Diallo",
        nas="321-654-987",
        revenu_annuel=120000.0,
        obligations_annuelles=40000.0,
        score_credit=765
    )

    propriete = fabrique.creer_propriete(
        adresse=Adresse(
            numero_porte="1504",
            numero_rue="1800",
            nom_rue="Bleury",
            ville="Montréal",
            province=ProvinceOuTerritoire.QUEBEC,
            code_postal="H2Y2Y5"
        ),
        valeur_marche=400000.0
    )

    return fabrique.creer_demande_pret(
        propriete=propriete,
        demandeur_pret=demandeur,
        prix_achat=405000.0,
        montant_mise_de_fonds=5000.0
    )

def demande_pret_oui_non_non():
    demandeur = fabrique.creer_demandeur_pret(
        prenom="Jorge",
        nom="Riviera",
        nas="987-654-123",
        revenu_annuel=150000.0,
        obligations_annuelles=50000.0,
        score_credit=780
    )

    propriete = fabrique.creer_propriete(
        adresse=Adresse(
            numero_porte="1504",
            numero_rue="1800",
            nom_rue="Bleury",
            ville="Calgary",
            province=ProvinceOuTerritoire.ALBERTA,
            code_postal="J3H3G5"
        ),
        valeur_marche=400000.0
    )

    return fabrique.creer_demande_pret(
        propriete=propriete,
        demandeur_pret=demandeur,
        prix_achat=405000.0,
        montant_mise_de_fonds=5000.0
    )

def demande_pret_non_non_non():
    demandeur = fabrique.creer_demandeur_pret(
        prenom="Donald",
        nom="Trump",
        nas="666-666-666",
        revenu_annuel=600000.0,
        obligations_annuelles=83000000.0,
        score_credit=520
    )

    propriete = fabrique.creer_propriete(
        adresse=Adresse(
            numero_porte="",
            numero_rue="325",
            nom_rue="Bay Street",
            ville="Toronto",
            province=ProvinceOuTerritoire.ONTARIO,
            code_postal="M5H 4G3"
        ),
        valeur_marche=100000.0
    )

    return fabrique.creer_demande_pret(
        propriete=propriete,
        demandeur_pret=demandeur,
        prix_achat=45000000.0,
        montant_mise_de_fonds=5000.0
    )

def demande_pret_oui_non_oui():
    demandeur = fabrique.creer_demandeur_pret(
        prenom="François",
        nom="Legault",
        nas="555-555-555",
        revenu_annuel=275000.0,
        obligations_annuelles=75000.0,
        score_credit=765
    )

    propriete = fabrique.creer_propriete(
        adresse=Adresse(
            numero_porte="",
            numero_rue="20",
            nom_rue="Alströmergatan",
            ville="Stockholm",
            province=ProvinceOuTerritoire.NEWFOUNDLAND_AND_LABRADOR,
            code_postal="M5H 4G3"
        ),
        valeur_marche=480000000.0
    )

    return fabrique.creer_demande_pret(
        propriete=propriete,
        demandeur_pret=demandeur,
        prix_achat=450000000.0,
        montant_mise_de_fonds=275000000.0
    )

    