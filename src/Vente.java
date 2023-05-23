public class Vente {
    private int idVente;
    private String statut;
    private String debVente;
    private String finVente;
    private double prixMin;

    public Vente(int idVente, String statut, String debVente, String finVente,double prixMin){
        this.idVente = idVente;
        this.statut = statut;
        this.debVente = debVente;
        this.finVente = finVente;
        this.prixMin = prixMin;
    }

    public int getId(){
        return this.idVente;
    }

    public String getStatut(){
        return this.statut;
    }

    public String getDebutVente(){
        return this.debVente;
    }

    public String getFinVente(){
        return this.finVente;
    }

    public double getPrixMin(){
        return this.prixMin;
    }

    public void setId(int idV){
        this.idVente = idV;
    }

    public void setStatut(String status){
        this.statut = status;
    }

    public void setDebutVente(String debut){
        this.debVente = debut;
    }

    public void setFinVente(String fin){
        this.finVente = fin;
    }

    public void setPrixMin(double newPrixMin){
        this.prixMin = newPrixMin;
    }
}
