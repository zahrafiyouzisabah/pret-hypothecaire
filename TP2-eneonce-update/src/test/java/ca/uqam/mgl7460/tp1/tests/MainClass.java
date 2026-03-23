package ca.uqam.mgl7460.tp1.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import ca.uqam.mgl7460.tp1.types.modeles.Adresse;
import ca.uqam.mgl7460.tp1.types.modeles.DemandePret;
import ca.uqam.mgl7460.tp1.types.modeles.DemandeurPret;
import ca.uqam.mgl7460.tp1.types.modeles.Propriete;
import ca.uqam.mgl7460.tp1.types.modeles.ProvinceOuTerritoire;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.DefinitionProcessus;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.DefinitionTache;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.TraitementTache;
import ca.uqam.mgl7460.tp1.types.traitements.instances.EtatTraitement;
import ca.uqam.mgl7460.tp1.types.traitements.instances.ExceptionDefinitionProcessus;
import ca.uqam.mgl7460.tp1.types.traitements.instances.InstanceProcessus;
import ca.uqam.mgl7460.tp1.types.utils.Fabrique;

public class MainClass {

    public static void main(String[] args) {

        Logger logger = Logger.getLogger("Test Logger");
        Fabrique fabrique = Fabrique.getSingletonFabrique();
        
        // 2.   Création de définition de processus
        
        DefinitionProcessus processusLineaire = CreationDefinitionsProcessus.getProcessusLineaire();
        DemandePret demandePret = CreationDemandesPret.getDemandePretOuiOuiOui();
        // 3.   Création d'instance de processus
        InstanceProcessus instanceProcessusLineaire = fabrique.creerInstanceProcessus(processusLineaire, demandePret);
        instanceProcessusLineaire.setLogger(logger);

        try {
            instanceProcessusLineaire.demarrer();
            if (instanceProcessusLineaire.getTacheCourante().getDefinitionTache().equals(CreationDefinitionsTaches.getTacheEligibilitePret()))
                logger.log(Level.INFO,"Cool, je me suis rendu jusqu'à la fin");
            if (instanceProcessusLineaire.getEtatProcessus().etatTraitement() == EtatTraitement.TERMINE)
                logger.log(Level.INFO, "Cool, l'exécution à réussi");
        } catch (ExceptionDefinitionProcessus e) {
            e.printStackTrace();
        }
    }
    
}
