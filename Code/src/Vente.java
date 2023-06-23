public class Vente {
    /**L'identifiant de la vente */
    private int id;

    /**La date de début de la vente */
    private String debVente;

    /**La date de fin de la vente */
    private String finVente;

    /**Le prix minimum de la vente */
    private double prixMin;

    /**Le prix de base de la vente */
    private double prixBase;

    /**L'objet qui a été mis en vente */
    private Objet objet;

    /**Le statut de la vente */
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
     * Permet de saisir le statut de la vente
     * @param statut
     */
    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    /**
     * 
     * @return la date de début de la vente
     */
    public String getDebutVente(){
        return this.debVente;
    }

    /**
     * 
     * @return la date de fin de la vente
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
     * @return l'objet qui a été mis en vente
     */
    public Objet getObjet(){
        return this.objet;
    }

    /**
     * Permet de saisir la date de début de la vente
     * @param debut
     */
    public void setDebutVente(String debut){
        this.debVente = debut;
    }

    /**
     * Permet de saisir la date de fin de la vente
     * @param fin
     */
    public void setFinVente(String fin){
        this.finVente = fin;
    }

    /**
     * Permet de saisir le prix minimum de la vente
     * @param newPrixMin
     */
    public void setPrixMin(double newPrixMin){
        this.prixMin = newPrixMin;
    }

    /**
     * Permet de saisir le prix de base de la vente
     * @param newPrixBase
     */
    public void setPrixBase(double newPrixBase){
        this.prixBase = newPrixBase;
    }

    /**
     * Permet de saisir l'objet que l'on met en vente
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
     * Permet de saisir l'identifiant de la vente
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
}
