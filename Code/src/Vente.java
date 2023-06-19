public class Vente {
    private int id;
    private String debVente;
    private String finVente;
    private double prixMin;
    private double prixBase;
    private Objet objet;
    private Statut statut;

    public Vente(Statut statut, String debVente, String finVente, double prixMin, double prixBase, Objet objet){
        this.statut = statut;
        this.debVente = debVente;
        this.finVente = finVente;
        this.prixMin = prixMin;
        this.prixBase = prixBase;
        this.objet = objet;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
