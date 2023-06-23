public class Enchere {
    /**L'utilisateur qui enchérit */
    private Utilisateur encherisseur;

    /**La vente */
    private Vente vente;

    /**La date et l'heure de l'enchère */
    private String dateheure;

    /**Le montant de l'enchère */
    private double montant;

    /**
     * Constructeur de base de la classe Enchere
     * @param encherisseur
     * @param vente
     * @param dateheure
     * @param montant
     */
    public Enchere(Utilisateur encherisseur, Vente vente, String dateheure, double montant){
        this.encherisseur = encherisseur;
        this.vente = vente;
        this.dateheure = dateheure;
        this.montant = montant;
    }

    /**
     * 
     * @return l'utilisateur qui enchérit
     */
    public Utilisateur getUtilisateur(){
        return this.encherisseur;
    }

    /**
     * 
     * @return la vente
     */
    public Vente getVente(){
        return this.vente;
    }

    /**
     * 
     * @return la date et l'heure de l'enchère
     */
    public String getDateHeure(){
        return this.dateheure;
    }

    /**
     * 
     * @return le montant de l'enchère
     */
    public double getMontant(){
        return this.montant;
    }

    /**
     * Permet de saisir l'utilisateur qui enchérit
     * @param newUt
     */
    public void setEncherisseur(Utilisateur newUt){
        this.encherisseur = newUt;
    }

    /**
     * Permet de saisir la vente
     * @param newVente
     */
    public void setVente(Vente newVente){
        this.vente = newVente;
    }

    /**
     * Permet de saisir la date et l'heure de l'enchère
     * @param time
     */
    public void setDateHeure(String time){
        this.dateheure = time;
    }

    /**
     * Permet de saisir le montant de l'enchère
     * @param newMontant
     */
    public void setMontant(double newMontant){
        this.montant = newMontant;
    }
}
