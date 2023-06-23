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
    @Override
    public boolean equals(Object o){
        if(o instanceof Enchere){
            Enchere enchere = (Enchere) o;
            return this.encherisseur.equals(enchere.getUtilisateur()) &&
                    this.vente.equals(enchere.getVente()) &&
                    this.dateheure.equals(enchere.getDateHeure()) &&
                    this.montant == enchere.getMontant();
        }
        return false;
    }
}
