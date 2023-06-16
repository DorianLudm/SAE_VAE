public class Vente {
    private int idVente;
    private String statut;
    private String debVente;
    private String finVente;
    private double prixMin;
    private double prixBase;
    private Objet objet;

    public Vente(int idVente, String statut, String debVente, String finVente, double prixMin, double prixBase, Objet objet){
        this.idVente = idVente;
        this.statut = statut;
        this.debVente = debVente;
        this.finVente = finVente;
        this.prixMin = prixMin;
        this.prixBase = prixBase;
        this.objet = objet;
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

    public double getPrixBase(){
        return this.prixBase;
    }

    public Objet getObjet(){
        return this.objet;
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

    public void setPrixBase(double newPrixBase){
        this.prixBase = newPrixBase;
    }

    public void setObjet(Objet obj){
        this.objet = obj;
    }
}
