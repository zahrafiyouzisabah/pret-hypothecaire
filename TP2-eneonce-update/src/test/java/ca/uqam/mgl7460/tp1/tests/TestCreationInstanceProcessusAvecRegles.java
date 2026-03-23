package ca.uqam.mgl7460.tp1.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import ca.uqam.mgl7460.tp1.drools.UtilitaireRegles;
import ca.uqam.mgl7460.tp1.types.modeles.DemandePret;
import ca.uqam.mgl7460.tp1.types.modeles.Resultat;
import ca.uqam.mgl7460.tp1.types.modeles.ResultatTraitement;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.DefinitionProcessus;
import ca.uqam.mgl7460.tp1.types.traitements.definitions.DefinitionTache;
import ca.uqam.mgl7460.tp1.types.traitements.instances.ExceptionDefinitionProcessus;
import ca.uqam.mgl7460.tp1.types.traitements.instances.InstanceProcessus;
import ca.uqam.mgl7460.tp1.types.traitements.instances.ModeExecution;
import ca.uqam.mgl7460.tp1.types.utils.Fabrique;

public class TestCreationInstanceProcessusAvecRegles {

    private static DefinitionProcessus processusLineaire = null;

    private static DefinitionProcessus processusComplexeAvecRegles = null;

    private static DefinitionProcessus processusLineaireAvecRegles = null;

    private static Fabrique fabrique = null;

    @BeforeAll
    public static void initialiser() {

        fabrique = Fabrique.getSingletonFabrique();

        processusLineaire = CreationDefinitionsProcessus.getProcessusLineaire();

        processusLineaireAvecRegles = CreationDefinitionsProcessus.getProcessusLineaireAvecRegles();

        processusComplexeAvecRegles = CreationDefinitionsProcessus.getProcessusComplexeAvecRegles();
;

    }

    @Test
    public void testCreationInstanceProcessus() {
        DemandePret demandePret = CreationDemandesPret.getDemandePretOuiOuiOui();
        InstanceProcessus instanceProcessus = fabrique.creerInstanceProcessus(processusLineaire, demandePret);

        assertEquals(processusLineaire,instanceProcessus.getDefinitionProcessus(),"Le nouveau processus n'a pas la bonne definition");
    }

    @Test
    public void testExecutionProcessusSimpleAvecReglesSurDemandeRevenuTropFaible() {

        // 1. Obtenir une demande de pret oui-oui-oui
        DemandePret demandePret = CreationDemandesPret.getDemandeRevenuTropFaible();

        // 2.   L'initialiser à ACCEPTEE. Si problème, ce sera changé par les règles
        demandePret.getResultatTraitement().setResultat(Resultat.ACCEPTEE);
        InstanceProcessus instanceProcessus = fabrique.creerInstanceProcessus(processusLineaireAvecRegles, demandePret);
        instanceProcessus.setModeExecution(ModeExecution.REGLES_DROOLS);
        try {
            instanceProcessus.demarrer();

            // 3. verifier que c'est refuse
            assertEquals(Resultat.REFUSEE,demandePret.getResultatTraitement().getResultat(), "Demande " + demandePret + " était supposée être refusée");

            // 4. avec le message d'erreur UtilitaireRegles.SALAIRE_TROP_FAIBLE
            assertTrue(aRecuLesDiagnostics(demandePret,UtilitaireRegles.SALAIRE_TROP_FAIBLE));

        } catch (ExceptionDefinitionProcessus e) {
            e.printStackTrace();
        }
    }

 @Test
    public void testExecutionProcessusSimpleAvecReglesSurDemandeScoreCreditFaible() {

        // 1. Obtenir une demande de pret oui-oui-oui
        DemandePret demandePret = CreationDemandesPret.getDemandeScoreCreditTropFaible();

        // 2.   L'initialiser à ACCEPTEE. Si problème, ce sera changé par les règles
        demandePret.getResultatTraitement().setResultat(Resultat.ACCEPTEE);
        InstanceProcessus instanceProcessus = fabrique.creerInstanceProcessus(processusLineaireAvecRegles, demandePret);
        instanceProcessus.setModeExecution(ModeExecution.REGLES_DROOLS);
        try {
            instanceProcessus.demarrer();

            // 3. verifier que c'est refuse
            assertEquals(Resultat.REFUSEE,demandePret.getResultatTraitement().getResultat(), "Demande " + demandePret + " était supposée être refusée");

            // 4. avec le message d'erreur UtilitaireRegles.SCORE_CREDIT_TROP_FAIBLE
            assertTrue(aRecuLesDiagnostics(demandePret,UtilitaireRegles.SCORE_CREDIT_TROP_FAIBLE));

        } catch (ExceptionDefinitionProcessus e) {
            e.printStackTrace();
        }
    }


    private boolean aRecuLesDiagnostics(DemandePret demandePret, String...diagnostics){

        ArrayList<String> messagesErreur = new ArrayList<>();
        demandePret.getResultatTraitement().getMessages().forEachRemaining(message -> messagesErreur.add(message));


        if (messagesErreur.size() != diagnostics.length) return false;

        boolean itMatchesAll = true;
        for (String diagnostic: diagnostics) {
            boolean itMatchesDiagnostic = false;
            for (String message: messagesErreur) {
                itMatchesDiagnostic = itMatchesDiagnostic || message.equals(diagnostic);
            }
            itMatchesAll = itMatchesAll && itMatchesDiagnostic;
        }
        return itMatchesAll;
    }


    @Test
    public void testExecutionProcessusSimpleAvecReglesSurDemandeTauxEndettementEleve() {

        // 1. Obtenir une demande de pret oui-oui-oui
        DemandePret demandePret = CreationDemandesPret.getDemandeTauxEndettementTropEleve();

        // 2.   L'initialiser à ACCEPTEE. Si problème, ce sera changé par les règles
        demandePret.getResultatTraitement().setResultat(Resultat.ACCEPTEE);
        InstanceProcessus instanceProcessus = fabrique.creerInstanceProcessus(processusLineaireAvecRegles, demandePret);
        instanceProcessus.setModeExecution(ModeExecution.REGLES_DROOLS);
        try {
            instanceProcessus.demarrer();

            // 3. verifier que c'est refuse
            assertEquals(Resultat.REFUSEE,demandePret.getResultatTraitement().getResultat(), "Demande " + demandePret + " était supposée être refusée");

            // 4. avec le message d'erreur UtilitaireRegles.TAUX_ENDETTEMENT_TROP_ELEVE
            assertTrue(aRecuLesDiagnostics(demandePret,UtilitaireRegles.TAUX_ENDETTEMENT_TROP_ELEVE));

        } catch (ExceptionDefinitionProcessus e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExecutionProcessusSimpleAvecReglesSurDemandeProprieteHorsQuebec() {

        // 1. Obtenir une demande de pret oui-oui-oui
        DemandePret demandePret = CreationDemandesPret.getDemandeProprieteHorsQuebec();

        // 2.   L'initialiser à ACCEPTEE. Si problème, ce sera changé par les règles
        demandePret.getResultatTraitement().setResultat(Resultat.ACCEPTEE);
        InstanceProcessus instanceProcessus = fabrique.creerInstanceProcessus(processusLineaireAvecRegles, demandePret);
        instanceProcessus.setModeExecution(ModeExecution.REGLES_DROOLS);
        try {
            instanceProcessus.demarrer();

            // 3. verifier que c'est refuse
            assertEquals(Resultat.REFUSEE,demandePret.getResultatTraitement().getResultat(), "Demande " + demandePret + " était supposée être refusée");

            // 4. avec le message d'erreur UtilitaireRegles.PROPRIETE_HORS_QUEBEC
            assertTrue(aRecuLesDiagnostics(demandePret,UtilitaireRegles.PROPRIETE_HORS_QUEBEC));

        } catch (ExceptionDefinitionProcessus e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExecutionProcessusSimpleAvecReglesSurDemandeTauxMiseDeFondsTropFaible() {

        // 1. Obtenir une demande de pret MiseDeFondsTropFaible
        DemandePret demandePret = CreationDemandesPret.getDemandeMiseDeFondsTropFaible();

        // 2.   L'initialiser à ACCEPTEE. Si problème, ce sera changé par les règles
        demandePret.getResultatTraitement().setResultat(Resultat.ACCEPTEE);
        InstanceProcessus instanceProcessus = fabrique.creerInstanceProcessus(processusLineaireAvecRegles, demandePret);
        instanceProcessus.setModeExecution(ModeExecution.REGLES_DROOLS);
        try {
            instanceProcessus.demarrer();

            // 3. verifier que c'est refuse
            assertEquals(Resultat.REFUSEE,demandePret.getResultatTraitement().getResultat(), "Demande " + demandePret + " était supposée être refusée");

            // 4. avec le message d'erreur UtilitaireRegles.TAUX_MISE_FONDS_TROP_FAIBLE
            assertTrue(aRecuLesDiagnostics(demandePret,UtilitaireRegles.TAUX_MISE_FONDS_TROP_FAIBLE));

        } catch (ExceptionDefinitionProcessus e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExecutionProcessusSimpleAvecReglesSurDemandeRatioEmpruntValeurMarcheTropEleve() {

        // 1. Obtenir une demande de pret RatioEmpruntValeurMarcheTropEleve
        DemandePret demandePret = CreationDemandesPret.getDemandeRatioEmpruntValeurMarcheTropEleve();

        // 2.   L'initialiser à ACCEPTEE. Si problème, ce sera changé par les règles
        demandePret.getResultatTraitement().setResultat(Resultat.ACCEPTEE);
        InstanceProcessus instanceProcessus = fabrique.creerInstanceProcessus(processusLineaireAvecRegles, demandePret);
        instanceProcessus.setModeExecution(ModeExecution.REGLES_DROOLS);
        try {
            instanceProcessus.demarrer();

            // 3. verifier que c'est refuse
            assertEquals(Resultat.REFUSEE,demandePret.getResultatTraitement().getResultat(), "Demande " + demandePret + " était supposée être refusée here.");

            // 4. avec le message d'erreur UtilitaireRegles.RATIO_EMPRUNT_VALEUR_TROP_ELEVE
            assertTrue(aRecuLesDiagnostics(demandePret,UtilitaireRegles.RATIO_EMPRUNT_VALEUR_TROP_ELEVE));

        } catch (ExceptionDefinitionProcessus e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testExecutionProcessusSimpleAvecReglesSurDemandeRevenuCoteCreditFaibles() {

        // 1. Obtenir une demande de pret RevenuCoteCreditFaibles
        DemandePret demandePret = CreationDemandesPret.getDemandeRevenuCoteCreditFaibles();

        // 2.   L'initialiser à ACCEPTEE. Si problème, ce sera changé par les règles
        demandePret.getResultatTraitement().setResultat(Resultat.ACCEPTEE);
        InstanceProcessus instanceProcessus = fabrique.creerInstanceProcessus(processusLineaireAvecRegles, demandePret);
        instanceProcessus.setModeExecution(ModeExecution.REGLES_DROOLS);
        try {
            instanceProcessus.demarrer();

            // 3. verifier que c'est refuse
            assertEquals(Resultat.REFUSEE,demandePret.getResultatTraitement().getResultat(), "Demande " + demandePret + " était supposée être refusée");

            // 4. avec les messages d'erreur UtilitaireRegles.SALAIRE_TROP_FAIBLE et UtilitaireRegles.SCORE_CREDIT_TROP_FAIBLE
            assertTrue(aRecuLesDiagnostics(demandePret,UtilitaireRegles.SALAIRE_TROP_FAIBLE,UtilitaireRegles.SCORE_CREDIT_TROP_FAIBLE));

        } catch (ExceptionDefinitionProcessus e) {
            e.printStackTrace();
        }
    }

        @Test
    public void testExecutionProcessusSimpleAvecReglesSurDemandeCoteCreditMiseDefondsFaibles() {

        // 1. Obtenir une demande de pret RevenuCoteCreditFaibles
        DemandePret demandePret = CreationDemandesPret.getDemandeCoteCreditMiseDefondsFaibles();

        // 2.   L'initialiser à ACCEPTEE. Si problème, ce sera changé par les règles
        demandePret.getResultatTraitement().setResultat(Resultat.ACCEPTEE);
        InstanceProcessus instanceProcessus = fabrique.creerInstanceProcessus(processusLineaireAvecRegles, demandePret);
        instanceProcessus.setModeExecution(ModeExecution.REGLES_DROOLS);
        try {
//            demandePret.getResultatTraitement().getMessages().forEachRemaining(System.out::println);
            instanceProcessus.demarrer();

            // 3. verifier que c'est refuse
            assertEquals(Resultat.REFUSEE,demandePret.getResultatTraitement().getResultat(), "Demande " + demandePret + " était supposée être refusée");

            // 4. avec les messages d'erreur UtilitaireRegles.SCORE_CREDIT_TROP_FAIBLE et UtilitaireRegles.TAUX_MISE_FONDS_TROP_FAIBLE
            assertTrue(aRecuLesDiagnostics(demandePret,UtilitaireRegles.SCORE_CREDIT_TROP_FAIBLE, UtilitaireRegles.TAUX_MISE_FONDS_TROP_FAIBLE));

        } catch (ExceptionDefinitionProcessus e) {
            e.printStackTrace();
        }
    }

        @Test
    public void testExecutionProcessusComplexeAvecReglesSurDemandeCoteCreditMiseDefondsFaibles() {

        // 1. Obtenir une demande de pret RevenuCoteCreditFaibles
        DemandePret demandePret = CreationDemandesPret.getDemandeCoteCreditMiseDefondsFaibles();

        // 2.   L'initialiser à ACCEPTEE. Si problème, ce sera changé par les règles
        demandePret.getResultatTraitement().setResultat(Resultat.ACCEPTEE);
        InstanceProcessus instanceProcessus = fabrique.creerInstanceProcessus(processusComplexeAvecRegles, demandePret);
        instanceProcessus.setModeExecution(ModeExecution.REGLES_DROOLS);
        try {
            instanceProcessus.demarrer();

            // 3. verifier que c'est refuse
            assertEquals(Resultat.REFUSEE,demandePret.getResultatTraitement().getResultat(), "Demande " + demandePret + " était supposée être refusée");

            // 4. avec le SEUL message d'erreur UtilitaireRegles.SCORE_CREDIT_TROP_FAIBLE
            assertTrue(aRecuLesDiagnostics(demandePret,UtilitaireRegles.SCORE_CREDIT_TROP_FAIBLE));

        } catch (ExceptionDefinitionProcessus e) {
            e.printStackTrace();
        }
    }

}
