public class Enchere {
    private Utilisateur encherisseur;
    private Vente vente;
    private String dateheure;
    private double montant;

    public Enchere(Utilisateur encherisseur, Vente vente, String dateheure, double montant){
        this.encherisseur = encherisseur;
        this.vente = vente;
        this.dateheure = dateheure;
        this.montant = montant;
    }

    public Utilisateur getUtilisateur(){
        return this.encherisseur;
    }

    public Vente getVente(){
        return this.vente;
    }

    public String getDateHeure(){
        return this.dateheure;
    }

    public double getMontant(){
        return this.montant;
    }

    public void setEncherisseur(Utilisateur newUt){
        this.encherisseur = newUt;
    }

    public void setVente(Vente newVente){
        this.vente = newVente;
    }

    public void setDateHeure(String time){
        this.dateheure = time;
    }

    public void setMontant(double newMontant){
        this.montant = newMontant;
    }
}
