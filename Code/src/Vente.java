public class Vente {
    private int id;
    private String debVente;
    private String finVente;
    private double prixMin;
    private double prixBase;
    private Objet objet;
    private Statut statut;

    /**
     * Constructeur de base de la classe Vente
     * @param id
     * @param debVente
     * @param finVente
     * @param prixMin
     * @param prixBase
     * @param objet
     * @param statut
     */
    public Vente(int id, String debVente, String finVente, double prixMin, double prixBase, Objet objet, Statut statut) {
        this.id = id;
        this.debVente = debVente;
        this.finVente = finVente;
        this.prixMin = prixMin;
        this.prixBase = prixBase;
        this.objet = objet;
        this.statut = statut;
    }

    /**
     * 
     * @return le statut de la vente
     */
    public Statut getStatut() {
        return statut;
    }

    /**
     * Permet de changer le statut de la vente
     * @param statut
     */
    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    /**
     * 
     * @return la date de début de la vente sous forme de chaînes de caractères
     */
    public String getDebutVente(){
        return this.debVente;
    }

    /**
     * 
     * @return la date de fin de la vente sous forme de chaînes de caractères
     */
    public String getFinVente(){
        return this.finVente;
    }

    /**
     * 
     * @return le prix minimum de la vente
     */
    public double getPrixMin(){
        return this.prixMin;
    }

    /**
     * 
     * @return le prix de base de la vente
     */
    public double getPrixBase(){
        return this.prixBase;
    }

    /**
     * 
     * @return l'objet à vendre
     */
    public Objet getObjet(){
        return this.objet;
    }

    /**
     * Permet de changer la date de début de la vente
     * @param debut
     */
    public void setDebutVente(String debut){
        this.debVente = debut;
    }

    /**
     * Permet de changer la date de fin de la vente
     * @param fin
     */
    public void setFinVente(String fin){
        this.finVente = fin;
    }

    /**
     * Permet de changer le prix minimum de la vente
     * @param newPrixMin
     */
    public void setPrixMin(double newPrixMin){
        this.prixMin = newPrixMin;
    }

    /**
     * Permet de changer le prix de base de la vente
     * @param newPrixBase
     */
    public void setPrixBase(double newPrixBase){
        this.prixBase = newPrixBase;
    }

    /**
     * Permet de changer l'objet mis en vente
     * @param obj
     */
    public void setObjet(Objet obj){
        this.objet = obj;
    }

    /**
     * 
     * @return l'identifiant de la vente
     */
    public int getId() {
        return id;
    }

    /**
     * Permet de changer l'identifiant de la vente
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
}
