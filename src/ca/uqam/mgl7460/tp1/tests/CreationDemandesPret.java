package ca.uqam.mgl7460.tp1.tests;

import ca.uqam.mgl7460.tp1.types.modeles.Adresse;
import ca.uqam.mgl7460.tp1.types.modeles.DemandePret;
import ca.uqam.mgl7460.tp1.types.modeles.DemandeurPret;
import ca.uqam.mgl7460.tp1.types.modeles.Propriete;
import ca.uqam.mgl7460.tp1.types.modeles.ProvinceOuTerritoire;
import ca.uqam.mgl7460.tp1.types.utils.Fabrique;

public class CreationDemandesPret {

    private static Fabrique fabrique = Fabrique.getSingletonFabrique();

    private CreationDemandesPret() {}

    public static DemandePret getDemandePretOuiOuiOui() {
        DemandeurPret demandeur = fabrique.creerDemandeurPret("Jeanne","Bergeron","123-456-789",120000f,40000f,765);
        Propriete propriete = fabrique.creerPropriete(new Adresse("201A", "2100", "Saint-Urbain", "Montréal", ProvinceOuTerritoire.QUEBEC, "H2X2XH"),400000f);
        return fabrique.creerDemandePret(propriete, demandeur,405000f,30000f);
    }

    public static DemandePret getDemandePretOuiOuiNon() {
        DemandeurPret demandeur = fabrique.creerDemandeurPret("Mamadou","Diallo","321-654-987",120000f,40000f,765);
        Propriete propriete = fabrique.creerPropriete(new Adresse("1504", "1800", "Bleury", "Montréal", ProvinceOuTerritoire.QUEBEC, "H2Y2Y5"),400000f);
        return fabrique.creerDemandePret(propriete, demandeur,405000f,5000f);
    }

    public static DemandePret getDemandePretOuiNonNon() {
        DemandeurPret demandeur = fabrique.creerDemandeurPret("Jorge","Riviera","987-654-123",150000f,50000f,780);
        Propriete propriete = fabrique.creerPropriete(new Adresse("1504", "1800", "Bleury", "Calgary", ProvinceOuTerritoire.ALBERTA, "J3H3G5"),400000f);
        return fabrique.creerDemandePret(propriete, demandeur,405000f,5000f);
    }

    public static DemandePret getDemandePretNonNonNon() {
        DemandeurPret trump = fabrique.creerDemandeurPret("Donald","Trump", "666-666-666",600000f,83000000f,520);
        Propriete trumpTower = fabrique.creerPropriete(new Adresse("", "325", "Bay Street", "Toronto", ProvinceOuTerritoire.ONTARIO, "M5H 4G3"),100000f);
        return fabrique.creerDemandePret(trumpTower, trump,45000000f,5000f);
    }

    public static DemandePret getDemandePretOuiNonOui() {
        DemandeurPret legault = fabrique.creerDemandeurPret("François","Legault", "555-555-555",275000,75000f,765);
        Propriete siegeNorthVolt = fabrique.creerPropriete(new Adresse("", "20", "Alströmergatan", "Stockholm", ProvinceOuTerritoire.NEWFOUNDLAND_AND_LABRADOR, "M5H 4G3"),480000000f);
        return fabrique.creerDemandePret(siegeNorthVolt, legault,450000000f,275000000f);
    }

}
