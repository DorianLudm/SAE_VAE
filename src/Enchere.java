public class Enchere {
    private Utilisateur encherisseur;
    private Vente vente;
    private double montant;

    public Enchere(Utilisateur encherisseur, Vente vente, double montant){
        this.encherisseur = encherisseur;
        this.vente = vente;
        this.montant = montant;
    }

    public Utilisateur getUtilisateur(){
        return this.encherisseur;
    }

    public Vente getVente(){
        return this.vente;
    }

    public double montant(){
        return this.montant;
    }

    public void setEncherisseur(Utilisateur newUt){
        this.encherisseur = newUt;
    }

    public void setVente(Vente newVente){
        this.vente = newVente;
    }

    public void setMontant(double newMontant){
        this.montant = newMontant;
    }
}
