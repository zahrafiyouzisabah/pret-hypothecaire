package ca.uqam.mgl7460.tp1.implementations.modeles;

import ca.uqam.mgl7460.tp1.types.modeles.DemandeurPret;

public class DemandeurPretImpl implements DemandeurPret {
    private String nom;
    private String prenom;
    private String nas;

    private float revenuAnnuel;
    private Integer scoreCredit;
    private float obligationsAnnuelles;

    public DemandeurPretImpl(String prenom, String nom, String nas) {
        this.prenom = prenom;
        this.nom = nom;
        this.nas = nas;
    }

    public DemandeurPretImpl(String prenom, String nom, String nas, float revenuAnnuel, float obligationsAnnuelles, Integer scoreCredit) {
        this.prenom = prenom;
        this.nom = nom;
        this.nas = nas;
        this.revenuAnnuel = revenuAnnuel;
        this.obligationsAnnuelles = obligationsAnnuelles;
        this.scoreCredit = scoreCredit;
    }

    @Override
    public String getNom() {
        return this.nom;
    }

    @Override
    public String getPrenom() {
        return this.prenom;
    }

    @Override
    public String getNas() {
        return this.nas;
    }

    @Override
    public float getRevenuAnnuel() {
        return this.revenuAnnuel;
    }

    @Override
    public void setRevenuAnnuel(float revenu) {
        this.revenuAnnuel = revenu;
    }

    @Override
    public float getObligationsAnnuelles() {
        return this.obligationsAnnuelles;
    }

    @Override
    public void setObligationsAnnuelles(float obligations) {
        this.obligationsAnnuelles = obligations;
    }

    @Override
    public float getTauxEndettement() {
        if (revenuAnnuel == 0) return 0;
        return obligationsAnnuelles / revenuAnnuel;
    }

    @Override
    public int getScoreCredit() {
        return this.scoreCredit;
    }

    @Override
    public void setScoreCredit(int score) {
        this.scoreCredit = score;
    }
}
