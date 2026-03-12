package ca.uqam.mgl7460.tp1.implementations.modeles;

import ca.uqam.mgl7460.tp1.types.modeles.*;

import java.time.Instant;
import java.util.UUID;

public class DemandePretImpl implements DemandePret {
    private String numeroDemande; // Init in constructor
    private Instant dateDemande;  // Init in constructor

    private final DemandeurPret demandeurPret;
    private final Propriete propriete;

    private float montantMiseDeFonds;
    private float prixAchat;

    private TermesPret termesPret;
    private ResultatTraitement resultatTraitement;

    public DemandePretImpl(Propriete propriete, DemandeurPret demandeurPret) {
        this.initialize();
        this.propriete = propriete;
        this.demandeurPret = demandeurPret;
    }

    public DemandePretImpl(Propriete propriete, DemandeurPret demandeurPret, float prixAchat, float montantMiseDeFonds) {
        this.initialize();
        this.propriete = propriete;
        this.demandeurPret = demandeurPret;
        this.prixAchat = prixAchat;
        this.montantMiseDeFonds = montantMiseDeFonds;
    }

    private void initialize() {
        this.numeroDemande = UUID.randomUUID().toString();
        this.dateDemande = Instant.now();

        // Question: Should resultat initalize as non determine?
    }

    @Override
    public String getNumeroDemande() {
        return this.numeroDemande;
    }

    @Override
    public Instant getDateDemande() {
        return this.dateDemande;
    }

    @Override
    public DemandeurPret getDemandeurPret() {
        return this.demandeurPret;
    }

    @Override
    public Propriete getPropriete() {
        return this.propriete;
    }

    @Override
    public float getMontantPret() {
        return this.prixAchat - this.montantMiseDeFonds;
    }

    @Override
    public float getMontantMiseDeFonds() {
        return this.montantMiseDeFonds;
    }

    @Override
    public void setMontantMiseDeFonds(float montant) {
        this.montantMiseDeFonds = montant;
    }

    @Override
    public ResultatTraitement getResultatTraitement() {
        return this.resultatTraitement;
    }

    @Override
    public void setResultatTraitement(ResultatTraitement etat) {
        this.resultatTraitement = etat;
    }

    @Override
    public float getRatioEmpruntValeur() {
        float valeur = propriete.getValeurDeMarche();
        if (valeur == 0) return 0;
        return this.getMontantPret() / valeur;
    }

    @Override
    public float getPrixAchat() {
        return this.prixAchat;
    }

    @Override
    public void setPrixAchat(float prixAchat) {
        this.prixAchat = prixAchat;
    }

    @Override
    public TermesPret getTermesPret() {
        return this.termesPret;
    }

    @Override
    public void setTermesPret(TermesPret terms) {
        this.termesPret = terms;
    }
}
